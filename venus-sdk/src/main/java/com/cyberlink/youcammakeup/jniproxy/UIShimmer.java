package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIShimmer {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33022a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33023b;

    public UIShimmer(long j11, boolean z11) {
        this.f33023b = z11;
        this.f33022a = j11;
    }

    public static long b(UIShimmer uIShimmer) {
        if (uIShimmer == null) {
            return 0L;
        }
        return uIShimmer.f33022a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33022a;
            if (j11 != 0) {
                if (this.f33023b) {
                    this.f33023b = false;
                    UIVenusJNI.delete_UIShimmer(j11);
                }
                this.f33022a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public int c() {
        return UIVenusJNI.UIShimmer_color_get(this.f33022a, this);
    }

    public int d() {
        return UIVenusJNI.UIShimmer_density_get(this.f33022a, this);
    }

    public int e() {
        return UIVenusJNI.UIShimmer_granularity_get(this.f33022a, this);
    }

    public int f() {
        return UIVenusJNI.UIShimmer_intensity_get(this.f33022a, this);
    }

    public void finalize() {
        a();
    }

    public UIShimmer(int i11, int i12, int i13, int i14) {
        this(UIVenusJNI.new_UIShimmer__SWIG_0(i11, i12, i13, i14), true);
    }
}
