package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class BufferDataParam {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32841a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32842b;

    public BufferDataParam(long j11, boolean z11) {
        this.f32842b = z11;
        this.f32841a = j11;
    }

    public static long b(BufferDataParam bufferDataParam) {
        if (bufferDataParam == null) {
            return 0L;
        }
        return bufferDataParam.f32841a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32841a;
            if (j11 != 0) {
                if (this.f32842b) {
                    this.f32842b = false;
                    UIImageRetouchJNI.delete_BufferDataParam(j11);
                }
                this.f32841a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        a();
    }

    public BufferDataParam(IImageBuffer iImageBuffer) {
        this(UIImageRetouchJNI.new_BufferDataParam__SWIG_1(IImageBuffer.b(iImageBuffer), iImageBuffer), true);
    }
}
