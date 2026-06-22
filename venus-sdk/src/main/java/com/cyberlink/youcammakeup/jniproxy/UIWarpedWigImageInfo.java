package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIWarpedWigImageInfo {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33046a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33047b;

    public UIWarpedWigImageInfo(long j11, boolean z11) {
        this.f33047b = z11;
        this.f33046a = j11;
    }

    public static long b(UIWarpedWigImageInfo uIWarpedWigImageInfo) {
        if (uIWarpedWigImageInfo == null) {
            return 0L;
        }
        return uIWarpedWigImageInfo.f33046a;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33046a;
            if (j11 != 0) {
                if (this.f33047b) {
                    this.f33047b = false;
                    UIVenusJNI.delete_UIWarpedWigImageInfo(j11);
                }
                this.f33046a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public int c() {
        return UIVenusJNI.UIWarpedWigImageInfo_height_get(this.f33046a, this);
    }

    public int d() {
        return UIVenusJNI.UIWarpedWigImageInfo_width_get(this.f33046a, this);
    }

    public void finalize() {
        a();
    }

    public UIWarpedWigImageInfo() {
        this(UIVenusJNI.new_UIWarpedWigImageInfo__SWIG_0(), true);
    }
}
