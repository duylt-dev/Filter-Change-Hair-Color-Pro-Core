package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UITransform {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33040a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33041b;

    public UITransform(long j11, boolean z11) {
        this.f33041b = z11;
        this.f33040a = j11;
    }

    public static UITransform a(UITransform uITransform) {
        long jUITransform_copy = UIVenusJNI.UITransform_copy(c(uITransform), uITransform);
        if (jUITransform_copy == 0) {
            return null;
        }
        return new UITransform(jUITransform_copy, true);
    }

    public static long c(UITransform uITransform) {
        if (uITransform == null) {
            return 0L;
        }
        return uITransform.f33040a;
    }

    public synchronized void b() {
        try {
            long j11 = this.f33040a;
            if (j11 != 0) {
                if (this.f33041b) {
                    this.f33041b = false;
                    UIVenusJNI.delete_UITransform(j11);
                }
                this.f33040a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public float d() {
        return UIVenusJNI.UITransform_getRotation(this.f33040a, this);
    }

    public float e() {
        return UIVenusJNI.UITransform_getScale(this.f33040a, this);
    }

    public float f() {
        return UIVenusJNI.UITransform_getShiftX(this.f33040a, this);
    }

    public void finalize() {
        b();
    }

    public float g() {
        return UIVenusJNI.UITransform_getShiftY(this.f33040a, this);
    }

    public void h(float f11) {
        UIVenusJNI.UITransform_setRotation(this.f33040a, this, f11);
    }

    public void i(float f11) {
        UIVenusJNI.UITransform_setScale(this.f33040a, this, f11);
    }

    public void j(float f11) {
        UIVenusJNI.UITransform_setShiftX(this.f33040a, this, f11);
    }

    public void k(float f11) {
        UIVenusJNI.UITransform_setShiftY(this.f33040a, this, f11);
    }

    public UITransform() {
        this(UIVenusJNI.new_UITransform__SWIG_0(), true);
    }

    public UITransform(UITransform uITransform) {
        this(UIVenusJNI.new_UITransform__SWIG_1(c(uITransform), uITransform), true);
    }
}
