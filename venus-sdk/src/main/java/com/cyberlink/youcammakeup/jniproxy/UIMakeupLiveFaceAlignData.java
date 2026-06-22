package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIMakeupLiveFaceAlignData {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33012a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33013b;

    public UIMakeupLiveFaceAlignData(long j11, boolean z11) {
        this.f33013b = z11;
        this.f33012a = j11;
    }

    public static long b(UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData) {
        if (uIMakeupLiveFaceAlignData == null) {
            return 0L;
        }
        return uIMakeupLiveFaceAlignData.f33012a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33012a;
            if (j11 != 0) {
                if (this.f33013b) {
                    this.f33013b = false;
                    UIVenusJNI.delete_UIMakeupLiveFaceAlignData(j11);
                }
                this.f33012a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        a();
    }

    public UIMakeupLiveFaceAlignData() {
        this(UIVenusJNI.new_UIMakeupLiveFaceAlignData__SWIG_0(), true);
    }

    public UIMakeupLiveFaceAlignData(UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData) {
        this(UIVenusJNI.new_UIMakeupLiveFaceAlignData__SWIG_1(b(uIMakeupLiveFaceAlignData), uIMakeupLiveFaceAlignData), true);
    }
}
