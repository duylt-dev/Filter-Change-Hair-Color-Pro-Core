plugins {
    alias(libs.plugins.android.library)
    `maven-publish`
}

// Publish coordinates. Version overridable with -PvenusSdk.version=… (default 1.0.0).
val sdkGroupId = "com.piontech.venussdk"
val sdkArtifactId = "venus-sdk"
val sdkVersion = "1.0.0"

android {
    namespace = "com.piontech.venussdk"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        // Library installs on API 24+, but the native engine requires API 26 at runtime
        // (libtensorflowlite_pf strong-imports AHardwareBuffer_*). Callers must gate via
        // VenusEngine.isEngineSupported() before init — see VenusEngine.
        minSdk = 24

        // CyberLink native engine ships only arm64-v8a + armeabi-v7a (no x86/x86_64).
        ndk {
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "consumer-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        jniLibs {
            // Prebuilt CyberLink .so are already 16KB-aligned & stripped — don't let AGP re-strip them.
            keepDebugSymbols += "**/*.so"
        }
    }

    // Required so `from(components["release"])` works — publish only the release AAR.
    publishing {
        singleVariant("release")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)
}

// ---- Publishing to GitHub Packages -------------------------------------------------------------
// The release AAR auto-bundles the 8 .so + 7 model assets + consumer-rules.pro.
// `publishToMavenLocal` needs no config (local verify). To publish to GitHub Packages, supply via
// ~/.gradle/gradle.properties or env: gpr.owner / gpr.repo + gpr.user / gpr.key (GITHUB_ACTOR /
// GITHUB_TOKEN with write:packages). ⚠️ PRIVATE repo only — .so/model are CyberLink-proprietary.
publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = sdkGroupId
            artifactId = sdkArtifactId
            version = sdkVersion
            afterEvaluate { from(components["release"]) }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            val owner = (findProperty("gpr.owner") as String?) ?: System.getenv("GPR_OWNER").orEmpty()
            val repo = (findProperty("gpr.repo") as String?) ?: System.getenv("GPR_REPO").orEmpty()
            url = uri("https://maven.pkg.github.com/$owner/$repo")
            credentials {
                username = (findProperty("gpr.user") as String?) ?: System.getenv("GITHUB_ACTOR")
                password = (findProperty("gpr.key") as String?) ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
