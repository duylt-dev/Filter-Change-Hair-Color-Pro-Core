package com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.usecase

import com.piontech.changehaircolor.filterchangehaircolorpro.demo.domain.repository.EngineRepository
import javax.inject.Inject

/** True if this device can run the native engine (API 26+ and an arm ABI). */
class CheckEngineSupportUseCase @Inject constructor(private val repo: EngineRepository) {
    operator fun invoke(): Boolean = repo.isSupported
}

/** Lazily initialize the still-image engine; returns true when ready. */
class PreparePhotoEngineUseCase @Inject constructor(private val repo: EngineRepository) {
    suspend operator fun invoke(): Boolean = repo.ensurePhotoReady()
}

/** Lazily initialize the live-camera engine; returns true when ready. */
class PrepareLiveEngineUseCase @Inject constructor(private val repo: EngineRepository) {
    suspend operator fun invoke(): Boolean = repo.ensureLiveReady()
}

/** Free the live engine's native memory (e.g. when the camera screen is finishing). */
class ReleaseLiveEngineUseCase @Inject constructor(private val repo: EngineRepository) {
    operator fun invoke() = repo.releaseLive()
}

/** Free the photo engine's native memory (e.g. when the home screen is finishing). */
class ReleasePhotoEngineUseCase @Inject constructor(private val repo: EngineRepository) {
    operator fun invoke() = repo.releasePhoto()
}
