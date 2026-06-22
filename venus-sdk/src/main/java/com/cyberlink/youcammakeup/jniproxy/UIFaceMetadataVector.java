package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceMetadataVector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32934a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32935b;

    public UIFaceMetadataVector(long j11, boolean z11) {
        this.f32935b = z11;
        this.f32934a = j11;
    }

    public static long d(UIFaceMetadataVector uIFaceMetadataVector) {
        if (uIFaceMetadataVector == null) {
            return 0L;
        }
        return uIFaceMetadataVector.f32934a;
    }

    public void a(UIFaceMetadata uIFaceMetadata) {
        UIVenusJNI.UIFaceMetadataVector_add(this.f32934a, this, UIFaceMetadata.b(uIFaceMetadata), uIFaceMetadata);
    }

    public synchronized void b() {
        try {
            long j11 = this.f32934a;
            if (j11 != 0) {
                if (this.f32935b) {
                    this.f32935b = false;
                    UIVenusJNI.delete_UIFaceMetadataVector(j11);
                }
                this.f32934a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIFaceMetadata c(int i11) {
        return new UIFaceMetadata(UIVenusJNI.UIFaceMetadataVector_get(this.f32934a, this, i11), false);
    }

    public long e() {
        return UIVenusJNI.UIFaceMetadataVector_size(this.f32934a, this);
    }

    public void finalize() {
        b();
    }

    public UIFaceMetadataVector() {
        this(UIVenusJNI.new_UIFaceMetadataVector__SWIG_0(), true);
    }
}
