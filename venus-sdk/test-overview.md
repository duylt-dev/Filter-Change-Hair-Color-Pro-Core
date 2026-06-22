# venus-sdk — Tổng quan Unit Test, Benchmark & các Fix

> Cập nhật 2026-06-22. Thiết bị thật: **SM‑A165F** (Exynos 1330, Android 16 / API 36, arm64-v8a), build `release`.
> Chi tiết kế hoạch test: [TESTING.md](TESTING.md) · Engine: [../extract_apk/overview.md](../extract_apk/overview.md)

---

## 0. TL;DR

- **35/35 unit test JVM PASS** (chạy mọi nơi, không cần device) — phủ logic thuần dễ gây crash.
- **7 benchmark hiệu năng** chạy thật trên SM‑A165F — có baseline đầy đủ.
- **5 bug đã fix** (1 crash production thật, 3 nhóm robustness, 1 lỗi tính năng) + **2 tính năng hoàn thiện** (MULTI full-color, thumbnail).
- Mọi thứ verify lại bằng test/máy thật sau khi sửa.

---

## 1. Unit test JVM (Tầng 1) — `:venus-sdk:testDebugUnitTest`

Chạy trên JVM, **không cần thiết bị**, vào được CI thường. Chỉ phủ **logic thuần** (không native/Android).

| Test class | Số test | Phủ gì | Chống bug gì |
|---|---:|---|---|
| `model.HairColorTest` | 5 | ghép ARGB, palette hợp lệ, enum mode đủ | xoá nhầm enum, sai màu swatch |
| `util.YuvConverterTest` | 15 | NV21 size + RGBA→NV21 (BT.601) + `evenFloor` | **crash `ArrayIndexOutOfBounds`** khi dim lẻ; lệch màu; buffer thiếu |
| `engine.EngineSupportTest` | 9 | gate API≥26 + arm ABI (thuần) | engine chạy nhầm trên **API 24/25** hoặc **x86** → crash |
| `engine.ModelProvisionerTest` | 6 | quyết định re-provision model | giữ model **truncated** → engine load fail vĩnh viễn |
| **Tổng** | **35** | | |

**Cách chạy:**
```bash
JAVA_HOME=<JBR> ./gradlew :venus-sdk:testDebugUnitTest
```

> Vì sao chỉ 35 test JVM: engine = **native + Android framework** (Bitmap/GL/MediaCodec) → phần lớn KHÔNG
> unit-test được trên JVM. Chiến lược: tách **hàm thuần** ra SDK rồi test (vd `YuvConverter`, `EngineSupport`,
> `ModelProvisioner.needsProvision`); phần native verify bằng benchmark/spike trên máy thật.

---

## 2. Benchmark hiệu năng (Tầng 3) — `:venus-benchmark`

Module `com.android.library` + `androidx.benchmark:benchmark-junit4`, `testBuildType=release`, `minSdk 26`,
abiFilters arm. Đo trên máy ARM thật.

**Baseline SM‑A165F (median, [min–max]):**

| Phép đo | Kết quả | Ngưỡng đề xuất | Nhận xét |
|---|---|---|---|
| Init engine PHOTO (`ensurePhotoReady`) | **757 ms** [735–839] | < 1500 ms ✅ | tốt hơn baseline cũ ~900ms |
| Init engine LIVE (`ensureLiveReady`) | **354 ms** [336–364] | < 1500 ms ✅ | model đã ở đĩa |
| Provision model (copy 7 file ~17MB) | **161 ms** [151–173] | < 1000 ms ✅ | I/O nhẹ |
| `applyOnPhoto` SINGLE @1280px | **486 ms** [424–524] | < 700 ms | baseline ảnh tĩnh |
| `applyOnPhoto` skin (mịn+trắng) @1280px | **364 ms** [319–446] | < 600 ms | rẻ hơn dye tóc ~25% |
| `applyOnPhoto` MULTI @1280px (GL) | **511 ms** [410–590] | < 800 ms | full-color qua GL (xem §4) |
| `processLiveFrame` @480px | **75.9 ms/frame ≈ 13 fps** [58–92] | < 120 ms ✅ | đúng kỳ vọng 10–15fps |

**Cách chạy** (máy ARM API≥26):
```bash
./gradlew :venus-benchmark:connectedReleaseAndroidTest
# lọc 1 class cho nhanh:
./gradlew :venus-benchmark:connectedReleaseAndroidTest \
  -Pandroid.testInstrumentationRunnerArguments.class=com.piontech.venusbenchmark.LiveFrameBenchmark
```

**Lưu ý đo:**
- Init/provision đo **cold** (release/xoá trong `runWithTimingDisabled` trước mỗi vòng).
- Cấu hình chạy máy retail API≥33: khai báo `WRITE_EXTERNAL_STORAGE` **không cap** (BenchmarkRule ép grant).
- Máy đời thấp **bị thermal-throttle** → benchmark tự nghỉ 90s/lần; chạy đủ 7 test ~17 phút.
- `count=1` (mỗi op nặng ~0.4–0.8s) → số mang tính **baseline**, không phải p50 độ tin cao; máy mạnh hơn sẽ ổn định hơn.

---

## 3. Bug đã tìm thấy & FIX (nguyên tắc: test phát hiện → fix → verify lại)

### 3.1. 🔴 CRASH production: use-after-free khi rời camera
- **Hiện tượng:** out camera → home/back → `Fatal signal 6 (SIGABRT)` tại `TrackYUV420Biplanar` (libvenus),
  trên thread `pool-…` (camera analysis).
