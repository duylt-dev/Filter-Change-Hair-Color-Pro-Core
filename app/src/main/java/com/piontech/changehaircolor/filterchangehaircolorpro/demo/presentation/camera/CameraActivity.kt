package com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.camera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.GradientDrawable
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.util.Size
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.R
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.databinding.ActivityCameraBinding
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.common.HairPatterns
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.common.hideSystemNavBar
import com.piontech.venussdk.gl.HairDyeGLRenderer
import com.piontech.venussdk.model.HairPalette
import com.piontech.venussdk.util.BitmapTransforms
import com.piontech.venussdk.util.YuvConverter
import com.piontech.venussdk.model.LiveMode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.Executors

/**
 * Live camera screen (MVVM). The Activity owns the GL renderer + CameraX (View concerns); the
 * engine init / color config / per-frame mask / saving live in [CameraViewModel] → domain → SDK.
 */
@AndroidEntryPoint
class CameraActivity : AppCompatActivity() {

    private val viewModel: CameraViewModel by viewModels()
    private lateinit var binding: ActivityCameraBinding
    private lateinit var renderer: HairDyeGLRenderer

    private val tabButtons = ArrayList<Button>()
    private var slot1Btn: Button? = null
    private var slot2Btn: Button? = null

    private var lensFacing = CameraSelector.LENS_FACING_FRONT
    private val analysisExecutor = Executors.newSingleThreadExecutor()

    private val patterns = HairPatterns.full.zip(HairPatterns.thumbs)
    private val tabs = listOf(
        LiveMode.SINGLE to R.string.mode_single, LiveMode.OMBRE to R.string.mode_ombre, LiveMode.MULTI to R.string.mode_multi
    )

    // render bookkeeping (avoid rebuilding the swatch row / reloading patterns every emission)
    private var builtMode: LiveMode? = null
    private var builtPattern = -1

