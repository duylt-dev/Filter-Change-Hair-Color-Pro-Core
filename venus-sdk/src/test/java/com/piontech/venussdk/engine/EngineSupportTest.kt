package com.piontech.venussdk.engine

import com.piontech.venussdk.engine.EngineSupport.Reason
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tier-1 JVM tests for the engine-support gate. These stand in for the hardware we can't test here:
 * the **armeabi-v7a** device and **API 24/25** phones. Locks the rule that the engine needs API ≥ 26
 * AND an arm ABI, so a regression can't accidentally let the engine load (and crash) on x86 / old API.
 */
class EngineSupportTest {

    private val ARM64 = arrayOf("arm64-v8a", "armeabi-v7a")
    private val V7A = arrayOf("armeabi-v7a")
    private val X86 = arrayOf("x86_64", "x86")
    private val X86_WITH_ARM_TRANSLATION = arrayOf("x86_64", "arm64-v8a") // some emulators expose both

    // ---- supported combinations ----

    @Test
    fun arm64_api26plus_isSupported() {
        assertTrue(EngineSupport.isSupported(26, ARM64))
        assertTrue(EngineSupport.isSupported(34, ARM64))
        assertTrue(EngineSupport.isSupported(36, ARM64))
    }

    @Test
    fun armeabiV7a_api26plus_isSupported() {
        // The v7a path we cannot verify on real hardware — assert the gate would allow it.
        assertTrue(EngineSupport.isSupported(26, V7A))
        assertTrue(EngineSupport.isSupported(30, V7A))
    }

    @Test
    fun x86WithArmTranslation_isSupported() {
        // arm64 present alongside x86 (translation) → still allowed.
        assertTrue(EngineSupport.isSupported(34, X86_WITH_ARM_TRANSLATION))
    }

    // ---- unsupported: API too low (24 / 25) ----

    @Test
    fun api24or25_onArm_isUnsupported() {
        assertFalse("API 24 must be unsupported", EngineSupport.isSupported(24, ARM64))
        assertFalse("API 25 must be unsupported", EngineSupport.isSupported(25, ARM64))
        assertEquals(Reason.API_TOO_LOW, EngineSupport.reason(24, ARM64))
        assertEquals(Reason.API_TOO_LOW, EngineSupport.reason(25, V7A))
    }

    @Test
    fun apiBoundary_exactly26_isSupported() {
        assertFalse(EngineSupport.isSupported(EngineSupport.MIN_API - 1, ARM64))
        assertTrue(EngineSupport.isSupported(EngineSupport.MIN_API, ARM64))
    }

    // ---- unsupported: non-arm ABI ----

    @Test
    fun x86Only_isUnsupported_evenOnNewApi() {
        assertFalse(EngineSupport.isSupported(36, X86))
        assertEquals(Reason.ABI_UNSUPPORTED, EngineSupport.reason(36, X86))
    }

    @Test
    fun emptyAbis_isUnsupported() {
        assertFalse(EngineSupport.isSupported(36, emptyArray()))
        assertEquals(Reason.ABI_UNSUPPORTED, EngineSupport.reason(36, emptyArray()))
    }

    // ---- unsupported: both ----

    @Test
    fun oldApiAndX86_reportsBoth() {
        assertFalse(EngineSupport.isSupported(24, X86))
        assertEquals(Reason.API_AND_ABI, EngineSupport.reason(24, X86))
    }

    // ---- reason text ----

    @Test
    fun unsupportedReason_nullWhenSupported_elseDescriptive() {
        assertNull(EngineSupport.unsupportedReason(34, ARM64))
        assertNotNull(EngineSupport.unsupportedReason(24, ARM64))
        assertNotNull(EngineSupport.unsupportedReason(36, X86))
        assertTrue(EngineSupport.unsupportedReason(24, ARM64)!!.contains("API"))
        assertTrue(EngineSupport.unsupportedReason(36, X86)!!.contains("arm"))
    }
}
