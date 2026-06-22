package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class CUIAthena {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32844a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32845b;

    public CUIAthena(long j11, boolean z11) {
        this.f32845b = z11;
        this.f32844a = j11;
    }

    public boolean a(float f11, float f12) {
        return UIAthenaJNI.CUIAthena_addStrokePoint(this.f32844a, this, f11, f12);
    }

    public boolean b(AthenaStrokeType athenaStrokeType, int i11) {
        return UIAthenaJNI.CUIAthena_beginStroke(this.f32844a, this, athenaStrokeType.c(), i11);
    }

    public boolean c() {
        return UIAthenaJNI.CUIAthena_clear(this.f32844a, this);
    }

    public synchronized void d() {
        try {
            long j11 = this.f32844a;
            if (j11 != 0) {
                if (this.f32845b) {
                    this.f32845b = false;
                    UIAthenaJNI.delete_CUIAthena(j11);
                }
                this.f32844a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public boolean e() {
        return UIAthenaJNI.CUIAthena_endStroke(this.f32844a, this);
    }

    public Object f(boolean z11) {
        return UIAthenaJNI.CUIAthena_getMask(this.f32844a, this, z11);
    }

    public void finalize() {
        d();
    }

    public boolean g(CImageBuffer cImageBuffer) {
        return UIAthenaJNI.CUIAthena_initialize(this.f32844a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public boolean h() {
        return UIAthenaJNI.CUIAthena_invert(this.f32844a, this);
    }

    public CUIAthena() {
        this(UIAthenaJNI.new_CUIAthena__SWIG_0(), true);
    }
}
