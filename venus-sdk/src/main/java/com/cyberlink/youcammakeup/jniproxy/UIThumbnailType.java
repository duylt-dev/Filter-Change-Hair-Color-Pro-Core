package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum UIThumbnailType {
    THUMBNAIL_UNDEFINED,
    THUMBNAIL_JPEG,
    THUMBNAIL_UNCOMPRESSED_BUFFER;

    private final int swigValue = a.a();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f33039a;

        public static /* synthetic */ int a() {
            int i11 = f33039a;
            f33039a = i11 + 1;
            return i11;
        }
    }

    UIThumbnailType() {
    }

    public static UIThumbnailType c(int i11) {
        UIThumbnailType[] uIThumbnailTypeArr = (UIThumbnailType[]) UIThumbnailType.class.getEnumConstants();
        if (i11 < uIThumbnailTypeArr.length && i11 >= 0) {
            UIThumbnailType uIThumbnailType = uIThumbnailTypeArr[i11];
            if (uIThumbnailType.swigValue == i11) {
                return uIThumbnailType;
            }
        }
        for (UIThumbnailType uIThumbnailType2 : uIThumbnailTypeArr) {
            if (uIThumbnailType2.swigValue == i11) {
                return uIThumbnailType2;
            }
        }
        throw new IllegalArgumentException("No enum " + UIThumbnailType.class + " with value " + i11);
    }
}
