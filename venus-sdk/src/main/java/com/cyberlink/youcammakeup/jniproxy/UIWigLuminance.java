package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIWigLuminance {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33055a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33056b;

    public UIWigLuminance(long j11, boolean z11) {
        this.f33056b = z11;
        this.f33055a = j11;
    }

    public static long b(UIWigLuminance uIWigLuminance) {
        if (uIWigLuminance == null) {
            return 0L;
        }
        return uIWigLuminance.f33055a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33055a;
            if (j11 != 0) {
                if (this.f33056b) {
                    this.f33056b = false;
                    UIVenusJNI.delete_UIWigLuminance(j11);
                }
                this.f33055a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public int c() {
        return UIVenusJNI.UIWigLuminance_getValue(this.f33055a, this);
    }

    public void d(int i11) {
        UIVenusJNI.UIWigLuminance_setValue(this.f33055a, this, i11);
    }

    public void finalize() {
        a();
    }

    public UIWigLuminance() {
        this(UIVenusJNI.new_UIWigLuminance__SWIG_0(), true);
    }

    public UIWigLuminance(UIWigLuminance uIWigLuminance) {
        this(UIVenusJNI.new_UIWigLuminance__SWIG_1(b(uIWigLuminance), uIWigLuminance), true);
    }
}
