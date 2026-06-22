package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UISmileMode {
    SMILE_CLASSIC(0),
    SMILE_CHIC,
    SMILE_SMIRK;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f33028a;

        public static /* synthetic */ int b() {
            int i11 = f33028a;
            f33028a = i11 + 1;
            return i11;
        }
    }

    UISmileMode() {
        this.swigValue = a.b();
    }

    public final int c() {
        return this.swigValue;
    }

    UISmileMode(int i11) {
        this.swigValue = i11;
        int unused = a.f33028a = i11 + 1;
    }
}
