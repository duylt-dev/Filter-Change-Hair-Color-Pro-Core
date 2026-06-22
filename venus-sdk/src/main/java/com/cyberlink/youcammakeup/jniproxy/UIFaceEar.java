package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceEar {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32926a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32927b;

    public UIFaceEar(long j11, boolean z11) {
        this.f32927b = z11;
        this.f32926a = j11;
    }

    public static long c(UIFaceEar uIFaceEar) {
        if (uIFaceEar == null) {
            return 0L;
        }
        return uIFaceEar.f32926a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32926a;
            if (j11 != 0) {
                if (this.f32927b) {
                    this.f32927b = false;
                    UIVenusJNI.delete_UIFaceEar(j11);
                }
                this.f32926a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFacePoint b() {
        long jUIFaceEar_bottom_get = UIVenusJNI.UIFaceEar_bottom_get(this.f32926a, this);
        if (jUIFaceEar_bottom_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceEar_bottom_get, false);
    }

    public UIFacePoint d() {
        long jUIFaceEar_top_get = UIVenusJNI.UIFaceEar_top_get(this.f32926a, this);
        if (jUIFaceEar_top_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceEar_top_get, false);
    }

    public void e(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceEar_bottom_set(this.f32926a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void f(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceEar_top_set(this.f32926a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void finalize() {
        a();
    }

    public UIFaceEar() {
        this(UIVenusJNI.new_UIFaceEar__SWIG_0(), true);
    }
}
