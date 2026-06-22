# venus-sdk

Android library bọc engine native **CyberLink Venus/Perfect** (đổi màu tóc + làm đẹp da, cho **ảnh tĩnh**
và **camera live**). Phơi API Kotlin sạch dưới `com.piontech.venussdk.*`.

- Tiêu thụ nội bộ: `implementation(project(":venus-sdk"))`
- Hoặc qua **GitHub Packages** (xem [§2](#2-cài-đặt--github-packages)).

Cập nhật 2026-06-22. Tổng quan test + benchmark: [test-overview.md](test-overview.md) · Kế hoạch test: [TESTING.md](TESTING.md).

---

## 1. Yêu cầu & ràng buộc

| Mục | Giá trị |
|---|---|
| **ABI** | chỉ `arm64-v8a` + `armeabi-v7a` (KHÔNG x86/x86_64) |
| **API engine** | **≥ 26** (libtensorflowlite_pf import `AHardwareBuffer_*`) |
| **minSdk thư viện** | 24 (cài được API 24+, nhưng **phải gate runtime** trước khi init — xem §4.1) |
| Kotlin/AGP | AGP 9.x + Kotlin built-in 2.2.x |

**Bất biến — đổi = crash:** package/tên class+method `jniproxy.UIVenusJNI` (SWIG symbol); FQN + field `m_*`
của `clgpuimage.*`; `FaceRectDeleteException` đúng package; 7 tên file model; mọi GL handle/uniform phải coi
như mất khi EGL context recreate. `consumer-rules.pro` đã giữ sẵn các symbol này cho R8 (tự áp khi consume).

---

## 2. Cài đặt / GitHub Packages

### 2.1. Tiêu thụ (consumer app)

`settings.gradle.kts` → `dependencyResolutionManagement.repositories`:
```kotlin
maven {
    url = uri("https://maven.pkg.github.com/duylt-dev/Filter-Change-Hair-Color-Pro-Core")
    credentials {
        username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
        password = providers.gradleProperty("gpr.key").orNull ?: System.getenv("GITHUB_TOKEN") // cần scope read:packages
    }
}
```
`app/build.gradle.kts`:
```kotlin
dependencies {
    implementation("com.piontech.venussdk:venus-sdk:1.0.0")
}
android {
    defaultConfig {
        ndk { abiFilters += listOf("arm64-v8a", "armeabi-v7a") } // engine không có x86
        minSdk = 24
    }
}
```
> AAR đã đóng kèm `.so` + 7 model (assets) + `consumer-rules.pro`. App KHÔNG cần copy gì thêm.

### 2.2. Publish (maintainer) — thêm vào `venus-sdk/build.gradle.kts`

```kotlin
plugins { /* … */ `maven-publish` }

android { publishing { singleVariant("release") } }

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.piontech.venussdk"; artifactId = "venus-sdk"; version = "1.0.0"
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/duylt-dev/Filter-Change-Hair-Color-Pro-Core") // ⚠️ repo PRIVATE
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN") // scope write:packages
                }
            }
        }
    }
}
```
Chạy: `./gradlew :venus-sdk:publishReleasePublicationToGitHubPackagesRepository`.
> Build sạch trước: `:venus-sdk:assembleRelease` + `:venus-sdk:testDebugUnitTest` (35 test).

---

## 3. Cấu trúc module

```
venus-sdk/src/main/
├── jniLibs/{arm64-v8a,armeabi-v7a}/*.so      8 .so engine
├── assets/model/*                            7 model (detect/align/skinMap/hairMask/eye2/mouth2/regressor)
├── consumer-rules.pro                        R8 keep cho jniproxy/clgpuimage/m_*/FaceRectDeleteException
└── java/
    ├── com/cyberlink/youcammakeup/jniproxy/**   JNI proxy (giữ FQN, BẤT BIẾN)
    ├── com/cyberlink/clgpuimage/*               24 struct carrier (giữ FQN + field m_*)
    ├── com/cyberlink/youcammakeup/kernelctrl/FaceRectDeleteException
    ├── com/pf/makeupcam/camera/a0               carrier reshape cho c0 (live)
    ├── ev/a, ya/d, proguard/annotation/*        loader + stub
    └── com/piontech/venussdk/                   ★ WRAPPER SẠCH (API dùng)
        ├── engine/  VenusEngine · EngineSupport · ModelProvisioner · ImageBuffers
        ├── model/   HairColor · HairPalette · HairMode · LiveMode
        ├── util/    YuvConverter (RGBA→NV21, đã unit-test) · BitmapTransforms (rotate/scale/toRgba)
        └── gl/      HairDyeGl (shader+upload dùng chung) · HairDyeGLRenderer (live) · PhotoHairDyeRenderer (ảnh) · HairDyeShaders · GlVideoEncoder
```

---

## 4. Public API

| API | Mô tả | Thread |
|---|---|---|
| `VenusEngine.isEngineSupported: Boolean` | API≥26 + arm ABI | bất kỳ |
| `VenusEngine.ensurePhotoReady(ctx, log): Boolean` | init engine ảnh (blocking ~0.8s) | **off-main** |
| `VenusEngine.applyOnPhoto(ctx, src, BeautifyRequest, log): DyeResult` | tóc + da 1 lần render (MULTI tự route qua GL) | off-main |
| `VenusEngine.ensureLiveReady(ctx, log): Boolean` | init engine live (~0.35s) | **off-main** |
| `VenusEngine.configureLiveHair(mode, c1, c2, alphaPct, shinePct)` | đặt màu live (khi đổi, KHÔNG mỗi frame) | bất kỳ |
| `VenusEngine.processLiveFrame(nv21, w, h, rotation, frontFlip, frameFlip): HairDyeData?` | mỗi frame (đã `@Synchronized`) | camera thread |
| `VenusEngine.releaseLive()` / `releasePhoto()` | giải phóng native | bất kỳ |
| `gl.HairDyeGLRenderer` | `GLSurfaceView.Renderer`: `updateCameraFrame/updateHairData/setFullColorPattern/setEnabled/capturePhoto/startRecording/stopRecording/isRecording` | GL thread |
| `util.YuvConverter` | `rgbaToNv21 / nv21Size / evenFloor` (thuần, đã test) | bất kỳ |
| `model.*` | `HairColor`, `HairPalette.colors`, `HairMode{SINGLE,OMBRE,TWO_COLOR,MULTI}`, `LiveMode{SINGLE,GRADIENT,OMBRE,MULTI}` | — |

`DyeResult(bitmap: Bitmap?, ok: Boolean, log: String)` — `ok=false` thì `bitmap=null` và `log` chứa lý do.

> Bề mặt public CỐ TÌNH tối thiểu (chỉ những gì app cần). Các tiện ích nội bộ (`EngineSupport`,
> `ModelProvisioner`, `ImageBuffers`, `PhotoHairDyeRenderer`, `HairDyeShaders`, `GlVideoEncoder`,
> `applyFullColorPhoto`, `generateHairThumbnail`, `dyeHairOnPhoto`…) là `internal` — đổi sang `public`
> nếu consumer tương lai cần.

---

## 5. HƯỚNG DẪN SỬ DỤNG CHI TIẾT (handle mọi case)

### 5.0. Quy tắc vàng (không theo = crash/ANR)
1. **LUÔN gate** `isEngineSupported` trước mọi `ensure*`/`apply*`/`processLiveFrame`.
2. `ensure*Ready`/`applyOnPhoto` **blocking** → chạy **off main thread** (coroutine/executor).
3. Engine là **singleton native**. `processLiveFrame` đã `@Synchronized` với `releaseLive` → an toàn race,
   nhưng app **vẫn phải** dừng cấp frame trước khi release (xem §5.3).
4. Khi rời màn cho hẳn → `releaseLive()`/`releasePhoto()` để trả RAM native.

### 5.1. Gate thiết bị (case: không hỗ trợ)
```kotlin
if (!VenusEngine.isEngineSupported) {
    showMessage("Thiết bị không hỗ trợ (cần Android 8.0+ và CPU arm)")
    return // KHÔNG init engine — tránh crash trên API 24/25 hoặc x86
}
```

### 5.2. Ảnh tĩnh — tất cả các chế độ

```kotlin
// luôn off-main:
val result: DyeResult = withContext(Dispatchers.Default) {
    VenusEngine.applyOnPhoto(appContext, sourceBitmap, request) { line -> Log.d("Venus", line) }
}
if (result.ok) showBitmap(result.bitmap!!) else showMessage(result.log)
```

`request` theo từng case:
```kotlin
val c1 = HairColor("Nâu", 0x6F, 0x3D, 0x1B, intensity = 100, shine = 50)
val c2 = HairColor("Đỏ", 0x9C, 0x02, 0x02)

// 1 màu (SINGLE)
BeautifyRequest(hair = c1)                                            // 1 màu
// OMBRE (đậm trên → nhạt đuôi)
BeautifyRequest(hair = c1, hairMode = HairMode.OMBRE)
// 2 màu (trên c1 / dưới c2)
BeautifyRequest(hair = c1, hair2 = c2, hairMode = HairMode.TWO_COLOR)
// MULTI full-color (pattern) — tự route sang đường GL applyFullColorPhoto
BeautifyRequest(hair = c1, hairMode = HairMode.MULTI, hairPattern = patternBitmap)
// chỉ làm đẹp da (mịn 0..100 + trắng 0..100), không đổi tóc
BeautifyRequest(skinSmooth = 60, skinWhiten = 40)
// kết hợp tóc + da trong 1 lần render (TRỪ MULTI — MULTI chỉ xử lý tóc)
BeautifyRequest(hair = c1, skinSmooth = 40, skinWhiten = 30)
// chỉnh độ đậm/bóng riêng (ghi đè field trong HairColor)
BeautifyRequest(hair = c1, hairIntensity = 70, hairShine = 80)
```

> **MULTI** lưu ý: native không bind được pattern (wrapper JNI thiếu) → `applyOnPhoto(MULTI, pattern)`
> tự route qua **đường GL nội bộ** (mask từ live engine + composite pattern offscreen). Vì vậy MULTI
> **chỉ xử lý tóc** (không kèm skin trong cùng request). Caller chỉ cần gọi `applyOnPhoto` như trên.

**Các case lỗi trả về `ok=false` (KHÔNG crash) — xử lý qua `result.log`:**

| Tình huống | `log` chứa | App nên làm |
|---|---|---|
| Không có hiệu ứng nào bật | "no effect selected" | (trả về source) bỏ qua |
| Ảnh không có mặt | "Không tìm thấy khuôn mặt" | báo người dùng chọn ảnh có mặt |
| Ảnh quá lớn / hết RAM | "Hết bộ nhớ hoặc lỗi cấp ảnh" | **downscale** rồi thử lại (xem §5.5) |
| Ảnh đã `recycle` / size ≤ 0 | "đã bị recycle" / "kích thước…không hợp lệ" | truyền bitmap hợp lệ |
| Engine init fail | "engine not ready" | kiểm tra model/log; thử lại |
| MULTI không thấy tóc | "Không tìm thấy tóc/khuôn mặt" | báo người dùng |

### 5.3. Camera live — vòng đời ĐẦY ĐỦ (quan trọng nhất)

Luồng 1 frame: CameraX RGBA → ép **even dims** + xoay đứng → `YuvConverter.rgbaToNv21` →
`processLiveFrame` → đẩy mask vào `HairDyeGLRenderer`.

```kotlin
class CameraActivity : AppCompatActivity() {
    private val renderer = HairDyeGLRenderer()
    private val analysisExecutor = Executors.newSingleThreadExecutor()

    // ... bind GLSurfaceView(renderer), CameraX ImageAnalysis OUTPUT_IMAGE_FORMAT_RGBA_8888 ...

    private fun onFrame(proxy: ImageProxy) {
        val front = lensFacing == CameraSelector.LENS_FACING_FRONT
        var bmp = try { proxy.toBitmap() } catch (t: Throwable) { proxy.close(); return } finally { proxy.close() }
        bmp = transformUprightEven(bmp, proxy.imageInfo.rotationDegrees.toFloat(), maxDim = 480) // dùng YuvConverter.evenFloor
        val w = bmp.width; val h = bmp.height
        val rgba = ByteArray(w * h * 4); bmp.copyPixelsToBuffer(ByteBuffer.wrap(rgba)); bmp.recycle()

        val hd = try {
            VenusEngine.processLiveFrame(YuvConverter.rgbaToNv21(rgba, w, h), w, h, 0, false, false)
        } catch (t: Throwable) { null }   // processLiveFrame tự drop frame xấu → null, không crash

        renderer.updateCameraFrame(rgba, w, h, front)
        renderer.updateHairData(hd)         // null = hiện camera gốc (chưa thấy tóc)
        glView.requestRender()
    }

    // init off-main
    private fun initEngine() = lifecycleScope.launch(Dispatchers.Default) {
        if (!VenusEngine.isEngineSupported) { /* show unsupportedReason */ return@launch }
        if (VenusEngine.ensureLiveReady(applicationContext) { Log.d("Venus", it) }) {
            VenusEngine.configureLiveHair(LiveMode.SINGLE, color1, null, alphaPct = 100, shinePct = 50)
        }
    }

    override fun onResume() { super.onResume(); glView.onResume() }
    override fun onPause()  {
        if (renderer.isRecording) { renderer.stopRecording(); glView.requestRender() }
        glView.onPause(); super.onPause()
    }
    override fun onDestroy() {
        // ⚠️ BẮT BUỘC: dừng + CHỜ frame đang chạy XONG TRƯỚC super.onDestroy().
        // super.onDestroy() → ViewModelStore.clear()/onCleared() → releaseLive() NGAY. Nếu còn frame
        // trong native TrackYUV420Biplanar → use-after-free (SIGABRT). Drain trước là bắt buộc.
        analysisExecutor.shutdown()
        try { analysisExecutor.awaitTermination(2, TimeUnit.SECONDS) } catch (_: InterruptedException) {}
        super.onDestroy()
        VenusEngine.releaseLive()  // nếu không dùng ViewModel.onCleared
    }
}
```

**Đổi chế độ / màu live** (gọi khi user chọn, KHÔNG mỗi frame):
```kotlin
VenusEngine.configureLiveHair(LiveMode.SINGLE,   c1, null, alphaPct = 80, shinePct = 50) // alpha = độ đậm (chỉ SINGLE)
VenusEngine.configureLiveHair(LiveMode.OMBRE,    c1, c2,  100, 50)   // 2 màu dọc
VenusEngine.configureLiveHair(LiveMode.GRADIENT, c1, c2,  100, 50)   // blend theo độ sáng tóc
VenusEngine.configureLiveHair(LiveMode.MULTI,    c1, null, 100, 50)  // + renderer.setFullColorPattern(bitmap)
renderer.setEnabled(false) // tắt recolor (hiện camera gốc) mà không cần release
```

**Chụp ảnh / quay video từ live:**
```kotlin
renderer.capturePhoto { bitmap -> /* lưu/hiển thị (gọi trên GL thread) */ }
renderer.startRecording(file.absolutePath, withAudio = true)  // cần quyền RECORD_AUDIO; tự fallback video-only
renderer.stopRecording()
```

### 5.4. Giải phóng tài nguyên
- Rời camera cho hẳn (Activity finishing) → drain executor → `releaseLive()` (xem §5.3).
- Rời màn ảnh tĩnh → `releasePhoto()` (vd trong `ViewModel.onCleared`).
- Bitmap kết quả: app tự quản lý/`recycle` khi xong; **đừng** recycle source khi đang `applyOnPhoto`.
- Lần dùng lại sau release: engine **tự re-init** (~0.35–0.8s) ở `ensure*` kế tiếp.

### 5.5. Mẫu retry khi OOM ảnh lớn
```kotlin
suspend fun applySafe(ctx: Context, src: Bitmap, req: BeautifyRequest): DyeResult {
    var bmp = src
    repeat(3) {
        val r = withContext(Dispatchers.Default) { VenusEngine.applyOnPhoto(ctx, bmp, req) {} }
        if (r.ok || !r.log.contains("bộ nhớ", true)) return r
        bmp = bmp.scaleDown(0.5f)   // giảm 1/2 rồi thử lại
    }
    return DyeResult(null, false, "ảnh quá lớn")
}
```

---

## 6. Mở rộng sang makeup khác (môi/mắt/má/…)

Engine hỗ trợ **toàn bộ makeup** — 24 carrier `clgpuimage` đã có sẵn (KHÔNG strip). Hiện wrap **hair + skin**.
Thêm feature = 3 bước:

1. **Tìm cờ + tham số native.** Photo: thêm setter trên `UIVenusPipelineSettings` trước `GetMakeupImage`
   (hair=`I1`+`w0`, skin=`c1`, foundation=`H1`+`v0`). Live: bật cờ trong `c0` (SetMakeupParameters, 44 args).
   Tham chiếu `decompiler/.../kernelctrl/Stylist.java` + `com/pf/makeupcam/camera/ApplyEffectCtrl.java`.
2. **Đọc metadata carrier (live).** `GetMakeupMetadata` (`l.t`) đã nhận **26 mảng** prefill non-null trong
   `ensureLiveReady`; đọc đúng slot:

| Feature | Carrier | Slot | Photo setter |
|---|---|---|---|
| Hair dye ✅ | `CLMakeupLiveHairDyeFilter$HairDyeData` | 18 | `w0` |
| Skin smooth | `CLMakeupLiveSmoothFilter$LiveSmoothMetadata` | 7 | `c1` |
| Foundation | `CLMakeupLiveFoundationFilter$LiveFoundationData` | 9 | `H1`+`v0` |
| Lipstick / Lipliner | `…LipStickFilter` / `…LiplinerFilter` | 4 / 5 | (dò) |
| Eye makeup / contact / sparkle | `…EyeFilter` / `…EyeContactFilter` / `…EyeSparkleFilter` | 3 / 1 / 2 | (dò) |
| Blush / Bronzer / Nose / Contour / Cheeks | `…Blush3D`/`…Bronzer`/`…NoseShadow`/`…FaceContour`/`…AppleCheeks` | 6/21/22/23/24 | (dò) |
| Reshape / Distortion | `…FaceReshapeFilter` / `…FaceDistortionFilter` | 10 / 11 | (dò) |
| 3D object / earring / eyebrow(+warp) | `…3DFilter` / `…3DEyebrowFilter` / `…WarpFilter` | 13·14 / 19 / 20 | (dò) |
| Face art / teeth / sticker / eyewear | `…FaceArt`/`…TeethWhiten`/`CLStickerLiveFilter`/`…CubeEyewear` | 15/25/17/26 | (dò) |

3. **Render.** Photo: miễn phí trong `GetMakeupImage` (đường khuyến nghị bắt đầu). Live: cần port **GL filter**
   tương ứng (như `HairDyeGLRenderer`). ⚠️ `CLMakeupLiveSmoothFilter` GL thiếu trong dump → mịn-da-live phải
   tự dựng shader (ref `fxlib/BodySkinSmoothFilter.java`).

> 📌 Mỗi feature mới = 1 spike (dò setter + verify máy thật). Bắt đầu bằng **đường photo** (rẻ nhất).

---

## 7. Kiểm thử / hiệu năng

- **35 unit test JVM** (`:venus-sdk:testDebugUnitTest`, không cần device).
- **7 benchmark** trên máy ARM thật (`:venus-benchmark`).
- Chi tiết + baseline + bug đã fix: [test-overview.md](test-overview.md) · kế hoạch 3 tầng: [TESTING.md](TESTING.md).
