package com.piontech.venusbenchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.piontech.venussdk.engine.VenusEngine
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tier-3: engine startup cost (venus-sdk/TESTING.md §Tầng 3).
 *
 * The engine is a native singleton with internal caches — a warm `ensure*Ready` returns instantly,
 * so we measure a *cold* start by releasing the engine inside `runWithTimingDisabled` before each
 * timed iteration. The model copy (I/O) and engine load are thus both included in the cold number.
 *
 * Observed baseline (SM-A165F): ~900 ms init. Suggested regression threshold: < 1500 ms.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class EngineInitBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Before
    fun setUp() {
        BenchSupport.assumeRunnable()
        // Warm the model files onto disk so the first timed init isn't skewed by a one-time copy
        // that the provision benchmark measures separately.
        VenusEngine.ensurePhotoReady(BenchSupport.appContext) {}
    }

    /** Cold PHOTO engine init: construct CUIVenusPhoto + provision + load 6 models. */
    @Test
    fun ensurePhotoReady_cold() {
        val ctx = BenchSupport.appContext
        benchmarkRule.measureRepeated {
            runWithTimingDisabled { VenusEngine.releasePhoto() }
            val ok = VenusEngine.ensurePhotoReady(ctx) {}
            assertTrue("photo engine failed to init", ok)
        }
    }

    /** Cold LIVE engine init: construct CUIVenusLive + provision + load 7 models + alloc buffers. */
    @Test
    fun ensureLiveReady_cold() {
        val ctx = BenchSupport.appContext
        benchmarkRule.measureRepeated {
            runWithTimingDisabled { VenusEngine.releaseLive() }
            val ok = VenusEngine.ensureLiveReady(ctx) {}
            assertTrue("live engine failed to init", ok)
        }
    }

    // (model provisioning cost is included in the cold-init benchmarks above; ModelProvisioner is
    //  internal to the SDK so it isn't benchmarked directly from this separate module.)
}
