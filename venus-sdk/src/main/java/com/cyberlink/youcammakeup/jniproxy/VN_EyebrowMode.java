package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum VN_EyebrowMode {
    EYEBROW_ORIGINAL_MODE(0),
    EYEBROW_2D_STYLE_MODE,
    EYEBROW_ART_DESIGN_MODE,
    EYEBROW_KAI_GOLDEN_MODE,
    EYEBROW_KAI_MINUS_MODE,
    EYEBROW_KAI_PLUS_MODE;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f33080a;

        public static /* synthetic */ int b() {
            int i11 = f33080a;
            f33080a = i11 + 1;
            return i11;
        }
    }

    VN_EyebrowMode() {
        this.swigValue = a.b();
    }

    public static VN_EyebrowMode c(int i11) {
        VN_EyebrowMode[] vN_EyebrowModeArr = (VN_EyebrowMode[]) VN_EyebrowMode.class.getEnumConstants();
        if (i11 < vN_EyebrowModeArr.length && i11 >= 0) {
            VN_EyebrowMode vN_EyebrowMode = vN_EyebrowModeArr[i11];
            if (vN_EyebrowMode.swigValue == i11) {
                return vN_EyebrowMode;
            }
        }
        for (VN_EyebrowMode vN_EyebrowMode2 : vN_EyebrowModeArr) {
            if (vN_EyebrowMode2.swigValue == i11) {
                return vN_EyebrowMode2;
            }
        }
        throw new IllegalArgumentException("No enum " + VN_EyebrowMode.class + " with value " + i11);
    }

    public final int f() {
        return this.swigValue;
    }

    VN_EyebrowMode(int i11) {
        this.swigValue = i11;
        int unused = a.f33080a = i11 + 1;
    }
}
