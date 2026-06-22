package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UIInterpolation {
    DEFAULT(0),
    BILINEAR,
    SUPER;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f33007a;

        public static /* synthetic */ int b() {
            int i11 = f33007a;
            f33007a = i11 + 1;
            return i11;
        }
    }

    UIInterpolation() {
        this.swigValue = a.b();
    }

    public final int c() {
        return this.swigValue;
    }

    UIInterpolation(int i11) {
        this.swigValue = i11;
        int unused = a.f33007a = i11 + 1;
    }
}
