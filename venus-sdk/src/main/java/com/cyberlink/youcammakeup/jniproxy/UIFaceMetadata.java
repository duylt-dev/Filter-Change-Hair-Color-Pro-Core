package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceMetadata {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32932a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32933b;

    public UIFaceMetadata(long j11, boolean z11) {
        this.f32933b = z11;
        this.f32932a = j11;
    }

    public static long b(UIFaceMetadata uIFaceMetadata) {
        if (uIFaceMetadata == null) {
            return 0L;
        }
        return uIFaceMetadata.f32932a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32932a;
            if (j11 != 0) {
                if (this.f32933b) {
                    this.f32933b = false;
                    UIVenusJNI.delete_UIFaceMetadata(j11);
                }
                this.f32932a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        a();
    }

    public UIFaceMetadata() {
        this(UIVenusJNI.new_UIFaceMetadata__SWIG_0(), true);
    }

    public UIFaceMetadata(UIFaceMetadata uIFaceMetadata) {
        this(UIVenusJNI.new_UIFaceMetadata__SWIG_1(b(uIFaceMetadata), uIFaceMetadata), true);
    }
}
