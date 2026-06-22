package com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.photo

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.R
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.model.PhotoEffectParams
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.ApplyPhotoEffectUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.CheckEngineSupportUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.LoadAssetUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.LoadImageUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.PreparePhotoEngineUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.ReleasePhotoEngineUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.SaveImageUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.util.StringProvider
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.common.HairPatterns
import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.HairMode
import com.piontech.venussdk.model.HairPalette
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    checkSupport: CheckEngineSupportUseCase,
    private val prepareEngine: PreparePhotoEngineUseCase,
    private val loadImage: LoadImageUseCase,
    private val loadAsset: LoadAssetUseCase,
    private val applyEffect: ApplyPhotoEffectUseCase,
    private val saveImage: SaveImageUseCase,
    private val releasePhotoEngine: ReleasePhotoEngineUseCase,
    private val strings: StringProvider,
) : ViewModel() {

    private val _state = MutableStateFlow(
        PhotoUiState(color2 = HairPalette.colors[3], statusText = strings.get(R.string.status_loading_engine))
    )
    val state: StateFlow<PhotoUiState> = _state.asStateFlow()

    /** Bundled full-color pattern assets (shared with the camera screen). */
    val patterns = HairPatterns.full
    val patternThumbs = HairPatterns.thumbs

    init {
        if (!checkSupport()) {
            _state.update { it.copy(statusText = strings.get(R.string.device_unsupported)) }
        } else {
            viewModelScope.launch {
                val ready = prepareEngine()
                _state.update {
                    it.copy(
                        engineReady = ready,
                        statusText = strings.get(
                            if (ready) R.string.status_engine_ready else R.string.engine_load_failed
                        ),
                    )
                }
            }
        }
    }

    fun onImagePicked(uri: Uri) {
        _state.update { it.copy(statusText = strings.get(R.string.status_loading_image)) }
        viewModelScope.launch {
            runCatching { loadImage(uri) }
                .onSuccess { bmp ->
                    _state.update {
                        it.copy(
                            original = bmp, result = null, showingResult = false,
                            statusText = strings.get(R.string.status_image_selected, bmp.width, bmp.height),
                        )
                    }
                    apply()
                }
                .onFailure { t ->
                    _state.update { it.copy(statusText = strings.get(R.string.status_image_load_error, t.message ?: "")) }
                }
        }
    }

    fun setMode(mode: HairMode) {
        _state.update { it.copy(hairMode = mode, activeSlot = 1) }
        if (mode == HairMode.MULTI && _state.value.patternBitmap == null) selectPattern(_state.value.patternIndex)
        else apply()
    }
    fun setActiveSlot(slot: Int) = _state.update { it.copy(activeSlot = slot) }
    fun clearHair() { _state.update { it.copy(color1 = null) }; apply() }

    /** Load + select a full-color pattern (MULTI mode), then re-apply. */
    fun selectPattern(index: Int) {
        viewModelScope.launch {
            val bmp = loadAsset(patterns[index])
            _state.update { it.copy(patternIndex = index, patternBitmap = bmp) }
            apply()
        }
    }

    fun selectColor(color: HairColor) {
        _state.update {
            if (it.hairMode == HairMode.SINGLE || it.activeSlot == 1) it.copy(color1 = color)
            else it.copy(color2 = color)
        }
        apply()
    }

    fun setIntensity(v: Int) { _state.update { it.copy(hairIntensity = v) }; apply() }
    fun setShine(v: Int) { _state.update { it.copy(hairShine = v) }; apply() }
    fun setSmooth(v: Int) { _state.update { it.copy(skinSmooth = v) }; apply() }
    fun setWhiten(v: Int) { _state.update { it.copy(skinWhiten = v) }; apply() }

    fun toggleView() {
        val s = _state.value
        if (s.original == null || s.result == null) return
        _state.update { it.copy(showingResult = !it.showingResult) }
    }

    fun save() {
        val bmp = _state.value.result ?: run {
            _state.update { it.copy(statusText = strings.get(R.string.status_no_result_to_save)) }; return
        }
        _state.update { it.copy(statusText = strings.get(R.string.status_saving_image)) }
        viewModelScope.launch {
            val ok = saveImage(bmp)
            _state.update {
                it.copy(statusText = strings.get(if (ok) R.string.status_image_saved else R.string.status_save_image_failed))
            }
        }
    }

    /** Free the photo engine when the home screen is gone for good (app exit, not rotation). */
    override fun onCleared() {
        super.onCleared()
        releasePhotoEngine()
    }

    /** Recompute the result for the current selection. */
    private fun apply() {
        val s = _state.value
        val src = s.original ?: return
        val params = PhotoEffectParams(
            hair = s.color1,
            hair2 = if (s.hairMode == HairMode.TWO_COLOR) s.color2 else null,
            hairMode = s.hairMode,
            hairIntensity = s.hairIntensity,
            hairShine = s.hairShine,
            hairPattern = if (s.hairMode == HairMode.MULTI) s.patternBitmap else null,
            skinSmooth = s.skinSmooth,
            skinWhiten = s.skinWhiten,
        )
        if (!params.hasAny) {
            _state.update {
                it.copy(result = null, showingResult = false, statusText = strings.get(R.string.status_original_no_effect))
            }
            return
        }
        _state.update { it.copy(isProcessing = true, statusText = strings.get(R.string.status_processing)) }
        viewModelScope.launch {
            val t0 = System.currentTimeMillis()
            val r = applyEffect(src, params)
            val ms = System.currentTimeMillis() - t0
            r.onSuccess { out ->
                _state.update {
                    it.copy(result = out, showingResult = true, isProcessing = false, statusText = summary(it, ms))
                }
            }.onFailure { t ->
                _state.update {
                    it.copy(isProcessing = false, statusText = t.message ?: strings.get(R.string.status_processing_error))
                }
            }
        }
    }

    private fun summary(s: PhotoUiState, ms: Long): String {
        val parts = buildList {
            if (s.hairMode == HairMode.MULTI && s.patternBitmap != null) {
                add(strings.get(R.string.summary_multi, s.patternIndex + 1))
            } else s.color1?.let {
                val label = if (s.hairMode == HairMode.TWO_COLOR)
                    strings.get(R.string.summary_hair_ombre, it.name, s.color2.name)
                else strings.get(R.string.summary_hair_single, it.name)
                add(strings.get(R.string.summary_hair_detail, label, s.hairIntensity, s.hairShine))
            }
            if (s.skinSmooth > 0) add(strings.get(R.string.summary_smooth, s.skinSmooth))
            if (s.skinWhiten > 0) add(strings.get(R.string.summary_whiten, s.skinWhiten))
        }
        return strings.get(R.string.summary_done, parts.joinToString(" + "), ms)
    }
}
