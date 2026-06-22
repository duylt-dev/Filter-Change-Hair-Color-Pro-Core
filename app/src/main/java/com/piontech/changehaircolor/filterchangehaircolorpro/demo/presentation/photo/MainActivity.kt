package com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.photo

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.R
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.databinding.ActivityMainBinding
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.camera.CameraActivity
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.common.hideSystemNavBar
import com.piontech.venussdk.model.HairMode
import com.piontech.venussdk.model.HairPalette
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/** Still-image screen (MVVM): observes [PhotoViewModel]; the engine work lives in the domain/data layers. */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PhotoViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val tabButtons = ArrayList<Button>()
    private var slot1Btn: Button? = null
    private var slot2Btn: Button? = null
    private var builtMode: HairMode? = null

    private val tabs = listOf(
        HairMode.SINGLE to R.string.mode_single,
        HairMode.TWO_COLOR to R.string.mode_ombre,
        HairMode.MULTI to R.string.mode_multi,
    )

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let { viewModel.onImagePicked(it) }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSystemNavBar()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        binding.pickButton.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.toggleButton.setOnClickListener { viewModel.toggleView() }
        binding.saveButton.setOnClickListener { viewModel.save() }
        binding.cameraButton.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        binding.hairIntensitySeek.onStop { viewModel.setIntensity(it) }
        binding.hairShineSeek.onStop { viewModel.setShine(it) }
        binding.smoothSeek.onStop { viewModel.setSmooth(it) }
        binding.evenSeek.onStop { viewModel.setWhiten(it) }

        buildTabs()
        buildSlots()
        observeState()
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

    private fun render(s: PhotoUiState) = with(binding) {
        preview.setImageBitmap(s.imageToShow)
        status.text = s.statusText
        progress.visibility = if (s.isProcessing) View.VISIBLE else View.GONE
        toggleButton.isEnabled = s.result != null
        toggleButton.text = getString(if (s.showingResult) R.string.view_original else R.string.view_result)
        saveButton.isEnabled = s.canSave

        tabButtons.forEachIndexed { i, b ->
            val sel = tabs[i].first == s.hairMode
            b.setTypeface(null, if (sel) Typeface.BOLD else Typeface.NORMAL)
            b.alpha = if (sel) 1f else 0.55f
        }
        slotRow.visibility = if (s.hairMode == HairMode.TWO_COLOR) View.VISIBLE else View.GONE
        slot1Btn?.let { it.setBackgroundColor(s.color1?.argb ?: 0xFF888888.toInt()); it.alpha = if (s.activeSlot == 1) 1f else 0.5f }
        slot2Btn?.let { it.setBackgroundColor(s.color2.argb); it.alpha = if (s.activeSlot == 2) 1f else 0.5f }

        // swatch row depends on mode (colors vs pattern thumbnails) — rebuild only on change
        if (s.hairMode != builtMode) { buildSwatchRow(s); builtMode = s.hairMode }
    }

    // ---------------- view builders ----------------

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

    private fun buildSwatchRow(s: PhotoUiState) {
        binding.swatchRow.removeAllViews()
        val d = resources.displayMetrics.density
        val size = (52 * d).toInt(); val margin = (4 * d).toInt()
        fun lp() = LinearLayout.LayoutParams(size, size).apply { setMargins(margin, 0, margin, 0) }

        if (s.hairMode == HairMode.MULTI) {  // full-color pattern thumbnails
            viewModel.patternThumbs.forEachIndexed { i, thumb ->
                ImageView(this).apply {
                    layoutParams = lp(); scaleType = ImageView.ScaleType.CENTER_CROP
                    contentDescription = getString(R.string.mode_multi) + " " + (i + 1)
                    try { assets.open(thumb).use { setImageBitmap(BitmapFactory.decodeStream(it)) } } catch (_: Throwable) {}
                    setOnClickListener { viewModel.selectPattern(i) }
                    binding.swatchRow.addView(this)
                }
            }
            return
        }

        Button(this).apply {
            text = getString(R.string.hair_original); textSize = 9f; layoutParams = lp()
            setOnClickListener { viewModel.clearHair() }
            binding.swatchRow.addView(this)
        }
        HairPalette.colors.forEach { color ->
            Button(this).apply {
                background = GradientDrawable().apply { setColor(color.argb); cornerRadius = 6 * d }
                layoutParams = lp()
                contentDescription = color.name
                setOnClickListener { viewModel.selectColor(color) }
                binding.swatchRow.addView(this)
            }
        }
    }

    /** SeekBar listener firing on release (apply is relatively expensive). */
    private inline fun SeekBar.onStop(crossinline cb: (Int) -> Unit) =
        setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(s: SeekBar?, p: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(s: SeekBar?) {}
            override fun onStopTrackingTouch(s: SeekBar?) { cb(progress) }
        })
}
