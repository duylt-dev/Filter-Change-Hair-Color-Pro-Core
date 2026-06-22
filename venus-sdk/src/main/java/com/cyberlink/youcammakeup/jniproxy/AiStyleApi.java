package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class AiStyleApi {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32834a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32835b;

    public AiStyleApi(long j11, boolean z11) {
        this.f32835b = z11;
        this.f32834a = j11;
    }

    public void a(CImageBuffer cImageBuffer, Object obj) {
        UIAiStyleJNI.AiStyleApi_Apply(this.f32834a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, obj);
    }

    public synchronized void b() {
        try {
            long j11 = this.f32834a;
            if (j11 != 0) {
                if (this.f32835b) {
                    this.f32835b = false;
                    UIAiStyleJNI.delete_AiStyleApi(j11);
                }
                this.f32834a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        b();
    }

    public AiStyleApi(String str, String str2, int i11) {
        this(UIAiStyleJNI.new_AiStyleApi(str, str2, i11), true);
    }
}
