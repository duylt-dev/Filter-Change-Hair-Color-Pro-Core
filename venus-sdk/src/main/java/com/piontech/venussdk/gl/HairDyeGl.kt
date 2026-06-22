package com.piontech.venussdk.gl

import android.graphics.Bitmap
import android.graphics.Matrix
import android.opengl.GLES20
import com.cyberlink.clgpuimage.CLMakeupLiveHairDyeFilter.HairDyeData
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * Shared GL helpers for the hair-dye shader, used by BOTH the live preview ([HairDyeGLRenderer]) and the
 * offscreen photo path ([PhotoHairDyeRenderer]). Keeps the (large) uniform + LUT/mask upload in ONE place
 * so the two renderers can't drift apart. Pure GL boilerplate (compile/link/buffers/readback) lives here too.
 */
internal object HairDyeGl {

    // ---- boilerplate ----

    fun compile(type: Int, src: String): Int {
        val s = GLES20.glCreateShader(type)
        GLES20.glShaderSource(s, src); GLES20.glCompileShader(s)
        val ok = IntArray(1); GLES20.glGetShaderiv(s, GLES20.GL_COMPILE_STATUS, ok, 0)
        if (ok[0] == 0) { val log = GLES20.glGetShaderInfoLog(s); GLES20.glDeleteShader(s); throw RuntimeException("compile: $log") }
        return s
    }

    fun buildProgram(vs: String, fs: String): Int {
        val v = compile(GLES20.GL_VERTEX_SHADER, vs)
        val f = compile(GLES20.GL_FRAGMENT_SHADER, fs)
        val p = GLES20.glCreateProgram()
        GLES20.glAttachShader(p, v); GLES20.glAttachShader(p, f); GLES20.glLinkProgram(p)
        val ok = IntArray(1); GLES20.glGetProgramiv(p, GLES20.GL_LINK_STATUS, ok, 0)
        if (ok[0] == 0) { val log = GLES20.glGetProgramInfoLog(p); GLES20.glDeleteProgram(p); throw RuntimeException("link: $log") }
        return p
    }

