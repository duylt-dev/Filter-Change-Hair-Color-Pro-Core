package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceMouth {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32940a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32941b;

    public UIFaceMouth(long j11, boolean z11) {
        this.f32941b = z11;
        this.f32940a = j11;
    }

    public static long d(UIFaceMouth uIFaceMouth) {
        if (uIFaceMouth == null) {
            return 0L;
        }
        return uIFaceMouth.f32940a;
    }

    public void A(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_interpTopLeft_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void B(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_interpTopRight_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void C(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_interpUpperLeft_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void D(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_interpUpperRight_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void E(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_leftCorner_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void F(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_rightCorner_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void G(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_topLip1_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void H(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_topLip2_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public synchronized void a() {
        try {
            long j11 = this.f32940a;
            if (j11 != 0) {
                if (this.f32941b) {
                    this.f32941b = false;
                    UIVenusJNI.delete_UIFaceMouth(j11);
                }
                this.f32940a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFacePoint b() {
        long jUIFaceMouth_bottomLip1_get = UIVenusJNI.UIFaceMouth_bottomLip1_get(this.f32940a, this);
        if (jUIFaceMouth_bottomLip1_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_bottomLip1_get, false);
    }

    public UIFacePoint c() {
        long jUIFaceMouth_bottomLip2_get = UIVenusJNI.UIFaceMouth_bottomLip2_get(this.f32940a, this);
        if (jUIFaceMouth_bottomLip2_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_bottomLip2_get, false);
    }

    public UIFacePoint e() {
        long jUIFaceMouth_interpBottomLeft_get = UIVenusJNI.UIFaceMouth_interpBottomLeft_get(this.f32940a, this);
        if (jUIFaceMouth_interpBottomLeft_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_interpBottomLeft_get, false);
    }

    public UIFacePoint f() {
        long jUIFaceMouth_interpBottomRight_get = UIVenusJNI.UIFaceMouth_interpBottomRight_get(this.f32940a, this);
        if (jUIFaceMouth_interpBottomRight_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_interpBottomRight_get, false);
    }

    public void finalize() {
        a();
    }

    public UIFacePoint g() {
        long jUIFaceMouth_interpInnerLeft_get = UIVenusJNI.UIFaceMouth_interpInnerLeft_get(this.f32940a, this);
        if (jUIFaceMouth_interpInnerLeft_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_interpInnerLeft_get, false);
    }

    public UIFacePoint h() {
        long jUIFaceMouth_interpInnerRight_get = UIVenusJNI.UIFaceMouth_interpInnerRight_get(this.f32940a, this);
        if (jUIFaceMouth_interpInnerRight_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_interpInnerRight_get, false);
    }

    public UIFacePoint i() {
        long jUIFaceMouth_interpLowerLeft_get = UIVenusJNI.UIFaceMouth_interpLowerLeft_get(this.f32940a, this);
        if (jUIFaceMouth_interpLowerLeft_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_interpLowerLeft_get, false);
    }

    public UIFacePoint j() {
        long jUIFaceMouth_interpLowerRight_get = UIVenusJNI.UIFaceMouth_interpLowerRight_get(this.f32940a, this);
        if (jUIFaceMouth_interpLowerRight_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_interpLowerRight_get, false);
    }

    public UIFacePoint k() {
        long jUIFaceMouth_interpTopLeft_get = UIVenusJNI.UIFaceMouth_interpTopLeft_get(this.f32940a, this);
        if (jUIFaceMouth_interpTopLeft_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_interpTopLeft_get, false);
    }

    public UIFacePoint l() {
        long jUIFaceMouth_interpTopRight_get = UIVenusJNI.UIFaceMouth_interpTopRight_get(this.f32940a, this);
        if (jUIFaceMouth_interpTopRight_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_interpTopRight_get, false);
    }

    public UIFacePoint m() {
        long jUIFaceMouth_interpUpperLeft_get = UIVenusJNI.UIFaceMouth_interpUpperLeft_get(this.f32940a, this);
        if (jUIFaceMouth_interpUpperLeft_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_interpUpperLeft_get, false);
    }

    public UIFacePoint n() {
        long jUIFaceMouth_interpUpperRight_get = UIVenusJNI.UIFaceMouth_interpUpperRight_get(this.f32940a, this);
        if (jUIFaceMouth_interpUpperRight_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_interpUpperRight_get, false);
    }

    public UIFacePoint o() {
        long jUIFaceMouth_leftCorner_get = UIVenusJNI.UIFaceMouth_leftCorner_get(this.f32940a, this);
        if (jUIFaceMouth_leftCorner_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_leftCorner_get, false);
    }

    public UIFacePoint p() {
        long jUIFaceMouth_rightCorner_get = UIVenusJNI.UIFaceMouth_rightCorner_get(this.f32940a, this);
        if (jUIFaceMouth_rightCorner_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_rightCorner_get, false);
    }

    public UIFacePoint q() {
        long jUIFaceMouth_topLip1_get = UIVenusJNI.UIFaceMouth_topLip1_get(this.f32940a, this);
        if (jUIFaceMouth_topLip1_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_topLip1_get, false);
    }

    public UIFacePoint r() {
        long jUIFaceMouth_topLip2_get = UIVenusJNI.UIFaceMouth_topLip2_get(this.f32940a, this);
        if (jUIFaceMouth_topLip2_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceMouth_topLip2_get, false);
    }

    public void s(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_bottomLip1_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void t(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_bottomLip2_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void u(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_interpBottomLeft_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void v(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_interpBottomRight_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void w(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_interpInnerLeft_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void x(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_interpInnerRight_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void y(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_interpLowerLeft_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void z(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceMouth_interpLowerRight_set(this.f32940a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public UIFaceMouth() {
        this(UIVenusJNI.new_UIFaceMouth__SWIG_0(), true);
    }
}
