package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceForehead {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32930a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32931b;

    public UIFaceForehead(long j11, boolean z11) {
        this.f32931b = z11;
        this.f32930a = j11;
    }

    public static long b(UIFaceForehead uIFaceForehead) {
        if (uIFaceForehead == null) {
            return 0L;
        }
        return uIFaceForehead.f32930a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32930a;
            if (j11 != 0) {
                if (this.f32931b) {
                    this.f32931b = false;
                    UIVenusJNI.delete_UIFaceForehead(j11);
                }
                this.f32930a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFacePoint c() {
        long jUIFaceForehead_left_get = UIVenusJNI.UIFaceForehead_left_get(this.f32930a, this);
        if (jUIFaceForehead_left_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceForehead_left_get, false);
    }

    public UIFacePoint d() {
        long jUIFaceForehead_middle_get = UIVenusJNI.UIFaceForehead_middle_get(this.f32930a, this);
        if (jUIFaceForehead_middle_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceForehead_middle_get, false);
    }

    public UIFacePoint e() {
        long jUIFaceForehead_right_get = UIVenusJNI.UIFaceForehead_right_get(this.f32930a, this);
        if (jUIFaceForehead_right_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceForehead_right_get, false);
    }

    public void f(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceForehead_left_set(this.f32930a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void finalize() {
        a();
    }

    public void g(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceForehead_middle_set(this.f32930a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void h(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceForehead_right_set(this.f32930a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public UIFaceForehead() {
        this(UIVenusJNI.new_UIFaceForehead__SWIG_0(), true);
    }
}
