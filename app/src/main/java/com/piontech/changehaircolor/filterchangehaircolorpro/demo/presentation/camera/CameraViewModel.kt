package com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberlink.clgpuimage.CLMakeupLiveHairDyeFilter.HairDyeData
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.R
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.CheckEngineSupportUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.ConfigureLiveHairUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.PrepareLiveEngineUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.ProcessLiveFrameUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.ReleaseLiveEngineUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.SaveImageUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase.SaveVideoUseCase
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.util.StringProvider
import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.HairPalette
import com.piontech.venussdk.model.LiveMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val checkSupport: CheckEngineSupportUseCase,
    private val prepareEngine: PrepareLiveEngineUseCase,
    private val configureHair: ConfigureLiveHairUseCase,
    private val processLiveFrame: ProcessLiveFrameUseCase,
    private val saveImage: SaveImageUseCase,
    private val saveVideo: SaveVideoUseCase,
    private val releaseLiveEngine: ReleaseLiveEngineUseCase,
    private val strings: StringProvider,
) : ViewModel() {

    private val _state = MutableStateFlow(
        CameraUiState(
            color1 = HairPalette.colors[0],
            color2 = HairPalette.colors[3],
            statusText = strings.get(R.string.camera_starting),
        )
    )
    val state: StateFlow<CameraUiState> = _state.asStateFlow()

    fun prepareEngineAsync() {
        if (!checkSupport()) {
            _state.update { it.copy(statusText = strings.get(R.string.camera_device_unsupported)) }
            return
        }
        viewModelScope.launch {
            val ready = prepareEngine()
            _state.update {
                it.copy(
                    engineReady = ready,
                    statusText = if (ready) it.statusText else strings.get(R.string.camera_engine_load_failed),
                )
            }
            if (ready) pushConfig()
        }
    }

    fun setMode(mode: LiveMode) {
        _state.update { it.copy(liveMode = mode, activeSlot = 1, recolorOn = true) }; pushConfig()
    }

    fun setActiveSlot(slot: Int) = _state.update { it.copy(activeSlot = slot) }

    fun selectColor(color: HairColor) {
        _state.update {
            val next = if (it.liveMode == LiveMode.SINGLE || it.activeSlot == 1) it.copy(color1 = color)
            else it.copy(color2 = color)
            next.copy(recolorOn = true)
        }
        pushConfig()
    }

    fun setPattern(index: Int) { _state.update { it.copy(patternIndex = index, recolorOn = true) }; pushConfig() }
    fun turnOff() { _state.update { it.copy(recolorOn = false) }; pushConfig() }
    fun setShine(v: Int) { _state.update { it.copy(shinePct = v) }; pushConfig() }
    fun setAlpha(v: Int) { _state.update { it.copy(alphaPct = v) }; pushConfig() }

    /** Per-frame mask compute (synchronous; called on the camera analysis thread). */
    fun processFrame(
        nv21: ByteArray, width: Int, height: Int,
        rotation: Int, frontFlip: Boolean, frameFlip: Boolean,
    ): HairDyeData? {
        val s = _state.value
        if (!s.engineReady || !s.recolorOn) return null
        return processLiveFrame(nv21, width, height, rotation, frontFlip, frameFlip)
    }

    fun savePhoto(bitmap: Bitmap, onResult: (Boolean) -> Unit) {
        viewModelScope.launch { onResult(saveImage(bitmap)) }
    }

    fun saveVideo(file: File, onResult: (Boolean) -> Unit) {
        viewModelScope.launch { onResult(saveVideo(file)) }
    }

    fun reportFps(fps: Int) {
        val s = _state.value
        if (!s.engineReady) return
        val label = if (s.recolorOn) s.liveMode.name else strings.get(R.string.off)
        _state.update { it.copy(statusText = strings.get(R.string.camera_fps_status, fps, label)) }
    }

    /** Free the live engine when the camera screen is gone for good (not on rotation). */
    override fun onCleared() {
        super.onCleared()
        releaseLiveEngine()
    }

    /** Push the current color/mode to the engine (no-op until engine ready). */
    private fun pushConfig() {
        val s = _state.value
        if (!s.engineReady || !s.recolorOn) return
        configureHair(s.liveMode, s.color1, s.color2, s.alphaPct, s.shinePct)
    }
}
