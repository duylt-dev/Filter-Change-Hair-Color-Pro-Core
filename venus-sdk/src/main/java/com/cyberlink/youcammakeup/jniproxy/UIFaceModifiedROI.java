package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceModifiedROI {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32938a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32939b;

    public UIFaceModifiedROI(long j11, boolean z11) {
        this.f32939b = z11;
        this.f32938a = j11;
    }

    public static long c(UIFaceModifiedROI uIFaceModifiedROI) {
        if (uIFaceModifiedROI == null) {
            return 0L;
        }
        return uIFaceModifiedROI.f32938a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32938a;
            if (j11 != 0) {
                if (this.f32939b) {
                    this.f32939b = false;
                    UIVenusJNI.delete_UIFaceModifiedROI(j11);
                }
                this.f32938a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public int b() {
        return UIVenusJNI.UIFaceModifiedROI_getBottom(this.f32938a, this);
    }

    public int d() {
        return UIVenusJNI.UIFaceModifiedROI_getLeft(this.f32938a, this);
    }

    public int e() {
        return UIVenusJNI.UIFaceModifiedROI_getRight(this.f32938a, this);
    }

    public int f() {
        return UIVenusJNI.UIFaceModifiedROI_getTop(this.f32938a, this);
    }

    public void finalize() {
        a();
    }

    public UIFaceModifiedROI() {
        this(UIVenusJNI.new_UIFaceModifiedROI__SWIG_0(), true);
    }
}
