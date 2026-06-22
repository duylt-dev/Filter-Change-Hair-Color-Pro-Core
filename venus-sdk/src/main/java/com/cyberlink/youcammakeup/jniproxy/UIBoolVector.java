package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIBoolVector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32895a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32896b;

    public UIBoolVector(long j11, boolean z11) {
        this.f32896b = z11;
        this.f32895a = j11;
    }

    public static long d(UIBoolVector uIBoolVector) {
        if (uIBoolVector == null) {
            return 0L;
        }
        return uIBoolVector.f32895a;
    }

    public void a(boolean z11) {
        UIVenusJNI.UIBoolVector_add(this.f32895a, this, z11);
    }

    public synchronized void b() {
        try {
            long j11 = this.f32895a;
            if (j11 != 0) {
                if (this.f32896b) {
                    this.f32896b = false;
                    UIVenusJNI.delete_UIBoolVector(j11);
                }
                this.f32895a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public boolean c(int i11) {
        return UIVenusJNI.UIBoolVector_get(this.f32895a, this, i11);
    }

    public void finalize() {
        b();
    }

    public UIBoolVector() {
        this(UIVenusJNI.new_UIBoolVector__SWIG_0(), true);
    }
}
