package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class CUIClair {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32846a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32847b;

    public CUIClair(long j11, boolean z11) {
        this.f32847b = z11;
        this.f32846a = j11;
    }

    public boolean a(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2, String str, String str2, boolean z11) {
        return UIClairJNI.CUIClair_GetSegmentation(this.f32846a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, CImageBuffer.D(cImageBuffer2), cImageBuffer2, str, str2, z11);
    }

    public synchronized void b() {
        try {
            long j11 = this.f32846a;
            if (j11 != 0) {
                if (this.f32847b) {
                    this.f32847b = false;
                    UIClairJNI.delete_CUIClair(j11);
                }
                this.f32846a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        b();
    }

    public CUIClair(String str, String str2) {
        this(UIClairJNI.new_CUIClair(str, str2), true);
    }
}
