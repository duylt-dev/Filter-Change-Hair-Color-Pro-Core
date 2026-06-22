package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIWigModelAnchor {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33057a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33058b;

    public UIWigModelAnchor(long j11, boolean z11) {
        this.f33058b = z11;
        this.f33057a = j11;
    }

    public static long b(UIWigModelAnchor uIWigModelAnchor) {
        if (uIWigModelAnchor == null) {
            return 0L;
        }
        return uIWigModelAnchor.f33057a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33057a;
            if (j11 != 0) {
                if (this.f33058b) {
                    this.f33058b = false;
                    UIVenusJNI.delete_UIWigModelAnchor(j11);
                }
                this.f33057a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFacePoint c() {
        long jUIWigModelAnchor_leftEyeCenter_get = UIVenusJNI.UIWigModelAnchor_leftEyeCenter_get(this.f33057a, this);
        if (jUIWigModelAnchor_leftEyeCenter_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIWigModelAnchor_leftEyeCenter_get, false);
    }

    public UIFacePoint d() {
        long jUIWigModelAnchor_rightEyeCenter_get = UIVenusJNI.UIWigModelAnchor_rightEyeCenter_get(this.f33057a, this);
        if (jUIWigModelAnchor_rightEyeCenter_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIWigModelAnchor_rightEyeCenter_get, false);
    }

    public void e(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIWigModelAnchor_leftEyeCenter_set(this.f33057a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void f(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIWigModelAnchor_leftFaceShape_set(this.f33057a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void finalize() {
        a();
    }

    public void g(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIWigModelAnchor_rightEyeCenter_set(this.f33057a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void h(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIWigModelAnchor_rightFaceShape_set(this.f33057a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public UIWigModelAnchor() {
        this(UIVenusJNI.new_UIWigModelAnchor__SWIG_0(), true);
    }
}
