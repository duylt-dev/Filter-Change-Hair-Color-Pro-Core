package com.piontech.venusbenchmark

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import androidx.test.platform.app.InstrumentationRegistry
import com.piontech.venussdk.engine.VenusEngine
import com.piontech.venussdk.util.YuvConverter
import org.junit.Assume.assumeTrue

/**
 * Shared fixtures for the Tier-3 benchmarks (venus-sdk/TESTING.md §Tầng 3).
 *
 * The sample face ships in `:venus-sdk` main assets (`assets/sample_face.jpg`) so it merges into the
 * target APK; [openSampleFace] also falls back to the instrumentation (test-apk) assets in case it is
 * dropped under `androidTest/assets/` instead. The model files come from `:venus-sdk` main assets and
 * are reached via the *target/application* context (where ModelProvisioner copies them to filesDir).
 */
object BenchSupport {

    const val SAMPLE_FACE_ASSET = "sample_face.jpg"

    /** App (target) context — owns filesDir + the merged :venus-sdk model + sample assets. */
    val appContext: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    /** Test-apk context — owns androidTest/assets, if the sample is placed there instead. */
    private val testContext: Context
        get() = InstrumentationRegistry.getInstrumentation().context

    private fun hasSampleFace(): Boolean =
        appContext.assets.list("")?.contains(SAMPLE_FACE_ASSET) == true ||
            testContext.assets.list("")?.contains(SAMPLE_FACE_ASSET) == true

    /** Opens the sample face from whichever assets bundle holds it (target first, then test apk). */
    private fun openSampleFace() = runCatching { appContext.assets.open(SAMPLE_FACE_ASSET) }
        .getOrElse { testContext.assets.open(SAMPLE_FACE_ASSET) }

    /**
     * Skip the whole benchmark (rather than fail) when it can't run meaningfully: a non-ARM /
     * pre-API-26 device where the engine is unsupported, or a missing sample image.
     */
    fun assumeRunnable() {
        assumeTrue("engine unsupported on this device (needs API>=26 + arm ABI)", VenusEngine.isEngineSupported)
        assumeTrue(
            "missing assets/$SAMPLE_FACE_ASSET (in :venus-sdk main or androidTest assets)",
            hasSampleFace()
        )
    }

    /** Decodes the sample face and rescales so its longest edge is [longestEdgePx] (ARGB_8888). */
    fun loadSampleFace(longestEdgePx: Int): Bitmap {
        val raw = openSampleFace().use { BitmapFactory.decodeStream(it) }
            ?: error("decode failed for $SAMPLE_FACE_ASSET")
        val scale = longestEdgePx.toFloat() / maxOf(raw.width, raw.height)
        val w = (raw.width * scale).toInt().coerceAtLeast(1)
        val h = (raw.height * scale).toInt().coerceAtLeast(1)
        val scaled = raw.scale(w, h)
        return if (scaled.config == Bitmap.Config.ARGB_8888) scaled
        else scaled.copy(Bitmap.Config.ARGB_8888, false)
    }

    /** A synthetic full-color gradient for the MULTI (multiColorMode=2) pattern path. */
    fun patternBitmap(size: Int = 256): Bitmap {
        val bmp = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val paint = Paint().apply {
            shader = LinearGradient(
                0f, 0f, size.toFloat(), size.toFloat(),
                intArrayOf(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA),
                null, Shader.TileMode.CLAMP
            )
        }
        Canvas(bmp).drawRect(0f, 0f, size.toFloat(), size.toFloat(), paint)
        return bmp
    }

    /**
     * Converts an ARGB_8888 bitmap to NV21 the SAME way production does: copy pixels to an RGBA byte
     * buffer (as CameraActivity does from the camera frame) then delegate to the one tested
     * [YuvConverter]. No hand-rolled conversion here — that duplication is exactly what caused the
     * earlier off-by-one crash (now covered by YuvConverterTest).
     */
    fun toNv21(bitmap: Bitmap): ByteArray {
        val rgba = ByteArray(bitmap.width * bitmap.height * 4)
        bitmap.copyPixelsToBuffer(java.nio.ByteBuffer.wrap(rgba))
        return YuvConverter.rgbaToNv21(rgba, bitmap.width, bitmap.height)
    }

    private fun Bitmap.scale(width: Int, height: Int): Bitmap =
        Bitmap.createScaledBitmap(this, width, height, true)
}
