package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class APNGDecoderWrapper {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32828a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32829b;

    public APNGDecoderWrapper(long j11, boolean z11) {
        this.f32829b = z11;
        this.f32828a = j11;
    }

    public boolean a() {
        return APNGDecoderJNI.APNGDecoderWrapper_EndDecode(this.f32828a, this);
    }

    public boolean b(Object obj, int i11, FrameInfo frameInfo) {
        return APNGDecoderJNI.APNGDecoderWrapper_GetNextFrame(this.f32828a, this, obj, i11, FrameInfo.b(frameInfo), frameInfo);
    }

    public boolean c(byte[] bArr, HeaderInfo headerInfo) {
        return APNGDecoderJNI.APNGDecoderWrapper_InitializedAPNGHeader(this.f32828a, this, bArr, HeaderInfo.b(headerInfo), headerInfo);
    }

    public synchronized void d() {
        try {
            long j11 = this.f32828a;
            if (j11 != 0) {
                if (this.f32829b) {
                    this.f32829b = false;
                    APNGDecoderJNI.delete_APNGDecoderWrapper(j11);
                }
                this.f32828a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        d();
    }

    public APNGDecoderWrapper() {
        this(APNGDecoderJNI.new_APNGDecoderWrapper__SWIG_0(), true);
    }
}
