package com.cyberlink.youcammakeup.jniproxy;

import ev.a;

/* loaded from: classes2.dex */
public class UIImageCodecJNI {
    static {
        a.a();
    }

    public static final native boolean ObsoleteImageCodec_RotateFlip(long j11, ObsoleteImageCodec obsoleteImageCodec, long j12, CImageBuffer cImageBuffer, long j13, CImageBuffer cImageBuffer2, int i11);

    public static final native boolean ObsoleteImageCodec_Stretch__SWIG_0(long j11, ObsoleteImageCodec obsoleteImageCodec, long j12, CImageBuffer cImageBuffer, long j13, CImageBuffer cImageBuffer2, int i11);

    public static final native boolean ObsoleteImageCodec_Stretch__SWIG_1(long j11, ObsoleteImageCodec obsoleteImageCodec, long j12, CImageBuffer cImageBuffer, long j13, CImageBuffer cImageBuffer2);

    public static final native void UIDecodeParamRef_nBytePerPixel_set(long j11, UIDecodeParamRef uIDecodeParamRef, int i11);

    public static final native void UIDecodeParamRef_nFormat_set(long j11, UIDecodeParamRef uIDecodeParamRef, int i11);

    public static final native void UIDecodeParamRef_ulHeight_set(long j11, UIDecodeParamRef uIDecodeParamRef, long j12);

    public static final native long UIDecodeParamRef_ulSampleSize_get(long j11, UIDecodeParamRef uIDecodeParamRef);

    public static final native void UIDecodeParamRef_ulSampleSize_set(long j11, UIDecodeParamRef uIDecodeParamRef, long j12);

    public static final native void UIDecodeParamRef_ulWidth_set(long j11, UIDecodeParamRef uIDecodeParamRef, long j12);

    public static final native long UIImageDimension_ulHeight_get(long j11, UIImageDimension uIImageDimension);

    public static final native void UIImageDimension_ulHeight_set(long j11, UIImageDimension uIImageDimension, long j12);

    public static final native long UIImageDimension_ulWidth_get(long j11, UIImageDimension uIImageDimension);

    public static final native void UIImageDimension_ulWidth_set(long j11, UIImageDimension uIImageDimension, long j12);

    public static final native long UIThumbnailPropertyItemVector_get(long j11, UIThumbnailPropertyItemVector uIThumbnailPropertyItemVector, int i11);

    public static final native long UIThumbnailPropertyItemVector_size(long j11, UIThumbnailPropertyItemVector uIThumbnailPropertyItemVector);

    public static final native long UIThumbnailPropertyItem_nHeight_get(long j11, UIThumbnailPropertyItem uIThumbnailPropertyItem);

    public static final native int UIThumbnailPropertyItem_nOrientation_get(long j11, UIThumbnailPropertyItem uIThumbnailPropertyItem);

    public static final native int UIThumbnailPropertyItem_nType_get(long j11, UIThumbnailPropertyItem uIThumbnailPropertyItem);

    public static final native long UIThumbnailPropertyItem_nWidth_get(long j11, UIThumbnailPropertyItem uIThumbnailPropertyItem);

    public static final native long UIThumbnailProperty_items_get(long j11, UIThumbnailProperty uIThumbnailProperty);

    public static final native void delete_ObsoleteImageCodec(long j11);

    public static final native void delete_UIDecodeParamRef(long j11);

    public static final native void delete_UIImageDimension(long j11);

    public static final native void delete_UIThumbnailProperty(long j11);

    public static final native void delete_UIThumbnailPropertyItem(long j11);

    public static final native void delete_UIThumbnailPropertyItemVector(long j11);

    public static final native long new_ObsoleteImageCodec();

    public static final native long new_UIDecodeParamRef();

    public static final native long new_UIImageDimension__SWIG_0();

    public static final native long new_UIThumbnailProperty();
}
