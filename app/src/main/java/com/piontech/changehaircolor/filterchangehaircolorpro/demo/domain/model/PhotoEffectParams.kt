package com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.model

import android.graphics.Bitmap
import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.HairMode

/**
 * Domain description of one still-image beautify request (hair recolor + skin). Mapped to the
 * SDK's input in the data layer. `intensity`/`shine` of -1 means "use the color's own default".
 */
data class PhotoEffectParams(
    val hair: HairColor? = null,
    val hair2: HairColor? = null,
    val hairMode: HairMode = HairMode.SINGLE,
    val hairIntensity: Int = -1,
    val hairShine: Int = -1,
    val hairPattern: Bitmap? = null,   // full-color pattern for MULTI mode
    val skinSmooth: Int = 0,
    val skinWhiten: Int = 0,
) {
    val hasAny: Boolean get() =
        hair != null || skinSmooth > 0 || skinWhiten > 0 ||
            (hairMode == HairMode.MULTI && hairPattern != null)
}
