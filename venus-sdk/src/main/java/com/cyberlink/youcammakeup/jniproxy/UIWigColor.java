package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIWigColor {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33048a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33049b;

    public UIWigColor(long j11, boolean z11) {
        this.f33049b = z11;
        this.f33048a = j11;
    }

    public static long b(UIWigColor uIWigColor) {
        if (uIWigColor == null) {
            return 0L;
        }
        return uIWigColor.f33048a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33048a;
            if (j11 != 0) {
                if (this.f33049b) {
                    this.f33049b = false;
                    UIVenusJNI.delete_UIWigColor(j11);
                }
                this.f33048a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void c(int i11) {
        UIVenusJNI.UIWigColor_setBRatio(this.f33048a, this, i11);
    }

    public void d(int i11) {
        UIVenusJNI.UIWigColor_setBrightness(this.f33048a, this, i11);
    }

    public void e(int i11) {
        UIVenusJNI.UIWigColor_setContrastFirstNewy(this.f33048a, this, i11);
    }

    public void f(int i11) {
        UIVenusJNI.UIWigColor_setContrastFirstOldy(this.f33048a, this, i11);
    }

    public void finalize() {
        a();
    }

    public void g(int i11) {
        UIVenusJNI.UIWigColor_setContrastSecondNewy(this.f33048a, this, i11);
    }

    public void h(int i11) {
        UIVenusJNI.UIWigColor_setContrastSecondOldy(this.f33048a, this, i11);
    }

    public void i(int i11) {
        UIVenusJNI.UIWigColor_setGRatio(this.f33048a, this, i11);
    }

    public void j(int i11) {
        UIVenusJNI.UIWigColor_setRRatio(this.f33048a, this, i11);
    }

    public UIWigColor() {
        this(UIVenusJNI.new_UIWigColor__SWIG_0(), true);
    }
}
