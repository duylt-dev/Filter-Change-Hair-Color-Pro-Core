package com.piontech.venussdk.gl

import android.graphics.Bitmap
import android.opengl.EGL14
import android.opengl.EGLConfig
import android.opengl.EGLContext
import android.opengl.EGLDisplay
import android.opengl.EGLSurface
import android.opengl.GLES20
import android.util.Log
import com.cyberlink.clgpuimage.CLMakeupLiveHairDyeFilter.HairDyeData
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * One-shot OFFSCREEN renderer for PHOTO full-color hair (MULTI, multiColorMode=2) — approach (b).
 *
 * The native photo engine can't bind the full-color pattern (its `setupHairFullColor` JNI wrapper is
 * missing). Instead we reuse the SAME shader the live preview uses ([HairDyeShaders]) + the shared
 * uniform/texture upload ([HairDyeGl]): feed the photo through the live engine to get the hair mask
 * ([HairDyeData]), then composite the pattern here in GL and read back to a Bitmap. Own EGL pbuffer so
 * the live renderer is untouched.
 */
internal object PhotoHairDyeRenderer {

    private const val TAG = "PhotoHairDye"

    /** Renders [rgba] (w×h RGBA8888, upright) with [hd]'s mask and [pattern]; null on any GL failure. */
    fun render(rgba: ByteArray, w: Int, h: Int, hd: HairDyeData, pattern: Bitmap): Bitmap? {
        if (w <= 0 || h <= 0 || rgba.size < w * h * 4) return null
        var egl: Egl? = null
        return try {
            egl = Egl(w, h)
            egl.makeCurrent()
            with(Program()) {
                build()
                uploadCamera(rgba, w, h)
                uploadPattern(pattern)
                uploadHair(hd)
                draw(w, h)
            }
            HairDyeGl.readPixelsFlipped(w, h)
        } catch (t: Throwable) {
            Log.e(TAG, "full-color photo render failed", t)
            null
        } finally {
            egl?.release()
        }
    }

    // ---------------------------------------------------------------- EGL pbuffer context

    private class Egl(width: Int, height: Int) {
        private val display: EGLDisplay
        private val context: EGLContext
        private val surface: EGLSurface

        init {
            display = EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY)
            check(display != EGL14.EGL_NO_DISPLAY) { "no EGL display" }
            val ver = IntArray(2)
            check(EGL14.eglInitialize(display, ver, 0, ver, 1)) { "eglInitialize failed" }
            val cfgAttr = intArrayOf(
                EGL14.EGL_RENDERABLE_TYPE, EGL14.EGL_OPENGL_ES2_BIT,
                EGL14.EGL_SURFACE_TYPE, EGL14.EGL_PBUFFER_BIT,
                EGL14.EGL_RED_SIZE, 8, EGL14.EGL_GREEN_SIZE, 8,
                EGL14.EGL_BLUE_SIZE, 8, EGL14.EGL_ALPHA_SIZE, 8,
                EGL14.EGL_NONE
            )
            val cfgs = arrayOfNulls<EGLConfig>(1)
            val nCfg = IntArray(1)
            check(EGL14.eglChooseConfig(display, cfgAttr, 0, cfgs, 0, 1, nCfg, 0) && nCfg[0] > 0) {
                "no matching EGL config"
            }
            val ctxAttr = intArrayOf(EGL14.EGL_CONTEXT_CLIENT_VERSION, 2, EGL14.EGL_NONE)
            context = EGL14.eglCreateContext(display, cfgs[0], EGL14.EGL_NO_CONTEXT, ctxAttr, 0)
            check(context != EGL14.EGL_NO_CONTEXT) { "eglCreateContext failed" }
            val surfAttr = intArrayOf(EGL14.EGL_WIDTH, width, EGL14.EGL_HEIGHT, height, EGL14.EGL_NONE)
            surface = EGL14.eglCreatePbufferSurface(display, cfgs[0], surfAttr, 0)
            check(surface != EGL14.EGL_NO_SURFACE) { "eglCreatePbufferSurface ${width}x$height failed" }
        }

        fun makeCurrent() = check(EGL14.eglMakeCurrent(display, surface, surface, context)) { "eglMakeCurrent failed" }

