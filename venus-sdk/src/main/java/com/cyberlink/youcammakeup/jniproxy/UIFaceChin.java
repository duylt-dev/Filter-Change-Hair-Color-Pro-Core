package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceChin {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32924a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32925b;

    public UIFaceChin(long j11, boolean z11) {
        this.f32925b = z11;
        this.f32924a = j11;
    }

    public static long b(UIFaceChin uIFaceChin) {
        if (uIFaceChin == null) {
            return 0L;
        }
        return uIFaceChin.f32924a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32924a;
            if (j11 != 0) {
                if (this.f32925b) {
                    this.f32925b = false;
                    UIVenusJNI.delete_UIFaceChin(j11);
                }
                this.f32924a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFacePoint c() {
        long jUIFaceChin_center_get = UIVenusJNI.UIFaceChin_center_get(this.f32924a, this);
        if (jUIFaceChin_center_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceChin_center_get, false);
    }

    public void d(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceChin_center_set(this.f32924a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void finalize() {
        a();
    }

    public UIFaceChin() {
        this(UIVenusJNI.new_UIFaceChin__SWIG_0(), true);
    }
}
