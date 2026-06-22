package com.piontech.changehaircolor.filterchangehaircolorpro.demo.data.repository

import android.content.Context
import android.graphics.Bitmap
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.model.PhotoEffectParams
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.PhotoBeautyRepository
import com.piontech.venussdk.engine.VenusEngine
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoBeautyRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : PhotoBeautyRepository {

    override suspend fun apply(source: Bitmap, params: PhotoEffectParams): Result<Bitmap> =
        withContext(Dispatchers.Default) {
            val req = VenusEngine.BeautifyRequest(
                hair = params.hair,
                hair2 = params.hair2,
                hairMode = params.hairMode,
                hairIntensity = params.hairIntensity,
                hairShine = params.hairShine,
                hairPattern = params.hairPattern,
                skinSmooth = params.skinSmooth,
                skinWhiten = params.skinWhiten,
            )
            val result = VenusEngine.applyOnPhoto(context, source, req) {}
            val out = result.bitmap
            if (result.ok && out != null) Result.success(out)
            else Result.failure(IllegalStateException(result.log))
        }
}
