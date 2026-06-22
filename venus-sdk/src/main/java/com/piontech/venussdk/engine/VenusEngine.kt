package com.piontech.venussdk.engine

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.cyberlink.youcammakeup.jniproxy.CImageBuffer
import com.cyberlink.youcammakeup.jniproxy.CUIVenusLive
import com.cyberlink.youcammakeup.jniproxy.CUIVenusPhoto
import com.cyberlink.youcammakeup.jniproxy.PixelFormat
import com.cyberlink.youcammakeup.jniproxy.UIColor
import com.cyberlink.youcammakeup.jniproxy.UIColorVector
import com.cyberlink.youcammakeup.jniproxy.UIFaceAlignmentData
import com.cyberlink.youcammakeup.jniproxy.UIFaceMetadataVector
import com.cyberlink.youcammakeup.jniproxy.UIFaceModelCacheVector
import com.cyberlink.youcammakeup.jniproxy.UIFaceRect
import com.cyberlink.youcammakeup.jniproxy.UIFaceRectVector
import com.cyberlink.youcammakeup.jniproxy.UIFoundationIntensityMode
import com.cyberlink.youcammakeup.jniproxy.UIHairDyeMode
import com.cyberlink.youcammakeup.jniproxy.UIIntVector
import com.cyberlink.youcammakeup.jniproxy.UIVenusPipelineSettings
import com.cyberlink.youcammakeup.jniproxy.VN_EyebrowMode
import com.cyberlink.youcammakeup.jniproxy.VN_TrackingMode
import com.cyberlink.clgpuimage.CLMakeupLiveHairDyeFilter
import androidx.core.graphics.createBitmap
import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.HairMode
import com.piontech.venussdk.model.HairPalette
import com.piontech.venussdk.model.LiveMode

/**
 * Owns the reused native Venus engine. Two paths:
 *  - PHOTO (Phase 2): [ensurePhotoReady] + [dyeHairOnPhoto] — still-image hair recolor (pure native).
 *  - LIVE  (Phase 1/3): [initLive] — camera engine (kept for later phases).
 */
object VenusEngine {

    private const val TAG = "VenusEngine"
    private const val LOAD_TIMEOUT_MS = 20_000L

    /**
     * Whether this device can run the native engine. The engine requires API 26+
     * (libtensorflowlite_pf strong-imports AHardwareBuffer_*) and an arm ABI (only arm64-v8a /
     * armeabi-v7a are shipped). Callers MUST check this before [ensurePhotoReady]/[ensureLiveReady]
     * on devices below the engine floor (the app may install on API 24/25 via a lower minSdk).
     */
    val isEngineSupported: Boolean
        get() = EngineSupport.isSupported(android.os.Build.VERSION.SDK_INT, android.os.Build.SUPPORTED_ABIS)

    /** Why the engine can't run on this device, or null if supported (internal — app uses [isEngineSupported]). */
    internal val unsupportedReason: String?
        get() = EngineSupport.unsupportedReason(android.os.Build.VERSION.SDK_INT, android.os.Build.SUPPORTED_ABIS)

    // ---------------------------------------------------------------- PHOTO path (Phase 2)

    @Volatile
    private var photo: CUIVenusPhoto? = null

    internal val isPhotoReady: Boolean get() = photo != null

    data class DyeResult(val bitmap: Bitmap?, val ok: Boolean, val log: String)

    /** What to apply in one GetMakeupImage pass. All optional; hair=null means skin-only. */
    data class BeautifyRequest(
        val hair: HairColor? = null,
        val hair2: HairColor? = null,          // 2nd color for TWO_COLOR mode
        val hairMode: HairMode = HairMode.SINGLE,
        val hairIntensity: Int = -1,           // -1 = use hair.intensity (độ đậm 0..100)
        val hairShine: Int = -1,               // -1 = use hair.shine (độ bóng 0..100)
        val hairPattern: Bitmap? = null,       // full-color pattern for MULTI (multiColorMode=2)
        val skinSmooth: Int = 0,  // 0..100 -> configSkinSmooth (làm mịn da)
        val skinWhiten: Int = 0,  // 0..100 -> light foundation overlay (trắng/sáng da)
    ) {
        val hasAny: Boolean get() =
            hair != null || skinSmooth > 0 || skinWhiten > 0 ||
                (hairMode == HairMode.MULTI && hairPattern != null)
    }

