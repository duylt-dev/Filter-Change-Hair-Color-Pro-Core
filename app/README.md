# app — Demo consumer của venus-sdk

App demo (đổi màu tóc + làm đẹp da, ảnh tĩnh & camera live) **tiêu thụ `venus-sdk` từ GitHub Packages**.
Kiến trúc **Clean Arch + Hilt + MVVM**. Đây là mẫu tham chiếu để các app khác implement SDK.

> SDK chi tiết: [../venus-sdk/README.md](../venus-sdk/README.md) · Test/benchmark: [../venus-sdk/test-overview.md](../venus-sdk/test-overview.md)
> 🔴 `.so`/model là tài sản CyberLink (license đang đàm phán) → **chỉ dùng nội bộ / repo private**.

---

## 1. Yêu cầu

| Mục | Giá trị |
|---|---|
| JDK | **JBR của Android Studio** (`/Applications/Android Studio.app/Contents/jbr/Contents/Home`) |
| AGP / Kotlin | AGP 9.x + Kotlin 2.2.x (built-in) |
| Thiết bị chạy engine | **máy thật ARM, API ≥ 26** (arm64-v8a / armeabi-v7a) — KHÔNG chạy emulator x86 |
| GitHub token | **classic PAT có scope `read:packages`** (để kéo SDK từ GitHub Packages private) |

---

## 2. Kéo project về & chạy

### B1. Clone
```bash
git clone https://github.com/duylt-dev/Filter-Change-Hair-Color-Pro-Core.git
cd Filter-Change-Hair-Color-Pro-Core
```

### B2. Khai credentials GitHub Packages — vào `~/.gradle/gradle.properties` (KHÔNG để trong repo)
```properties
gpr.user=<github-username>
gpr.key=ghp_xxxxxxxxxxxxxxxxxxxx   # PAT có read:packages
```
> Token để ở `~/.gradle` (global) để **không bị commit**. `chmod 600 ~/.gradle/gradle.properties`.
> `settings.gradle.kts` đọc `gpr.user`/`gpr.key` (hoặc env `GITHUB_ACTOR`/`GITHUB_TOKEN`).

### B3. Mở bằng Android Studio (tự tạo `local.properties` trỏ Android SDK) rồi sync, hoặc CLI:
```bash
JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home" ./gradlew :app:assembleDebug
```

### B4. Cài + chạy trên máy thật ARM
```bash
JAVA_HOME="<JBR>" ./gradlew :app:installDebug
# rồi mở app, hoặc:
adb shell monkey -p com.piontech.changehaircolor.filterchangehaircolorpro.demo -c android.intent.category.LAUNCHER 1
```
> Trên máy < API 26 hoặc x86, app vẫn cài nhưng UI hiện "thiết bị không hỗ trợ" (gate runtime), không crash.

---

## 3. Implement venus-sdk từ GitHub Packages (project mới)

Trong project này đã wire sẵn — đây là cách áp cho **app demo khác**:

`settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositories {
        google(); mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/duylt-dev/Filter-Change-Hair-Color-Pro-Core")
            credentials {
                username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
                password = providers.gradleProperty("gpr.key").orNull ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
```
`app/build.gradle.kts`:
```kotlin
android {
    defaultConfig {
        minSdk = 24
        ndk { abiFilters += listOf("arm64-v8a", "armeabi-v7a") } // engine không có x86
    }
}
dependencies {
    implementation("com.piontech.venussdk:venus-sdk:1.0.0")
}
```
> AAR đã kèm sẵn **8 `.so` + 7 model + `consumer-rules.pro`** → R8 tự giữ symbol JNI, app KHÔNG cần copy gì thêm.

---

## 4. Sử dụng SDK — chi tiết, handle mọi case

### 4.0. Quy tắc bắt buộc
1. **Gate trước**: `VenusEngine.isEngineSupported` rồi mới `ensure*/apply*/processLiveFrame`.
2. `ensure*Ready` / `applyOnPhoto` **blocking** → chạy **off main thread** (coroutine `Dispatchers.Default`).
3. Engine là **singleton native**: rời màn cho hẳn thì `releaseLive()`/`releasePhoto()`.
4. Camera: **drain frame trước khi release** (xem 4.3) — nếu không sẽ crash use-after-free.

