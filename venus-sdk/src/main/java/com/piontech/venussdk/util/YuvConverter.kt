package com.piontech.venussdk.util

/**
 * Pure RGBA→NV21 conversion + dimension helpers for the live camera path.
 *
 * This logic used to be duplicated (CameraActivity + benchmark helper) and is pure-but-fragile:
 * an off-by-one in the chroma plane size silently overruns the output array and crashes with
 * `ArrayIndexOutOfBoundsException` on the camera thread — a production crash that NO Android test
 * can catch but a JVM unit test can. So it lives here, validated, with one source of truth and a
 * thorough Tier-1 test (see `YuvConverterTest`). Callers must NOT re-implement it.
 *
 * Format: NV21 = YUV420 semi-planar, BT.601 full-range, full Y plane followed by interleaved
 * V,U (Cr,Cb) chroma sub-sampled 2×2. This is the byte layout the Venus live engine
 * (`TrackYUV420Biplanar`) expects.
 */
object YuvConverter {

    /**
     * Byte length of an NV21 buffer for [width]×[height]. Chroma has ceil(w/2)*ceil(h/2) (V,U) pairs;
     * sizing it as (w*h+1)/2 is wrong when a dimension is odd (one pair short) and overruns on write.
     */
    fun nv21Size(width: Int, height: Int): Int {
        require(width > 0 && height > 0) { "dimensions must be positive (was ${width}x$height)" }
        val chromaPairs = ((width + 1) / 2) * ((height + 1) / 2)
        return width * height + 2 * chromaPairs
    }

    /**
     * Largest even number ≤ [value] (clamped at 0). The engine sub-samples chroma 2×2, so camera
     * frames are forced to even dimensions before conversion; use this instead of a raw `and 1.inv()`
     * so negative/zero inputs can't produce a negative size.
     */
    fun evenFloor(value: Int): Int = if (value <= 0) 0 else value and 1.inv()

    /** Allocates and returns an NV21 buffer from [rgba] (R,G,B,A bytes) of size [width]×[height]. */
    fun rgbaToNv21(rgba: ByteArray, width: Int, height: Int): ByteArray {
        val out = ByteArray(nv21Size(width, height))
        rgbaToNv21Into(rgba, width, height, out)
        return out
    }

    /**
     * Converts [rgba] into the caller-provided [out] (reusable per-frame to avoid allocations).
     * Validates both buffers up-front so a malformed frame fails fast with a clear message rather
     * than an opaque out-of-bounds crash deep in the loop.
     */
    fun rgbaToNv21Into(rgba: ByteArray, width: Int, height: Int, out: ByteArray) {
        val needRgba = width * height * 4
        require(rgba.size >= needRgba) {
            "rgba too small: ${rgba.size} < $needRgba for ${width}x$height"
        }
        val needOut = nv21Size(width, height)
        require(out.size >= needOut) { "nv21 out too small: ${out.size} < $needOut for ${width}x$height" }

        var yi = 0
        var uvi = width * height
        for (j in 0 until height) {
            for (i in 0 until width) {
                val p = (j * width + i) * 4
                val r = rgba[p].toInt() and 0xFF
                val g = rgba[p + 1].toInt() and 0xFF
                val b = rgba[p + 2].toInt() and 0xFF
                out[yi++] = (((66 * r + 129 * g + 25 * b + 128) shr 8) + 16).coerceIn(0, 255).toByte()
                // chroma sampled at even (row, col); NV21 stores V (Cr) then U (Cb)
                if (j and 1 == 0 && i and 1 == 0) {
                    out[uvi++] = (((112 * r - 94 * g - 18 * b + 128) shr 8) + 128).coerceIn(0, 255).toByte()
                    out[uvi++] = (((-38 * r - 74 * g + 112 * b + 128) shr 8) + 128).coerceIn(0, 255).toByte()
                }
            }
        }
    }
}
