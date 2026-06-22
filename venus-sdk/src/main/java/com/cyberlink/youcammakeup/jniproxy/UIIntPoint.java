package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIIntPoint {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32997a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32998b;

    public UIIntPoint(long j11, boolean z11) {
        this.f32998b = z11;
        this.f32997a = j11;
    }

    public static long b(UIIntPoint uIIntPoint) {
        if (uIIntPoint == null) {
            return 0L;
        }
        return uIIntPoint.f32997a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32997a;
            if (j11 != 0) {
                if (this.f32998b) {
                    this.f32998b = false;
                    UIVenusJNI.delete_UIIntPoint(j11);
                }
                this.f32997a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void c(int i11) {
        UIVenusJNI.UIIntPoint_x_set(this.f32997a, this, i11);
    }

    public void d(int i11) {
        UIVenusJNI.UIIntPoint_y_set(this.f32997a, this, i11);
    }

    public void finalize() {
        a();
    }

    public UIIntPoint() {
        this(UIVenusJNI.new_UIIntPoint__SWIG_0(), true);
    }
}
