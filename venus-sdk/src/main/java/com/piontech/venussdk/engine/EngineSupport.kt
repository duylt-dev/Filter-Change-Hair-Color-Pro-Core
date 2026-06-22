package com.piontech.venussdk.engine

/**
 * Pure device-capability gate for the native engine — no Android calls, so it is fully JVM unit-testable
 * (covers the "armeabi-v7a" and "API 24/25" cases we can't reproduce on real hardware here).
 *
 * Two hard requirements (see improve_plan §3 — change either and the engine crashes at load):
 *  - API ≥ 26: libtensorflowlite_pf strong-imports `AHardwareBuffer_*` (added in API 26).
 *  - an arm ABI: CyberLink ships only `arm64-v8a` / `armeabi-v7a` (no x86/x86_64).
 *
 * The app installs on API 24+ (lower minSdk) so this MUST be checked before touching the engine;
 * on an unsupported device the UI shows [unsupportedReason] instead of crashing.
 */
internal object EngineSupport {

    /** Minimum Android API the native engine can load on. */
    const val MIN_API = 26

    /** True when [sdkInt] (Build.VERSION.SDK_INT) meets the engine's API floor. */
    fun isApiSupported(sdkInt: Int): Boolean = sdkInt >= MIN_API

    /** True when at least one supported ABI is an arm ABI (Build.SUPPORTED_ABIS). */
    fun isAbiSupported(abis: Array<out String>): Boolean = abis.any { it.startsWith("arm") }

    /** True only when BOTH the API floor and an arm ABI are satisfied. */
    fun isSupported(sdkInt: Int, abis: Array<out String>): Boolean =
        isApiSupported(sdkInt) && isAbiSupported(abis)

    /** Why the engine can't run, or [Reason.SUPPORTED] if it can. Useful for precise UX / tests. */
    enum class Reason { SUPPORTED, API_TOO_LOW, ABI_UNSUPPORTED, API_AND_ABI }

    fun reason(sdkInt: Int, abis: Array<out String>): Reason {
        val api = isApiSupported(sdkInt)
        val abi = isAbiSupported(abis)
        return when {
            api && abi -> Reason.SUPPORTED
            !api && !abi -> Reason.API_AND_ABI
            !api -> Reason.API_TOO_LOW
            else -> Reason.ABI_UNSUPPORTED
        }
    }

    /** Human-readable reason (Vietnamese, matches the app's status strings), or null when supported. */
    fun unsupportedReason(sdkInt: Int, abis: Array<out String>): String? = when (reason(sdkInt, abis)) {
        Reason.SUPPORTED -> null
        Reason.API_TOO_LOW -> "Cần Android 8.0+ (API $MIN_API), thiết bị đang ở API $sdkInt"
        Reason.ABI_UNSUPPORTED -> "Cần CPU arm (arm64-v8a/armeabi-v7a); thiết bị: ${abis.joinToString()}"
        Reason.API_AND_ABI -> "Cần Android 8.0+ và CPU arm; thiết bị: API $sdkInt, ${abis.joinToString()}"
    }
}