    // Bright fair foundation tone for "whitening / brightening" skin (RGB). Verified: foundation
    // applies to the detected face region (a tan tone tanned 28% of pixels). Swapped to BGR at call.
    private const val WHITEN_R = 255
    private const val WHITEN_G = 245
    private const val WHITEN_B = 238

    /** Lazily constructs the photo engine and loads its 6 models. Blocking — call off main thread. */
    @Synchronized
    fun ensurePhotoReady(context: Context, log: (String) -> Unit): Boolean {
        photo?.let { return true }
        return try {
            val p = CUIVenusPhoto()
            log("CUIVenusPhoto constructed")
            // Ask the engine which models it wants (photo path = 6 slots, no background).
            val vec = UIFaceModelCacheVector()
            val rc = p.H(vec)
            val slots = vec.g().toInt()
            log("Photo GetInternalModelVersion rc=$rc, slots=$slots")
            val paths = (0 until slots).map { i ->
                val fn = vec.d(i)
                log("  model[$i]=$fn")
                ModelProvisioner.provision(context, fn)
            }
            require(paths.size >= 6) { "expected >=6 photo models, got ${paths.size}" }
            val setRc = p.j0(paths[0], paths[1], paths[2], paths[3], paths[4], paths[5], false)
            log("Photo SetInternalModelPaths rc=$setRc")
            val start = System.currentTimeMillis()
            var loaded = p.X()
            while (!loaded && System.currentTimeMillis() - start < LOAD_TIMEOUT_MS) {
                Thread.sleep(80); loaded = p.X()
            }
            if (loaded) {
                photo = p
                log("✅ Photo engine ready (${System.currentTimeMillis() - start} ms)")
                true
            } else {
                p.m0(); log("❌ Photo IsModelLoaded=false"); false
            }
        } catch (t: Throwable) {
            Log.e(TAG, "ensurePhotoReady failed", t)
            log("❌ ${t.javaClass.simpleName}: ${t.message}")
            false
        }
    }

    /**
     * Recolors hair on a still bitmap. Returns the result bitmap (or null + reason in log).
     * Sequence: AnalyzeImage -> GetFaceInfos -> GetFaceAlignmentData -> DeepDetectHairDyeMask
     *           -> enableHairDye + configHairDye + configFaceData -> GetMakeupImage -> readback.
     */
    /** Convenience: hair-only. */
    internal fun dyeHairOnPhoto(context: Context, source: Bitmap, color: HairColor, log: (String) -> Unit): DyeResult =
        applyOnPhoto(context, source, BeautifyRequest(hair = color), log)

