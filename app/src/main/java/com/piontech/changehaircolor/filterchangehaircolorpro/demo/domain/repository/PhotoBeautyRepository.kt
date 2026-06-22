package com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository

import android.graphics.Bitmap
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.model.PhotoEffectParams

/** Still-image hair recolor + skin beautify (one native GetMakeupImage pass). */
interface PhotoBeautyRepository {
    /** Applies [params] to [source]; returns the result bitmap or a failure with a reason. */
    suspend fun apply(source: Bitmap, params: PhotoEffectParams): Result<Bitmap>
}
