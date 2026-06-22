package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceAlignmentDataAll {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32920a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32921b;

    public UIFaceAlignmentDataAll(long j11, boolean z11) {
        this.f32921b = z11;
        this.f32920a = j11;
    }

    public static long b(UIFaceAlignmentDataAll uIFaceAlignmentDataAll) {
        if (uIFaceAlignmentDataAll == null) {
            return 0L;
        }
        return uIFaceAlignmentDataAll.f32920a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32920a;
            if (j11 != 0) {
                if (this.f32921b) {
                    this.f32921b = false;
                    UIVenusJNI.delete_UIFaceAlignmentDataAll(j11);
                }
                this.f32920a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void c(int i11, UIFaceAlignmentData uIFaceAlignmentData) {
        UIVenusJNI.UIFaceAlignmentDataAll_push(this.f32920a, this, i11, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData);
    }

    public void finalize() {
        a();
    }

    public UIFaceAlignmentDataAll(int i11) {
        this(UIVenusJNI.new_UIFaceAlignmentDataAll__SWIG_0(i11), true);
    }
}
