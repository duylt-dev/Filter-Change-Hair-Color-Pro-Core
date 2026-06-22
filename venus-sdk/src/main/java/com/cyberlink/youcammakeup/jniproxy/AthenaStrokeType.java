package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum AthenaStrokeType {
    ATN_FOREGROUND(0),
    ATN_BACKGROUND,
    ATN_ERASER;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32840a;

        public static /* synthetic */ int b() {
            int i11 = f32840a;
            f32840a = i11 + 1;
            return i11;
        }
    }

    AthenaStrokeType() {
        this.swigValue = a.b();
    }

    public final int c() {
        return this.swigValue;
    }

    AthenaStrokeType(int i11) {
        this.swigValue = i11;
        int unused = a.f32840a = i11 + 1;
    }
}
