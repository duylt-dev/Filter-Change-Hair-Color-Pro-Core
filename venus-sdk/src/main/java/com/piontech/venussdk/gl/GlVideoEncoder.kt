package com.piontech.venussdk.gl

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.media.MediaMuxer
import android.media.MediaRecorder
import android.opengl.EGL14
import android.opengl.EGLConfig
import android.opengl.EGLContext
import android.opengl.EGLDisplay
import android.opengl.EGLExt
import android.opengl.EGLSurface
import android.util.Log

/**
 * Records the GL output to an MP4 (H.264 video + optional AAC audio). Created on the GL thread: it
 * builds a MediaCodec video encoder with a Surface input wrapped in an EGLSurface sharing the GL
 * thread's current EGL context, and lets the renderer draw the same scene into it each frame.
 *
 * When [withAudio] is true it ALSO captures the mic on a private thread (AudioRecord → AAC encoder)
 * and muxes it as a second track. The shared MediaMuxer is started only once BOTH tracks' formats
 * are known, and all writes are serialized via [muxerLock] (video drains on the GL thread, audio on
 * its own thread). If the mic can't be opened, it silently falls back to video-only.
 *
 * Usage on the GL thread:
 *   val enc = GlVideoEncoder(w, h, path, withAudio = true)
 *   // per frame: enc.makeCurrent(); <draw>; enc.swap(nanos)
 *   enc.finish()   // drain end-of-stream + release
 */
