package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIThumbnailPropertyItem {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33031a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33032b;

    public UIThumbnailPropertyItem(long j11, boolean z11) {
        this.f33032b = z11;
        this.f33031a = j11;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33031a;
            if (j11 != 0) {
                if (this.f33032b) {
                    this.f33032b = false;
                    UIImageCodecJNI.delete_UIThumbnailPropertyItem(j11);
                }
                this.f33031a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public long b() {
        return UIImageCodecJNI.UIThumbnailPropertyItem_nHeight_get(this.f33031a, this);
    }

    public UIImageOrientation c() {
        return UIImageOrientation.c(UIImageCodecJNI.UIThumbnailPropertyItem_nOrientation_get(this.f33031a, this));
    }

    public UIThumbnailType d() {
        return UIThumbnailType.c(UIImageCodecJNI.UIThumbnailPropertyItem_nType_get(this.f33031a, this));
    }

    public long e() {
        return UIImageCodecJNI.UIThumbnailPropertyItem_nWidth_get(this.f33031a, this);
    }

    public void finalize() {
        a();
    }
}