    /**
     * Applies hair color and/or skin beautify on a still bitmap in ONE GetMakeupImage pass
     * (the engine composites all enabled features together).
     */
    fun applyOnPhoto(context: Context, source: Bitmap, req: BeautifyRequest, log: (String) -> Unit): DyeResult {
        if (!req.hasAny) return DyeResult(source, true, "no effect selected")
        // input guards (fail with a result, never crash the caller)
        if (source.isRecycled) return DyeResult(null, false, "ảnh nguồn đã bị recycle")
        if (source.width <= 0 || source.height <= 0) return DyeResult(null, false, "kích thước ảnh không hợp lệ")
        // MULTI full-color: the native photo engine can't bind the pattern (setupHairFullColor wrapper
        // missing) -> composite via GL using the live engine's mask (approach b). Hair-only in this mode.
        if (req.hairMode == HairMode.MULTI && req.hairPattern != null) {
            return applyFullColorPhoto(context, source, req.hairPattern, log)
        }
        if (!ensurePhotoReady(context, log)) return DyeResult(null, false, "engine not ready")
        val p = photo ?: return DyeResult(null, false, "engine null")

        // Allocating the working copy can OOM on very large images — surface it as a result, not a crash.
        val src: Bitmap
        val srcBuf: com.cyberlink.youcammakeup.jniproxy.CImageBuffer
        try {
            src = createBitmap(source.width, source.height)
            android.graphics.Canvas(src).drawBitmap(source, 0f, 0f, null)
            srcBuf = ImageBuffers.fromBitmap(src)
        } catch (t: Throwable) {
            Log.e(TAG, "applyOnPhoto allocation failed", t)
            return DyeResult(null, false, "❌ Hết bộ nhớ hoặc lỗi cấp ảnh (${source.width}x${source.height}): ${t.message}")
        }
        var patternFile: java.io.File? = null
        try {
            val faceCount = p.a(srcBuf) // AnalyzeImage
            log("AnalyzeImage faces=$faceCount")
            if (faceCount <= 0) return DyeResult(null, false, "Không tìm thấy khuôn mặt trong ảnh")

            val rects = UIFaceRectVector()
            p.y(faceCount, rects) // GetFaceInfos
            val rect = UIFaceRect(rects.c(0))
            val align = UIFaceAlignmentData()
            p.w(rect, align) // GetFaceAlignmentData

            val settings = UIVenusPipelineSettings()

            // ---- hair dye (run if a color OR a MULTI pattern is requested) ----
            val isMulti = req.hairMode == HairMode.MULTI && req.hairPattern != null
            if (req.hair != null || isMulti) {
                val c1 = req.hair ?: HairColor("base", HairPalette.colors[0].r, HairPalette.colors[0].g, HairPalette.colors[0].b)
                val maskOk = p.f(srcBuf, rects, 0, true) // DeepDetectHairDyeMask
                log("DeepDetectHairDyeMask ok=$maskOk")
                val inten = if (req.hairIntensity >= 0) req.hairIntensity else c1.intensity
                val shn = if (req.hairShine >= 0) req.hairShine else c1.shine

                // engine applies color in BGR order vs our RGBA buffers -> swap R<->B
                val colors = UIColorVector()
                val strengths = UIIntVector()
                val shines = UIIntVector()
                fun add(c: HairColor, st: Int, sh: Int) {
                    colors.a(UIColor(c.b, c.g, c.r)); strengths.a(st); shines.a(sh)
                }
                var ombreLine = 0f
                var ombreRange = 0f
                var multiColorMode = 0
                val cache = UIFaceModelCacheVector()
                when (req.hairMode) {
                    HairMode.SINGLE -> add(c1, inten, shn)
                    HairMode.OMBRE -> { // base color fading to natural (transparent tail)
                        add(c1, inten, shn); add(c1, 0, 0); ombreLine = 0f; ombreRange = 0.7f
                    }
                    HairMode.TWO_COLOR -> { // top c1 / bottom c2 split
                        add(c1, inten, shn); add(req.hair2 ?: c1, inten, shn); ombreLine = 0f; ombreRange = 0.7f
                    }
                    HairMode.MULTI -> { // full-color pattern: dump pattern to a temp file -> cache, multiColorMode=2
                        add(c1, inten, shn)
                        req.hairPattern?.let { pat ->
                            val f = java.io.File.createTempFile("hairpat_", ".png", context.cacheDir)
                            f.outputStream().use { pat.compress(Bitmap.CompressFormat.PNG, 100, it) }
                            patternFile = f
                            cache.a(f.absolutePath)
                            multiColorMode = 2
                        }
                        // KNOWN-BROKEN on the photo path (benchmark spike 2026-06-22): GetMakeupImage logs
                        // `[setupHairFullColor] hair dye full color bindImageMaskBuffer fail` and returns
                        // false. Root cause: full-color needs the pattern BOUND as a native image buffer
                        // (the `hair_full_color_texture` the GL shader samples for multi_color_hair_dye_mode==2);
                        // the photo engine binds it via the native `CUIVenusPhoto_setupHairFullColor`, whose
                        // Java/SWIG wrapper was DROPPED from this decompiled jniproxy subset. Passing only a
                        // file path in `cache` never binds the buffer, so BGR/dump-format tweaks can't help.
                        // Fix path (deferred): expose setupHairFullColor (RE its SWIG sig — crash risk) OR
                        // composite full-color in GL like the live renderer. SINGLE/OMBRE/TWO_COLOR are fine.
                    }
                }
                settings.I1(true) // enableHairDye
                settings.w0(
                    strengths, shines, colors,
                    UIHairDyeMode.HAIR_DYE_GENERIC_MODE,
                    ombreLine, ombreRange, false, 0f, 0f, 0.7f, 0.75f, multiColorMode,
                    cache
                )
                log("hair mode=${req.hairMode} inten=$inten shine=$shn multiColorMode=$multiColorMode")
            }

            // ---- skin beautify ----
            if (req.skinSmooth > 0) { settings.c1(req.skinSmooth); log("configSkinSmooth=${req.skinSmooth}") } // làm mịn da
            if (req.skinWhiten > 0) { // trắng/sáng da: light foundation overlay (color in BGR order)
                settings.H1(true) // enableFoundation
                settings.v0(req.skinWhiten, UIColor(WHITEN_B, WHITEN_G, WHITEN_R), UIFoundationIntensityMode.NORMAL)
                log("configFoundation whiten=${req.skinWhiten}")
            }

            settings.e0(rect, align, null, null) // configFaceData (mandatory)

            val out = createBitmap(src.width, src.height)
            val dstBuf = ImageBuffers.fromBitmap(out)
            val rendered = try {
                p.L(srcBuf, dstBuf, settings, UIFaceMetadataVector()) // GetMakeupImage
            } finally {
                dstBuf.m(); dstBuf.a()
            }
            log("GetMakeupImage ok=$rendered")
            return if (rendered) DyeResult(out, true, "✅ Đã áp dụng") else DyeResult(null, false, "GetMakeupImage thất bại")
        } catch (t: Throwable) {
            Log.e(TAG, "applyOnPhoto failed", t)
            return DyeResult(null, false, "❌ ${t.javaClass.simpleName}: ${t.message}")
        } finally {
            ImageBuffers.release(srcBuf)
            patternFile?.delete()
        }
    }

