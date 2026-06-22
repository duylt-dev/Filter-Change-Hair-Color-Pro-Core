package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceRectVector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32948a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32949b;

    public UIFaceRectVector(long j11, boolean z11) {
        this.f32949b = z11;
        this.f32948a = j11;
    }

    public static long d(UIFaceRectVector uIFaceRectVector) {
        if (uIFaceRectVector == null) {
            return 0L;
        }
        return uIFaceRectVector.f32948a;
    }

    public void a(UIFaceRect uIFaceRect) {
        UIVenusJNI.UIFaceRectVector_add(this.f32948a, this, UIFaceRect.c(uIFaceRect), uIFaceRect);
    }

    public synchronized void b() {
        try {
            long j11 = this.f32948a;
            if (j11 != 0) {
                if (this.f32949b) {
                    this.f32949b = false;
                    UIVenusJNI.delete_UIFaceRectVector(j11);
                }
                this.f32948a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFaceRect c(int i11) {
        return new UIFaceRect(UIVenusJNI.UIFaceRectVector_get(this.f32948a, this, i11), false);
    }

    public boolean e() {
        return UIVenusJNI.UIFaceRectVector_isEmpty(this.f32948a, this);
    }

    public long f() {
        return UIVenusJNI.UIFaceRectVector_size(this.f32948a, this);
    }

    public void finalize() {
        b();
    }

    public UIFaceRectVector() {
        this(UIVenusJNI.new_UIFaceRectVector__SWIG_0(), true);
    }
}
