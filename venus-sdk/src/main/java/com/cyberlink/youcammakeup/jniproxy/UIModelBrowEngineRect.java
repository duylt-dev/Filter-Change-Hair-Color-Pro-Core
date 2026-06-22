package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIModelBrowEngineRect {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33014a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33015b;

    public UIModelBrowEngineRect(long j11, boolean z11) {
        this.f33015b = z11;
        this.f33014a = j11;
    }

    public static long b(UIModelBrowEngineRect uIModelBrowEngineRect) {
        if (uIModelBrowEngineRect == null) {
            return 0L;
        }
        return uIModelBrowEngineRect.f33014a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33014a;
            if (j11 != 0) {
                if (this.f33015b) {
                    this.f33015b = false;
                    UIVenusJNI.delete_UIModelBrowEngineRect(j11);
                }
                this.f33014a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void c(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIModelBrowEngineRect_head_set(this.f33014a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void d(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIModelBrowEngineRect_tail_set(this.f33014a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void e(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIModelBrowEngineRect_top_set(this.f33014a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void finalize() {
        a();
    }

    public UIModelBrowEngineRect() {
        this(UIVenusJNI.new_UIModelBrowEngineRect__SWIG_0(), true);
    }
}