    /**
     * Full-color (MULTI) hair on a still photo via the GL path (approach b). The native photo engine
     * can't bind the pattern (its `setupHairFullColor` wrapper is missing), so we get the hair mask +
     * LUTs from the LIVE engine ([processLiveFrame]) and composite the [pattern] with the same shader
     * the live preview uses ([PhotoHairDyeRenderer]). Hair-only (skin beautify not combined here).
     */
    internal fun applyFullColorPhoto(context: Context, source: Bitmap, pattern: Bitmap, log: (String) -> Unit): DyeResult {
        if (source.isRecycled || pattern.isRecycled) return DyeResult(null, false, "ảnh đã bị recycle")
        if (source.width <= 0 || source.height <= 0) return DyeResult(null, false, "kích thước ảnh không hợp lệ")
        if (!isEngineSupported) return DyeResult(null, false, "thiết bị không hỗ trợ engine")
        if (!ensureLiveReady(context, log)) return DyeResult(null, false, "live engine không sẵn sàng")
        return try {
            // Any base color; in full-color mode the pattern image drives the result.
            val c0 = HairPalette.colors[0]
            configureLiveHair(LiveMode.MULTI, HairColor("base", c0.r, c0.g, c0.b), null, alphaPct = 100, shinePct = 50)

            val w = source.width; val h = source.height
            val rgba = com.piontech.venussdk.util.BitmapTransforms.toRgba(source)
            val nv21 = com.piontech.venussdk.util.YuvConverter.rgbaToNv21(rgba, w, h)

            // The live tracker is temporal — feed the static frame a few times so it locks onto the face.
            var hd: CLMakeupLiveHairDyeFilter.HairDyeData? = null
            repeat(5) { hd = processLiveFrame(nv21, w, h, 0, false, false) ?: hd }
            val data = hd ?: return DyeResult(null, false, "Không tìm thấy tóc/khuôn mặt trong ảnh")
            log("full-color: mask ${data.m_segment_map_width}x${data.m_segment_map_height}")

            val out = com.piontech.venussdk.gl.PhotoHairDyeRenderer.render(rgba, w, h, data, pattern)
                ?: return DyeResult(null, false, "GL full-color render thất bại")
            DyeResult(out, true, "✅ full-color (GL)")
        } catch (t: Throwable) {
            Log.e(TAG, "applyFullColorPhoto failed", t)
            DyeResult(null, false, "❌ ${t.javaClass.simpleName}: ${t.message}")
        }
    }

