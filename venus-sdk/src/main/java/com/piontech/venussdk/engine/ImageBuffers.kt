package com.piontech.venussdk.engine

import android.graphics.Bitmap
import com.cyberlink.youcammakeup.jniproxy.CImageBuffer
import com.cyberlink.youcammakeup.jniproxy.PixelFormat

/**
 * Bitmap <-> CImageBuffer bridge — a dependency-free reimplementation of the original
 * [com.cyberlink.youcammakeup.core.ImageBufferBridge] (which pulls in com.pf.common.utility.Bitmaps).
 *
 * Uses only the jniproxy CImageBuffer (zero-copy AttachAndroidBitmap) + Android Bitmap.
 */
internal object ImageBuffers {

    /** Wraps an ARGB_8888 bitmap as a 32-bit RGBA CImageBuffer (zero-copy attach). */
    fun fromBitmap(bitmap: Bitmap): CImageBuffer {
        require(bitmap.config == Bitmap.Config.ARGB_8888) {
            "Bitmap must be ARGB_8888 (was ${bitmap.config})"
        }
        val buffer = CImageBuffer(PixelFormat.Format32bppRGBA)
        check(buffer.d(bitmap)) { "AttachAndroidBitmap failed" }
        return buffer
    }

    /** Copies a 32-bit CImageBuffer back into a new ARGB_8888 bitmap. */
    fun toBitmap(src: CImageBuffer): Bitmap {
        val out = Bitmap.createBitmap(src.u().toInt(), src.r().toInt(), Bitmap.Config.ARGB_8888)
        val dst = fromBitmap(out)
        try {
            if (src.t() == dst.t()) {
                check(CImageBuffer.h(src, dst, null)) { "CopyImageBufferToImageBuffer failed" }
            } else {
                check(CImageBuffer.C(src, dst)) { "SwapColorChannel copy failed" }
            }
        } finally {
            dst.m() // DetachAndroidBitmap
            dst.a() // free native buffer
        }
        return out
    }

    /** Detach + free a buffer created via [fromBitmap]. */
    fun release(buffer: CImageBuffer) {
        buffer.m()
        buffer.a()
    }
}
