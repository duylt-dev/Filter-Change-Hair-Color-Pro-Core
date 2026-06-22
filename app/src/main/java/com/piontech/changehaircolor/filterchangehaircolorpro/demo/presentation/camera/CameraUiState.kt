package com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.camera

import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.LiveMode

/** Live-camera screen state. Per-frame mask data is NOT here — it flows straight to the GL renderer. */
data class CameraUiState(
    val engineReady: Boolean = false,
    val statusText: String = "",
    val liveMode: LiveMode = LiveMode.SINGLE,
    val color1: HairColor,
    val color2: HairColor,
    val activeSlot: Int = 1,
    val shinePct: Int = 50,
    val alphaPct: Int = 100,
    val recolorOn: Boolean = true,
    val patternIndex: Int = 0,
)
