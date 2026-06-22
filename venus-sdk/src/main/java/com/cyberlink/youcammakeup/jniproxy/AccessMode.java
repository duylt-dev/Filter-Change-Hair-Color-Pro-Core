package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum AccessMode {
    ReadOnly(0),
    ReadWrite;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32833a;

        public static /* synthetic */ int b() {
            int i11 = f32833a;
            f32833a = i11 + 1;
            return i11;
        }
    }

    AccessMode() {
        this.swigValue = a.b();
    }

    public final int c() {
        return this.swigValue;
    }

    AccessMode(int i11) {
        this.swigValue = i11;
        int unused = a.f32833a = i11 + 1;
    }
}
