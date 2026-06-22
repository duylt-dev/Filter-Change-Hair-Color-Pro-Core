# venus-benchmark androidTest assets

Drop a real face photo here named **`sample_face.jpg`** before running the benchmarks.

- Must contain exactly one clearly-visible, well-lit face (frontal works best) — the engine's hair/face
  detector has to find it, otherwise `applyOnPhoto` returns `ok=false` and the benchmark fails fast.
- Any resolution ≥ 1280px on the long edge is fine; `BenchSupport.loadSampleFace` rescales per test
  (1280px for photo apply, 480px for the live-frame path).
- Keep it out of version control if it shows a real person; a stock/CC0 portrait is ideal.

Without this file every benchmark **skips** (via `Assume`) instead of failing — so CI on a device
that lacks the image stays green, it just reports "assumption failed".
