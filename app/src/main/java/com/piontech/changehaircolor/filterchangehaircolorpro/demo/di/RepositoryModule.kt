package com.piontech.changehaircolor.filterchangehaircolorpro.demo.di

import com.piontech.changehaircolor.filterchangehaircolorpro.demo.data.repository.EngineRepositoryImpl
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.data.repository.LiveHairRepositoryImpl
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.data.repository.MediaRepositoryImpl
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.data.StringProviderImpl
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.data.repository.PhotoBeautyRepositoryImpl
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.EngineRepository
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.LiveHairRepository
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.MediaRepository
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.PhotoBeautyRepository
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.util.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEngineRepository(impl: EngineRepositoryImpl): EngineRepository

    @Binds
    abstract fun bindPhotoBeautyRepository(impl: PhotoBeautyRepositoryImpl): PhotoBeautyRepository

    @Binds
    @Singleton
    abstract fun bindLiveHairRepository(impl: LiveHairRepositoryImpl): LiveHairRepository

    @Binds
    abstract fun bindMediaRepository(impl: MediaRepositoryImpl): MediaRepository

    @Binds
    @Singleton
    abstract fun bindStringProvider(impl: StringProviderImpl): StringProvider
}
