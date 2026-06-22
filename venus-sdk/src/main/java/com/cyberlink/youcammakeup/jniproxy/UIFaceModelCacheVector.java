package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceModelCacheVector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32936a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32937b;

    public UIFaceModelCacheVector(long j11, boolean z11) {
        this.f32937b = z11;
        this.f32936a = j11;
    }

    public static long e(UIFaceModelCacheVector uIFaceModelCacheVector) {
        if (uIFaceModelCacheVector == null) {
            return 0L;
        }
        return uIFaceModelCacheVector.f32936a;
    }

    public void a(String str) {
        UIVenusJNI.UIFaceModelCacheVector_add(this.f32936a, this, str);
    }

    public void b() {
        UIVenusJNI.UIFaceModelCacheVector_clear(this.f32936a, this);
    }

    public synchronized void c() {
        try {
            long j11 = this.f32936a;
            if (j11 != 0) {
                if (this.f32937b) {
                    this.f32937b = false;
                    UIVenusJNI.delete_UIFaceModelCacheVector(j11);
                }
                this.f32936a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public String d(int i11) {
        return UIVenusJNI.UIFaceModelCacheVector_get(this.f32936a, this, i11);
    }

    public boolean f() {
        return UIVenusJNI.UIFaceModelCacheVector_isEmpty(this.f32936a, this);
    }

    public void finalize() {
        c();
    }

    public long g() {
        return UIVenusJNI.UIFaceModelCacheVector_size(this.f32936a, this);
    }

    public UIFaceModelCacheVector() {
        this(UIVenusJNI.new_UIFaceModelCacheVector__SWIG_0(), true);
    }
}