    /**
     * Generates a recolored hair-swatch thumbnail for [color] via the native
     * `GenerateHairDyeThumbnail` (`CUIVenusPhoto.m`). Mirrors the original VenusHelper.o0: attach a
     * grayscale hair-strand base to a buffer, recolor it IN PLACE, detach — the base bitmap then holds
     * the result. No face needed (it's a swatch, not a photo). Returns null on failure (caller can fall
     * back to a flat color chip). [size] is the square thumbnail edge in px.
     */
    internal fun generateHairThumbnail(context: Context, color: HairColor, size: Int = 96, log: (String) -> Unit = {}): Bitmap? {
        if (!isEngineSupported) return null
        if (!ensurePhotoReady(context, log)) return null
        val p = photo ?: return null
        return try {
            val base = buildSwatchBase(size)
            val buf = ImageBuffers.fromBitmap(base)
            try {
                // engine takes BGR for photo buffers (same convention as applyOnPhoto)
                val rc = p.m(buf, UIColor(color.b, color.g, color.r), UIHairDyeMode.HAIR_DYE_GENERIC_MODE)
                log("GenerateHairDyeThumbnail rc=$rc")
            } finally {
                buf.m(); buf.a() // detach + free — `base` now holds the recolored swatch
            }
            base
        } catch (t: Throwable) {
            Log.e(TAG, "generateHairThumbnail failed", t)
            null
        }
    }

    /** Grayscale hair-strand base for the thumbnail: vertical light→dark gradient + subtle strands. */
    private fun buildSwatchBase(size: Int): Bitmap {
        val bmp = createBitmap(size, size)
        val canvas = android.graphics.Canvas(bmp)
        val grad = android.graphics.LinearGradient(
            0f, 0f, 0f, size.toFloat(),
            intArrayOf(0xFFE6E6E6.toInt(), 0xFF9E9E9E.toInt(), 0xFF5A5A5A.toInt()),
            floatArrayOf(0f, 0.5f, 1f), android.graphics.Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, 0f, size.toFloat(), size.toFloat(), android.graphics.Paint().apply { shader = grad })
        // faint vertical strands so the recolor reads as "hair" rather than a flat panel
        val strand = android.graphics.Paint().apply { color = 0x22000000; strokeWidth = 1f }
        var x = 2f
        while (x < size) { canvas.drawLine(x, 0f, x, size.toFloat(), strand); x += 5f }
        return bmp
    }

    // ---------------------------------------------------------------- LIVE path (Phase 1/3)

    @Volatile
    private var live: CUIVenusLive? = null

    @Volatile
    private var livePhoto: CUIVenusPhoto? = null

    internal data class InitResult(val ok: Boolean, val maxFaces: Int, val log: String)

    internal fun initLive(context: Context, onProgress: (String) -> Unit): InitResult {
        val sb = StringBuilder()
        fun log(line: String) { Log.i(TAG, line); sb.appendLine(line); onProgress(sb.toString()) }
        live?.let { return InitResult(true, CUIVenusLive.u(), sb.append("already init\n").toString()) }
        return try {
            val maxFaces = CUIVenusLive.u()
            log("Engine max faces: $maxFaces")
            val p = CUIVenusPhoto()
            val l = CUIVenusLive(p)
            val vec = UIFaceModelCacheVector()
            val versionRc = l.q(vec)
            val slots = vec.g().toInt()
            log("GetInternalModelVersion rc=$versionRc, slots=$slots")
            val paths = (0 until slots).map { ModelProvisioner.provision(context, vec.d(it)) }
            val setRc = l.b0(paths[0], paths[1], paths[2], paths[3], paths[4], paths[5], paths[6], false)
            log("SetInternalModelPaths rc=$setRc")
            val start = System.currentTimeMillis()
            var loaded = l.I()
            while (!loaded && System.currentTimeMillis() - start < LOAD_TIMEOUT_MS) { Thread.sleep(100); loaded = l.I() }
            if (loaded) {
                l.j0(VN_TrackingMode.VN_LIVE_TRACKING_MODE); l.d0(1)
                livePhoto = p; live = l
                log("✅ IsModelLoaded=true (${System.currentTimeMillis() - start} ms)")
                InitResult(true, maxFaces, sb.toString())
            } else {
                l.q0(); p.m0(); log("❌ IsModelLoaded=false"); InitResult(false, maxFaces, sb.toString())
            }
        } catch (t: Throwable) {
            Log.e(TAG, "initLive failed", t)
            InitResult(false, -1, sb.append("❌ ${t.message}\n").toString())
        }
    }

