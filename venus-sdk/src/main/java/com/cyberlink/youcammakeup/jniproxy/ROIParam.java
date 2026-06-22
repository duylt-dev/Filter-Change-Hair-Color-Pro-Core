package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class ROIParam {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32881a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32882b;

    public ROIParam(long j11, boolean z11) {
        this.f32882b = z11;
        this.f32881a = j11;
    }

    public static long b(ROIParam rOIParam) {
        if (rOIParam == null) {
            return 0L;
        }
        return rOIParam.f32881a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32881a;
            if (j11 != 0) {
                if (this.f32882b) {
                    this.f32882b = false;
                    UIImageRetouchJNI.delete_ROIParam(j11);
                }
                this.f32881a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        a();
    }

    public ROIParam() {
        this(UIImageRetouchJNI.new_ROIParam(), true);
    }
}
