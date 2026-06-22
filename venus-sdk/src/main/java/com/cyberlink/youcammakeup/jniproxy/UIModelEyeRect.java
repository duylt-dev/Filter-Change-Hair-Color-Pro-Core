package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIModelEyeRect {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33016a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33017b;

    public UIModelEyeRect(long j11, boolean z11) {
        this.f33017b = z11;
        this.f33016a = j11;
    }

    public static long b(UIModelEyeRect uIModelEyeRect) {
        if (uIModelEyeRect == null) {
            return 0L;
        }
        return uIModelEyeRect.f33016a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33016a;
            if (j11 != 0) {
                if (this.f33017b) {
                    this.f33017b = false;
                    UIVenusJNI.delete_UIModelEyeRect(j11);
                }
                this.f33016a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void c(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIModelEyeRect_bottom_set(this.f33016a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void d(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIModelEyeRect_left_set(this.f33016a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void e(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIModelEyeRect_right_set(this.f33016a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void f(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIModelEyeRect_top_set(this.f33016a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void finalize() {
        a();
    }

    public UIModelEyeRect() {
        this(UIVenusJNI.new_UIModelEyeRect__SWIG_0(), true);
    }
}
