# venus-sdk — Test & Hiệu năng

> Cập nhật 2026-06-22. Phủ test cho module `venus-sdk` (engine wrapper) — đặc biệt **hiệu năng**.
> **Trạng thái:** Tầng 1 ✅ (35 test JVM pass) · Tầng 2 ⏳ (chưa viết) · Tầng 3 ✅ (7 benchmark đã chạy
> thật trên SM‑A165F). Tổng quan kết quả + bug đã fix: [benmarch.md](benmarch.md).

## 0. Ràng buộc quyết định cách test

- Engine = **native + Android framework** (Bitmap/GL/MediaCodec) → **phần lớn KHÔNG unit-test được trên JVM**.
- `.so` chỉ có **arm64-v8a / armeabi-v7a** → instrumented/benchmark phải chạy **máy thật ARM hoặc emulator Apple
  Silicon (arm)**; **KHÔNG chạy CI x86 thường**. Engine cần **API ≥ 26**.
- ⟹ Chia 3 tầng: **(1) JVM unit** (logic thuần) · **(2) Instrumented correctness** (engine đúng) ·
  **(3) Benchmark hiệu năng** (đo thời gian). "Unit test hiệu năng" = tầng 3 (Microbenchmark), KHÔNG phải JVM.

---

## Tầng 1 — JVM unit test (`src/test/`, chạy mọi nơi, không cần device)

Chỉ phủ **logic thuần Kotlin** (không native/Android):

