package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UIEyebrowMode {
    EYEBROW_ORIGINAL_MODE(0),
    EYEBROW_ART_DESIGN_MODE,
    EYEBROW_KAI_GOLDEN_MODE,
    EYEBROW_KAI_MINUS_MODE,
    EYEBROW_KAI_PLUS_MODE;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32917a;

        public static /* synthetic */ int b() {
            int i11 = f32917a;
            f32917a = i11 + 1;
            return i11;
        }
    }

    UIEyebrowMode() {
        this.swigValue = a.b();
    }

    public static UIEyebrowMode c(int i11) {
        UIEyebrowMode[] uIEyebrowModeArr = (UIEyebrowMode[]) UIEyebrowMode.class.getEnumConstants();
        if (i11 < uIEyebrowModeArr.length && i11 >= 0) {
            UIEyebrowMode uIEyebrowMode = uIEyebrowModeArr[i11];
            if (uIEyebrowMode.swigValue == i11) {
                return uIEyebrowMode;
            }
        }
        for (UIEyebrowMode uIEyebrowMode2 : uIEyebrowModeArr) {
            if (uIEyebrowMode2.swigValue == i11) {
                return uIEyebrowMode2;
            }
        }
        throw new IllegalArgumentException("No enum " + UIEyebrowMode.class + " with value " + i11);
    }

    public final int f() {
        return this.swigValue;
    }

    UIEyebrowMode(int i11) {
        this.swigValue = i11;
        int unused = a.f32917a = i11 + 1;
    }
}