    // ---- LIVE per-frame fast path (Phase 3 true 30fps path) ----

    private var liveMaxFaces = 0
    private var liveHairData: Array<CLMakeupLiveHairDyeFilter.HairDyeData>? = null
    private var liveFaceDetected: BooleanArray? = null
    private var md: Array<Array<Any?>>? = null // the 26 GetMakeupMetadata arrays
    // cached c0 (SetMakeupParameters) arg arrays (all features off except hair)
    private var cFoundationOff: BooleanArray? = null
    private var cDewyZero: IntArray? = null
    private var cDistortOff: BooleanArray? = null
    private var cReshape: Array<Any?>? = null
    private var ceObj: Array<Any?>? = null
    private var ceInt: IntArray? = null
    private var ceFloat: FloatArray? = null

    /** Ensure the live engine (Phase 1) is initialized + allocate live buffers. */
    fun ensureLiveReady(context: Context, log: (String) -> Unit): Boolean {
        if (live == null) {
            val r = initLive(context, log)
            if (!r.ok) return false
        }
        if (md == null) {
            val n = CUIVenusLive.u()
            liveMaxFaces = n
            val hair = Array(n) { CLMakeupLiveHairDyeFilter.HairDyeData() }
            liveHairData = hair
            liveFaceDetected = BooleanArray(n)
            cFoundationOff = BooleanArray(n); cDewyZero = IntArray(n); cDistortOff = BooleanArray(n)
            // reshape params must be non-null a0.c instances (all-zero = no reshape); native reads their fields
            cReshape = Array(n) { com.pf.makeupcam.camera.a0.c() as Any? }
            ceObj = arrayOfNulls(n); ceInt = IntArray(n); ceFloat = FloatArray(n)

            // All 26 metadata arrays must be non-null with element instances (mirrors core.g.d()).
            fun a(f: () -> Any): Array<Any?> = Array(n) { f() }
            md = arrayOf(
                a { com.cyberlink.clgpuimage.CLMakeupLiveEyeContactFilter.LiveEyeContactMetadata() }, // 1
                a { com.cyberlink.clgpuimage.CLMakeupLiveEyeSparkleFilter.LiveEyeSparkleMetadata() }, // 2
                a { arrayOf<Any?>(                                                                    // 3 (2 per face)
                    com.cyberlink.clgpuimage.CLMakeupLiveEyeFilter.LiveEyeMakeupMetadata(),
                    com.cyberlink.clgpuimage.CLMakeupLiveEyeFilter.LiveEyeMakeupMetadata()
                ) },
                a { com.cyberlink.clgpuimage.CLMakeupLiveLipStickFilter.LipstickData() },             // 4
                a { com.cyberlink.clgpuimage.CLMakeupLiveLiplinerFilter.LiplinerData() },             // 5
                a { com.cyberlink.clgpuimage.CLMakeupLiveBlush3DFilter.LiveBlush3DMetadata() },       // 6
                a { com.cyberlink.clgpuimage.CLMakeupLiveSmoothFilter.LiveSmoothMetadata() },         // 7
                a { com.cyberlink.clgpuimage.CLMakeupLiveFilter.LiveFrameInformation() },             // 8
                a { com.cyberlink.clgpuimage.CLMakeupLiveFoundationFilter.LiveFoundationData() },     // 9
                a { com.cyberlink.clgpuimage.CLMakeupLiveFaceReshapeFilter.LiveFaceReshapeMetadata() },// 10
                a { com.cyberlink.clgpuimage.CLMakeupLiveFaceDistortionFilter.LiveFaceDistortionMetadata() }, // 11
                a { com.cyberlink.clgpuimage.CLMakeupLiveObject3DWarpFilter.LiveObject3DWarpMetadata() }, // 12
                a { com.cyberlink.clgpuimage.CLMakeupLive3DFilter.LiveObject3DMetadata() },           // 13
                a { com.cyberlink.clgpuimage.CLMakeupLive3DFilter.LiveEarringMetadata() },            // 14
                a { com.cyberlink.clgpuimage.CLMakeupLiveFaceArtFilter.LiveFaceArtMetadata() },       // 15
                a { com.cyberlink.clgpuimage.CLMakeupLiveFilter.LiveDynamicRangeMetadata() },         // 16
                a { com.cyberlink.clgpuimage.CLStickerLiveFilter.StickerData() },                     // 17
                Array(n) { hair[it] as Any? },                                                        // 18 hair
                a { com.cyberlink.clgpuimage.CLMakeupLive3DEyebrowFilter.LiveEyebrow3DMetadata() },   // 19
                a { com.cyberlink.clgpuimage.CLMakeupLive3DEyebrowWarpFilter.LiveEyebrowWarp3DMetadata() }, // 20
                a { com.cyberlink.clgpuimage.CLMakeupLiveBronzerFilter.LiveBronzerMetadata() },       // 21
                a { com.cyberlink.clgpuimage.CLMakeupLiveNoseShadowFilter.LiveNoseShadowMetadata() }, // 22
                a { com.cyberlink.clgpuimage.CLMakeupLiveFaceContourFilter.LiveFaceContourMetadata() }, // 23
                a { com.cyberlink.clgpuimage.CLMakeupLiveAppleCheeksFilter.LiveAppleCheeksMetadata() }, // 24
                a { com.cyberlink.clgpuimage.CLMakeupLiveTeethWhitenFilter.TeethWhitenMetaData() },   // 25
                a { com.cyberlink.clgpuimage.CLMakeupLiveCubeEyewearFilter.LiveCubeEyewearMetaData() } // 26
            )
        }
        return true
    }

