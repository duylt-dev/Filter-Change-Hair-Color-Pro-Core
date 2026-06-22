package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIIrisRadius {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33008a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33009b;

    public UIIrisRadius(long j11, boolean z11) {
        this.f33009b = z11;
        this.f33008a = j11;
    }

    public static long b(UIIrisRadius uIIrisRadius) {
        if (uIIrisRadius == null) {
            return 0L;
        }
        return uIIrisRadius.f33008a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33008a;
            if (j11 != 0) {
                if (this.f33009b) {
                    this.f33009b = false;
                    UIVenusJNI.delete_UIIrisRadius(j11);
                }
                this.f33008a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public float c() {
        return UIVenusJNI.UIIrisRadius_getValue(this.f33008a, this);
    }

    public void d(float f11) {
        UIVenusJNI.UIIrisRadius_setValue(this.f33008a, this, f11);
    }

    public void finalize() {
        a();
    }

    public UIIrisRadius() {
        this(UIVenusJNI.new_UIIrisRadius__SWIG_0(), true);
    }

    public UIIrisRadius(UIIrisRadius uIIrisRadius) {
        this(UIVenusJNI.new_UIIrisRadius__SWIG_1(b(uIIrisRadius), uIIrisRadius), true);
    }
}
