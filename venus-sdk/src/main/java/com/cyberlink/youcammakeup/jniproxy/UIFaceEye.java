package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceEye {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32928a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32929b;

    public UIFaceEye(long j11, boolean z11) {
        this.f32929b = z11;
        this.f32928a = j11;
    }

    public static long c(UIFaceEye uIFaceEye) {
        if (uIFaceEye == null) {
            return 0L;
        }
        return uIFaceEye.f32928a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32928a;
            if (j11 != 0) {
                if (this.f32929b) {
                    this.f32929b = false;
                    UIVenusJNI.delete_UIFaceEye(j11);
                }
                this.f32928a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFacePoint b() {
        long jUIFaceEye_bottom_get = UIVenusJNI.UIFaceEye_bottom_get(this.f32928a, this);
        if (jUIFaceEye_bottom_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceEye_bottom_get, false);
    }

    public UIFacePoint d() {
        long jUIFaceEye_center_get = UIVenusJNI.UIFaceEye_center_get(this.f32928a, this);
        if (jUIFaceEye_center_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceEye_center_get, false);
    }

    public UIFacePoint e() {
        long jUIFaceEye_left_get = UIVenusJNI.UIFaceEye_left_get(this.f32928a, this);
        if (jUIFaceEye_left_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceEye_left_get, false);
    }

    public UIFacePoint f() {
        long jUIFaceEye_right_get = UIVenusJNI.UIFaceEye_right_get(this.f32928a, this);
        if (jUIFaceEye_right_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceEye_right_get, false);
    }

    public void finalize() {
        a();
    }

    public UIFacePoint g() {
        long jUIFaceEye_top_get = UIVenusJNI.UIFaceEye_top_get(this.f32928a, this);
        if (jUIFaceEye_top_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceEye_top_get, false);
    }

    public void h(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceEye_bottom_set(this.f32928a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void i(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceEye_center_set(this.f32928a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void j(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceEye_left_set(this.f32928a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void k(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceEye_right_set(this.f32928a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void l(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceEye_top_set(this.f32928a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public UIFaceEye() {
        this(UIVenusJNI.new_UIFaceEye__SWIG_0(), true);
    }
}
