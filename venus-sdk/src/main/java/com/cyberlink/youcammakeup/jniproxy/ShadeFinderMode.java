package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum ShadeFinderMode {
    SHADE_FINDER_DISABLE(0),
    SHADE_FINDER_WITH_CALIBRATION,
    SHADE_FINDER_WITHOUT_CALIBRATION,
    SHADE_FINDER_WITHOUT_CALIBRATION_V2,
    SHADE_FINDER_WITHOUT_CALIBRATION_V3,
    SHADE_FINDER_WITHOUT_CALIBRATION_V4,
    SHADE_FINDER_WITHOUT_CALIBRATION_V4_FACE_ATTRIBUTE;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32892a;

        public static /* synthetic */ int b() {
            int i11 = f32892a;
            f32892a = i11 + 1;
            return i11;
        }
    }

    ShadeFinderMode() {
        this.swigValue = a.b();
    }

    public final int c() {
        return this.swigValue;
    }

    ShadeFinderMode(int i11) {
        this.swigValue = i11;
        int unused = a.f32892a = i11 + 1;
    }
}
