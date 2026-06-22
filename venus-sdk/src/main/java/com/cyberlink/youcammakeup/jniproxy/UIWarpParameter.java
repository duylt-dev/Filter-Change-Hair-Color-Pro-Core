package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIWarpParameter {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33044a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33045b;

    public UIWarpParameter(long j11, boolean z11) {
        this.f33045b = z11;
        this.f33044a = j11;
    }

    public static long b(UIWarpParameter uIWarpParameter) {
        if (uIWarpParameter == null) {
            return 0L;
        }
        return uIWarpParameter.f33044a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33044a;
            if (j11 != 0) {
                if (this.f33045b) {
                    this.f33045b = false;
                    UIVenusJNI.delete_UIWarpParameter(j11);
                }
                this.f33044a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        a();
    }

    public UIWarpParameter(UIFacePoint uIFacePoint, UIFacePoint uIFacePoint2, int i11) {
        this(UIVenusJNI.new_UIWarpParameter__SWIG_1(UIFacePoint.b(uIFacePoint), uIFacePoint, UIFacePoint.b(uIFacePoint2), uIFacePoint2, i11), true);
    }
}
