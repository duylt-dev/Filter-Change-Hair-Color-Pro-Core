package com.piontech.changehaircolor.filterchangehaircolorpro.demo.data.repository

import android.content.Context
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.EngineRepository
import com.piontech.venussdk.engine.VenusEngine
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EngineRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : EngineRepository {

    override val isSupported: Boolean get() = VenusEngine.isEngineSupported

    override suspend fun ensurePhotoReady(): Boolean = withContext(Dispatchers.IO) {
        VenusEngine.ensurePhotoReady(context) {}
    }

    override suspend fun ensureLiveReady(): Boolean = withContext(Dispatchers.IO) {
        VenusEngine.ensureLiveReady(context) {}
    }

    override fun releaseLive() = VenusEngine.releaseLive()

    override fun releasePhoto() = VenusEngine.releasePhoto()
}
