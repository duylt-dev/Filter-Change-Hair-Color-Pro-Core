package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIColorVector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32905a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32906b;

    public UIColorVector(long j11, boolean z11) {
        this.f32906b = z11;
        this.f32905a = j11;
    }

    public static long c(UIColorVector uIColorVector) {
        if (uIColorVector == null) {
            return 0L;
        }
        return uIColorVector.f32905a;
    }

    public void a(UIColor uIColor) {
        UIVenusJNI.UIColorVector_add(this.f32905a, this, UIColor.b(uIColor), uIColor);
    }

    public synchronized void b() {
        try {
            long j11 = this.f32905a;
            if (j11 != 0) {
                if (this.f32906b) {
                    this.f32906b = false;
                    UIVenusJNI.delete_UIColorVector(j11);
                }
                this.f32905a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        b();
    }

    public UIColorVector() {
        this(UIVenusJNI.new_UIColorVector__SWIG_0(), true);
    }
}
