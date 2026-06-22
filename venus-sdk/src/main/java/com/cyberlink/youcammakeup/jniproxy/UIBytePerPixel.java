package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UIBytePerPixel {
    PIXEL_4BYTE(4),
    PIXEL_8BYTE(8);

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32900a;
    }

    UIBytePerPixel(int i11) {
        this.swigValue = i11;
        int unused = a.f32900a = i11 + 1;
    }

    public final int c() {
        return this.swigValue;
    }
}
