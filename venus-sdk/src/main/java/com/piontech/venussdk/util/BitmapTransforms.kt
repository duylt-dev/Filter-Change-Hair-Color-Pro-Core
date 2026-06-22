package com.piontech.venussdk.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import java.nio.ByteBuffer

/**
 * Small Bitmap helpers shared by the photo + live paths (app and SDK), so the rotate/downscale and
 * RGBA-extraction logic lives in ONE place. Android-coupled (kept out of the pure, JVM-tested
 * [YuvConverter]).
 */
object BitmapTransforms {

    /**
     * Rotate [src] upright by [degrees], downscale so the longest edge ≤ [maxDim], and ensure
     * ARGB_8888. [forceEven] rounds dimensions down to even (required by the engine's 2×2 chroma
     * sub-sampling on the live NV21 path; pass false for still photos).
     */
    fun orientAndDownscale(src: Bitmap, degrees: Float, maxDim: Int, forceEven: Boolean): Bitmap {
        var out = src
        if (degrees != 0f) {
            out = Bitmap.createBitmap(src, 0, 0, src.width, src.height, Matrix().apply { postRotate(degrees) }, true)
        }
        val longest = maxOf(out.width, out.height)
        if (longest > maxDim) {
            val s = maxDim.toFloat() / longest
            var w = (out.width * s).toInt()
            var h = (out.height * s).toInt()
            if (forceEven) { w = YuvConverter.evenFloor(w); h = YuvConverter.evenFloor(h) }
            out = Bitmap.createScaledBitmap(out, w.coerceAtLeast(1), h.coerceAtLeast(1), true)
        }
        if (out.config != Bitmap.Config.ARGB_8888) {
            val argb = Bitmap.createBitmap(out.width, out.height, Bitmap.Config.ARGB_8888)
            Canvas(argb).drawBitmap(out, 0f, 0f, null)
            out = argb
        }
        return out
    }

    /** Extract [bitmap] as an RGBA byte array (R,G,B,A), converting to ARGB_8888 first if needed. */
    fun toRgba(bitmap: Bitmap): ByteArray {
        val src = if (bitmap.config == Bitmap.Config.ARGB_8888) bitmap
        else bitmap.copy(Bitmap.Config.ARGB_8888, false)
        val rgba = ByteArray(src.width * src.height * 4)
        src.copyPixelsToBuffer(ByteBuffer.wrap(rgba))
        return rgba
    }
}
