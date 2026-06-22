plugins {
    alias(libs.plugins.android.library)
}

// Tier-3 performance harness for :venus-sdk (see venus-sdk/TESTING.md §Tầng 3).
//
// NOTE on module type: this is a `com.android.library` whose androidTest uses AndroidX
// *Micro*benchmark (`benchmark-junit4` + AndroidBenchmarkRunner) to time VenusEngine calls in-process.
// It is NOT `com.android.test` (that is for *Macro*benchmark, which drives a separate target app via
// UI Automator and needs `targetProjectPath` — wrong tool here).
//
// The `androidx.benchmark` *Gradle plugin* is intentionally NOT applied: v1.3.4 expects AGP's removed
// `TestedExtension` and fails under AGP 9. The plugin only adds convenience (debuggable guardrails +
// `benchmarkData` aggregation); the BenchmarkRule itself runs fine without it as long as the test
// build is non-debuggable — hence `testBuildType = "release"` below. TESTING.md §Setup corrected.
android {
    namespace = "com.piontech.venusbenchmark"

    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        // Engine floor: API 26 (libtensorflowlite_pf strong-imports AHardwareBuffer_*).
        minSdk = 26

        // CyberLink native engine ships only arm64-v8a + armeabi-v7a — benchmarks must run on an
        // ARM device or Apple-Silicon ARM emulator; never on the default x86 CI emulator.
        ndk {
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }

        // The benchmark runner locks clocks, disables JIT noise, and writes p50/p90/min reports.
        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"

        // Disable on-device file output (JSON report + per-test Perfetto trace). Reasons:
        //  - Without the androidx.benchmark Gradle plugin there is no pull-back of report files, and
        //  - a non-debuggable release app on a retail (non-rooted) device is NOT Perfetto-traceable,
        //    so the trace capture yields no file and the writer throws FileNotFound.
        // With output disabled, Outputs.writeFile() short-circuits; the measured p50/p90/min are still
        // emitted to Logcat (tag "Benchmark") and to the instrumentation result, which is what we read.
        testInstrumentationRunnerArguments["androidx.benchmark.output.enable"] = "false"
        // Retail device, no root → clocks can't be locked; downgrade that hard error to a warning so
        // the benchmarks still run (numbers carry a bit more variance, noted in the report).
        testInstrumentationRunnerArguments["androidx.benchmark.suppressErrors"] = "UNLOCKED"
    }

    buildTypes {
        // Benchmarks must run against a non-debuggable build for trustworthy numbers; the
        // androidx.benchmark plugin enforces this and refuses (or warns) on a debuggable variant.
        debug {
            // Keep a debug variant so the module configures, but steer instrumentation to release.
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            isDefault = true
        }
    }
    // Run the instrumented benchmarks against the release build type (non-debuggable).
    testBuildType = "release"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        jniLibs {
            // The transitive CyberLink .so come from :venus-sdk already 16KB-aligned & stripped.
            keepDebugSymbols += "**/*.so"
        }
    }
}

dependencies {
    // The module under test. Brings VenusEngine + the jniLibs (.so) + the model assets transitively.
    implementation(project(":venus-sdk"))

    androidTestImplementation(libs.androidx.benchmark.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.junit)
}
