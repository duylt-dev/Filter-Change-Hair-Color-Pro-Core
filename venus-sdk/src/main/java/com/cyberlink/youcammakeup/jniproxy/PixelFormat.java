package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum PixelFormat {
    Format32bppRGBA(0),
    Format32bppBGRA,
    Format64bppRGBA,
    Format64bppBGRA,
    Format8bppGray;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32880a;

        public static /* synthetic */ int b() {
            int i11 = f32880a;
            f32880a = i11 + 1;
            return i11;
        }
    }

    PixelFormat() {
        this.swigValue = a.b();
    }

    public static PixelFormat c(int i11) {
        PixelFormat[] pixelFormatArr = (PixelFormat[]) PixelFormat.class.getEnumConstants();
        if (i11 < pixelFormatArr.length && i11 >= 0) {
            PixelFormat pixelFormat = pixelFormatArr[i11];
            if (pixelFormat.swigValue == i11) {
                return pixelFormat;
            }
        }
        for (PixelFormat pixelFormat2 : pixelFormatArr) {
            if (pixelFormat2.swigValue == i11) {
                return pixelFormat2;
            }
        }
        throw new IllegalArgumentException("No enum " + PixelFormat.class + " with value " + i11);
    }

    public final int f() {
        return this.swigValue;
    }

    PixelFormat(int i11) {
        this.swigValue = i11;
        int unused = a.f32880a = i11 + 1;
    }
}
