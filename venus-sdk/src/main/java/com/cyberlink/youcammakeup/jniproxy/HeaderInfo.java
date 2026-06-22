package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class HeaderInfo {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32864a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32865b;

    public HeaderInfo(long j11, boolean z11) {
        this.f32865b = z11;
        this.f32864a = j11;
    }

    public static long b(HeaderInfo headerInfo) {
        if (headerInfo == null) {
            return 0L;
        }
        return headerInfo.f32864a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32864a;
            if (j11 != 0) {
                if (this.f32865b) {
                    this.f32865b = false;
                    APNGDecoderJNI.delete_HeaderInfo(j11);
                }
                this.f32864a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public int c() {
        return APNGDecoderJNI.HeaderInfo_height_get(this.f32864a, this);
    }

    public int d() {
        return APNGDecoderJNI.HeaderInfo_image_count_get(this.f32864a, this);
    }

    public int e() {
        return APNGDecoderJNI.HeaderInfo_width_get(this.f32864a, this);
    }

    public void finalize() {
        a();
    }

    public HeaderInfo() {
        this(APNGDecoderJNI.new_HeaderInfo__SWIG_0(), true);
    }
}
