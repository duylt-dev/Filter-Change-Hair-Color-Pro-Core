package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UIFoundationIntensityMode {
    NORMAL(0),
    EXTREME;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32959a;

        public static /* synthetic */ int b() {
            int i11 = f32959a;
            f32959a = i11 + 1;
            return i11;
        }
    }

    UIFoundationIntensityMode() {
        this.swigValue = a.b();
    }

    public final int c() {
        return this.swigValue;
    }

    UIFoundationIntensityMode(int i11) {
        this.swigValue = i11;
        int unused = a.f32959a = i11 + 1;
    }
}
