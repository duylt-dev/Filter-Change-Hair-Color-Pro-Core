package com.piontech.changehaircolor.filterchangehaircolorpro.demo.data.repository

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.MediaRepository
import com.piontech.venussdk.util.BitmapTransforms
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : MediaRepository {

    private val resolver get() = context.contentResolver
    private val folder = "FilterHairColor"

    override suspend fun loadBitmap(uri: Uri, maxDim: Int): Bitmap = withContext(Dispatchers.IO) {
        val orientation = resolver.openInputStream(uri)?.use {
            ExifInterface(it).getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        } ?: ExifInterface.ORIENTATION_NORMAL

        val bmp = resolver.openInputStream(uri)!!.use {
            BitmapFactory.decodeStream(
                it, null, BitmapFactory.Options().apply { inPreferredConfig = Bitmap.Config.ARGB_8888 }
            )
        } ?: error("decode failed")

        val degrees = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90f
            ExifInterface.ORIENTATION_ROTATE_180 -> 180f
            ExifInterface.ORIENTATION_ROTATE_270 -> 270f
            else -> 0f
        }
        BitmapTransforms.orientAndDownscale(bmp, degrees, maxDim, forceEven = false)
    }

    override suspend fun loadAsset(path: String): Bitmap? = withContext(Dispatchers.IO) {
        runCatching { context.assets.open(path).use { BitmapFactory.decodeStream(it) } }.getOrNull()
    }

    override suspend fun saveImage(bitmap: Bitmap): Boolean = withContext(Dispatchers.IO) {
        runCatching {
            val cv = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "haircolor_${System.currentTimeMillis()}.jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                if (Build.VERSION.SDK_INT >= 29)
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/$folder")
            }
            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv)
                ?: error("insert null")
            resolver.openOutputStream(uri).use { bitmap.compress(Bitmap.CompressFormat.JPEG, 95, it!!) }
            true
        }.getOrDefault(false)
    }

    override suspend fun saveVideo(file: File): Boolean = withContext(Dispatchers.IO) {
        runCatching {
            val cv = ContentValues().apply {
                put(MediaStore.Video.Media.DISPLAY_NAME, file.name)
                put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
                if (Build.VERSION.SDK_INT >= 29)
                    put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/$folder")
            }
            val uri = resolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, cv)
                ?: error("insert null")
            resolver.openOutputStream(uri).use { out -> file.inputStream().use { it.copyTo(out!!) } }
            file.delete()
            true
        }.getOrDefault(false)
    }
}
