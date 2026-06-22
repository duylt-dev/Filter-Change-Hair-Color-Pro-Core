package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class CUIFaceShapeDetector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32848a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32849b;

    public CUIFaceShapeDetector(long j11, boolean z11) {
        this.f32849b = z11;
        this.f32848a = j11;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32848a;
            if (j11 != 0) {
                if (this.f32849b) {
                    this.f32849b = false;
                    UIFaceShapeDetectorJNI.delete_CUIFaceShapeDetector(j11);
                }
                this.f32848a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void b(Object obj, int i11, int i12, UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData, Object obj2) {
        UIFaceShapeDetectorJNI.CUIFaceShapeDetector_predictImage(this.f32848a, this, obj, i11, i12, UIMakeupLiveFaceAlignData.b(uIMakeupLiveFaceAlignData), uIMakeupLiveFaceAlignData, obj2);
    }

    public void finalize() {
        a();
    }

    public CUIFaceShapeDetector(String str, String str2) {
        this(UIFaceShapeDetectorJNI.new_CUIFaceShapeDetector(str, str2), true);
    }
}
