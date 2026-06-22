package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class VN_BadLightingReport {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33059a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33060b;

    public VN_BadLightingReport(long j11, boolean z11) {
        this.f33060b = z11;
        this.f33059a = j11;
    }

    public static long b(VN_BadLightingReport vN_BadLightingReport) {
        if (vN_BadLightingReport == null) {
            return 0L;
        }
        return vN_BadLightingReport.f33059a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33059a;
            if (j11 != 0) {
                if (this.f33060b) {
                    this.f33060b = false;
                    UIVenusJNI.delete_VN_BadLightingReport(j11);
                }
                this.f33059a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        a();
    }

    public VN_BadLightingReport() {
        this(UIVenusJNI.new_VN_BadLightingReport__SWIG_0(), true);
    }
}
