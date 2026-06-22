package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UIImageOrientation {
    ImageUnknownOrientation(0),
    ImageRotate0(1),
    ImageFlipHorizontal(2),
    ImageRotate180(3),
    ImageFlipVertical(4),
    ImageRotate90AndFlipHorizontal(5),
    ImageRotate90(6),
    ImageRotate270AndFlipHorizontal(7),
    ImageRotate270(8);

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32994a;
    }

    UIImageOrientation(int i11) {
        this.swigValue = i11;
        int unused = a.f32994a = i11 + 1;
    }

    public static UIImageOrientation c(int i11) {
        UIImageOrientation[] uIImageOrientationArr = (UIImageOrientation[]) UIImageOrientation.class.getEnumConstants();
        if (i11 < uIImageOrientationArr.length && i11 >= 0) {
            UIImageOrientation uIImageOrientation = uIImageOrientationArr[i11];
            if (uIImageOrientation.swigValue == i11) {
                return uIImageOrientation;
            }
        }
        for (UIImageOrientation uIImageOrientation2 : uIImageOrientationArr) {
            if (uIImageOrientation2.swigValue == i11) {
                return uIImageOrientation2;
            }
        }
        throw new IllegalArgumentException("No enum " + UIImageOrientation.class + " with value " + i11);
    }

    public final int f() {
        return this.swigValue;
    }
}