### 4.1. Gate thiết bị
```kotlin
if (!VenusEngine.isEngineSupported) {
    showMessage("Thiết bị không hỗ trợ (cần Android 8.0+ & CPU arm)")
    return
}
```

### 4.2. Ảnh tĩnh — tất cả chế độ
```kotlin
val c1 = HairColor("Nâu", 0x6F, 0x3D, 0x1B, intensity = 100, shine = 50)
val c2 = HairColor("Đỏ", 0x9C, 0x02, 0x02)

val req = when (mode) {
    Mode.SINGLE     -> VenusEngine.BeautifyRequest(hair = c1)
    Mode.OMBRE      -> VenusEngine.BeautifyRequest(hair = c1, hairMode = HairMode.OMBRE)
    Mode.TWO_COLOR  -> VenusEngine.BeautifyRequest(hair = c1, hair2 = c2, hairMode = HairMode.TWO_COLOR)
    Mode.MULTI      -> VenusEngine.BeautifyRequest(hair = c1, hairMode = HairMode.MULTI, hairPattern = patternBmp)
    Mode.SKIN       -> VenusEngine.BeautifyRequest(skinSmooth = 60, skinWhiten = 40)
    Mode.HAIR_SKIN  -> VenusEngine.BeautifyRequest(hair = c1, skinSmooth = 40, skinWhiten = 30)
}
val result = withContext(Dispatchers.Default) {
    VenusEngine.applyOnPhoto(appContext, sourceBitmap, req) { line -> Log.d("Venus", line) }
}
if (result.ok) showBitmap(result.bitmap!!) else showMessage(result.log)   // ok=false -> bitmap=null
```
**Bảng case lỗi (trả `ok=false`, KHÔNG crash) — đọc `result.log`:**

| `log` chứa | App nên làm |
|---|---|
| "Không tìm thấy khuôn mặt" | yêu cầu chọn ảnh có mặt |
| "Hết bộ nhớ hoặc lỗi cấp ảnh" | downscale ảnh rồi thử lại |
| "đã bị recycle" / "kích thước…không hợp lệ" | truyền bitmap hợp lệ |
| "engine not ready" | kiểm tra model/log, thử lại |

> MULTI tự route qua đường GL nội bộ (chỉ xử lý tóc, không kèm skin trong cùng request).

### 4.3. Camera live — vòng đời ĐẦY ĐỦ (quan trọng nhất)
Luồng 1 frame: CameraX RGBA → ép even + xoay đứng → NV21 → `processLiveFrame` → đẩy mask vào `HairDyeGLRenderer`.
```kotlin
class CameraActivity : AppCompatActivity() {
    private val renderer = HairDyeGLRenderer()
    private val analysisExecutor = Executors.newSingleThreadExecutor()

    // init off-main
    private fun initEngine() = lifecycleScope.launch(Dispatchers.Default) {
        if (!VenusEngine.isEngineSupported) return@launch
        if (VenusEngine.ensureLiveReady(applicationContext) {}) {
            VenusEngine.configureLiveHair(LiveMode.SINGLE, color1, null, alphaPct = 100, shinePct = 50)
        }
    }

    private fun onFrame(proxy: ImageProxy) {          // CameraX ImageAnalysis, OUTPUT_IMAGE_FORMAT_RGBA_8888
        val front = lensFacing == CameraSelector.LENS_FACING_FRONT
        var bmp = try { proxy.toBitmap() } catch (t: Throwable) { proxy.close(); return } finally { proxy.close() }
        bmp = BitmapTransforms.orientAndDownscale(bmp, proxy.imageInfo.rotationDegrees.toFloat(), maxDim = 480, forceEven = true)
        val w = bmp.width; val h = bmp.height
        val rgba = BitmapTransforms.toRgba(bmp); bmp.recycle()
        val hd = try { VenusEngine.processLiveFrame(YuvConverter.rgbaToNv21(rgba, w, h), w, h, 0, false, false) } catch (t: Throwable) { null }
        renderer.updateCameraFrame(rgba, w, h, front)
        renderer.updateHairData(hd)                   // null = hiện camera gốc (chưa thấy tóc)
        glView.requestRender()
    }

    override fun onResume() { super.onResume(); glView.onResume() }
    override fun onPause()  { if (renderer.isRecording) { renderer.stopRecording(); glView.requestRender() }; glView.onPause(); super.onPause() }

    override fun onDestroy() {
        // ⚠️ BẮT BUỘC: drain frame TRƯỚC super.onDestroy() (nó trigger onCleared -> releaseLive ngay).
        analysisExecutor.shutdown()
        try { analysisExecutor.awaitTermination(2, TimeUnit.SECONDS) } catch (_: InterruptedException) {}
        super.onDestroy()
        VenusEngine.releaseLive()
    }
}
```
**Đổi mode/màu** (gọi khi user chọn, KHÔNG mỗi frame):
```kotlin
VenusEngine.configureLiveHair(LiveMode.SINGLE,   c1, null, alphaPct = 80, shinePct = 50) // alpha=độ đậm (chỉ SINGLE)
VenusEngine.configureLiveHair(LiveMode.OMBRE,    c1, c2,  100, 50)
VenusEngine.configureLiveHair(LiveMode.GRADIENT, c1, c2,  100, 50)
VenusEngine.configureLiveHair(LiveMode.MULTI,    c1, null, 100, 50); renderer.setFullColorPattern(patternBmp)
renderer.setEnabled(false) // tắt recolor (camera gốc) không cần release
```
**Chụp/quay từ live:**
```kotlin
renderer.capturePhoto { bitmap -> /* lưu (trên GL thread) */ }
renderer.startRecording(file.absolutePath, withAudio = true)  // cần RECORD_AUDIO; fallback video-only
renderer.stopRecording()
```

