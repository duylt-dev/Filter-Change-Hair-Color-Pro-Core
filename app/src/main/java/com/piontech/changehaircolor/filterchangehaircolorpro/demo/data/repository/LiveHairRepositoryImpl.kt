package com.piontech.changehaircolor.filterchangehaircolorpro.demo.data.repository

import com.cyberlink.clgpuimage.CLMakeupLiveHairDyeFilter.HairDyeData
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.LiveHairRepository
import com.piontech.venussdk.engine.VenusEngine
import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.LiveMode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LiveHairRepositoryImpl @Inject constructor() : LiveHairRepository {

    override fun configure(mode: LiveMode, c1: HairColor, c2: HairColor?, alphaPct: Int, shinePct: Int) =
        VenusEngine.configureLiveHair(mode, c1, c2, alphaPct, shinePct)

    override fun processFrame(
        nv21: ByteArray, width: Int, height: Int,
        rotation: Int, frontFlip: Boolean, frameFlip: Boolean,
    ): HairDyeData? = VenusEngine.processLiveFrame(nv21, width, height, rotation, frontFlip, frameFlip)
}
