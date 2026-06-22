package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class FrameInfo {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32862a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32863b;

    public FrameInfo(long j11, boolean z11) {
        this.f32863b = z11;
        this.f32862a = j11;
    }

    public static long b(FrameInfo frameInfo) {
        if (frameInfo == null) {
            return 0L;
        }
        return frameInfo.f32862a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32862a;
            if (j11 != 0) {
                if (this.f32863b) {
                    this.f32863b = false;
                    APNGDecoderJNI.delete_FrameInfo(j11);
                }
                this.f32862a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public short c() {
        return APNGDecoderJNI.FrameInfo_delay_den_get(this.f32862a, this);
    }

    public short d() {
        return APNGDecoderJNI.FrameInfo_delay_num_get(this.f32862a, this);
    }

    public void finalize() {
        a();
    }

    public FrameInfo() {
        this(APNGDecoderJNI.new_FrameInfo__SWIG_0(), true);
    }
}
