package com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository

import android.graphics.Bitmap
import android.net.Uri
import java.io.File

/** Loading source images and exporting results to the device gallery. */
interface MediaRepository {
    /** Decode [uri] to an upright, downscaled (<= [maxDim]) ARGB_8888 bitmap. */
    suspend fun loadBitmap(uri: Uri, maxDim: Int = 1280): Bitmap

    /** Decode a bundled asset (e.g. a hair-dye full-color pattern), or null on failure. */
    suspend fun loadAsset(path: String): Bitmap?

    /** Save a JPEG to Pictures/FilterHairColor. Returns true on success. */
    suspend fun saveImage(bitmap: Bitmap): Boolean

    /** Move a recorded MP4 file into Movies/FilterHairColor. Returns true on success. */
    suspend fun saveVideo(file: File): Boolean
}
