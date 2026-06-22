package com.piontech.changehaircolor.filterchangehaircolorpro.demo.presentation.photo

import android.graphics.Bitmap
import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.HairMode

/** Everything the photo screen needs to render. Selection state lives here so it survives rotation. */
data class PhotoUiState(
    val engineReady: Boolean = false,
    val statusText: String = "",
    val original: Bitmap? = null,
    val result: Bitmap? = null,
    val showingResult: Boolean = false,
    val isProcessing: Boolean = false,
    // selection
    val hairMode: HairMode = HairMode.SINGLE,
    val color1: HairColor? = null,           // null = "Tóc gốc"
    val color2: HairColor,
    val activeSlot: Int = 1,
    val hairIntensity: Int = 100,
    val hairShine: Int = 50,
    val skinSmooth: Int = 0,
    val skinWhiten: Int = 0,
    // MULTI (full-color pattern)
    val patternIndex: Int = 0,
    val patternBitmap: Bitmap? = null,
) {
    val canSave: Boolean get() = result != null
    val imageToShow: Bitmap? get() = if (showingResult) result else original
}
