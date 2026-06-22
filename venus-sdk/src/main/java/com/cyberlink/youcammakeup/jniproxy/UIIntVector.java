package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIIntVector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33001a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33002b;

    public UIIntVector(long j11, boolean z11) {
        this.f33002b = z11;
        this.f33001a = j11;
    }

    public static long d(UIIntVector uIIntVector) {
        if (uIIntVector == null) {
            return 0L;
        }
        return uIIntVector.f33001a;
    }

    public void a(int i11) {
        UIVenusJNI.UIIntVector_add(this.f33001a, this, i11);
    }

    public synchronized void b() {
        try {
            long j11 = this.f33001a;
            if (j11 != 0) {
                if (this.f33002b) {
                    this.f33002b = false;
                    UIVenusJNI.delete_UIIntVector(j11);
                }
                this.f33001a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public int c(int i11) {
        return UIVenusJNI.UIIntVector_get(this.f33001a, this, i11);
    }

    public long e() {
        return UIVenusJNI.UIIntVector_size(this.f33001a, this);
    }

    public void finalize() {
        b();
    }

    public UIIntVector() {
        this(UIVenusJNI.new_UIIntVector__SWIG_0(), true);
    }
}
