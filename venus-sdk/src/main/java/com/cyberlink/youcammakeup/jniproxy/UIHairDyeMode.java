package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UIHairDyeMode {
    HAIR_DYE_GENERIC_MODE(0),
    HAIR_DYE_SALON_MODE;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32963a;

        public static /* synthetic */ int b() {
            int i11 = f32963a;
            f32963a = i11 + 1;
            return i11;
        }
    }

    UIHairDyeMode() {
        this.swigValue = a.b();
    }

    public final int c() {
        return this.swigValue;
    }

    UIHairDyeMode(int i11) {
        this.swigValue = i11;
        int unused = a.f32963a = i11 + 1;
    }
}
