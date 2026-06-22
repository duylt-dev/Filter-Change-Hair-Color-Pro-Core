package com.piontech.venussdk.gl

import android.opengl.EGL14
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.cyberlink.clgpuimage.CLMakeupLiveHairDyeFilter.HairDyeData
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Standalone GL renderer that ports the original CLMakeupLiveHairDyeFilter shader (vertex+fragment)
 * + its texture/uniform upload (G), bind (n) and location lookup (o). Decoupled design:
 *  - the camera RGBA frame is the source texture (inputImageTexture, unit 0), updated every camera frame
 *  - the latest [HairDyeData] (mask + LUTs, computed on a worker thread) is uploaded + reused
 *
 * The frame fed to the engine is upright (rotation handled before, rotation arg = 0), so the
 * per-vertex `input_hair_texture_coordinate` is the unrotated A0 quad and orientation stays trivial.
 */
class HairDyeGLRenderer : GLSurfaceView.Renderer {

    // ---- shared state handed from worker threads to the GL thread ----
    @Volatile private var pendingRgba: ByteArray? = null
    @Volatile private var rgbaW = 0
    @Volatile private var rgbaH = 0
    @Volatile private var rgbaDirty = false
    @Volatile private var pendingHair: HairDyeData? = null
    @Volatile private var mirror = false
    @Volatile private var enabled = true

    /** Push the latest camera frame (RGBA8888, upright). Called from the camera thread. */
    fun updateCameraFrame(rgba: ByteArray, w: Int, h: Int, mirrorX: Boolean) {
        pendingRgba = rgba; rgbaW = w; rgbaH = h; mirror = mirrorX; rgbaDirty = true
    }

    /** Push the latest hair metadata (null = no hair / show raw). Called from the engine thread. */
    fun updateHairData(hd: HairDyeData?) { pendingHair = hd }

    /** Set the full-color pattern image for MULTI mode (null = none). Uploaded on the GL thread. */
    @Volatile private var pendingPattern: android.graphics.Bitmap? = null
    @Volatile private var patternDirty = false
    fun setFullColorPattern(bmp: android.graphics.Bitmap?) { pendingPattern = bmp; patternDirty = true }

    fun setEnabled(on: Boolean) { enabled = on }

    // ---- GL handles ----
    private var program = 0
    private var aPosition = 0
    private var aInputTexCoord = 0
    private var aHairTexCoord = 0
    private var uInputImage = 0
    // hair textures
    private var texCamera = -1
    private var texHair = -1          // segment mask (R8)
    private var texPostProc = -1      // RGBA
    private var texAvgGray = -1       // RGBA
    private var texGamma = -1         // 256x8 RGBA
    private var texSmoothedY = -1     // R8
    private var texShine = -1         // 256x4 RGBA
    private var texFullColor = -1

    private val uniform = HashMap<String, Int>()
    private fun u(name: String) = uniform.getOrPut(name) { GLES20.glGetUniformLocation(program, name) }

