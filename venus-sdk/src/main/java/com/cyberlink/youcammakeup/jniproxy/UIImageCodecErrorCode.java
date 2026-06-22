package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UIImageCodecErrorCode {
    UIIMGCODEC_NOERROR(0),
    UIIMGCODEC_DECODE_ERROR,
    UIIMGCODEC_UNSUPPORT_HALF_DECODE,
    UIIMGCODEC_DECODE_CANCEL,
    UIIMGCODEC_ENCODE_ERROR,
    UIIMGCODEC_ENCODE_CANCEL,
    UIIMGCODEC_FILE_NOT_FOUND,
    UIIMGCODEC_FILE_BEING_USED,
    UIIMGCODEC_OUT_OF_MEMORY,
    UIIMGCODEC_DISK_FULL;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f32973a;

        public static /* synthetic */ int b() {
            int i11 = f32973a;
            f32973a = i11 + 1;
            return i11;
        }
    }

    UIImageCodecErrorCode() {
        this.swigValue = a.b();
    }

    UIImageCodecErrorCode(int i11) {
        this.swigValue = i11;
        int unused = a.f32973a = i11 + 1;
    }
}
