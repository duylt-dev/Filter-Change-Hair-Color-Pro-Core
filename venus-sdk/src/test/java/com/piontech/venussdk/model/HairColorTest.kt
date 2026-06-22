package com.piontech.venussdk.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** Tier-1 JVM unit tests — pure model logic (no native / no Android framework). */
class HairColorTest {

    @Test
    fun argb_packsOpaqueRgb() {
        assertEquals(0xFF6F3D1B.toInt(), HairColor("Nâu sẫm", 0x6F, 0x3D, 0x1B).argb)
        assertEquals(0xFF000000.toInt(), HairColor("Đen", 0, 0, 0).argb)
        assertEquals(0xFFFFFFFF.toInt(), HairColor("Trắng", 255, 255, 255).argb)
    }

    @Test
    fun argb_alphaAlwaysOpaque() {
        HairPalette.colors.forEach {
            assertEquals("alpha must be 0xFF for ${it.name}", 0xFF, (it.argb ushr 24) and 0xFF)
        }
    }

    @Test
    fun palette_isValid() {
        val colors = HairPalette.colors
        assertTrue("palette not empty", colors.isNotEmpty())
        colors.forEach { c ->
            assertTrue("name blank", c.name.isNotBlank())
            assertTrue("r in range (${c.name})", c.r in 0..255)
            assertTrue("g in range (${c.name})", c.g in 0..255)
            assertTrue("b in range (${c.name})", c.b in 0..255)
            assertTrue("intensity in range (${c.name})", c.intensity in 0..100)
            assertTrue("shine in range (${c.name})", c.shine in 0..100)
        }
        assertEquals("duplicate color names", colors.size, colors.map { it.name }.toSet().size)
    }

    @Test
    fun defaults_intensityAndShine() {
        val c = HairColor("x", 1, 2, 3)
        assertEquals(100, c.intensity)
        assertEquals(50, c.shine)
    }

    @Test
    fun modes_containExpectedValues() {
        // guards against accidental enum removal that callers (and the engine mapping) depend on
        assertTrue(HairMode.entries.containsAll(listOf(HairMode.SINGLE, HairMode.OMBRE, HairMode.TWO_COLOR, HairMode.MULTI)))
        assertTrue(LiveMode.entries.containsAll(listOf(LiveMode.SINGLE, LiveMode.GRADIENT, LiveMode.OMBRE, LiveMode.MULTI)))
    }
}
