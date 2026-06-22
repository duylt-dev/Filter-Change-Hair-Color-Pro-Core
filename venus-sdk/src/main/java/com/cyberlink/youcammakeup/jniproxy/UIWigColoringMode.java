package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UIWigColoringMode {
    WIG_COLORING_VERSION_0(0),
    WIG_COLORING_VERSION_1,
    WIG_COLORING_VERSION_2;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f33054a;

        public static /* synthetic */ int b() {
            int i11 = f33054a;
            f33054a = i11 + 1;
            return i11;
        }
    }

    UIWigColoringMode() {
        this.swigValue = a.b();
    }

    public final int c() {
        return this.swigValue;
    }

    UIWigColoringMode(int i11) {
        this.swigValue = i11;
        int unused = a.f33054a = i11 + 1;
    }
}
