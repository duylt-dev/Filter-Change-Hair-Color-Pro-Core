package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIThumbnailProperty {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33029a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33030b;

    public UIThumbnailProperty(long j11, boolean z11) {
        this.f33030b = z11;
        this.f33029a = j11;
    }

    public synchronized void a() {
        try {
            long j11 = this.f33029a;
            if (j11 != 0) {
                if (this.f33030b) {
                    this.f33030b = false;
                    UIImageCodecJNI.delete_UIThumbnailProperty(j11);
                }
                this.f33029a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public UIThumbnailPropertyItemVector b() {
        long jUIThumbnailProperty_items_get = UIImageCodecJNI.UIThumbnailProperty_items_get(this.f33029a, this);
        if (jUIThumbnailProperty_items_get == 0) {
            return null;
        }
        return new UIThumbnailPropertyItemVector(jUIThumbnailProperty_items_get, false);
    }

    public void finalize() {
        a();
    }

    public UIThumbnailProperty() {
        this(UIImageCodecJNI.new_UIThumbnailProperty(), true);
    }
}
