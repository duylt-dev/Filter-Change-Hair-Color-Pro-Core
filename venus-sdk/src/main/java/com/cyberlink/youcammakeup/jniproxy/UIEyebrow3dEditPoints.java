package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIEyebrow3dEditPoints {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32909a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32910b;

    public UIEyebrow3dEditPoints(long j11, boolean z11) {
        this.f32910b = z11;
        this.f32909a = j11;
    }

    public static long d(UIEyebrow3dEditPoints uIEyebrow3dEditPoints) {
        if (uIEyebrow3dEditPoints == null) {
            return 0L;
        }
        return uIEyebrow3dEditPoints.f32909a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32909a;
            if (j11 != 0) {
                if (this.f32910b) {
                    this.f32910b = false;
                    UIVenusJNI.delete_UIEyebrow3dEditPoints(j11);
                }
                this.f32909a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public boolean b(UIEyebrow3dEditPoints uIEyebrow3dEditPoints) {
        return UIVenusJNI.UIEyebrow3dEditPoints_equals(this.f32909a, this, d(uIEyebrow3dEditPoints), uIEyebrow3dEditPoints);
    }

    public UIFacePoint c() {
        long jUIEyebrow3dEditPoints_bottom_get = UIVenusJNI.UIEyebrow3dEditPoints_bottom_get(this.f32909a, this);
        if (jUIEyebrow3dEditPoints_bottom_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIEyebrow3dEditPoints_bottom_get, false);
    }

    public UIFacePoint e() {
        long jUIEyebrow3dEditPoints_inner_get = UIVenusJNI.UIEyebrow3dEditPoints_inner_get(this.f32909a, this);
        if (jUIEyebrow3dEditPoints_inner_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIEyebrow3dEditPoints_inner_get, false);
    }

    public UIFacePoint f() {
        long jUIEyebrow3dEditPoints_outer_get = UIVenusJNI.UIEyebrow3dEditPoints_outer_get(this.f32909a, this);
        if (jUIEyebrow3dEditPoints_outer_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIEyebrow3dEditPoints_outer_get, false);
    }

    public void finalize() {
        a();
    }

    public UIFacePoint g() {
        long jUIEyebrow3dEditPoints_topInner_get = UIVenusJNI.UIEyebrow3dEditPoints_topInner_get(this.f32909a, this);
        if (jUIEyebrow3dEditPoints_topInner_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIEyebrow3dEditPoints_topInner_get, false);
    }

    public UIFacePoint h() {
        long jUIEyebrow3dEditPoints_topMiddle_get = UIVenusJNI.UIEyebrow3dEditPoints_topMiddle_get(this.f32909a, this);
        if (jUIEyebrow3dEditPoints_topMiddle_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIEyebrow3dEditPoints_topMiddle_get, false);
    }

    public UIFacePoint i() {
        long jUIEyebrow3dEditPoints_topOuter_get = UIVenusJNI.UIEyebrow3dEditPoints_topOuter_get(this.f32909a, this);
        if (jUIEyebrow3dEditPoints_topOuter_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIEyebrow3dEditPoints_topOuter_get, false);
    }

    public void j(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIEyebrow3dEditPoints_bottom_set(this.f32909a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void k(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIEyebrow3dEditPoints_inner_set(this.f32909a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void l(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIEyebrow3dEditPoints_outer_set(this.f32909a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void m(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIEyebrow3dEditPoints_topInner_set(this.f32909a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void n(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIEyebrow3dEditPoints_topMiddle_set(this.f32909a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void o(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIEyebrow3dEditPoints_topOuter_set(this.f32909a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public UIEyebrow3dEditPoints() {
        this(UIVenusJNI.new_UIEyebrow3dEditPoints__SWIG_0(), true);
    }

    public UIEyebrow3dEditPoints(UIEyebrow3dEditPoints uIEyebrow3dEditPoints) {
        this(UIVenusJNI.new_UIEyebrow3dEditPoints__SWIG_1(d(uIEyebrow3dEditPoints), uIEyebrow3dEditPoints), true);
    }
}
