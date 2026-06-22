package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UIImageFormat {
    FORMAT_UNKNOWN,
    FORMAT_JPEG,
    FORMAT_TIFF,
    FORMAT_RAW,
    FORMAT_ARGB,
    FORMAT_PNG,
    FORMAT_BMP;

    private final int swigValue = a.a();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32984a;

        public static /* synthetic */ int a() {
            int i11 = f32984a;
            f32984a = i11 + 1;
            return i11;
        }
    }

    UIImageFormat() {
    }

    public final int c() {
        return this.swigValue;
    }
}
