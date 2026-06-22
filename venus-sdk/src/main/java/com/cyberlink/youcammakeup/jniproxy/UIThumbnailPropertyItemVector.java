package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIThumbnailPropertyItemVector {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33033a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33034b;

    public UIThumbnailPropertyItemVector(long j11, boolean z11) {
        this.f33034b = z11;
        this.f33033a = j11;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33033a;
            if (j11 != 0) {
                if (this.f33034b) {
                    this.f33034b = false;
                    UIImageCodecJNI.delete_UIThumbnailPropertyItemVector(j11);
                }
                this.f33033a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIThumbnailPropertyItem b(int i11) {
        return new UIThumbnailPropertyItem(UIImageCodecJNI.UIThumbnailPropertyItemVector_get(this.f33033a, this, i11), false);
    }

    public long c() {
        return UIImageCodecJNI.UIThumbnailPropertyItemVector_size(this.f33033a, this);
    }

    public void finalize() {
        a();
    }
}
