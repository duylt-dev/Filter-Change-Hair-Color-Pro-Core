package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIDecodeParamRef {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32907a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32908b;

    public UIDecodeParamRef(long j11, boolean z11) {
        this.f32908b = z11;
        this.f32907a = j11;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32907a;
            if (j11 != 0) {
                if (this.f32908b) {
                    this.f32908b = false;
                    UIImageCodecJNI.delete_UIDecodeParamRef(j11);
                }
                this.f32907a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public long b() {
        return UIImageCodecJNI.UIDecodeParamRef_ulSampleSize_get(this.f32907a, this);
    }

    public void c(UIBytePerPixel uIBytePerPixel) {
        UIImageCodecJNI.UIDecodeParamRef_nBytePerPixel_set(this.f32907a, this, uIBytePerPixel.c());
    }

    public void d(UIImageFormat uIImageFormat) {
        UIImageCodecJNI.UIDecodeParamRef_nFormat_set(this.f32907a, this, uIImageFormat.c());
    }

    public void e(long j11) {
        UIImageCodecJNI.UIDecodeParamRef_ulHeight_set(this.f32907a, this, j11);
    }

    public void f(long j11) {
        UIImageCodecJNI.UIDecodeParamRef_ulSampleSize_set(this.f32907a, this, j11);
    }

    public void finalize() {
        a();
    }

    public void g(long j11) {
        UIImageCodecJNI.UIDecodeParamRef_ulWidth_set(this.f32907a, this, j11);
    }

    public UIDecodeParamRef() {
        this(UIImageCodecJNI.new_UIDecodeParamRef(), true);
    }
}
