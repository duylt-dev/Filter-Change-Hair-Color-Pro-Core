package com.piontech.venussdk.engine

import android.content.Context
import java.io.File

/**
 * Copies the native engine model files from APK assets to the app's filesDir, mirroring the
 * original CyberLink logic ([com.cyberlink.youcammakeup.core.k]): destination is
 * {@code filesDir/venus_model_6/<filename>}.
 *
 * Robustness (so a half-written model can't permanently break engine load):
 *  - re-provision when the file is **missing**, **empty**, or its **size ≠ the asset size**
 *    (truncated by a crash / full disk during a previous copy);
 *  - copy to a `.tmp` then **atomically rename**, so an interrupted copy never leaves a partial
 *    file at the real path.
 *
 * The exact filenames are decided at runtime by the engine via
 * {@code CUIVenusLive.GetInternalModelVersion(...)}; we provision whatever it asks for.
 */
internal object ModelProvisioner {

    private const val MODEL_DIR = "venus_model_6"
    private const val ASSET_PREFIX = "model"

    /**
     * Pure decision (unit-tested): should we (re)copy the model? Re-provision when missing, empty, or
     * — when the asset size is known ([expectedLength] > 0) — when the on-disk size doesn't match it.
     * [expectedLength] == 0 means "unknown" (e.g. compressed asset) → only missing/empty triggers a copy.
     */
    fun needsProvision(exists: Boolean, actualLength: Long, expectedLength: Long): Boolean =
        !exists || actualLength == 0L || (expectedLength > 0L && actualLength != expectedLength)

    /** Copies `assets/model/<filename>` to filesDir if needed and returns its absolute path. */
    fun provision(context: Context, filename: String): String {
        val dir = File(context.filesDir, MODEL_DIR)
        if (!dir.isDirectory) dir.mkdirs()
        val out = File(dir, filename)
        val assetPath = "$ASSET_PREFIX/$filename"
        val expected = assetLength(context, assetPath)

        if (needsProvision(out.exists(), out.length(), expected)) {
            val tmp = File(dir, "$filename.tmp")
            try {
                context.assets.open(assetPath).use { input ->
                    tmp.outputStream().use { output -> input.copyTo(output) }
                }
                // atomic swap; if rename fails (rare), fall back to delete+rename
                if (out.exists()) out.delete()
                if (!tmp.renameTo(out)) {
                    tmp.copyTo(out, overwrite = true)
                }
            } finally {
                if (tmp.exists()) tmp.delete()
            }
        }
        return out.absolutePath
    }

    /** Best-effort uncompressed size of an asset; 0 when unknown (compressed asset / error). */
    private fun assetLength(context: Context, assetPath: String): Long =
        try {
            context.assets.openFd(assetPath).use { it.length }
        } catch (_: Throwable) {
            // Compressed assets have no file descriptor — size unknown, skip the size check.
            0L
        }
}
