package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum VN_TrackingMode {
    VN_LIVE_TRACKING_MODE(0),
    VN_VIDEO_TRACKING_MODE;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f33112a;

        public static /* synthetic */ int b() {
            int i11 = f33112a;
            f33112a = i11 + 1;
            return i11;
        }
    }

    VN_TrackingMode() {
        this.swigValue = a.b();
    }

    public final int c() {
        return this.swigValue;
    }

    VN_TrackingMode(int i11) {
        this.swigValue = i11;
        int unused = a.f33112a = i11 + 1;
    }
}
