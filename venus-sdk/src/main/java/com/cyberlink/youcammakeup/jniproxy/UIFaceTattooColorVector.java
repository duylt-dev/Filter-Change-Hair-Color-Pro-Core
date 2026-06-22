package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceTattooColorVector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32954a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32955b;

    public UIFaceTattooColorVector(long j11, boolean z11) {
        this.f32955b = z11;
        this.f32954a = j11;
    }

    public static long c(UIFaceTattooColorVector uIFaceTattooColorVector) {
        if (uIFaceTattooColorVector == null) {
            return 0L;
        }
        return uIFaceTattooColorVector.f32954a;
    }

    public void a(UIFaceTattooColor uIFaceTattooColor) {
        UIVenusJNI.UIFaceTattooColorVector_add(this.f32954a, this, UIFaceTattooColor.b(uIFaceTattooColor), uIFaceTattooColor);
    }

    public synchronized void b() {
        try {
            long j11 = this.f32954a;
            if (j11 != 0) {
                if (this.f32955b) {
                    this.f32955b = false;
                    UIVenusJNI.delete_UIFaceTattooColorVector(j11);
                }
                this.f32954a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        b();
    }

    public UIFaceTattooColorVector() {
        this(UIVenusJNI.new_UIFaceTattooColorVector__SWIG_0(), true);
    }
}
