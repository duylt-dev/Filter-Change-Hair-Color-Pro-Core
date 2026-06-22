package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIImageROI {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32995a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32996b;

    public UIImageROI(long j11, boolean z11) {
        this.f32996b = z11;
        this.f32995a = j11;
    }

    public static long b(UIImageROI uIImageROI) {
        if (uIImageROI == null) {
            return 0L;
        }
        return uIImageROI.f32995a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32995a;
            if (j11 != 0) {
                if (this.f32996b) {
                    this.f32996b = false;
                    CommonJNI.delete_UIImageROI(j11);
                }
                this.f32995a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        a();
    }

    public UIImageROI(long j11, long j12, long j13, long j14) {
        this(CommonJNI.new_UIImageROI__SWIG_0(j11, j12, j13, j14), true);
    }
}
