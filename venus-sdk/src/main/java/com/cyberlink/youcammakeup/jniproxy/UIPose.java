package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIPose {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33018a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33019b;

    public UIPose(long j11, boolean z11) {
        this.f33019b = z11;
        this.f33018a = j11;
    }

    public static long b(UIPose uIPose) {
        if (uIPose == null) {
            return 0L;
        }
        return uIPose.f33018a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33018a;
            if (j11 != 0) {
                if (this.f33019b) {
                    this.f33019b = false;
                    UIVenusJNI.delete_UIPose(j11);
                }
                this.f33018a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public float c() {
        return UIVenusJNI.UIPose_pitchDegree_get(this.f33018a, this);
    }

    public float d() {
        return UIVenusJNI.UIPose_rollDegree_get(this.f33018a, this);
    }

    public float e() {
        return UIVenusJNI.UIPose_yawDegree_get(this.f33018a, this);
    }

    public void finalize() {
        a();
    }

    public UIPose() {
        this(UIVenusJNI.new_UIPose__SWIG_0(), true);
    }

    public UIPose(UIPose uIPose) {
        this(UIVenusJNI.new_UIPose__SWIG_1(b(uIPose), uIPose), true);
    }
}