| Lớp | Test cần viết | Khẳng định |
|---|---|---|
| `model.HairColor` | `argb` ghép đúng | `HairColor(_,0x6F,0x3D,0x1B).argb == 0xFF6F3D1B.toInt()` |
| `model.HairPalette` | palette hợp lệ | size > 0; mọi r/g/b ∈ 0..255; name không rỗng; không trùng |
| `model.HairMode/LiveMode` | enum đủ giá trị | chứa SINGLE/OMBRE/TWO_COLOR/MULTI … (chống xoá nhầm) |
| `util.YuvConverter` ✅ | NV21 size + RGBA→NV21 BT.601 + evenFloor | size đúng cả **dim lẻ** (chống crash AIOOBE), Y/V/U BT.601 đúng số, reject buffer thiếu |
| `engine.EngineSupport` ✅ | gate API≥26 + arm ABI (pure) | **API 24/25 → unsupported**, **armeabi-v7a → supported**, x86 → unsupported, lý do đúng |
| `engine.ModelProvisioner.needsProvision` ✅ | quyết định re-copy | re-provision khi thiếu/rỗng/**truncated**; skip khi size khớp |
| `VenusEngine.BeautifyRequest.hasAny` (gợi ý thêm) | điều kiện bật | hair=null+skin=0+pattern=null → false; mode=MULTI+pattern → true |

> `VenusEngine.isEngineSupported` đọc `Build.VERSION`/`SUPPORTED_ABIS` → cần **Robolectric** mới test JVM
> (tùy chọn, thêm sau).

> **NV21 (BT.601) ĐÃ tách hàm thuần** `com.piontech.venussdk.util.YuvConverter.rgbaToNv21` (+ `nv21Size`,
> `evenFloor`) — nguồn DUY NHẤT, có validate đầu vào (reject buffer thiếu thay vì crash). `CameraActivity`
> (production) **và** benchmark `BenchSupport` đều gọi hàm này; không còn bản copy tay. Đây chính là chỗ
> bug off-by-one (dim lẻ → `ArrayIndexOutOfBoundsException`) đã lọt vào bản copy của benchmark → nay khoá
> bằng `YuvConverterTest` (15 case). `computeCoverScale` (renderer, GL) vẫn ở app — tách sau nếu cần.

✅ **Đã có:** `model/HairColorTest` (5) + `util/YuvConverterTest` (15) + `engine/EngineSupportTest` (9) +
`engine/ModelProvisionerTest` (6) = **35 test JVM pass**. Chạy: `:venus-sdk:testDebugUnitTest` (không cần device).

---

## Tầng 2 — Instrumented correctness (`src/androidTest/`, cần máy ARM API≥26) — ⏳ CHƯA VIẾT

Xác minh engine **chạy đúng** (không đo tốc độ). Cần 1 ảnh mặt mẫu trong `androidTest/assets/`.
*(Hiện tính đúng đắn được verify gián tiếp qua benchmark + spike máy thật — xem [test-overview.md](test-overview.md).)*

| Test | Bước | Pass khi |
|---|---|---|
| `isEngineSupported` | gọi getter | true trên máy ARM API≥26 |
| `ensurePhotoReady` | init | trả `true` trong < timeout; `isPhotoReady==true` |
| `applyOnPhoto` SINGLE | dye 1 màu trên ảnh mẫu | `ok==true`, bitmap != null, **% pixel đổi > 3%** (đúng vùng tóc) |
| `applyOnPhoto` OMBRE/TWO_COLOR/MULTI | từng mode | `ok==true`, bitmap != null; MULTI cần pattern bitmap |
| `applyOnPhoto` skin (smooth+whiten) | chỉ da | `ok==true`, % đổi > 0 |
| `applyOnPhoto` no-face | ảnh không mặt | `ok==false`, log "không tìm thấy khuôn mặt" |
| `ensureLiveReady` + `processLiveFrame` | feed NV21 từ ảnh mẫu | sau vài frame trả `HairDyeData != null`, mask non-zero |
| `ModelProvisioner` | provision | 7 file tồn tại ở `filesDir/venus_model_6`, size > 0 |

> Tái dùng logic `DevSelfTest` cũ (đã xoá) làm khung. Engine là **singleton** → chạy tuần tự (`@Test` order hoặc
> 1 class), tránh init song song. Đánh dấu `@RequiresDevice` / skip nếu `!isEngineSupported`.

---

## Tầng 3 — BENCHMARK hiệu năng (AndroidX Microbenchmark, máy ARM, **trọng tâm**)

Module/biến thể benchmark riêng dùng `androidx.benchmark:benchmark-junit4` (đo nhiều lần, loại JIT/warmup,
xuất p50/p90/min). ✅ **Đã bootstrap module `:venus-benchmark`** (`com.android.library` + `BenchmarkRule`,
`testBuildType=release`) với 7 benchmark (3 class), **đã chạy thật trên SM‑A165F** — bảng số bên dưới. Đo:

**Baseline đã đo — SM‑A165F (Exynos 1330, Android 16/API 36), release, 2026‑06‑22** (median, [min–max]):

| Phép đo | Cách | Baseline ĐÃ ĐO (SM‑A165F) | Ngưỡng đề xuất (cảnh báo nếu vượt) |
|---|---|---|---|
| Engine init PHOTO (`ensurePhotoReady`) | đo 1 lần lạnh (release+reinit) | **757 ms** [735–839] | < 1500 ms ✅ |
| Engine init LIVE (`ensureLiveReady`) | đo 1 lần lạnh | **354 ms** [336–364] (model đã ở đĩa) | < 1500 ms ✅ |
| `applyOnPhoto` SINGLE @1280px | loop trên ảnh mẫu đã init | **486 ms** [424–524] | < 700 ms |
| `applyOnPhoto` skin (smooth+whiten) @1280px | chỉ da | **364 ms** [319–446] | < 600 ms |
| `applyOnPhoto` MULTI @1280px | **GL path** (live mask + offscreen shader) | **~511 ms** [410–590] ok=true ✅ | < 800 ms |
| `processLiveFrame` @480px | loop nhiều frame NV21 | **75.9 ms/frame** [58–92] ≈ **13 fps** | < 120 ms (≥ ~8fps) ✅ |
| Provision model (copy 7 file ~17MB) | lần đầu, xoá dir mỗi vòng | **161 ms** [151–173] | < 1000 ms ✅ |

> Cách chạy đã chốt cho máy retail API≥33 (xem build.gradle.kts): khai báo `WRITE_EXTERNAL_STORAGE` **không
> cap** (BenchmarkRule ép grant), `additionalTestOutputDir`/`output.enable=false`, `suppressErrors=UNLOCKED`.
> Máy đời thấp **bị thermal-throttle** → benchmark tự chèn nghỉ 90s; chạy đủ 7 test ~17 phút (lọc 1 class để nhanh).
> `count=1` (mỗi op nặng ~0.4–0.8s nên ít vòng đo) — số mang tính baseline, không phải p50 độ tin cao.

**🔬 Spike MULTI `ok=false` (2026-06-22, đã lần ra gốc):** log từng bước cho thấy
`AnalyzeImage faces=1 ✅ → DeepDetectHairDyeMask ok=true ✅ → multiColorMode=2 ✅ →`
`E/UIVenusPhoto-jni: [setupHairFullColor] hair dye full color bindImageMaskBuffer fail → GetMakeupImage ok=false`.
**Gốc:** full-color cần **bind ảnh pattern thành image buffer**; native `CUIVenusPhoto_setupHairFullColor` có symbol
trong `.so` nhưng **wrapper Java/SWIG bị rớt khỏi jniproxy decompiled** → đẩy file-path vào `cache` không bind được
(BGR/đổi định dạng vô ích).
**✅ ĐÃ SỬA theo hướng (b):** `VenusEngine.applyFullColorPhoto` lấy hair-mask từ **live engine**
(`processLiveFrame`) rồi composite pattern **offscreen GL** ([PhotoHairDyeRenderer], tái dùng shader live đã verify).
`applyOnPhoto(MULTI, pattern)` tự route sang đây. Đã chạy thật trên SM‑A165F: **ok=true, ~511ms**, mask 512×512.
*(Còn lại: kiểm thị giác pattern lên tóc — cần mắt người; perf đã đo.)*

**Lưu ý đo đúng:**
- Engine **singleton + native cache** → init đo **riêng** (không đưa vào vòng lặp `measureRepeated`).
- `applyOnPhoto`/`processLiveFrame`: init 1 lần trước, chỉ loop phần xử lý; **dùng lại** 1 ảnh/đệm cố định.
- Loại I/O ra khỏi vòng đo (provision model, decode ảnh) — làm ở setup.
- Khác máy → khác số: gắn baseline theo **model thiết bị** (vd SM‑A165F), không so tuyệt đối giữa máy.

---

## Setup (deps & cách chạy)

1. **JVM unit (Tầng 1) — đã bật:** `venus-sdk/build.gradle.kts` thêm `testImplementation(libs.junit)` +
   `src/test/java/com/piontech/venussdk/model/HairColorTest.kt`. Chạy:
   `JAVA_HOME=<JBR> ./gradlew :venus-sdk:testDebugUnitTest`.
2. **Instrumented (Tầng 2):** thêm `androidTestImplementation` androidx.test (`runner`, `ext:junit`),
   `src/androidTest/...` + `androidTest/assets/sample_face.jpg`. Chạy trên máy ARM:
   `./gradlew :venus-sdk:connectedDebugAndroidTest`.
3. **Benchmark (Tầng 3) — ✅ đã dựng:** module `:venus-benchmark` là **`com.android.library`** (KHÔNG phải
   `com.android.test` — đó là Macrobenchmark cần app đích) + `benchmark-junit4` trong `androidTest`, `minSdk 26`,
   abiFilters arm, `testBuildType=release`. **Không** áp Gradle plugin `androidx.benchmark` (bản 1.3.4 đòi
   `TestedExtension` đã bị AGP 9 bỏ → fail); `BenchmarkRule` vẫn chạy chuẩn khi build release non-debuggable.
   - **Cần:** bỏ ảnh mặt thật `venus-benchmark/src/androidTest/assets/sample_face.jpg` (xem README cạnh đó);
     thiếu ảnh → mọi benchmark **skip** qua `Assume` (không fail).
   - Benchmark có: `EngineInitBenchmark` (photo/live cold init + provision model), `PhotoApplyBenchmark`
     (SINGLE/MULTI/skin @1280px), `LiveFrameBenchmark` (`processLiveFrame` @480px).
   - Chạy trên máy ARM API≥26: `./gradlew :venus-benchmark:connectedReleaseAndroidTest` → đọc report
     `venus-benchmark/build/outputs/...` (json + Logcat p50/p90/min). Init/provision đo **cold** bằng cách
     release/xoá trong `runWithTimingDisabled` trước mỗi vòng đo.
4. **CI:** Tầng 1 chạy CI thường. Tầng 2/3 cần **device farm ARM** (Firebase Test Lab ARM / máy thật) — không
   chạy được trên emulator x86 mặc định.

---

## Thứ tự đề xuất
1. Tầng 1 (đã bootstrap) → mở rộng thêm các case logic.
2. Tầng 2 trên 1 máy ARM → chốt engine đúng sau mỗi thay đổi SDK.
3. Tầng 3 → lập **baseline** số liệu, gắn ngưỡng hồi quy; chạy khi đụng vào path xử lý (apply/frame).
