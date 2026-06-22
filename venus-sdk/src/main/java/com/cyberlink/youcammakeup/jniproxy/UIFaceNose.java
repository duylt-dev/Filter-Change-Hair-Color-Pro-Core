package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceNose {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32942a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32943b;

    public UIFaceNose(long j11, boolean z11) {
        this.f32943b = z11;
        this.f32942a = j11;
    }

    public static long d(UIFaceNose uIFaceNose) {
        if (uIFaceNose == null) {
            return 0L;
        }
        return uIFaceNose.f32942a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32942a;
            if (j11 != 0) {
                if (this.f32943b) {
                    this.f32943b = false;
                    UIVenusJNI.delete_UIFaceNose(j11);
                }
                this.f32942a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFacePoint b() {
        long jUIFaceNose_bottom_get = UIVenusJNI.UIFaceNose_bottom_get(this.f32942a, this);
        if (jUIFaceNose_bottom_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceNose_bottom_get, false);
    }

    public UIFacePoint c() {
        long jUIFaceNose_bridgeTop_get = UIVenusJNI.UIFaceNose_bridgeTop_get(this.f32942a, this);
        if (jUIFaceNose_bridgeTop_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceNose_bridgeTop_get, false);
    }

    public UIFacePoint e() {
        long jUIFaceNose_left_get = UIVenusJNI.UIFaceNose_left_get(this.f32942a, this);
        if (jUIFaceNose_left_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceNose_left_get, false);
    }

    public UIFacePoint f() {
        long jUIFaceNose_right_get = UIVenusJNI.UIFaceNose_right_get(this.f32942a, this);
        if (jUIFaceNose_right_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceNose_right_get, false);
    }

    public void finalize() {
        a();
    }

    public UIFacePoint g() {
        long jUIFaceNose_top_get = UIVenusJNI.UIFaceNose_top_get(this.f32942a, this);
        if (jUIFaceNose_top_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceNose_top_get, false);
    }

    public void h(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceNose_bottom_set(this.f32942a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void i(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceNose_bridgeTop_set(this.f32942a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void j(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceNose_left_set(this.f32942a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void k(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceNose_right_set(this.f32942a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void l(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceNose_top_set(this.f32942a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public UIFaceNose() {
        this(UIVenusJNI.new_UIFaceNose__SWIG_0(), true);
    }
}
