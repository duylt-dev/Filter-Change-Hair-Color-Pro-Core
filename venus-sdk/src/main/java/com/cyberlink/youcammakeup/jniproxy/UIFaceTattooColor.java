package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceTattooColor {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32952a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32953b;

    public UIFaceTattooColor(long j11, boolean z11) {
        this.f32953b = z11;
        this.f32952a = j11;
    }

    public static long b(UIFaceTattooColor uIFaceTattooColor) {
        if (uIFaceTattooColor == null) {
            return 0L;
        }
        return uIFaceTattooColor.f32952a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32952a;
            if (j11 != 0) {
                if (this.f32953b) {
                    this.f32953b = false;
                    UIVenusJNI.delete_UIFaceTattooColor(j11);
                }
                this.f32952a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void c(int i11) {
        UIVenusJNI.UIFaceTattooColor_setBRatio(this.f32952a, this, i11);
    }

    public void d(int i11) {
        UIVenusJNI.UIFaceTattooColor_setBrightness(this.f32952a, this, i11);
    }

    public void e(boolean z11) {
        UIVenusJNI.UIFaceTattooColor_setColorAdjustable(this.f32952a, this, z11);
    }

    public void f(int i11) {
        UIVenusJNI.UIFaceTattooColor_setContrastFirstNewy(this.f32952a, this, i11);
    }

    public void finalize() {
        a();
    }

    public void g(int i11) {
        UIVenusJNI.UIFaceTattooColor_setContrastFirstOldy(this.f32952a, this, i11);
    }

    public void h(int i11) {
        UIVenusJNI.UIFaceTattooColor_setContrastSecondNewy(this.f32952a, this, i11);
    }

    public void i(int i11) {
        UIVenusJNI.UIFaceTattooColor_setContrastSecondOldy(this.f32952a, this, i11);
    }

    public void j(int i11) {
        UIVenusJNI.UIFaceTattooColor_setGRatio(this.f32952a, this, i11);
    }

    public void k(int i11) {
        UIVenusJNI.UIFaceTattooColor_setLuminanceParameter(this.f32952a, this, i11);
    }

    public void l(int i11) {
        UIVenusJNI.UIFaceTattooColor_setRRatio(this.f32952a, this, i11);
    }

    public UIFaceTattooColor() {
        this(UIVenusJNI.new_UIFaceTattooColor__SWIG_0(), true);
    }
}
