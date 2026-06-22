package com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository

import com.cyberlink.clgpuimage.CLMakeupLiveHairDyeFilter.HairDyeData
import com.piontech.venussdk.model.HairColor
import com.piontech.venussdk.model.LiveMode

/**
 * Live-camera hair recolor. [processFrame] is called per camera frame on the analysis thread
 * (NOT a coroutine — kept synchronous to stay in lock-step with the displayed frame), so it
 * returns the SDK's [HairDyeData] directly for the GL renderer to consume.
 */
interface LiveHairRepository {
    /** Set the target color/mode (call on change, not per frame). */
    fun configure(mode: LiveMode, c1: HairColor, c2: HairColor?, alphaPct: Int, shinePct: Int)

    /** Compute the hair mask + LUTs for this NV21 frame, or null if no hair detected. */
    fun processFrame(
        nv21: ByteArray, width: Int, height: Int,
        rotation: Int, frontFlip: Boolean, frameFlip: Boolean,
    ): HairDyeData?
}
