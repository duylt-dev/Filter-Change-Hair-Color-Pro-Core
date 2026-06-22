package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class CUIRepairDetector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32852a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32853b;

    public CUIRepairDetector(long j11, boolean z11) {
        this.f32853b = z11;
        this.f32852a = j11;
    }

    public void a(CImageBuffer cImageBuffer, float[] fArr, float[] fArr2) {
        UIRepairDetectorJNI.CUIRepairDetector_Predict(this.f32852a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, fArr, fArr2);
    }

    public synchronized void b() {
        try {
            long j11 = this.f32852a;
            if (j11 != 0) {
                if (this.f32853b) {
                    this.f32853b = false;
                    UIRepairDetectorJNI.delete_CUIRepairDetector(j11);
                }
                this.f32852a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        b();
    }

    public CUIRepairDetector(String str, String str2, int i11) {
        this(UIRepairDetectorJNI.new_CUIRepairDetector(str, str2, i11), true);
    }
}
