package com.piontech.venusbenchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.piontech.venussdk.engine.VenusEngine
import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.HairPalette
import com.piontech.venussdk.model.LiveMode
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tier-3: per-frame live tracking cost (venus-sdk/TESTING.md §Tầng 3) — the frame-rate-critical path.
 *
 * Live engine + buffers initialized ONCE in setup; one fixed NV21 frame (built once from the sample
 * face) is fed every iteration. We measure SetMakeupParameters -> TrackYUV420Biplanar ->
 * GetMakeupMetadata, i.e. the work done per camera frame.
 *
 * Baseline (SM-A165F): ~65–100 ms/frame (~10–15 fps). Suggested threshold: < 120 ms (>= ~8 fps).
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class LiveFrameBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private lateinit var nv21: ByteArray
    private var frameW = 0
    private var frameH = 0

    @Before
    fun setUp() {
        BenchSupport.assumeRunnable()
        val frame = BenchSupport.loadSampleFace(longestEdgePx = 480)
        frameW = frame.width
        frameH = frame.height
        nv21 = BenchSupport.toNv21(frame)

        val ok = VenusEngine.ensureLiveReady(BenchSupport.appContext) {}
        assertTrue("live engine failed to init", ok)
        val c: HairColor = HairPalette.colors.first()
        VenusEngine.configureLiveHair(LiveMode.SINGLE, c, null, alphaPct = 100, shinePct = 50)
        // Warm a couple of frames so the tracker has locked on before timing (cold first frames are
        // re-detect heavy and would skew the loop).
        repeat(3) {
            VenusEngine.processLiveFrame(nv21, frameW, frameH, rotation = 0, frontFlip = false, frameFlip = false)
        }
    }

    /** Steady-state per-frame processing @480px. */
    @Test
    fun processLiveFrame_480px() {
        benchmarkRule.measureRepeated {
            VenusEngine.processLiveFrame(
                nv21, frameW, frameH, rotation = 0, frontFlip = false, frameFlip = false
            )
        }
    }
}
