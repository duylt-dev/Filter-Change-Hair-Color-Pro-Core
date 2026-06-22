package com.cyberlink.youcammakeup.jniproxy;

import ev.a;

/* loaded from: classes2.dex */
public class UIImageRetouchJNI {
    static {
        a.a();
    }

    public static final native String CUIImageRetouch_Detect_AutoTone(long j11, CUIImageRetouch cUIImageRetouch, int i11, int i12);

    public static final native int CUIImageRetouch_Image_ClearTask(long j11, CUIImageRetouch cUIImageRetouch, int i11, int i12);

    public static final native int CUIImageRetouch_Image_PrepareTask(long j11, CUIImageRetouch cUIImageRetouch, int i11, long j12, IParamBase iParamBase, long j13, IParamBase iParamBase2);

    public static final native int CUIImageRetouch_Image_PushTask(long j11, CUIImageRetouch cUIImageRetouch, int i11, int i12);

    public static final native int CUIImageRetouch_Image_RunAllTask(long j11, CUIImageRetouch cUIImageRetouch, int i11, int i12, int i13, long j12, ROIParam rOIParam, long j13, BufferDataParam bufferDataParam);

    public static final native int CUIImageRetouch_RegisterImageRatio(long j11, CUIImageRetouch cUIImageRetouch, int i11, int i12, float f11, long j12, BufferDataParam bufferDataParam);

    public static final native int CUIImageRetouch_ReleaseAllImage(long j11, CUIImageRetouch cUIImageRetouch);

    public static final native int CUIImageRetouch_ReleaseImage(long j11, CUIImageRetouch cUIImageRetouch, int i11);

    public static final native int CUIImageRetouch_SetImage(long j11, CUIImageRetouch cUIImageRetouch, int i11, long j12, BufferDataParam bufferDataParam);

    public static final native int CUIImageRetouch_UnregisterImageRatio(long j11, CUIImageRetouch cUIImageRetouch, int i11, int i12);

    public static final native boolean HslVibSettingParam_Compare(long j11, HslVibSettingParam hslVibSettingParam, long j12, IParamBase iParamBase);

    public static final native String HslVibSettingParam_EncodeString(long j11, HslVibSettingParam hslVibSettingParam);

    public static final native long HslVibSettingParam_SWIGUpcast(long j11);

    public static final native boolean IParamBase_Compare(long j11, IParamBase iParamBase, long j12, IParamBase iParamBase2);

    public static final native String IParamBase_EncodeString(long j11, IParamBase iParamBase);

    public static final native boolean SaturationSettingParam_Compare(long j11, SaturationSettingParam saturationSettingParam, long j12, IParamBase iParamBase);

    public static final native String SaturationSettingParam_EncodeString(long j11, SaturationSettingParam saturationSettingParam);

    public static final native long SaturationSettingParam_SWIGUpcast(long j11);

    public static final native boolean SharpenSettingParam_Compare(long j11, SharpenSettingParam sharpenSettingParam, long j12, IParamBase iParamBase);

    public static final native String SharpenSettingParam_EncodeString(long j11, SharpenSettingParam sharpenSettingParam);

    public static final native long SharpenSettingParam_SWIGUpcast(long j11);

    public static final native boolean ToneSettingParam_Compare(long j11, ToneSettingParam toneSettingParam, long j12, IParamBase iParamBase);

    public static final native void ToneSettingParam_DecodeString(long j11, ToneSettingParam toneSettingParam, String str);

    public static final native String ToneSettingParam_EncodeString(long j11, ToneSettingParam toneSettingParam);

    public static final native void ToneSettingParam_InitFrom(long j11, ToneSettingParam toneSettingParam, long j12, IParamBase iParamBase);

    public static final native long ToneSettingParam_SWIGUpcast(long j11);

    public static final native boolean WBSettingParam_Compare(long j11, WBSettingParam wBSettingParam, long j12, IParamBase iParamBase);

    public static final native String WBSettingParam_EncodeString(long j11, WBSettingParam wBSettingParam);

    public static final native long WBSettingParam_SWIGUpcast(long j11);

    public static final native void delete_BufferDataParam(long j11);

    public static final native void delete_CUIImageRetouch(long j11);

    public static final native void delete_HslVibSettingParam(long j11);

    public static final native void delete_IParamBase(long j11);

    public static final native void delete_ROIParam(long j11);

    public static final native void delete_SaturationSettingParam(long j11);

    public static final native void delete_SharpenSettingParam(long j11);

    public static final native void delete_ToneSettingParam(long j11);

    public static final native void delete_WBSettingParam(long j11);

    public static final native long new_BufferDataParam__SWIG_1(long j11, IImageBuffer iImageBuffer);

    public static final native long new_CUIImageRetouch__SWIG_0(String str);

    public static final native long new_HslVibSettingParam__SWIG_0();

    public static final native long new_ROIParam();

    public static final native long new_SaturationSettingParam__SWIG_0();

    public static final native long new_SharpenSettingParam__SWIG_0();

    public static final native long new_ToneSettingParam__SWIG_0();

    public static final native long new_WBSettingParam__SWIG_0();
}
