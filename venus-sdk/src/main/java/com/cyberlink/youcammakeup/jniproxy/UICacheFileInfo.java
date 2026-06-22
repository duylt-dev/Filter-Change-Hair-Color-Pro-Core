package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UICacheFileInfo {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32901a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32902b;

    public UICacheFileInfo(long j11, boolean z11) {
        this.f32902b = z11;
        this.f32901a = j11;
    }

    public static long b(UICacheFileInfo uICacheFileInfo) {
        if (uICacheFileInfo == null) {
            return 0L;
        }
        return uICacheFileInfo.f32901a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f32901a;
            if (j11 != 0) {
                if (this.f32902b) {
                    this.f32902b = false;
                    CommonJNI.delete_UICacheFileInfo(j11);
                }
                this.f32901a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public long c() {
        return CommonJNI.UICacheFileInfo_ulBpp_get(this.f32901a, this);
    }

    public long d() {
        return CommonJNI.UICacheFileInfo_ulHeight_get(this.f32901a, this);
    }

    public long e() {
        return CommonJNI.UICacheFileInfo_ulWidth_get(this.f32901a, this);
    }

    public void finalize() {
        a();
    }

    public UICacheFileInfo() {
        this(CommonJNI.new_UICacheFileInfo__SWIG_0(), true);
    }
}
