package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceShape {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32950a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32951b;

    public UIFaceShape(long j11, boolean z11) {
        this.f32951b = z11;
        this.f32950a = j11;
    }

    public static long b(UIFaceShape uIFaceShape) {
        if (uIFaceShape == null) {
            return 0L;
        }
        return uIFaceShape.f32950a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32950a;
            if (j11 != 0) {
                if (this.f32951b) {
                    this.f32951b = false;
                    UIVenusJNI.delete_UIFaceShape(j11);
                }
                this.f32950a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFacePoint c() {
        long jUIFaceShape_shape1_get = UIVenusJNI.UIFaceShape_shape1_get(this.f32950a, this);
        if (jUIFaceShape_shape1_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceShape_shape1_get, false);
    }

    public UIFacePoint d() {
        long jUIFaceShape_shape2_get = UIVenusJNI.UIFaceShape_shape2_get(this.f32950a, this);
        if (jUIFaceShape_shape2_get == 0) {
            return null;
        }
        return new UIFacePoint(jUIFaceShape_shape2_get, false);
    }

    public void e(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceShape_shape1_set(this.f32950a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void f(UIFacePoint uIFacePoint) {
        UIVenusJNI.UIFaceShape_shape2_set(this.f32950a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void finalize() {
        a();
    }

    public UIFaceShape() {
        this(UIVenusJNI.new_UIFaceShape__SWIG_0(), true);
    }
}
