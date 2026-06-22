package com.cyberlink.youcammakeup.jniproxy;

import ev.a;

/* loaded from: classes2.dex */
public class CommonJNI {
    static {
        a.a();
    }

    public static final native boolean CImageBuffer_ApplyMaskBitmap(long j11, CImageBuffer cImageBuffer, Object obj);

    public static final native boolean CImageBuffer_AttachAndroidBitmap(long j11, CImageBuffer cImageBuffer, Object obj);

    public static final native boolean CImageBuffer_BlendingWhite(long j11, CImageBuffer cImageBuffer, long j12, CImageBuffer cImageBuffer2);

    public static final native boolean CImageBuffer_ConvertAlphaMask(long j11, CImageBuffer cImageBuffer, float f11, boolean z11, boolean z12, boolean z13);

    public static final native boolean CImageBuffer_ConvertColorDepth(long j11, CImageBuffer cImageBuffer, long j12, CImageBuffer cImageBuffer2);

    public static final native boolean CImageBuffer_CopyImageBufferToImageBuffer__SWIG_0(long j11, CImageBuffer cImageBuffer, long j12, CImageBuffer cImageBuffer2, long j13, UIImageROI uIImageROI);

    public static final native void CImageBuffer_CopyPixelToArray(long j11, CImageBuffer cImageBuffer, int[] iArr, boolean z11);

    public static final native boolean CImageBuffer_CreateBuffer(long j11, CImageBuffer cImageBuffer, long j12, long j13, long j14);

    public static final native boolean CImageBuffer_CreateFromImageBuffer(long j11, CImageBuffer cImageBuffer, long j12, CImageBuffer cImageBuffer2, long j13, UIImageROI uIImageROI);

    public static final native void CImageBuffer_Destroy(long j11, CImageBuffer cImageBuffer);

    public static final native boolean CImageBuffer_DetachAndroidBitmap(long j11, CImageBuffer cImageBuffer);

    public static final native boolean CImageBuffer_DumpToFile(long j11, CImageBuffer cImageBuffer, String str);

    public static final native boolean CImageBuffer_FillAlphaChannelsWithRedColor(long j11, CImageBuffer cImageBuffer);

    public static final native long CImageBuffer_GetBytesPerPixel(long j11, CImageBuffer cImageBuffer);

    public static final native boolean CImageBuffer_GetCacheFileInfo(String str, long j11, UICacheFileInfo uICacheFileInfo);

    public static final native long CImageBuffer_GetHeight(long j11, CImageBuffer cImageBuffer);

    public static final native long CImageBuffer_GetLength(long j11, CImageBuffer cImageBuffer);

    public static final native int CImageBuffer_GetPixelFormat(long j11, CImageBuffer cImageBuffer);

    public static final native long CImageBuffer_GetWidth(long j11, CImageBuffer cImageBuffer);

    public static final native boolean CImageBuffer_LoadFromFile(long j11, CImageBuffer cImageBuffer, String str);

    public static final native boolean CImageBuffer_MaskBlending(long j11, CImageBuffer cImageBuffer, long j12, CImageBuffer cImageBuffer2, long j13, CImageBuffer cImageBuffer3, boolean z11, long j14, CImageBuffer cImageBuffer4);

    public static final native boolean CImageBuffer_Premultiply(long j11, CImageBuffer cImageBuffer);

    public static final native boolean CImageBuffer_RemovePremultiply(long j11, CImageBuffer cImageBuffer);

    public static final native long CImageBuffer_SWIGUpcast(long j11);

    public static final native void CImageBuffer_SetAccessMode(long j11, CImageBuffer cImageBuffer, int i11);

    public static final native void CImageBuffer_SetPixelFormat(long j11, CImageBuffer cImageBuffer, int i11);

    public static final native boolean CImageBuffer_SwapColorChannel__SWIG_0(long j11, CImageBuffer cImageBuffer);

    public static final native boolean CImageBuffer_SwapColorChannel__SWIG_1(long j11, CImageBuffer cImageBuffer, long j12, CImageBuffer cImageBuffer2);

    public static final native long IImageBuffer_SWIGUpcast(long j11);

    public static final native boolean RuntimeHelper_Is64Bit();

    public static final native boolean RuntimeHelper_IsARMArch();

    public static final native boolean RuntimeHelper_IsSupportNeon();

    public static final native long UICacheFileInfo_ulBpp_get(long j11, UICacheFileInfo uICacheFileInfo);

    public static final native long UICacheFileInfo_ulHeight_get(long j11, UICacheFileInfo uICacheFileInfo);

    public static final native long UICacheFileInfo_ulWidth_get(long j11, UICacheFileInfo uICacheFileInfo);

    public static final native void delete_CImageBuffer(long j11);

    public static final native void delete_IDestroyable(long j11);

    public static final native void delete_IImageBuffer(long j11);

    public static final native void delete_UICacheFileInfo(long j11);

    public static final native void delete_UIImageROI(long j11);

    public static final native long new_CImageBuffer__SWIG_0(int i11);

    public static final native long new_CImageBuffer__SWIG_1();

    public static final native long new_UICacheFileInfo__SWIG_0();

    public static final native long new_UIImageROI__SWIG_0(long j11, long j12, long j13, long j14);
}
