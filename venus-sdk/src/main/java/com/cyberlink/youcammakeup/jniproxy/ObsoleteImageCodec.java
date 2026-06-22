package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class ObsoleteImageCodec {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32872a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32873b;

    public ObsoleteImageCodec(long j11, boolean z11) {
        this.f32873b = z11;
        this.f32872a = j11;
    }

    public boolean a(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2, UIImageOrientation uIImageOrientation) {
        return UIImageCodecJNI.ObsoleteImageCodec_RotateFlip(this.f32872a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, CImageBuffer.D(cImageBuffer2), cImageBuffer2, uIImageOrientation.f());
    }

    public boolean b(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2) {
        return UIImageCodecJNI.ObsoleteImageCodec_Stretch__SWIG_1(this.f32872a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, CImageBuffer.D(cImageBuffer2), cImageBuffer2);
    }

    public boolean c(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2, UIInterpolation uIInterpolation) {
        return UIImageCodecJNI.ObsoleteImageCodec_Stretch__SWIG_0(this.f32872a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, CImageBuffer.D(cImageBuffer2), cImageBuffer2, uIInterpolation.c());
    }

    public synchronized void d() {
        try {
            long j11 = this.f32872a;
            if (j11 != 0) {
                if (this.f32873b) {
                    this.f32873b = false;
                    UIImageCodecJNI.delete_ObsoleteImageCodec(j11);
                }
                this.f32872a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        d();
    }

    public ObsoleteImageCodec() {
        this(UIImageCodecJNI.new_ObsoleteImageCodec(), true);
    }
}
