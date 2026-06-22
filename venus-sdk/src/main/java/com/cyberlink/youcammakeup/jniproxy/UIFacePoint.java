package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFacePoint {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32944a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32945b;

    public UIFacePoint(long j11, boolean z11) {
        this.f32945b = z11;
        this.f32944a = j11;
    }

    public static long b(UIFacePoint uIFacePoint) {
        if (uIFacePoint == null) {
            return 0L;
        }
        return uIFacePoint.f32944a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32944a;
            if (j11 != 0) {
                if (this.f32945b) {
                    this.f32945b = false;
                    UIVenusJNI.delete_UIFacePoint(j11);
                }
                this.f32944a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public float c() {
        return UIVenusJNI.UIFacePoint_x_get(this.f32944a, this);
    }

    public float d() {
        return UIVenusJNI.UIFacePoint_y_get(this.f32944a, this);
    }

    public void e(float f11) {
        UIVenusJNI.UIFacePoint_x_set(this.f32944a, this, f11);
    }

    public void f(float f11) {
        UIVenusJNI.UIFacePoint_y_set(this.f32944a, this, f11);
    }

    public void finalize() {
        a();
    }

    public UIFacePoint() {
        this(UIVenusJNI.new_UIFacePoint__SWIG_0(), true);
    }

    public UIFacePoint(UIFacePoint uIFacePoint) {
        this(UIVenusJNI.new_UIFacePoint__SWIG_1(b(uIFacePoint), uIFacePoint), true);
    }
}
