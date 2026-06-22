package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceBrow {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32922a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32923b;

    public UIFaceBrow(long j11, boolean z11) {
        this.f32923b = z11;
        this.f32922a = j11;
    }

    public static long c(UIFaceBrow uIFaceBrow) {
        if (uIFaceBrow == null) {
            return 0L;
        }
        return uIFaceBrow.f32922a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32922a;
            if (j11 != 0) {
                if (this.f32923b) {
                    this.f32923b = false;
                    UIVenusJNI.delete_UIFaceBrow(j11);
                }
                this.f32922a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFacePoint b() {
        long jUIFaceBrow_bottom_get = UIVenusJNI.UIFaceBrow_bottom_get(this.f32922a, this);
        if (jUIFaceBrow_bottom_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceBrow_bottom_get, false);
    }

    public UIFacePoint d() {
        long jUIFaceBrow_left_get = UIVenusJNI.UIFaceBrow_left_get(this.f32922a, this);
        if (jUIFaceBrow_left_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceBrow_left_get, false);
    }

    public UIFacePoint e() {
        long jUIFaceBrow_right_get = UIVenusJNI.UIFaceBrow_right_get(this.f32922a, this);
        if (jUIFaceBrow_right_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceBrow_right_get, false);
    }

    public UIFacePoint f() {
        long jUIFaceBrow_top_get = UIVenusJNI.UIFaceBrow_top_get(this.f32922a, this);
        if (jUIFaceBrow_top_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceBrow_top_get, false);
    }

    public void finalize() {
        a();
    }

    public void g(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceBrow_bottom_set(this.f32922a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void h(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceBrow_left_set(this.f32922a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void i(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceBrow_right_set(this.f32922a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void j(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceBrow_top_set(this.f32922a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public UIFaceBrow() {
        this(UIVenusJNI.new_UIFaceBrow__SWIG_0(), true);
    }

    public UIFaceBrow(UIFaceBrow uIFaceBrow) {
        this(UIVenusJNI.new_UIFaceBrow__SWIG_1(c(uIFaceBrow), uIFaceBrow), true);
    }
}
