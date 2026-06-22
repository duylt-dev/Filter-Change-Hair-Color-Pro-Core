package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class IDestroyable {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32867a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32868b;

    public IDestroyable(long j11, boolean z11) {
        this.f32868b = z11;
        this.f32867a = j11;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32867a;
            if (j11 != 0) {
                if (this.f32868b) {
                    this.f32868b = false;
                    CommonJNI.delete_IDestroyable(j11);
                }
                this.f32867a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }
}
