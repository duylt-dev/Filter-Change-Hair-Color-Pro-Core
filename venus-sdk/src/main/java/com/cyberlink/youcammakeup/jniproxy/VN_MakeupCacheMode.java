package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum VN_MakeupCacheMode {
    CACHE_NONE(0),
    LOOK_INTENSITY,
    WRINKLE_REMOVAL,
    WRINKLE_REMOVAL_HD,
    RED_EYE_REMOVAL,
    SPOT_REMOVAL,
    EYEBAG_REMOVAL,
    ANTI_SHINE,
    PORE_REMOVAL,
    REDNESS_OR_TEXTURE_REMOVAL,
    UNEVEN_OR_DARK_CIRCLE_REMOVAL,
    SKIN_SMOOTH,
    SKIN_WHITEN,
    ADJUST_CONTRAST,
    RETOUCH_LIP_WRINKLESS,
    FOUNDATION,
    CONCEALER,
    RESERVED_FOR_INFO,
    FACE_CONTOUR_PATTERN,
    BLUSH,
    BRONZER,
    FRECKLE,
    EYEBROW_MAKEUP,
    NOSE_SHADOW,
    SPARKLE_EYE,
    SPARKLE_EYE_V2,
    RESERVED_FOR_EYEBROW,
    DOUBLE_EYELIDS,
    EYE_CONTACTS,
    LIPLINER,
    LIPSTICK,
    FACE_ART,
    EYE_MAKEUP,
    FACE_ART_LAYER2,
    WHITEN_TEETH,
    HAIR_DYE,
    FACE_LIFT,
    FACE_RESHAPE,
    WIG,
    FACE_WIDGET,
    ACCESSORY,
    CUBE_EYEWEAR,
    COLOR_EFFECT,
    CACHE_MODE_AMOUNT;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f33108a;

        public static /* synthetic */ int b() {
            int i11 = f33108a;
            f33108a = i11 + 1;
            return i11;
        }
    }

    VN_MakeupCacheMode() {
        this.swigValue = a.b();
    }

    public static VN_MakeupCacheMode c(int i11) {
        VN_MakeupCacheMode[] vN_MakeupCacheModeArr = (VN_MakeupCacheMode[]) VN_MakeupCacheMode.class.getEnumConstants();
        if (i11 < vN_MakeupCacheModeArr.length && i11 >= 0) {
            VN_MakeupCacheMode vN_MakeupCacheMode = vN_MakeupCacheModeArr[i11];
            if (vN_MakeupCacheMode.swigValue == i11) {
                return vN_MakeupCacheMode;
            }
        }
        for (VN_MakeupCacheMode vN_MakeupCacheMode2 : vN_MakeupCacheModeArr) {
            if (vN_MakeupCacheMode2.swigValue == i11) {
                return vN_MakeupCacheMode2;
            }
        }
        throw new IllegalArgumentException("No enum " + VN_MakeupCacheMode.class + " with value " + i11);
    }

    public final int f() {
        return this.swigValue;
    }

    VN_MakeupCacheMode(int i11) {
        this.swigValue = i11;
        int unused = a.f33108a = i11 + 1;
    }
}
