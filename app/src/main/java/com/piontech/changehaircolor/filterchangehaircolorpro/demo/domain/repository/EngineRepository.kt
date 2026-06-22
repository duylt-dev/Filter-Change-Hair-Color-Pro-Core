package com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository

/** Lifecycle of the native Venus engine (device support + lazy init for each path). */
interface EngineRepository {
    /** Device can run the engine (API 26+ and an arm ABI). Check before any ensure*Ready(). */
    val isSupported: Boolean

    /** Lazily init the still-image engine. Suspends on IO; returns true when ready. */
    suspend fun ensurePhotoReady(): Boolean

    /** Lazily init the live-camera engine. Suspends on IO; returns true when ready. */
    suspend fun ensureLiveReady(): Boolean

    /** Release the live engine's native memory (re-inits on next ensureLiveReady). Quick, non-blocking. */
    fun releaseLive()

    /** Release the photo engine's native memory (re-inits on next ensurePhotoReady). */
    fun releasePhoto()
}
