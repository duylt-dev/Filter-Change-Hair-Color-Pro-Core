package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIRecommendFaceContour {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33020a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33021b;

    public UIRecommendFaceContour(long j11, boolean z11) {
        this.f33021b = z11;
        this.f33020a = j11;
    }

    public static long b(UIRecommendFaceContour uIRecommendFaceContour) {
        if (uIRecommendFaceContour == null) {
            return 0L;
        }
        return uIRecommendFaceContour.f33020a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33020a;
            if (j11 != 0) {
                if (this.f33021b) {
                    this.f33021b = false;
                    UIVenusJNI.delete_UIRecommendFaceContour(j11);
                }
                this.f33020a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public int c() {
        return UIVenusJNI.UIRecommendFaceContour_colorIndex_get(this.f33020a, this);
    }

    public UIIntVector d() {
        long jUIRecommendFaceContour_intensities_get = UIVenusJNI.UIRecommendFaceContour_intensities_get(this.f33020a, this);
        if (jUIRecommendFaceContour_intensities_get == 0) {
            return null;
        }
        return new UIIntVector(jUIRecommendFaceContour_intensities_get, false);
    }

    public void finalize() {
        a();
    }

    public UIRecommendFaceContour() {
        this(UIVenusJNI.new_UIRecommendFaceContour__SWIG_0(), true);
    }
}
