package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceRect {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32946a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32947b;

    public UIFaceRect(long j11, boolean z11) {
        this.f32947b = z11;
        this.f32946a = j11;
    }

    public static long c(UIFaceRect uIFaceRect) {
        if (uIFaceRect == null) {
            return 0L;
        }
        return uIFaceRect.f32946a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32946a;
            if (j11 != 0) {
                if (this.f32947b) {
                    this.f32947b = false;
                    UIVenusJNI.delete_UIFaceRect(j11);
                }
                this.f32946a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public int b() {
        return UIVenusJNI.UIFaceRect_getBottom(this.f32946a, this);
    }

    public int d() {
        return UIVenusJNI.UIFaceRect_getLeft(this.f32946a, this);
    }

    public int e() {
        return UIVenusJNI.UIFaceRect_getRight(this.f32946a, this);
    }

    public int f() {
        return UIVenusJNI.UIFaceRect_getTop(this.f32946a, this);
    }

    public void finalize() {
        a();
    }

    public void g(int i11) {
        UIVenusJNI.UIFaceRect_setBottom(this.f32946a, this, i11);
    }

    public void h(int i11) {
        UIVenusJNI.UIFaceRect_setLeft(this.f32946a, this, i11);
    }

    public void i(int i11) {
        UIVenusJNI.UIFaceRect_setRight(this.f32946a, this, i11);
    }

    public void j(int i11) {
        UIVenusJNI.UIFaceRect_setTop(this.f32946a, this, i11);
    }

    public UIFaceRect() {
        this(UIVenusJNI.new_UIFaceRect__SWIG_0(), true);
    }

    public UIFaceRect(UIFaceRect uIFaceRect) {
        this(UIVenusJNI.new_UIFaceRect__SWIG_1(c(uIFaceRect), uIFaceRect), true);
    }
}
