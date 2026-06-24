import com.android.build.api.dsl.ApplicationBuildType
import java.text.SimpleDateFormat
import java.util.Date

fun ApplicationBuildType.stringConfigField(key: String, value: String) =
    this.buildConfigField("String", key, "\"$value\"")

fun ApplicationBuildType.booleanConfigField(key: String, value: Boolean) =
    this.buildConfigField("Boolean", key, value.toString())

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.service)
}

android {
    namespace = "com.piontech.changehaircolor.filterchangehaircolorpro.demo"

    signingConfigs {
        create("release") {
            keyAlias = "key0"
            keyPassword = "com.piontech.changehaircolor.filterchangehaircolorpro.demo"
            storePassword = "com.piontech.changehaircolor.filterchangehaircolorpro.demo"
            storeFile = File(projectDir, "../app/key_store.jks")
        }
    }

    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.piontech.changehaircolor.filterchangehaircolorpro.demo"
        // App installs on API 24+, but the engine needs API 26 (libtensorflowlite_pf imports
        // AHardwareBuffer_*). VenusEngine.isEngineSupported gates init at runtime; on API 24/25
        // the UI shows "thiết bị không hỗ trợ" instead of crashing.
        minSdk = 24
        targetSdk = 36
        versionCode = 100
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val simpleDateFormat = SimpleDateFormat("dd.M.yyyy")
        val formattedDate = simpleDateFormat.format(Date())
        base.archivesName.set("FilterChangeHairColorPro_vName_${versionName}_vCode${versionCode}_$formattedDate")

        // The CyberLink native engine only ships arm64-v8a + armeabi-v7a (no x86/x86_64).
        ndk {
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }
    }

    buildTypes {
        debug {
            versionNameSuffix = "-debug"
            isMinifyEnabled = false
            isDebuggable = true

            booleanConfigField("isDebug", true)
        }
        release {
            // R8: rút gọn + obfuscate code và tài nguyên. Keep rules cho Filament/ML Kit/Gson/custom-view
            // nằm ở proguard-rules.pro (BẮT BUỘC, thiếu là vỡ runtime: JSON không parse, bọ không render).
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // Ký bằng keystore release thật (app/key_store.jks) khai báo ở signingConfigs trên.
            signingConfig = signingConfigs.getByName("release")

            booleanConfigField("isDebug", false)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    splits {
        abi {
            isEnable = false
        }
    }

    packaging {
        jniLibs {
            // Prebuilt CyberLink .so are already 16KB-aligned & stripped — don't let AGP re-strip them.
            keepDebugSymbols += "**/*.so"
            // B4: if another native lib (OpenCV, ML Kit, ffmpeg…) is added later that also bundles
            // libc++_shared.so, take the first to avoid a duplicate-merge failure.
            pickFirsts += "**/libc++_shared.so"
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // Consume venus-sdk from GitHub Packages (published AAR) instead of the local module — validates
    // the real consumption path. (Local module stays in the build for :venus-benchmark + publishing.)
    implementation("com.piontech.venussdk:venus-sdk:1.0.0")

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

    // CameraX (live camera feed)
    val camerax = "1.4.0"
    implementation("androidx.camera:camera-core:$camerax")
    implementation("androidx.camera:camera-camera2:$camerax")
    implementation("androidx.camera:camera-lifecycle:$camerax")
    implementation("androidx.camera:camera-view:$camerax")

    // Hilt (DI)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Coroutines + Lifecycle (MVVM)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}