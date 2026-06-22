package com.cyberlink.youcammakeup.jniproxy;

import ya.d;

/* loaded from: classes2.dex */
public class UIImageDimension implements d {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32974a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32975b;

    public UIImageDimension(long j11, boolean z11) {
        this.f32975b = z11;
        this.f32974a = j11;
    }

    @Override // ya.d
    public void a(long j11) {
        UIImageCodecJNI.UIImageDimension_ulHeight_set(this.f32974a, this, j11);
    }

    @Override // ya.d
    public void b(long j11) {
        UIImageCodecJNI.UIImageDimension_ulWidth_set(this.f32974a, this, j11);
    }

    public synchronized void c() {
        try {
            long j11 = this.f32974a;
            if (j11 != 0) {
                if (this.f32975b) {
                    this.f32975b = false;
                    UIImageCodecJNI.delete_UIImageDimension(j11);
                }
                this.f32974a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public long d() {
        return UIImageCodecJNI.UIImageDimension_ulHeight_get(this.f32974a, this);
    }

    public long e() {
        return UIImageCodecJNI.UIImageDimension_ulWidth_get(this.f32974a, this);
    }

    public void finalize() {
        c();
    }

    public UIImageDimension() {
        this(UIImageCodecJNI.new_UIImageDimension__SWIG_0(), true);
    }
}