    /**
     * Set the live hair-dye target color (call on color change, not per frame).
     * Unlike the photo path (BGR swap for GetMakeupImage's buffer convention), the live GL shader
     * outputs RGB straight to the screen over an RGB camera texture, so pass true R,G,B here.
     * If colors look swapped in the live preview, flip LIVE_BGR_SWAP.
     */
    private const val LIVE_BGR_SWAP = false

    /**
     * Configure the live hair-dye target. Modes mirror the original YMK HairDyePatternType:
     *  - SINGLE   : 1 color, multiColorMode 0. `alphaPct` = color strength (0..100, single only).
     *  - OMBRE    : 2 colors top->bottom vertical split, multiColorMode 0, ombreRange 0.7.
     *  - GRADIENT : 2 colors smooth blend by hair luminance (TWO_COLOR_PIGMENT), multiColorMode 1.
     *  - MULTI    : full-color pattern image (FULL_COLORS), multiColorMode 2 (pattern fed to the GL renderer).
     * `shinePct`/`alphaPct` are 0..100 (engine scale).
     */
    fun configureLiveHair(mode: LiveMode, c1: HairColor, c2: HairColor?, alphaPct: Int, shinePct: Int) {
        val l = live ?: return
        val shine = shinePct.coerceIn(0, 100)
        val alpha = alphaPct.coerceIn(0, 100)
        val c2e = c2 ?: c1
        fun r(c: HairColor) = if (LIVE_BGR_SWAP) c.b else c.r
        fun b(c: HairColor) = if (LIVE_BGR_SWAP) c.r else c.b
        val gen = UIHairDyeMode.HAIR_DYE_GENERIC_MODE.c()
        when (mode) {
            LiveMode.SINGLE, LiveMode.MULTI -> l.a0(
                intArrayOf(r(c1)), intArrayOf(c1.g), intArrayOf(b(c1)),
                intArrayOf(if (mode == LiveMode.SINGLE) alpha else 100), intArrayOf(shine),
                gen, 1, 0f, 0f, 0.7f, 0.75f, if (mode == LiveMode.MULTI) 2 else 0
            )
            LiveMode.OMBRE -> l.a0(
                intArrayOf(r(c1), r(c2e)), intArrayOf(c1.g, c2e.g), intArrayOf(b(c1), b(c2e)),
                intArrayOf(100, 100), intArrayOf(shine, shine),
                gen, 2, 0f, 0.7f, 0.7f, 0.75f, 0
            )
            LiveMode.GRADIENT -> l.a0(
                intArrayOf(r(c1), r(c2e)), intArrayOf(c1.g, c2e.g), intArrayOf(b(c1), b(c2e)),
                intArrayOf(100, 100), intArrayOf(shine, shine),
                gen, 2, 0f, 0f, 0.7f, 0.75f, 1
            )
        }
    }

