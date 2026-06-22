package com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase

import com.cyberlink.clgpuimage.CLMakeupLiveHairDyeFilter.HairDyeData
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.LiveHairRepository
import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.MediaRepository
import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.LiveMode
import java.io.File
import javax.inject.Inject

/** Set the live hair-dye target color/mode. */
class ConfigureLiveHairUseCase @Inject constructor(private val repo: LiveHairRepository) {
    operator fun invoke(mode: LiveMode, c1: HairColor, c2: HairColor?, alphaPct: Int, shinePct: Int) =
        repo.configure(mode, c1, c2, alphaPct, shinePct)
}

/** Compute the hair mask for one camera frame (synchronous; called on the analysis thread). */
class ProcessLiveFrameUseCase @Inject constructor(private val repo: LiveHairRepository) {
    operator fun invoke(
        nv21: ByteArray, width: Int, height: Int,
        rotation: Int, frontFlip: Boolean, frameFlip: Boolean,
    ): HairDyeData? = repo.processFrame(nv21, width, height, rotation, frontFlip, frameFlip)
}

/** Export a recorded video file to the gallery. */
class SaveVideoUseCase @Inject constructor(private val media: MediaRepository) {
    suspend operator fun invoke(file: File): Boolean = media.saveVideo(file)
}