internal class GlVideoEncoder(
    width: Int,
    height: Int,
    outputPath: String,
    fps: Int = 24,
    withAudio: Boolean = false,
) {
    private val TAG = "GlVideoEncoder"
    private val codec: MediaCodec
    private val muxer: MediaMuxer
    private val inputSurface: android.view.Surface
    private val eglDisplay: EGLDisplay = EGL14.eglGetCurrentDisplay()
    private val eglContext: EGLContext = EGL14.eglGetCurrentContext()
    private val eglSurface: EGLSurface
    private val bufferInfo = MediaCodec.BufferInfo()

    // shared muxer state (video drains on GL thread, audio on its own thread)
    private val muxerLock = Any()
    private var videoTrack = -1
    private var muxerStarted = false
    private var tracksAdded = 0
    private var tracksNeeded = 1

    // common timeline base for video + audio so the two tracks stay in sync
    private val startNanos = System.nanoTime()

    private var audio: AudioPart? = null

    init {
        val w = width and 1.inv(); val h = height and 1.inv()
        val format = MediaFormat.createVideoFormat(MediaFormat.MIMETYPE_VIDEO_AVC, w, h).apply {
            setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
            setInteger(MediaFormat.KEY_BIT_RATE, (w * h * fps * 0.18f).toInt().coerceAtLeast(2_000_000))
            setInteger(MediaFormat.KEY_FRAME_RATE, fps)
            setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1)
        }
        codec = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_VIDEO_AVC)
        codec.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
        inputSurface = codec.createInputSurface()
        codec.start()

        val cfg = chooseConfig()
        eglSurface = EGL14.eglCreateWindowSurface(eglDisplay, cfg, inputSurface, intArrayOf(EGL14.EGL_NONE), 0)
        check(eglSurface != EGL14.EGL_NO_SURFACE) { "eglCreateWindowSurface failed" }

        muxer = MediaMuxer(outputPath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)

        if (withAudio) {
            audio = try { AudioPart() } catch (t: Throwable) {
                Log.w(TAG, "audio init failed → video only", t); null
            }
        }
        tracksNeeded = if (audio != null) 2 else 1
        audio?.start()
    }

    private fun chooseConfig(): EGLConfig {
        val attrs = intArrayOf(
            EGL14.EGL_RED_SIZE, 8, EGL14.EGL_GREEN_SIZE, 8, EGL14.EGL_BLUE_SIZE, 8, EGL14.EGL_ALPHA_SIZE, 8,
            EGL14.EGL_RENDERABLE_TYPE, EGL14.EGL_OPENGL_ES2_BIT,
            0x3142 /* EGL_RECORDABLE_ANDROID */, 1,
            EGL14.EGL_NONE
        )
        val configs = arrayOfNulls<EGLConfig>(1); val n = IntArray(1)
        EGL14.eglChooseConfig(eglDisplay, attrs, 0, configs, 0, 1, n, 0)
        return configs[0]!!
    }

    private fun startMuxerIfReady() {
        if (!muxerStarted && tracksAdded >= tracksNeeded) { muxer.start(); muxerStarted = true }
    }

    /** Make the encoder surface current so subsequent GL draws go to the video. */
    fun makeCurrent() { EGL14.eglMakeCurrent(eglDisplay, eglSurface, eglSurface, eglContext) }

    /** Present the just-drawn frame to the encoder (drains output first), nanos = System.nanoTime(). */
    fun swap(nanos: Long) {
        drain(false)
        EGLExt.eglPresentationTimeANDROID(eglDisplay, eglSurface, nanos - startNanos)
        EGL14.eglSwapBuffers(eglDisplay, eglSurface)
    }

    private fun drain(endOfStream: Boolean) {
        if (endOfStream) codec.signalEndOfInputStream()
        while (true) {
            val idx = codec.dequeueOutputBuffer(bufferInfo, if (endOfStream) 10_000 else 0)
            when {
                idx == MediaCodec.INFO_TRY_AGAIN_LATER -> if (!endOfStream) return
                idx == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED -> synchronized(muxerLock) {
                    videoTrack = muxer.addTrack(codec.outputFormat); tracksAdded++; startMuxerIfReady()
                }
                idx >= 0 -> {
                    val buf = codec.getOutputBuffer(idx)!!
                    if (bufferInfo.flags and MediaCodec.BUFFER_FLAG_CODEC_CONFIG != 0) bufferInfo.size = 0
                    if (bufferInfo.size > 0) synchronized(muxerLock) {
                        if (muxerStarted) {
                            buf.position(bufferInfo.offset); buf.limit(bufferInfo.offset + bufferInfo.size)
                            muxer.writeSampleData(videoTrack, buf, bufferInfo)
                        }
                    }
                    codec.releaseOutputBuffer(idx, false)
                    if (bufferInfo.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM != 0) return
                }
            }
        }
    }

    /** Drain end-of-stream, then release everything. Call on the GL thread. */
    fun finish() {
        try { audio?.stopAndRelease() } catch (t: Throwable) { Log.w(TAG, "audio stop", t) }
        try { drain(true) } catch (t: Throwable) { Log.w(TAG, "drain eos", t) }
        try { codec.stop() } catch (_: Throwable) {}
        try { codec.release() } catch (_: Throwable) {}
        try { if (muxerStarted) muxer.stop() } catch (_: Throwable) {}
        try { muxer.release() } catch (_: Throwable) {}
        try { EGL14.eglDestroySurface(eglDisplay, eglSurface) } catch (_: Throwable) {}
        try { inputSurface.release() } catch (_: Throwable) {}
    }

    /** Mic capture → AAC encoder → muxer (own thread). Throws from ctor if the mic can't be opened. */
    private inner class AudioPart {
        private val sampleRate = 44_100
        private val channelCount = 1
        private val record: AudioRecord
        private val aCodec: MediaCodec
        private val aInfo = MediaCodec.BufferInfo()
        private val readSize: Int
        @Volatile private var stop = false
        private var thread: Thread? = null
        private var audioTrack = -1

        init {
            val minBuf = AudioRecord.getMinBufferSize(
                sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT
            )
            check(minBuf > 0) { "bad min buffer" }
            readSize = maxOf(minBuf, 4096)
            @Suppress("MissingPermission") // caller gates on RECORD_AUDIO; failure → video-only
            record = AudioRecord(
                MediaRecorder.AudioSource.MIC, sampleRate,
                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, readSize * 2
            )
            check(record.state == AudioRecord.STATE_INITIALIZED) { "AudioRecord not initialized" }
            val fmt = MediaFormat.createAudioFormat(MediaFormat.MIMETYPE_AUDIO_AAC, sampleRate, channelCount).apply {
                setInteger(MediaFormat.KEY_AAC_PROFILE, MediaCodecInfo.CodecProfileLevel.AACObjectLC)
                setInteger(MediaFormat.KEY_BIT_RATE, 96_000)
                setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, readSize * 2)
            }
            aCodec = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_AUDIO_AAC)
            aCodec.configure(fmt, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
        }

        fun start() {
            aCodec.start(); record.startRecording()
            thread = Thread({ loop() }, "audio-enc").also { it.start() }
        }

        private fun loop() {
            val pcm = ByteArray(readSize)
            while (!stop) {
                val n = record.read(pcm, 0, pcm.size)
                if (n > 0) feed(pcm, n, false)
                drainAudio(false)
            }
            feed(pcm, 0, true)   // queue EOS
            drainAudio(true)
        }

        private fun feed(data: ByteArray, len: Int, eos: Boolean) {
            val ii = aCodec.dequeueInputBuffer(10_000)
            if (ii < 0) return
            val ib = aCodec.getInputBuffer(ii)!!
            ib.clear()
            if (len > 0) ib.put(data, 0, len)
            val ptsUs = (System.nanoTime() - startNanos) / 1_000
            aCodec.queueInputBuffer(ii, 0, len, ptsUs, if (eos) MediaCodec.BUFFER_FLAG_END_OF_STREAM else 0)
        }

        private fun drainAudio(eos: Boolean) {
            while (true) {
                val idx = aCodec.dequeueOutputBuffer(aInfo, if (eos) 10_000 else 0)
                when {
                    idx == MediaCodec.INFO_TRY_AGAIN_LATER -> if (!eos) return
                    idx == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED -> synchronized(muxerLock) {
                        audioTrack = muxer.addTrack(aCodec.outputFormat); tracksAdded++; startMuxerIfReady()
                    }
                    idx >= 0 -> {
                        val buf = aCodec.getOutputBuffer(idx)!!
                        if (aInfo.flags and MediaCodec.BUFFER_FLAG_CODEC_CONFIG != 0) aInfo.size = 0
                        if (aInfo.size > 0) synchronized(muxerLock) {
                            if (muxerStarted) {
                                buf.position(aInfo.offset); buf.limit(aInfo.offset + aInfo.size)
                                muxer.writeSampleData(audioTrack, buf, aInfo)
                            }
                        }
                        aCodec.releaseOutputBuffer(idx, false)
                        if (aInfo.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM != 0) return
                    }
                }
            }
        }

        fun stopAndRelease() {
            stop = true
            try { thread?.join(800) } catch (_: Throwable) {}
            try { record.stop() } catch (_: Throwable) {}
            try { record.release() } catch (_: Throwable) {}
            try { aCodec.stop() } catch (_: Throwable) {}
            try { aCodec.release() } catch (_: Throwable) {}
        }
    }
}
