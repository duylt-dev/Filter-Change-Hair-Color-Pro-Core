package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIIntPointVector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32999a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33000b;

    public UIIntPointVector(long j11, boolean z11) {
        this.f33000b = z11;
        this.f32999a = j11;
    }

    public static long c(UIIntPointVector uIIntPointVector) {
        if (uIIntPointVector == null) {
            return 0L;
        }
        return uIIntPointVector.f32999a;
    }

    public void a(UIIntPoint uIIntPoint) {
        UIVenusJNI.UIIntPointVector_add(this.f32999a, this, UIIntPoint.b(uIIntPoint), uIIntPoint);
    }

    public synchronized void b() {
        try {
            long j11 = this.f32999a;
            if (j11 != 0) {
                if (this.f33000b) {
                    this.f33000b = false;
                    UIVenusJNI.delete_UIIntPointVector(j11);
                }
                this.f32999a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        b();
    }

    public UIIntPointVector() {
        this(UIVenusJNI.new_UIIntPointVector__SWIG_0(), true);
    }
}
