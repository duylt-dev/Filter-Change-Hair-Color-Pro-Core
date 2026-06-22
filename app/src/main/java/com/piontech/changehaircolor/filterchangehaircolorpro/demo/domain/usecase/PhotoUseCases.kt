package com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase

import android.graphics.Bitmap
import android.net.Uri
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.model.PhotoEffectParams
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.MediaRepository
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.PhotoBeautyRepository
import javax.inject.Inject

/** Decode + orient + downscale a picked image. */
class LoadImageUseCase @Inject constructor(private val media: MediaRepository) {
    suspend operator fun invoke(uri: Uri, maxDim: Int = 1280): Bitmap = media.loadBitmap(uri, maxDim)
}

/** Decode a bundled asset (e.g. a full-color hair pattern). */
class LoadAssetUseCase @Inject constructor(private val media: MediaRepository) {
    suspend operator fun invoke(path: String): Bitmap? = media.loadAsset(path)
}

/** Apply hair + skin effects to a still image. */
class ApplyPhotoEffectUseCase @Inject constructor(private val repo: PhotoBeautyRepository) {
    suspend operator fun invoke(source: Bitmap, params: PhotoEffectParams): Result<Bitmap> =
        repo.apply(source, params)
}

/** Export a result bitmap to the gallery. */
class SaveImageUseCase @Inject constructor(private val media: MediaRepository) {
    suspend operator fun invoke(bitmap: Bitmap): Boolean = media.saveImage(bitmap)
}
