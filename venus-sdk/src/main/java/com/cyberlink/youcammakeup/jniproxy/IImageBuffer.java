package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class IImageBuffer extends IDestroyable {

    /* renamed from: c, reason: collision with root package name */
    public transient long f32869c;

    public IImageBuffer(long j11, boolean z11) {
        super(CommonJNI.IImageBuffer_SWIGUpcast(j11), z11);
        this.f32869c = j11;
    }

    public static long b(IImageBuffer iImageBuffer) {
        if (iImageBuffer == null) {
            return 0L;
        }
        return iImageBuffer.f32869c;
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IDestroyable
    public synchronized void a() {
        try {
            long j11 = this.f32869c;
            if (j11 != 0) {
                if (this.f32868b) {
                    this.f32868b = false;
                    CommonJNI.delete_IImageBuffer(j11);
                }
                this.f32869c = 0L;
            }
            super.a();
        } catch (Throwable th2) {
            throw th2;
        }
    }
}