    /**
     * Per-frame: SetMakeupParameters(hair only) -> TrackYUV420Biplanar -> GetMakeupMetadata.
     * Returns HairDyeData[0] if hair detected, else null. NV21 = YUV420 biplanar bytes.
     * EXPERIMENT: passes null for the 25 unused metadata arrays (only hair_dye_data is real).
     *
     * `@Synchronized` shares the engine monitor with [releaseLive]/[ensureLiveReady]: a release can't
     * run while a frame is mid native `TrackYUV420Biplanar`, and a frame started after release sees
     * `live == null` and returns. This is the definitive guard against the camera-thread use-after-free
     * crash (SIGABRT in TrackYUV420Biplanar) when leaving the camera while a frame is in flight.
     */
    @Synchronized
    fun processLiveFrame(
        nv21: ByteArray, w: Int, h: Int, rotation: Int, frontFlip: Boolean, frameFlip: Boolean
    ): CLMakeupLiveHairDyeFilter.HairDyeData? {
        val l = live ?: return null
        val hd = liveHairData ?: return null
        val fd = liveFaceDetected ?: return null

        // Guard the native tracker: a frame whose buffer is smaller than a w×h NV21 plane would make
        // TrackYUV420Biplanar read out of bounds and crash. Drop such frames instead (return null).
        if (w <= 0 || h <= 0) return null
        if (nv21.size < com.piontech.venussdk.util.YuvConverter.nv21Size(w, h)) {
            Log.w(TAG, "processLiveFrame: NV21 too small (${nv21.size} for ${w}x$h) — frame dropped")
            return null
        }

        // SetMakeupParameters: HAIR_DYE on (arg #21/z17), all other features off. Reshape array must
        // be non-null a0.c instances (native reads their fields to decide segmentation).
        l.c0(
            false, 0, VN_EyebrowMode.EYEBROW_ORIGINAL_MODE, 0, 0, 0, 0, 0, 0, 0, 0,
            false, false, cFoundationOff, cDewyZero, cDistortOff, cReshape,
            false, false, false, true /* HAIR_DYE */, false, false, false, false,
            false, false, false, false, false, false,
            ceObj, ceObj, ceInt, ceInt, ceInt, ceFloat, ceObj, ceObj, ceObj, ceFloat, ceFloat, ceObj, 0
        )
        // feed the frame (tracker — fast temporal track, not full re-detect)
        l.l0(nv21, w, h, rotation, frontFlip, frameFlip)
        // pull metadata: all 26 arrays non-null (hair at slot 18), faceDetected at 27.
        // Carriers carry the native-called Alloc* methods so native can allocate per-face buffers.
        val m = md ?: return null
        l.t(
            m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12],
            m[13], m[14], m[15], m[16], m[17], m[18], m[19], m[20], m[21], m[22], m[23], m[24], m[25], fd
        )
        return if (l.G()) hd[0] else null
    }

    // ---------------------------------------------------------------- resource release

    /**
     * Release the LIVE engine + its metadata buffers (native memory). Call when leaving the camera
     * for good (e.g. Activity.isFinishing). [ensureLiveReady] re-inits on next use (~900ms).
     */
    @Synchronized
    fun releaseLive() {
        try { live?.q0() } catch (t: Throwable) { Log.w(TAG, "releaseLive live", t) }
        try { livePhoto?.m0() } catch (t: Throwable) { Log.w(TAG, "releaseLive photo", t) }
        live = null
        livePhoto = null
        md = null
        liveHairData = null
        liveFaceDetected = null
        cFoundationOff = null; cDewyZero = null; cDistortOff = null; cReshape = null
        ceObj = null; ceInt = null; ceFloat = null
    }

    /** Release the PHOTO engine (native memory). [ensurePhotoReady] re-inits on next use. */
    @Synchronized
    fun releasePhoto() {
        try { photo?.m0() } catch (t: Throwable) { Log.w(TAG, "releasePhoto", t) }
        photo = null
    }
}
