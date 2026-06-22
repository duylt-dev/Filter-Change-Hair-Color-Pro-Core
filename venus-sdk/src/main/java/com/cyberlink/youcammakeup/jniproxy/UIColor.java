package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIColor {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32903a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32904b;

    public UIColor(long j11, boolean z11) {
        this.f32904b = z11;
        this.f32903a = j11;
    }

    public static long b(UIColor uIColor) {
        if (uIColor == null) {
            return 0L;
        }
        return uIColor.f32903a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32903a;
            if (j11 != 0) {
                if (this.f32904b) {
                    this.f32904b = false;
                    UIVenusJNI.delete_UIColor(j11);
                }
                this.f32903a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void c(int i11) {
        UIVenusJNI.UIColor_setBLevel(this.f32903a, this, i11);
    }

    public void d(int i11) {
        UIVenusJNI.UIColor_setGLevel(this.f32903a, this, i11);
    }

    public void e(int i11) {
        UIVenusJNI.UIColor_setRLevel(this.f32903a, this, i11);
    }

    public void finalize() {
        a();
    }

    public UIColor() {
        this(UIVenusJNI.new_UIColor__SWIG_0(), true);
    }

    public UIColor(int i11, int i12, int i13) {
        this(UIVenusJNI.new_UIColor__SWIG_1(i11, i12, i13), true);
    }
}
