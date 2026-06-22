package com.piontech.venussdk.engine

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tier-1 JVM test for the pure re-provision decision. Guards against a half-written / truncated model
 * being kept (which makes engine load fail forever) and against needless re-copies of a valid model.
 */
class ModelProvisionerTest {

    private val EXPECTED = 17_000L // pretend asset size

    @Test
    fun copiesWhenMissing() {
        assertTrue(ModelProvisioner.needsProvision(exists = false, actualLength = 0, expectedLength = EXPECTED))
        assertTrue(ModelProvisioner.needsProvision(exists = false, actualLength = 0, expectedLength = 0))
    }

    @Test
    fun copiesWhenEmpty() {
        assertTrue(ModelProvisioner.needsProvision(exists = true, actualLength = 0, expectedLength = EXPECTED))
        assertTrue(ModelProvisioner.needsProvision(exists = true, actualLength = 0, expectedLength = 0))
    }

    @Test
    fun reCopiesWhenTruncated() {
        // present but smaller than the asset → crash/full-disk left it truncated → must re-provision
        assertTrue(ModelProvisioner.needsProvision(exists = true, actualLength = 12_345, expectedLength = EXPECTED))
    }

    @Test
    fun reCopiesWhenLargerThanExpected() {
        assertTrue(ModelProvisioner.needsProvision(exists = true, actualLength = EXPECTED + 1, expectedLength = EXPECTED))
    }

    @Test
    fun skipsWhenSizeMatches() {
        assertFalse(ModelProvisioner.needsProvision(exists = true, actualLength = EXPECTED, expectedLength = EXPECTED))
    }

    @Test
    fun skipsWhenPresentAndExpectedUnknown() {
        // compressed asset → expected unknown (0); a present, non-empty file is trusted (no size check)
        assertFalse(ModelProvisioner.needsProvision(exists = true, actualLength = 12_345, expectedLength = 0))
    }
}