    private var frameCount = 0
    private var lastFpsTime = 0L
    private var recordFile: File? = null

    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            when {
                result[Manifest.permission.CAMERA] == true -> startCamera()
                result.containsKey(Manifest.permission.CAMERA) ->
                    binding.cameraStatus.text = getString(R.string.camera_permission_needed)
            }
        }

    private fun hasPermission(p: String) =
        ContextCompat.checkSelfPermission(this, p) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSystemNavBar()
        binding.cameraGl.setEGLContextClientVersion(2)
        // Keep GL context across background so textures/program survive (no filter loss on resume).
        // If the system drops it anyway, the renderer rebuilds in onSurfaceCreated (uniform cache cleared).
        binding.cameraGl.preserveEGLContextOnPause = true
        renderer = HairDyeGLRenderer()
        binding.cameraGl.setRenderer(renderer)
        binding.cameraGl.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY

        binding.flipButton.setOnClickListener { toggleLens() }
        binding.captureButton.setOnClickListener {
            renderer.capturePhoto { bmp -> viewModel.savePhoto(bmp) { ok -> toast(getString(if (ok) R.string.toast_image_saved else R.string.toast_image_save_failed)) } }
        }
        binding.recordButton.setOnClickListener { toggleRecording() }

        binding.shineSeek.progress = viewModel.state.value.shinePct
        binding.alphaSeek.progress = viewModel.state.value.alphaPct
        binding.shineSeek.onChange { viewModel.setShine(it) }
        binding.alphaSeek.onChange { viewModel.setAlpha(it) }

        buildTabs()
        buildSlots()
        observeState()

        viewModel.prepareEngineAsync()

        val camGranted = hasPermission(Manifest.permission.CAMERA)
        if (camGranted) startCamera()
        val toRequest = buildList {
            if (!camGranted) add(Manifest.permission.CAMERA)
            if (!hasPermission(Manifest.permission.RECORD_AUDIO)) add(Manifest.permission.RECORD_AUDIO)
        }
        if (toRequest.isNotEmpty()) requestPermissions.launch(toRequest.toTypedArray())
    }

    override fun onResume() { super.onResume(); if (::binding.isInitialized) binding.cameraGl.onResume() }

    override fun onPause() {
        if (::binding.isInitialized) {
            // Flush an in-progress recording before the GL thread pauses, else MediaCodec/AudioRecord/
            // muxer leak and the temp .mp4 is left half-written. requestRender lets the GL thread run
            // the encoder.finish() path on the next draw.
            if (renderer.isRecording) { renderer.stopRecording(); binding.cameraGl.requestRender() }
            binding.cameraGl.onPause()
        }
        super.onPause()
    }

    override fun onDestroy() {
        // Stop feeding frames and WAIT for any in-flight processFrame to finish BEFORE super.onDestroy().
        // super.onDestroy() dispatches ON_DESTROY, which clears the ViewModelStore -> CameraViewModel
        // .onCleared() -> releaseLive() SYNCHRONOUSLY. If a camera frame is still inside the native
        // TrackYUV420Biplanar at that moment, the engine is freed under it -> SIGABRT (use-after-free).
        // Draining the executor first guarantees no native call is in flight when the engine is released.
        // (VenusEngine.processLiveFrame is also @Synchronized with releaseLive as a second line of defense.)
        analysisExecutor.shutdown()
        try { analysisExecutor.awaitTermination(2, java.util.concurrent.TimeUnit.SECONDS) } catch (_: InterruptedException) {}
        super.onDestroy()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemNavBar()
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { render(it) }
            }
        }
    }

    private fun render(s: CameraUiState) = with(binding) {
        cameraStatus.text = s.statusText
        renderer.setEnabled(s.recolorOn)

        tabButtons.forEachIndexed { i, b ->
            val sel = tabs[i].first == s.liveMode
            b.setTypeface(null, if (sel) android.graphics.Typeface.BOLD else android.graphics.Typeface.NORMAL)
            b.alpha = if (sel) 1f else 0.55f
        }
        val twoColor = s.liveMode == LiveMode.GRADIENT || s.liveMode == LiveMode.OMBRE
        slotRow.visibility = if (twoColor) View.VISIBLE else View.GONE
        alphaRow.visibility = if (s.liveMode == LiveMode.SINGLE) View.VISIBLE else View.GONE
        slot1Btn?.let { it.setBackgroundColor(s.color1.argb); it.alpha = if (s.activeSlot == 1) 1f else 0.5f }
        slot2Btn?.let { it.setBackgroundColor(s.color2.argb); it.alpha = if (s.activeSlot == 2) 1f else 0.5f }

        // pattern + swatch row only when mode/pattern actually changed
        if (s.liveMode != builtMode || s.patternIndex != builtPattern) {
            renderer.setFullColorPattern(if (s.liveMode == LiveMode.MULTI) loadPattern(s.patternIndex) else null)
            buildSwatchRow(s)
            builtMode = s.liveMode; builtPattern = s.patternIndex
        }
    }

    private fun loadPattern(i: Int): Bitmap? = try {
        assets.open(patterns[i].first).use { BitmapFactory.decodeStream(it) }
    } catch (t: Throwable) { null }

    // ---------------- UI builders ----------------

    private fun buildTabs() {
        tabButtons.clear(); binding.tabRow.removeAllViews()
        tabs.forEach { (mode, labelRes) ->
            val b = Button(this).apply {
                text = getString(labelRes); textSize = 11f
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                setOnClickListener { viewModel.setMode(mode) }
            }
            tabButtons.add(b); binding.tabRow.addView(b)
        }
    }

    private fun buildSlots() {
        binding.slotRow.removeAllViews()
        val d = resources.displayMetrics.density
        fun slot(label: String, slot: Int) = Button(this).apply {
            text = label; textSize = 10f
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (34 * d).toInt())
                .apply { marginEnd = (6 * d).toInt() }
            setOnClickListener { viewModel.setActiveSlot(slot) }
        }
        slot1Btn = slot(getString(R.string.slot_color1), 1); slot2Btn = slot(getString(R.string.slot_color2), 2)
        binding.slotRow.addView(slot1Btn); binding.slotRow.addView(slot2Btn)
    }

    private fun buildSwatchRow(s: CameraUiState) {
        binding.cameraSwatchRow.removeAllViews()
        val d = resources.displayMetrics.density
        val size = (44 * d).toInt(); val margin = (3 * d).toInt()
        fun lp() = LinearLayout.LayoutParams(size, size).apply { setMargins(margin, 0, margin, 0) }
        if (s.liveMode == LiveMode.MULTI) {
            patterns.forEachIndexed { i, (_, thumb) ->
                val iv = ImageView(this).apply {
                    layoutParams = lp(); scaleType = ImageView.ScaleType.CENTER_CROP
                    alpha = if (i == s.patternIndex) 1f else 0.6f
                    contentDescription = getString(R.string.mode_multi) + " " + (i + 1)
                    try { assets.open(thumb).use { setImageBitmap(BitmapFactory.decodeStream(it)) } } catch (_: Throwable) {}
                    setOnClickListener { viewModel.setPattern(i) }
                }
                binding.cameraSwatchRow.addView(iv)
            }
            return
        }
        Button(this).apply {
            text = getString(R.string.off); textSize = 9f; layoutParams = lp()
            setOnClickListener { viewModel.turnOff() }
            binding.cameraSwatchRow.addView(this)
        }
        HairPalette.colors.forEach { color ->
            Button(this).apply {
                background = GradientDrawable().apply { setColor(color.argb); cornerRadius = 6 * d }
                layoutParams = lp()
                contentDescription = color.name
                setOnClickListener { viewModel.selectColor(color) }
                binding.cameraSwatchRow.addView(this)
            }
        }
    }

    private inline fun SeekBar.onChange(crossinline cb: (Int) -> Unit) =
        setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(s: SeekBar?, p: Int, fromUser: Boolean) { if (fromUser) cb(p) }
            override fun onStartTrackingTouch(s: SeekBar?) {}
            override fun onStopTrackingTouch(s: SeekBar?) {}
        })

    private fun toggleLens() {
        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_FRONT)
            CameraSelector.LENS_FACING_BACK else CameraSelector.LENS_FACING_FRONT
        startCamera()
    }

    private fun toast(m: String) = runOnUiThread { Toast.makeText(this, m, Toast.LENGTH_SHORT).show() }

    // ---------------- recording ----------------

    private fun toggleRecording() {
        if (!renderer.isRecording) {
            if (!viewModel.state.value.engineReady) { toast(getString(R.string.toast_engine_not_ready)); return }
            recordFile = File(getExternalFilesDir(null), "clip_${System.currentTimeMillis()}.mp4")
            renderer.startRecording(recordFile!!.absolutePath, withAudio = hasPermission(Manifest.permission.RECORD_AUDIO))
            binding.recordButton.text = getString(R.string.record_stop); toast(getString(R.string.toast_recording))
        } else {
            renderer.stopRecording(); binding.recordButton.text = getString(R.string.record)
            val f = recordFile
            Thread {
                var tries = 0
                while (renderer.isRecording && tries++ < 100) Thread.sleep(50)
                if (f != null && f.exists() && f.length() > 0)
                    viewModel.saveVideo(f) { ok -> toast(getString(if (ok) R.string.toast_video_saved else R.string.toast_video_save_failed)) }
                else toast(getString(R.string.toast_record_failed_empty))
            }.start()
        }
    }

    // ---------------- camera ----------------

    private fun startCamera() {
        val future = ProcessCameraProvider.getInstance(this)
        future.addListener({
            val provider = future.get()
            val resSelector = ResolutionSelector.Builder()
                .setResolutionStrategy(
                    ResolutionStrategy(Size(640, 480), ResolutionStrategy.FALLBACK_RULE_CLOSEST_LOWER_THEN_HIGHER)
                )
                .build()
            val analysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .setResolutionSelector(resSelector)
                .build()
            analysis.setAnalyzer(analysisExecutor) { proxy -> processFrame(proxy) }
            val selector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
            try {
                provider.unbindAll()
                provider.bindToLifecycle(this, selector, analysis)
            } catch (t: Throwable) {
                runOnUiThread { binding.cameraStatus.text = getString(R.string.camera_open_error, t.message ?: "") }
            }
        }, ContextCompat.getMainExecutor(this))
    }

    /** Camera thread: compute the mask for THIS frame, then render camera + its own mask together. */
    private fun processFrame(proxy: ImageProxy) {
        val front = lensFacing == CameraSelector.LENS_FACING_FRONT
        var bmp = try { proxy.toBitmap() } catch (t: Throwable) { proxy.close(); return } finally { proxy.close() }

        bmp = BitmapTransforms.orientAndDownscale(bmp, proxy.imageInfo.rotationDegrees.toFloat(), maxDim = 480, forceEven = true)
        val w = bmp.width; val h = bmp.height
        val rgba = BitmapTransforms.toRgba(bmp)
        bmp.recycle()   // pixels already copied to rgba; free the frame bitmap each frame (hot loop)

        val hd = try {
            viewModel.processFrame(YuvConverter.rgbaToNv21(rgba, w, h), w, h, 0, false, false)
        } catch (t: Throwable) { null }

        renderer.updateCameraFrame(rgba, w, h, front)
        renderer.updateHairData(hd)
        binding.cameraGl.requestRender()

        frameCount++
        val now = System.currentTimeMillis()
        if (now - lastFpsTime >= 1000) {
            val fps = (frameCount * 1000 / (now - lastFpsTime)).toInt()
            frameCount = 0; lastFpsTime = now
            runOnUiThread { viewModel.reportFps(fps) }
        }
    }

}
