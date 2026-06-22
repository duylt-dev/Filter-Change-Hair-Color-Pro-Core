package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class VenusPhotoFaceAlignData {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33113a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33114b;

    public VenusPhotoFaceAlignData(long j11, boolean z11) {
        this.f33114b = z11;
        this.f33113a = j11;
    }

    public static long b(VenusPhotoFaceAlignData venusPhotoFaceAlignData) {
        if (venusPhotoFaceAlignData == null) {
            return 0L;
        }
        return venusPhotoFaceAlignData.f33113a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33113a;
            if (j11 != 0) {
                if (this.f33114b) {
                    this.f33114b = false;
                    UIVenusJNI.delete_VenusPhotoFaceAlignData(j11);
                }
                this.f33113a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        a();
    }

    public VenusPhotoFaceAlignData() {
        this(UIVenusJNI.new_VenusPhotoFaceAlignData(), true);
    }
}
