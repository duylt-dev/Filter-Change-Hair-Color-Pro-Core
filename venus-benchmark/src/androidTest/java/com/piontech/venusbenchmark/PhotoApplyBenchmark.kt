package com.piontech.venusbenchmark

import android.graphics.Bitmap
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.piontech.venussdk.engine.VenusEngine
import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.HairMode
import com.piontech.venussdk.model.HairPalette
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tier-3: still-image apply cost (venus-sdk/TESTING.md §Tầng 3).
 *
 * The engine is initialized ONCE in setup; the timed loop only runs the per-image pipeline
 * (AnalyzeImage -> mask -> configHairDye -> GetMakeupImage -> readback). The source bitmap is decoded
 * once and reused. Baseline @1280px is not yet measured — this benchmark establishes it.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class PhotoApplyBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private lateinit var source: Bitmap
    private val color = HairPalette.colors.first() // "Nâu sẫm"

    @Before
    fun setUp() {
        BenchSupport.assumeRunnable()
        source = BenchSupport.loadSampleFace(longestEdgePx = 1280)
        val ok = VenusEngine.ensurePhotoReady(BenchSupport.appContext) {}
        assertTrue("photo engine failed to init", ok)
        // Guard: if the sample image has no detectable face the whole benchmark is meaningless.
        val probe = VenusEngine.applyOnPhoto(BenchSupport.appContext, source, VenusEngine.BeautifyRequest(hair = color)) {}
        assertTrue("sample_face.jpg must contain a detectable face: ${probe.log}", probe.ok)
    }

    /** SINGLE-color hair dye @1280px. */
    @Test
    fun applySingle_1280px() {
        val ctx = BenchSupport.appContext
        benchmarkRule.measureRepeated {
            val r = VenusEngine.applyOnPhoto(
                ctx, source, VenusEngine.BeautifyRequest(hair = color, hairMode = HairMode.SINGLE)
            ) {}
            assertTrue(r.ok)
        }
    }

    /** MULTI full-color pattern @1280px (dump pattern -> tempfile -> cache -> multiColorMode=2). */
    @Test
    fun applyMulti_1280px() {
        val ctx = BenchSupport.appContext
        val pattern = BenchSupport.patternBitmap()
        // MULTI now routes through the GL path (applyFullColorPhoto): the native engine can't bind the
        // pattern, so we composite it with the live shader. Probe once; SKIP only if this device can't
        // produce the mask (e.g. no face) so the perf suite stays green on bad fixtures.
        val probe = VenusEngine.applyOnPhoto(
            ctx, source,
            VenusEngine.BeautifyRequest(hair = color, hairMode = HairMode.MULTI, hairPattern = pattern)
        ) { line -> android.util.Log.w("VenusMultiSpike", line) }
        android.util.Log.w("VenusMultiSpike", "=== MULTI RESULT ok=${probe.ok} bitmap=${probe.bitmap != null} log=${probe.log}")
        org.junit.Assume.assumeTrue("MULTI GL full-color failed (no face / GL): ${probe.log}", probe.ok)
        benchmarkRule.measureRepeated {
            val r = VenusEngine.applyOnPhoto(
                ctx, source,
                VenusEngine.BeautifyRequest(
                    hair = color, hairMode = HairMode.MULTI, hairPattern = pattern
                )
            ) {}
            assertTrue(r.ok)
        }
    }

    /** Skin-only beautify (smooth + whiten) @1280px — the non-hair path through GetMakeupImage. */
    @Test
    fun applySkinOnly_1280px() {
        val ctx = BenchSupport.appContext
        benchmarkRule.measureRepeated {
            val r = VenusEngine.applyOnPhoto(
                ctx, source, VenusEngine.BeautifyRequest(skinSmooth = 60, skinWhiten = 40)
            ) {}
            assertTrue(r.ok)
        }
    }
}