- **Gốc:** `CameraActivity.onDestroy` gọi `super.onDestroy()` **trước** — chính nó trigger
  `ViewModelStore.clear()` → `CameraViewModel.onCleared()` → `releaseLive()` **ngay**, trong khi 1 frame còn
  đang chạy native → engine bị free dưới chân nó.
- **Fix (2 lớp):**
  1. `onDestroy`: drain `analysisExecutor` (shutdown + await) **TRƯỚC** `super.onDestroy()`.
  2. `VenusEngine.processLiveFrame` thêm `@Synchronized` (chung monitor với `releaseLive`) → release không
     chạy giữa frame; frame sau release thấy `live==null` → return.
- **Verify máy thật:** 3× BACK + HOME/re-enter (engine re-init) → hết crash.

### 3.2. NV21 off-by-one → `ArrayIndexOutOfBounds`
- **Phát hiện:** benchmark `processLiveFrame` crash ở setup khi ảnh có **kích thước lẻ** (chroma plane thiếu 1 cặp).
- **Gốc sâu hơn:** logic NV21 **bị copy 2 nơi** (app + helper) **không có test** → 1 bản copy sai.
- **Fix:** gom về 1 hàm thuần `util.YuvConverter.rgbaToNv21` (+ `nv21Size`, `evenFloor`) có **validate đầu vào**;
  `CameraActivity` (production) và benchmark đều gọi hàm này. Khoá bằng 15 test (gồm ca dim lẻ).

### 3.3. Robustness chống crash (đã thêm guard)
- **Model truncated/thiếu/rỗng** → `ModelProvisioner` copy **atomic** (tmp + rename) + re-provision khi sai size;
  `needsProvision` có unit test.
- **OOM / bitmap recycled** trong `applyOnPhoto` → bọc try/catch quanh cấp bitmap + guard → trả `DyeResult`
  thay vì crash.
- **NV21 frame méo → native OOB** → `processLiveFrame` drop frame nếu buffer < `nv21Size`.
- **Ảnh không có mặt** → trả `ok=false` ("Không tìm thấy khuôn mặt"), không crash.

### 3.4. Engine-support gate test được (armeabi-v7a + API 24/25)
- Không có máy v7a/API24/25 thật → tách `EngineSupport.isSupported(sdkInt, abis)` thuần + `unsupportedReason`,
  phủ bằng `EngineSupportTest` (API 24/25 → unsupported; v7a + API≥26 → supported; x86 → unsupported).

---

## 4. Tính năng hoàn thiện (spike + verify máy thật)

### 4.1. MULTI full-color ảnh tĩnh — qua GL (hướng b)
- **Bug:** native `GetMakeupImage` MULTI trả `ok=false` — log `[setupHairFullColor] bindImageMaskBuffer fail`.
  Gốc: wrapper JNI `CUIVenusPhoto_setupHairFullColor` **bị rớt khỏi jniproxy decompiled** → không bind được
  pattern (BGR/đổi định dạng vô ích). RE chữ ký SWIG = rủi ro crash → **không đoán**.
- **Giải pháp (b):** `VenusEngine.applyFullColorPhoto` lấy hair-mask từ **live engine** (`processLiveFrame`)
  rồi composite pattern **offscreen GL** (`PhotoHairDyeRenderer`, tái dùng shader live đã verify).
  `applyOnPhoto(MULTI, pattern)` tự route sang đây.
- **Verify máy thật:** `ok=true`, mask 512×512, **~511 ms** @1280px. *(Còn: kiểm thị giác pattern lên tóc.)*

### 4.2. GenerateHairDyeThumbnail
- `VenusEngine.generateHairThumbnail(ctx, color, size)`: tạo base xám (gradient + sợi) → attach buffer →
  native `m()` recolor **in-place** → detach → bitmap đã đổi màu. Không cần khuôn mặt.
- **Verify máy thật:** `rc=0`, swatch đỏ mean RGB **(156,48,48)**. *(App có thể thay ô màu phẳng bằng hàm này.)*

---

## 5. Trạng thái file test

```
venus-sdk/src/test/java/com/piontech/venussdk/
  model/HairColorTest.kt              (5)
  util/YuvConverterTest.kt            (15)
  engine/EngineSupportTest.kt         (9)
  engine/ModelProvisionerTest.kt      (6)

venus-benchmark/src/androidTest/java/com/piontech/venusbenchmark/
  BenchSupport.kt                     (fixtures: sample face, NV21 qua YuvConverter)
  EngineInitBenchmark.kt              (photo/live cold init + provision)
  PhotoApplyBenchmark.kt              (SINGLE / MULTI(GL) / skin @1280px)
  LiveFrameBenchmark.kt               (processLiveFrame @480px)
  ThumbnailSpikeTest.kt               (verify thumbnail recolor)
```

---

## 6. Còn lại (không chặn)
- **Kiểm thị giác:** pattern MULTI lên tóc + swatch thumbnail có đẹp không (cần mắt người; perf/pipeline đã ok).
- **Tầng 2 instrumented correctness** (engine init/apply/frame, ModelProvisioner) — chưa viết.
- **Baseline p50 ổn định hơn** trên máy mát/tầm trung-cao.
- **Wire UI app:** dùng `generateHairThumbnail()` + MULTI ảnh tĩnh trong màn Photo.
- Dọn orphan `clip_*.mp4` khi background lúc đang quay.