    private val quadPos = HairDyeGl.floatBuf(floatArrayOf(-1f, -1f, 1f, -1f, -1f, 1f, 1f, 1f))
    // camera texcoords: data row 0 is top of upright image -> map to v=0 at top vertex
    private val texCoord = HairDyeGl.floatBuf(floatArrayOf(0f, 1f, 1f, 1f, 0f, 0f, 1f, 0f))
    private val texCoordMirror = HairDyeGl.floatBuf(floatArrayOf(1f, 1f, 0f, 1f, 1f, 0f, 0f, 0f))
    // input_hair_texture_coordinate = normalized image position in the engine's convention
    // (y=0 at image top, matching the analyzing frame we feed). Same as the camera texcoord since
    // we display the same upright frame the engine analyzed (rotation=0).
    private val hairCoord = HairDyeGl.floatBuf(floatArrayOf(0f, 1f, 1f, 1f, 0f, 0f, 1f, 0f))

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0f, 0f, 0f, 1f)
        // The EGL context is destroyed on background by default → this runs again on resume with a
        // NEW program handle. Drop cached uniform locations (they belong to the old program) and force
        // re-upload of pattern/camera textures, else the hair shader gets stale locations = no recolor.
        uniform.clear()
        program = HairDyeGl.buildProgram(HairDyeShaders.VERTEX, HairDyeShaders.FRAGMENT)
        aPosition = GLES20.glGetAttribLocation(program, "position")
        aInputTexCoord = GLES20.glGetAttribLocation(program, "inputTextureCoordinate")
        aHairTexCoord = GLES20.glGetAttribLocation(program, "input_hair_texture_coordinate")
        uInputImage = GLES20.glGetUniformLocation(program, "inputImageTexture")
        // allocate textures (mirror original o())
        val ids = IntArray(8)
        GLES20.glGenTextures(8, ids, 0)
        texCamera = ids[0]; texHair = ids[1]; texPostProc = ids[2]; texAvgGray = ids[3]
        texGamma = ids[4]; texSmoothedY = ids[5]; texShine = ids[6]; texFullColor = ids[7]
        for (t in ids) HairDyeGl.configureTexture2D(t)
        // gamma 256x8, shine 256x4 (allocated, filled per frame via glTexSubImage2D)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texGamma)
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 256, 8, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texShine)
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 256, 4, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null)
        // re-upload the MULTI pattern into the freshly-created texture (camera/hair textures refill per frame)
        if (pendingPattern != null) patternDirty = true
    }

    private var viewW = 0
    private var viewH = 0
    // screen quad scaled to preserve camera aspect (center-crop "cover", no stretch)
    private val scaledPos = HairDyeGl.floatBuf(floatArrayOf(-1f, -1f, 1f, -1f, -1f, 1f, 1f, 1f))
    @Volatile private var captureCb: ((android.graphics.Bitmap) -> Unit)? = null

    /** Grab the next rendered frame as a Bitmap (on the GL thread) and hand it back. */
    fun capturePhoto(cb: (android.graphics.Bitmap) -> Unit) { captureCb = cb }

    // ---- video recording (encoder runs on the GL thread) ----
    @Volatile private var recordPath: String? = null
    @Volatile private var recordAudio = false
    @Volatile private var stopRecord = false
    private var encoder: GlVideoEncoder? = null
    private var encW = 0
    private var encH = 0
    val isRecording: Boolean get() = encoder != null || recordPath != null

    /** [withAudio] requires RECORD_AUDIO granted; the encoder falls back to video-only if the mic fails. */
    fun startRecording(path: String, withAudio: Boolean = false) { recordAudio = withAudio; recordPath = path }
    fun stopRecording() { stopRecord = true }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        viewW = width; viewH = height
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        if (program == 0) return
        // upload latest camera frame
        if (rgbaDirty) {
            val rgba = pendingRgba
            if (rgba != null) {
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texCamera)
                GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, rgbaW, rgbaH, 0,
                    GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(rgba))
            }
            rgbaDirty = false
        }
        if (patternDirty) {
            val p = pendingPattern
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texFullColor)
            if (p != null) {
                val pb = if (p.config == android.graphics.Bitmap.Config.ARGB_8888) p else p.copy(android.graphics.Bitmap.Config.ARGB_8888, false)
                val buf = ByteBuffer.allocateDirect(pb.width * pb.height * 4).order(ByteOrder.nativeOrder())
                pb.copyPixelsToBuffer(buf); buf.position(0)
                GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, pb.width, pb.height, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, buf)
            } else {
                GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 4, 4, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(ByteArray(64)))
            }
            patternDirty = false
        }
        if (rgbaW == 0) return
        GLES20.glUseProgram(program)

        // camera texcoord (mirror for front cam) + hair coords (same image point)
        val tc = if (mirror) texCoordMirror else texCoord
        tc.position(0)
        GLES20.glVertexAttribPointer(aInputTexCoord, 2, GLES20.GL_FLOAT, false, 0, tc)
        GLES20.glEnableVertexAttribArray(aInputTexCoord)
        tc.position(0)
        GLES20.glVertexAttribPointer(aHairTexCoord, 2, GLES20.GL_FLOAT, false, 0, tc)
        GLES20.glEnableVertexAttribArray(aHairTexCoord)
        GLES20.glEnableVertexAttribArray(aPosition)

        val hd = if (enabled) pendingHair else null
        if (hd != null && hd.m_segment_map_data != null) {
            GLES20.glUniform1i(u("bypass"), 0)
            uploadHair(hd)        // NOTE: clobbers whatever texture is bound on the active unit
            bindHairTextures()    // binds hair LUTs/maps to units 1-7
        } else {
            // no hair / no mask yet -> pass the camera through untouched
            GLES20.glUniform1i(u("bypass"), 1)
            bindHairTextures() // safe: binds the 7 sampler units to valid (possibly stale) textures
        }

        // camera as inputImageTexture (unit 0) — MUST be after uploadHair (which binds on unit 0)
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texCamera)
        GLES20.glUniform1i(uInputImage, 0)

        // ---- screen pass: aspect-correct (center-crop) ----
        computeCoverScale()
        drawQuad(scaledPos, viewW, viewH)

        captureCb?.let { cb -> val bmp = readPixels(viewW, viewH); captureCb = null; cb(bmp) }

        // ---- video pass: full frame (no crop), to the encoder surface ----
        drawToEncoder()

        GLES20.glDisableVertexAttribArray(aPosition)
        GLES20.glDisableVertexAttribArray(aInputTexCoord)
        GLES20.glDisableVertexAttribArray(aHairTexCoord)
    }

    /** Render the current scene (full frame, no crop) into the video encoder, on the GL thread. */
    private fun drawToEncoder() {
        val display = EGL14.eglGetCurrentDisplay()
        val ctx = EGL14.eglGetCurrentContext()
        val screen = EGL14.eglGetCurrentSurface(EGL14.EGL_DRAW)

        val path = recordPath
        if (path != null && encoder == null && rgbaW > 0) {
            encW = rgbaW; encH = rgbaH
            encoder = try { GlVideoEncoder(encW, encH, path, withAudio = recordAudio) } catch (t: Throwable) {
                android.util.Log.e("HairDyeGL", "encoder init failed", t); null
            }
            recordPath = null
        }
        val enc = encoder ?: return

        if (stopRecord) {
            enc.makeCurrent(); enc.finish()
            EGL14.eglMakeCurrent(display, screen, screen, ctx)
            encoder = null; stopRecord = false
            return
        }
        // program / attributes / textures are still bound from the screen pass
        enc.makeCurrent()
        drawQuad(quadPos, encW, encH)              // full frame (no view crop)
        enc.swap(System.nanoTime())
        EGL14.eglMakeCurrent(display, screen, screen, ctx)
    }

    private fun drawQuad(pos: FloatBuffer, vpW: Int, vpH: Int) {
        GLES20.glViewport(0, 0, vpW, vpH)
        pos.position(0)
        GLES20.glVertexAttribPointer(aPosition, 2, GLES20.GL_FLOAT, false, 0, pos)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4)
    }

    /** Scale the quad so the camera frame fills the view with its real aspect (crop overflow). */
    private fun computeCoverScale() {
        if (viewW == 0 || rgbaW == 0) return
        val ratio = (viewW.toFloat() / viewH) / (rgbaW.toFloat() / rgbaH) // a_view / a_img
        var sx = 1f; var sy = 1f
        if (ratio >= 1f) sy = ratio else sx = 1f / ratio
        scaledPos.clear()
        scaledPos.put(floatArrayOf(-sx, -sy, sx, -sy, -sx, sy, sx, sy)); scaledPos.position(0)
    }

    private fun readPixels(w: Int, h: Int): android.graphics.Bitmap = HairDyeGl.readPixelsFlipped(w, h)

    /** Upload all hair uniforms + LUT/mask textures via the shared [HairDyeGl] (live mode from hd). */
    private fun uploadHair(hd: HairDyeData) {
        HairDyeGl.uploadHairUniforms(hd, ::u, ::texOf, hd.m_multi_color_hair_dye_mode)
    }

    private fun texOf(role: HairDyeGl.HairTex): Int = when (role) {
        HairDyeGl.HairTex.HAIR -> texHair
        HairDyeGl.HairTex.POST_PROC -> texPostProc
        HairDyeGl.HairTex.AVG_GRAY -> texAvgGray
        HairDyeGl.HairTex.GAMMA -> texGamma
        HairDyeGl.HairTex.SMOOTHED_Y -> texSmoothedY
        HairDyeGl.HairTex.SHINE -> texShine
    }

    private fun bindHairTextures() {
        bindUnit(1, texHair, "hairTexture")
        bindUnit(2, texGamma, "gammaTexture")
        bindUnit(3, texSmoothedY, "hairLocalSmoothedY")
        bindUnit(4, texShine, "shineTexture")
        bindUnit(5, texAvgGray, "hairAverageGray")
        bindUnit(6, texPostProc, "hairPostProcessed")
        bindUnit(7, texFullColor, "hair_full_color_texture")
    }

    private fun bindUnit(unit: Int, tex: Int, sampler: String) {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + unit)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex)
        GLES20.glUniform1i(u(sampler), unit)
    }

}