    fun floatBuf(a: FloatArray): FloatBuffer =
        ByteBuffer.allocateDirect(a.size * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().apply { put(a); position(0) }

    /** Bind [tex] as a 2D texture and set LINEAR filtering + CLAMP_TO_EDGE wrap (leaves it bound). */
    fun configureTexture2D(tex: Int) {
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex)
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR.toFloat())
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR.toFloat())
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE.toFloat())
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE.toFloat())
    }

    /** Read the current framebuffer [w]×[h] into a Bitmap, flipped vertically (glReadPixels is bottom-up). */
    fun readPixelsFlipped(w: Int, h: Int): Bitmap {
        val buf = ByteBuffer.allocateDirect(w * h * 4).order(ByteOrder.nativeOrder())
        GLES20.glReadPixels(0, 0, w, h, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, buf)
        buf.position(0)
        val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        bmp.copyPixelsFromBuffer(buf)
        val flipped = Bitmap.createBitmap(bmp, 0, 0, w, h, Matrix().apply { postScale(1f, -1f) }, true)
        if (flipped != bmp) bmp.recycle()
        return flipped
    }

    // ---- hair uniform + texture upload (the part that was duplicated) ----

    /** The 6 LUT/mask textures the hair shader samples (full-color pattern is bound separately by caller). */
    enum class HairTex { HAIR, POST_PROC, AVG_GRAY, GAMMA, SMOOTHED_Y, SHINE }

    /**
     * Uploads every hair uniform + the 6 LUT/mask textures from [hd]. Caller must have `glUseProgram`'d.
     * [loc] maps uniform name → location; [tex] maps a [HairTex] role → texture handle; [multiColorMode]
     * overrides `hd.m_multi_color_hair_dye_mode` (the photo full-color path forces 2). Mirrors the original
     * `CLMakeupLiveHairDyeFilter` upload (G).
     */
    fun uploadHairUniforms(hd: HairDyeData, loc: (String) -> Int, tex: (HairTex) -> Int, multiColorMode: Int) {
        fun setF(n: String, v: Float) = GLES20.glUniform1f(loc(n), v)
        fun setI(n: String, v: Int) = GLES20.glUniform1i(loc(n), v)
        fun set2(n: String, x: Float, y: Float) = GLES20.glUniform2f(loc(n), x, y)

        val afw = hd.m_analyzing_frame_width.toFloat()
        val afh = hd.m_analyzing_frame_height.toFloat()
        setF("center_x", (hd.m_roi_x + hd.m_roi_width * 0.5f) / afw)
        setF("center_y", (hd.m_roi_y + hd.m_roi_height * 0.5f) / afh)
        setF("background_image_width", afw)
        setF("background_image_height", afh)
        setF("hair_width", hd.m_roi_width.toFloat())
        setF("hair_height", hd.m_roi_height.toFloat())
        setF("median_gray", hd.m_median_gray / 255f)
        setF("quantile_25_gray", hd.m_quantile_25_gray / 255f)
        setI("target_color_count", hd.m_target_color_count)

        val color = FloatArray(12); val alpha = FloatArray(4); val shine = FloatArray(4)
        val th = FloatArray(4); val ts = FloatArray(4); val tl = FloatArray(4)
        for (i in 0 until 4) {
            color[i * 3] = hd.m_target_color_r[i] / 255f
            color[i * 3 + 1] = hd.m_target_color_g[i] / 255f
            color[i * 3 + 2] = hd.m_target_color_b[i] / 255f
            alpha[i] = hd.m_alpha_adjuster[i] / 255f
            shine[i] = hd.m_shine_ratio[i] / 255f
            th[i] = hd.m_target_h[i]; ts[i] = hd.m_target_s[i]; tl[i] = hd.m_target_l[i]
        }
        GLES20.glUniform3fv(loc("target_color"), 4, color, 0)
        GLES20.glUniform1fv(loc("alpha_adjuster"), 4, alpha, 0)
        GLES20.glUniform1fv(loc("shine_ratio"), 4, shine, 0)
        GLES20.glUniform1fv(loc("target_h"), 4, th, 0)
        GLES20.glUniform1fv(loc("target_s"), 4, ts, 0)
        GLES20.glUniform1fv(loc("target_l"), 4, tl, 0)

        val smw = hd.m_segment_map_width.toFloat()
        val smh = hd.m_segment_map_height.toFloat()
        setF("shine_alpha_jump_step_x", 1f / smw)
        setF("shine_alpha_jump_step_y", 1f / smh)
        setI("kernel_radius", 2)
        setF("skin_h", hd.m_skin_h)
        setF("skin_l", hd.m_skin_l)
        setF("salon_mode_only_boundary_improvement", if (hd.m_salon_mode_only_boundary_improvement) 1f else 0f)

        val fMax = maxOf(smw, smh) / maxOf(afw, afh)
        if (hd.m_use_ymk_algorithm != 0)
            set2("pixel_step", fMax * 4f / smw, 8f * fMax / smh)
        else
            set2("pixel_step", fMax * 2f / smw, fMax * 4f / smh)
        set2("jump_pixel_step", (2f * fMax / smw) * 5f, (fMax * 4f / smh) * 5f)
        setI("use_ymk_algorithm", if (hd.m_use_ymk_algorithm != 0) 1 else 0)
        set2("mask_center", hd.m_mask_center_x, hd.m_mask_center_y)
        set2("hair_top", hd.m_hair_top_x, hd.m_hair_top_y)
        set2("hair_bottom", hd.m_hair_bottom_x, hd.m_hair_bottom_y)
        setF("hair_ombre_y", hd.m_hair_ombre_y)
        setF("hair_ombre_range", hd.m_hair_ombre_range)
        setF("hair_ombre_mapping_range", hd.m_hair_ombre_mapping_range)
        setI("multi_color_hair_dye_mode", multiColorMode)
        setF("hair_purepigments_silkness", hd.m_hair_purepigments_silkness)
        setF("hair_purepigments_ratio_value", hd.m_hair_purepigments_ratio_value / 255f)

        val sw = hd.m_segment_map_width; val sh = hd.m_segment_map_height
        // segment mask (LUMINANCE R8)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex(HairTex.HAIR))
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_LUMINANCE, sw, sh, 0, GLES20.GL_LUMINANCE, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(hd.m_segment_map_data))
        // post-processed (RGBA)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex(HairTex.POST_PROC))
        hd.m_post_processed_map_data?.let {
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, sw, sh, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(it))
        } ?: GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 4, 4, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(ByteArray(64)))
        // average target gray (RGBA)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex(HairTex.AVG_GRAY))
        hd.m_average_target_gray_map_data?.let {
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, hd.m_average_target_gray_map_width, hd.m_average_target_gray_map_height, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(it))
        } ?: GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 4, 4, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(ByteArray(64)))
        // gamma 256x8 (RGBA, pre-allocated)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex(HairTex.GAMMA))
        GLES20.glTexSubImage2D(GLES20.GL_TEXTURE_2D, 0, 0, 0, 256, 8, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(hd.m_gamma_table))
        // local smoothed Y (R8)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex(HairTex.SMOOTHED_Y))
        hd.m_local_smoothed_y_map_data?.let {
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_LUMINANCE, sw, sh, 0, GLES20.GL_LUMINANCE, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(it))
        } ?: GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_LUMINANCE, 4, 4, 0, GLES20.GL_LUMINANCE, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(ByteArray(16)))
        // shine 256x4 (RGBA, pre-allocated)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex(HairTex.SHINE))
        GLES20.glTexSubImage2D(GLES20.GL_TEXTURE_2D, 0, 0, 0, 256, 4, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(hd.m_shine_table))
    }
}