### 4.4. Giải phóng
- Rời camera hẳn → drain executor → `releaseLive()` (như 4.3).
- Rời màn ảnh → `releasePhoto()` (vd `ViewModel.onCleared`).
- Lần dùng lại: engine tự re-init (~0.35–0.8s).

---

## 5. 🔑 Khuyến nghị build môi trường RELEASE

Engine nặng + nhiều `.so`/model → **bản release tối ưu hơn debug đáng kể**. Cấu hình khuyến nghị:

```kotlin
android {
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
    // Tách APK theo ABI → mỗi máy tải đúng .so của nó (giảm ~40% size)
    splits { abi { isEnable = true; reset(); include("arm64-v8a", "armeabi-v7a"); isUniversalApk = false } }
}
```
- **`consumer-rules.pro` của SDK tự áp** → R8 đã giữ symbol JNI/SWIG; app **không cần** tự thêm keep cho engine.
- **ABI split**: arm64 ~44M / v7a ~38M thay vì universal ~66M. Lên Play thì dùng **`bundleRelease` (.aab)** để Play tự tách.
- **Luôn gate `isEngineSupported`** (đã làm) — release minify không được xoá nhầm class engine (consumer-rules lo việc này).
- Test bản release thật trước khi phát hành: `./gradlew :app:assembleRelease` rồi cài APK đã ký, kiểm camera/photo trên máy ARM.
- ⚠️ Engine yêu cầu API ≥ 26; app `minSdk 24` chỉ để cài rộng + hiện thông báo, không phải để chạy engine ở 24/25.

---

## 6. Kiến trúc app (Clean Arch)
```
presentation/  Activity + ViewModel + UiState (photo, camera)   ← chỉ UI + lifecycle
domain/        repository (interface) + usecase + model thuần   ← không phụ thuộc Android engine
data/          repositoryImpl gọi VenusEngine + Media/String     ← cầu nối tới SDK
di/            Hilt RepositoryModule (bind interface -> impl)
```
SDK chỉ được chạm ở tầng **data** (repositoryImpl); presentation/domain không gọi `VenusEngine` trực tiếp.

---

## 7. Troubleshooting

| Lỗi | Nguyên nhân / xử lý |
|---|---|
| `Could not resolve com.piontech.venussdk:venus-sdk` / 401 | thiếu/sai `gpr.user`/`gpr.key` trong `~/.gradle/gradle.properties`, token thiếu `read:packages`, hoặc không có quyền vào repo private |
| `UnsatisfiedLinkError` / crash khi init engine | đang chạy máy **x86** hoặc **API < 26** → gate `isEngineSupported` (đừng init); hoặc app minify mà thiếu consumer-rules (SDK đã kèm sẵn) |
| Crash khi out camera | chưa drain executor trước `releaseLive` — theo đúng `onDestroy` ở 4.3 |
| Ảnh trả `ok=false` | đọc `DyeResult.log` (bảng ở 4.2) |