        fun release() {
            EGL14.eglMakeCurrent(display, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT)
            EGL14.eglDestroySurface(display, surface)
            EGL14.eglDestroyContext(display, context)
            EGL14.eglTerminate(display)
        }
    }

    // ---------------------------------------------------------------- GL program

    private class Program {
        private var program = 0
        private var aPosition = 0
        private var aInputTexCoord = 0
        private var aHairTexCoord = 0
        private var uInputImage = 0
        private val tex = IntArray(8) // camera, hair, postProc, avgGray, gamma, smoothedY, shine, fullColor
        private val uniform = HashMap<String, Int>()
        private fun u(n: String) = uniform.getOrPut(n) { GLES20.glGetUniformLocation(program, n) }

        // Same quad + texcoords as the live renderer (upright, no mirror for a still photo).
        private val quadPos = HairDyeGl.floatBuf(floatArrayOf(-1f, -1f, 1f, -1f, -1f, 1f, 1f, 1f))
        private val texCoord = HairDyeGl.floatBuf(floatArrayOf(0f, 1f, 1f, 1f, 0f, 0f, 1f, 0f))

        fun build() {
            program = HairDyeGl.buildProgram(HairDyeShaders.VERTEX, HairDyeShaders.FRAGMENT)
            aPosition = GLES20.glGetAttribLocation(program, "position")
            aInputTexCoord = GLES20.glGetAttribLocation(program, "inputTextureCoordinate")
            aHairTexCoord = GLES20.glGetAttribLocation(program, "input_hair_texture_coordinate")
            uInputImage = GLES20.glGetUniformLocation(program, "inputImageTexture")
            GLES20.glGenTextures(8, tex, 0)
            for (t in tex) HairDyeGl.configureTexture2D(t)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex[4]) // gamma 256x8 (filled per upload)
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 256, 8, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex[6]) // shine 256x4
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 256, 4, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null)
        }

        fun uploadCamera(rgba: ByteArray, w: Int, h: Int) {
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex[0])
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, w, h, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, ByteBuffer.wrap(rgba))
        }

        fun uploadPattern(pattern: Bitmap) {
            val pb = if (pattern.config == Bitmap.Config.ARGB_8888) pattern else pattern.copy(Bitmap.Config.ARGB_8888, false)
            val buf = ByteBuffer.allocateDirect(pb.width * pb.height * 4).order(ByteOrder.nativeOrder())
            pb.copyPixelsToBuffer(buf); buf.position(0)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex[7])
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, pb.width, pb.height, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, buf)
        }

        fun uploadHair(hd: HairDyeData) {
            GLES20.glUseProgram(program)
            // photo full-color path forces the multi-color branch regardless of what the tracker reported
            HairDyeGl.uploadHairUniforms(hd, ::u, ::texOf, multiColorMode = 2)
        }

        private fun texOf(role: HairDyeGl.HairTex): Int = when (role) {
            HairDyeGl.HairTex.HAIR -> tex[1]
            HairDyeGl.HairTex.POST_PROC -> tex[2]
            HairDyeGl.HairTex.AVG_GRAY -> tex[3]
            HairDyeGl.HairTex.GAMMA -> tex[4]
            HairDyeGl.HairTex.SMOOTHED_Y -> tex[5]
            HairDyeGl.HairTex.SHINE -> tex[6]
        }

        fun draw(w: Int, h: Int) {
            GLES20.glViewport(0, 0, w, h)
            GLES20.glClearColor(0f, 0f, 0f, 1f)
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
            GLES20.glUseProgram(program)
            GLES20.glUniform1i(u("bypass"), 0)

            texCoord.position(0)
            GLES20.glVertexAttribPointer(aInputTexCoord, 2, GLES20.GL_FLOAT, false, 0, texCoord)
            GLES20.glEnableVertexAttribArray(aInputTexCoord)
            texCoord.position(0)
            GLES20.glVertexAttribPointer(aHairTexCoord, 2, GLES20.GL_FLOAT, false, 0, texCoord)
            GLES20.glEnableVertexAttribArray(aHairTexCoord)

            bindUnit(1, tex[1], "hairTexture")
            bindUnit(2, tex[4], "gammaTexture")
            bindUnit(3, tex[5], "hairLocalSmoothedY")
            bindUnit(4, tex[6], "shineTexture")
            bindUnit(5, tex[3], "hairAverageGray")
            bindUnit(6, tex[2], "hairPostProcessed")
            bindUnit(7, tex[7], "hair_full_color_texture")

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex[0])
            GLES20.glUniform1i(uInputImage, 0)

            GLES20.glEnableVertexAttribArray(aPosition)
            quadPos.position(0)
            GLES20.glVertexAttribPointer(aPosition, 2, GLES20.GL_FLOAT, false, 0, quadPos)
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4)
            GLES20.glFinish()
        }

        private fun bindUnit(unit: Int, t: Int, sampler: String) {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + unit)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, t)
            GLES20.glUniform1i(u(sampler), unit)
        }
    }
}
