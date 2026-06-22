package com.piontech.venussdk.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tier-1 JVM tests for [YuvConverter] — pure logic, runs everywhere with no device.
 *
 * The headline guard is [outputLength_matchesSize_forOddDims]: an undersized chroma plane on odd
 * dimensions overruns the array and crashes the camera thread in production. These tests lock the
 * size formula, the BT.601 coefficients (so colors can't silently regress), and the input validation.
 */
class YuvConverterTest {

    private fun solid(w: Int, h: Int, r: Int, g: Int, b: Int): ByteArray {
        val px = ByteArray(w * h * 4)
        for (k in 0 until w * h) {
            px[k * 4] = r.toByte()
            px[k * 4 + 1] = g.toByte()
            px[k * 4 + 2] = b.toByte()
            px[k * 4 + 3] = 0xFF.toByte()
        }
        return px
    }

    private fun ByteArray.u(i: Int) = this[i].toInt() and 0xFF

    // ---- nv21Size ---------------------------------------------------------------------------

    @Test
    fun nv21Size_evenDims() {
        assertEquals(6, YuvConverter.nv21Size(2, 2))    // 4 + 2*1*1
        assertEquals(24, YuvConverter.nv21Size(4, 4))   // 16 + 2*2*2
        assertEquals(460800, YuvConverter.nv21Size(480, 640)) // 307200 * 1.5
    }

    @Test
    fun nv21Size_oddDims_roundsChromaUp() {
        assertEquals(3, YuvConverter.nv21Size(1, 1))    // 1 + 2*1*1
        assertEquals(17, YuvConverter.nv21Size(3, 3))   // 9 + 2*2*2
        assertEquals(27, YuvConverter.nv21Size(5, 3))   // 15 + 2*3*2
    }

    @Test
    fun nv21Size_rejectsNonPositive() {
        assertThrows(IllegalArgumentException::class.java) { YuvConverter.nv21Size(0, 4) }
        assertThrows(IllegalArgumentException::class.java) { YuvConverter.nv21Size(4, 0) }
        assertThrows(IllegalArgumentException::class.java) { YuvConverter.nv21Size(-2, 4) }
    }

    // ---- the crash regression: output length must match size for ANY dims --------------------

    @Test
    fun outputLength_matchesSize_forOddDims() {
        // The exact class of bug that crashed the benchmark helper: odd width and/or height.
        for ((w, h) in listOf(2 to 2, 3 to 3, 4 to 6, 7 to 5, 1 to 9, 481 to 271, 480 to 641)) {
            val out = YuvConverter.rgbaToNv21(solid(w, h, 10, 20, 30), w, h)
            assertEquals("size mismatch for ${w}x$h", YuvConverter.nv21Size(w, h), out.size)
        }
    }

    @Test
    fun oddDimensions_doNotThrow() {
        // Direct reproduction of the original ArrayIndexOutOfBoundsException.
        YuvConverter.rgbaToNv21(solid(3, 3, 0, 0, 0), 3, 3)
        YuvConverter.rgbaToNv21(solid(5, 7, 255, 255, 255), 5, 7)
        YuvConverter.rgbaToNv21(solid(481, 271, 128, 64, 32), 481, 271)
    }

    // ---- BT.601 correctness (exact, full-range) ----------------------------------------------

    private fun assertSolidYuv(r: Int, g: Int, b: Int, y: Int, v: Int, u: Int) {
        val w = 4; val h = 4
        val out = YuvConverter.rgbaToNv21(solid(w, h, r, g, b), w, h)
        for (i in 0 until w * h) assertEquals("Y[$i] for ($r,$g,$b)", y, out.u(i))
        var k = w * h
        while (k < out.size) {
            assertEquals("V for ($r,$g,$b)", v, out.u(k))
            assertEquals("U for ($r,$g,$b)", u, out.u(k + 1))
            k += 2
        }
    }

    @Test fun white_isYMaxChromaNeutral() = assertSolidYuv(255, 255, 255, y = 235, v = 128, u = 128)
    @Test fun black_isYMinChromaNeutral() = assertSolidYuv(0, 0, 0, y = 16, v = 128, u = 128)
    @Test fun red_bt601() = assertSolidYuv(255, 0, 0, y = 82, v = 240, u = 90)
    @Test fun green_bt601() = assertSolidYuv(0, 255, 0, y = 144, v = 34, u = 54)
    @Test fun blue_bt601() = assertSolidYuv(0, 0, 255, y = 41, v = 110, u = 240)

    @Test
    fun yPlaneFillsExactlyWidthTimesHeight_thenChroma() {
        val w = 6; val h = 4
        val out = YuvConverter.rgbaToNv21(solid(w, h, 255, 255, 255), w, h)
        // Y plane is the first w*h bytes; chroma (V,U) follows and is 2*(w/2)*(h/2) bytes.
        assertEquals(w * h + 2 * (w / 2) * (h / 2), out.size)
    }

    // ---- input validation (fail fast, not a deep OOB crash) ----------------------------------

    @Test
    fun rejectsRgbaTooSmall() {
        val tooSmall = ByteArray(4 * 4 * 4 - 1)
        assertThrows(IllegalArgumentException::class.java) { YuvConverter.rgbaToNv21(tooSmall, 4, 4) }
    }

    @Test
    fun rejectsOutBufferTooSmall() {
        val rgba = solid(4, 4, 1, 2, 3)
        val out = ByteArray(YuvConverter.nv21Size(4, 4) - 1)
        assertThrows(IllegalArgumentException::class.java) {
            YuvConverter.rgbaToNv21Into(rgba, 4, 4, out)
        }
    }

    @Test
    fun rgbaToNv21Into_reusesBuffer() {
        val w = 8; val h = 8
        val out = ByteArray(YuvConverter.nv21Size(w, h))
        YuvConverter.rgbaToNv21Into(solid(w, h, 255, 255, 255), w, h, out)
        assertEquals(235, out.u(0)) // white Y, written into the caller buffer
    }

    // ---- evenFloor --------------------------------------------------------------------------

    @Test
    fun evenFloor_roundsDownAndClampsAtZero() {
        assertEquals(480, YuvConverter.evenFloor(480))
        assertEquals(480, YuvConverter.evenFloor(481))
        assertEquals(2, YuvConverter.evenFloor(3))
        assertEquals(0, YuvConverter.evenFloor(1))
        assertEquals(0, YuvConverter.evenFloor(0))
        assertEquals(0, YuvConverter.evenFloor(-7))
        assertTrue("must always be even", (YuvConverter.evenFloor(99999) and 1) == 0)
    }
}
