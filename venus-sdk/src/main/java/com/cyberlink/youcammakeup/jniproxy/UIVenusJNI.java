package com.cyberlink.youcammakeup.jniproxy;

import ev.a;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class UIVenusJNI {
    static {
        a.a();
    }

    public static final native int CUISkinBeautify_BodyReshapeNoFace(long j11, CUISkinBeautify cUISkinBeautify, long j12, CImageBuffer cImageBuffer, long j13, CImageBuffer cImageBuffer2, int i11, int i12, long j14, UIWarpParameter uIWarpParameter, long j15, UIFaceModifiedROI uIFaceModifiedROI);

    public static final native boolean CUISkinBeautify_CanRedoReshape(long j11, CUISkinBeautify cUISkinBeautify);

    public static final native boolean CUISkinBeautify_CanUndoReshape(long j11, CUISkinBeautify cUISkinBeautify);

    public static final native int CUISkinBeautify_CancelReshape(long j11, CUISkinBeautify cUISkinBeautify);

    public static final native int CUISkinBeautify_FinishBodyReshapeNoFace(long j11, CUISkinBeautify cUISkinBeautify);

    public static final native int CUISkinBeautify_GetCurrentReshapeStep(long j11, CUISkinBeautify cUISkinBeautify);

    public static final native int CUISkinBeautify_GetInternalModelVersion(long j11, CUISkinBeautify cUISkinBeautify, long j12, UIFaceModelCacheVector uIFaceModelCacheVector);

    public static final native int CUISkinBeautify_InitBeautify(long j11, CUISkinBeautify cUISkinBeautify, long j12, CImageBuffer cImageBuffer, long j13, UIFaceRect uIFaceRect, long j14, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native int CUISkinBeautify_InitBodyReshape(long j11, CUISkinBeautify cUISkinBeautify, int i11, int i12, int i13, long j12, UIFaceAlignmentDataAll uIFaceAlignmentDataAll, int i14);

    public static final native boolean CUISkinBeautify_IsModelLoaded(long j11, CUISkinBeautify cUISkinBeautify);

    public static final native int CUISkinBeautify_RedoReshape(long j11, CUISkinBeautify cUISkinBeautify, long j12, CImageBuffer cImageBuffer, long j13, CImageBuffer cImageBuffer2, long j14, UIFaceModifiedROI uIFaceModifiedROI, long j15, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native int CUISkinBeautify_ReshapeProduction(long j11, CUISkinBeautify cUISkinBeautify, long j12, CImageBuffer cImageBuffer, long j13, CImageBuffer cImageBuffer2, long j14, UIFaceModifiedROI uIFaceModifiedROI);

    public static final native int CUISkinBeautify_SetInternalModelPaths(long j11, CUISkinBeautify cUISkinBeautify, String str, String str2, boolean z11);

    public static final native int CUISkinBeautify_UndoReshape(long j11, CUISkinBeautify cUISkinBeautify, long j12, CImageBuffer cImageBuffer, long j13, CImageBuffer cImageBuffer2, long j14, UIFaceModifiedROI uIFaceModifiedROI, long j15, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native int CUISkinBeautify_UninitBeautify(long j11, CUISkinBeautify cUISkinBeautify);

    public static final native boolean CUIVenusLive_AnalyzeLiveImage(long j11, CUIVenusLive cUIVenusLive, Object obj, Object obj2, boolean z11, int i11, int i12, int i13, int[] iArr, Object obj3, boolean z12, Object obj4, Object obj5, float f11, float f12, boolean z13, Object obj6, Object obj7, boolean z14);

    public static final native boolean CUIVenusLive_AsyncDecodeApng(long j11, CUIVenusLive cUIVenusLive, int i11, String str, boolean z11, Object obj, Object obj2);

    public static final native boolean CUIVenusLive_DetectOpenMouth(long j11, CUIVenusLive cUIVenusLive, long j12, UIFaceRect uIFaceRect);

    public static final native int CUIVenusLive_GetAutoWigLuminanceParameter(long j11, CUIVenusLive cUIVenusLive, long j12, UIFaceRect uIFaceRect, long j13, UIWigLuminance uIWigLuminance);

    public static final native boolean CUIVenusLive_GetBackgroundMetadata(long j11, CUIVenusLive cUIVenusLive, Object obj);

    public static final native boolean CUIVenusLive_GetBadLightingModelVersion(long j11, CUIVenusLive cUIVenusLive, long j12, UIFaceModelCacheVector uIFaceModelCacheVector);

    public static final native boolean CUIVenusLive_GetBadLightingReport(long j11, CUIVenusLive cUIVenusLive, long j12, VN_BadLightingReport vN_BadLightingReport);

    public static final native boolean CUIVenusLive_GetEyeContactModelParameters(long j11, CUIVenusLive cUIVenusLive, int i11, int i12, Object obj, Object obj2, Object obj3);

    public static final native int CUIVenusLive_GetEyebrowOriginalColor(long j11, CUIVenusLive cUIVenusLive, long j12, UIFaceRect uIFaceRect, long j13, UIIntVector uIIntVector);

    public static final native boolean CUIVenusLive_GetFaceAlignmentData(long j11, CUIVenusLive cUIVenusLive, Object obj, Object obj2);

    public static final native boolean CUIVenusLive_GetFaceDistortionIntermediateSize(long j11, CUIVenusLive cUIVenusLive, Object obj);

    public static final native boolean CUIVenusLive_GetFaceInfos(long j11, CUIVenusLive cUIVenusLive, Object obj, int i11);

    public static final native boolean CUIVenusLive_GetFaceRectangle(long j11, CUIVenusLive cUIVenusLive, Object[] objArr);

    public static final native float CUIVenusLive_GetHairDyeOmbreMappingRange(long j11, CUIVenusLive cUIVenusLive);

    public static final native float CUIVenusLive_GetHairDyeOmbreY(long j11, CUIVenusLive cUIVenusLive);

    public static final native int CUIVenusLive_GetInternalModelVersion(long j11, CUIVenusLive cUIVenusLive, long j12, UIFaceModelCacheVector uIFaceModelCacheVector);

    public static final native boolean CUIVenusLive_GetIrisRadius(long j11, CUIVenusLive cUIVenusLive, Object obj, Object obj2, Object obj3);

    public static final native int CUIVenusLive_GetLiveLookParameters(long j11, CUIVenusLive cUIVenusLive, long j12, UILookParameters uILookParameters, int i11, long j13, UILookParameters uILookParameters2);

    public static final native boolean CUIVenusLive_GetMakeupMetadata(long j11, CUIVenusLive cUIVenusLive, Object[] objArr, Object[] objArr2, Object[] objArr3, Object[] objArr4, Object[] objArr5, Object[] objArr6, Object[] objArr7, Object[] objArr8, Object[] objArr9, Object[] objArr10, Object[] objArr11, Object[] objArr12, Object[] objArr13, Object[] objArr14, Object[] objArr15, Object[] objArr16, Object[] objArr17, Object[] objArr18, Object[] objArr19, Object[] objArr20, Object[] objArr21, Object[] objArr22, Object[] objArr23, Object[] objArr24, Object[] objArr25, Object[] objArr26, boolean[] zArr);

    public static final native int CUIVenusLive_GetMaxDetectedFaceCount();

    public static final native boolean CUIVenusLive_GetNextApngImage(long j11, CUIVenusLive cUIVenusLive, int i11, Object obj, int i12, int i13, Object obj2, Object obj3, Object obj4);

    public static final native boolean CUIVenusLive_GetShadeFinderData(long j11, CUIVenusLive cUIVenusLive, Object obj);

    public static final native boolean CUIVenusLive_GetShadeFinderNeighborShade(long j11, CUIVenusLive cUIVenusLive, int[] iArr, int i11, Object obj);

    public static final native boolean CUIVenusLive_GetShadeFinderShadeMatching(long j11, CUIVenusLive cUIVenusLive, int i11, int[] iArr, Object[] objArr);

    public static final native boolean CUIVenusLive_GetSkinCareCheckResult(long j11, CUIVenusLive cUIVenusLive, Object obj);

    public static final native boolean CUIVenusLive_GetSkinCareData(long j11, CUIVenusLive cUIVenusLive, Object obj);

    public static final native boolean CUIVenusLive_GetSmoothedInfo(long j11, CUIVenusLive cUIVenusLive, int i11, Object obj, Object obj2);

    public static final native boolean CUIVenusLive_InitFaceDistortionModelCommonInfo(long j11, CUIVenusLive cUIVenusLive, int i11, int i12);

    public static final native boolean CUIVenusLive_InitialEyeContactModelCommonInfo(long j11, CUIVenusLive cUIVenusLive, int i11, int i12);

    public static final native void CUIVenusLive_InitialEyeModelCommonInfo(long j11, CUIVenusLive cUIVenusLive, Object[] objArr, int i11, int i12);

    public static final native boolean CUIVenusLive_InitializeFaceContour(long j11, CUIVenusLive cUIVenusLive, Object obj, Object obj2, Object obj3, Object obj4);

    public static final native boolean CUIVenusLive_IsHairDetected(long j11, CUIVenusLive cUIVenusLive);

    public static final native boolean CUIVenusLive_IsKissDetected(long j11, CUIVenusLive cUIVenusLive);

    public static final native boolean CUIVenusLive_IsModelLoaded(long j11, CUIVenusLive cUIVenusLive);

    public static final native boolean CUIVenusLive_LoadEarringModel(long j11, CUIVenusLive cUIVenusLive, String str, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, boolean z11, boolean z12);

    public static final native boolean CUIVenusLive_LoadObject3DHDR(long j11, CUIVenusLive cUIVenusLive, String str, Object obj);

    public static final native boolean CUIVenusLive_LoadObject3DModel(long j11, CUIVenusLive cUIVenusLive, String str, String str2, Object obj, boolean z11, boolean z12, Object obj2);

    public static final native boolean CUIVenusLive_PreprocessEyeContactModel(long j11, CUIVenusLive cUIVenusLive, int[] iArr, byte[] bArr, Object[] objArr, int[] iArr2, int i11, int i12, int i13, int i14, int i15, int i16);

    public static final native boolean CUIVenusLive_PreprocessEyelashModel(long j11, CUIVenusLive cUIVenusLive, byte[] bArr, Object[] objArr, int i11, int i12, int i13, int i14);

    public static final native boolean CUIVenusLive_PreprocessEyelinerModel(long j11, CUIVenusLive cUIVenusLive, int[] iArr, Object[] objArr, int[] iArr2, int[] iArr3, int i11, int i12, int i13);

    public static final native boolean CUIVenusLive_PreprocessEyeshadowModel(long j11, CUIVenusLive cUIVenusLive, int[] iArr, Object[] objArr, int[] iArr2, int[] iArr3, int i11, int i12, int i13, int i14, int i15, int[] iArr4, int i16, byte[] bArr, byte[] bArr2);

    public static final native boolean CUIVenusLive_ReleaseInternalModel(long j11, CUIVenusLive cUIVenusLive);

    public static final native boolean CUIVenusLive_ReleaseSkinAnalysisBuffer(long j11, CUIVenusLive cUIVenusLive);

    public static final native boolean CUIVenusLive_ResetApngDecoder(long j11, CUIVenusLive cUIVenusLive, int i11);

    public static final native boolean CUIVenusLive_SetClassicLipstick(long j11, CUIVenusLive cUIVenusLive, Object obj, int i11, int i12, Object[] objArr, boolean z11, int i13, int i14, long j12, UIShimmer uIShimmer);

    public static final native boolean CUIVenusLive_SetEnableEyebrowGoldenRatio(long j11, CUIVenusLive cUIVenusLive, boolean z11);

    public static final native boolean CUIVenusLive_SetEventInfo(long j11, CUIVenusLive cUIVenusLive, int i11, int i12);

    public static final native boolean CUIVenusLive_SetEyeContactSize(long j11, CUIVenusLive cUIVenusLive, int i11, int i12);

    public static final native boolean CUIVenusLive_SetEyebrowMatchOriginalThickness(long j11, CUIVenusLive cUIVenusLive, boolean z11);

    public static final native boolean CUIVenusLive_SetFaceDistortionModel(long j11, CUIVenusLive cUIVenusLive, Object obj, byte[] bArr, int i11);

    public static final native boolean CUIVenusLive_SetFaceSizeLowerBound(long j11, CUIVenusLive cUIVenusLive, int i11);

    public static final native boolean CUIVenusLive_SetHairDyeParameter(long j11, CUIVenusLive cUIVenusLive, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int i11, int i12, float f11, float f12, float f13, float f14, int i13);

    public static final native int CUIVenusLive_SetInternalModelPaths(long j11, CUIVenusLive cUIVenusLive, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z11);

    public static final native boolean CUIVenusLive_SetMakeupParameters(long j11, CUIVenusLive cUIVenusLive, boolean z11, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i21, boolean z12, boolean z13, boolean[] zArr, int[] iArr, boolean[] zArr2, Object[] objArr, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z21, boolean z22, boolean z23, boolean z24, boolean z25, boolean z26, boolean z27, boolean z28, Object[] objArr2, Object[] objArr3, int[] iArr2, int[] iArr3, int[] iArr4, float[] fArr, Object[] objArr4, Object[] objArr5, Object[] objArr6, float[] fArr2, float[] fArr3, Object[] objArr7, int i22);

    public static final native boolean CUIVenusLive_SetMaxDetectedFaceNumber(long j11, CUIVenusLive cUIVenusLive, int i11);

    public static final native boolean CUIVenusLive_SetShadeFinderMode(long j11, CUIVenusLive cUIVenusLive, int i11);

    public static final native boolean CUIVenusLive_SetSkinCareFeatureColor(long j11, CUIVenusLive cUIVenusLive, long j12, UIColorVector uIColorVector);

    public static final native boolean CUIVenusLive_SetSkinCareParametersLive(long j11, CUIVenusLive cUIVenusLive, boolean z11, boolean z12, boolean z13, boolean z14);

    public static final native void CUIVenusLive_SetSkinSmoothFilterStatus(long j11, CUIVenusLive cUIVenusLive, boolean z11, float f11);

    public static final native boolean CUIVenusLive_SetStickerInfo(long j11, CUIVenusLive cUIVenusLive, Object[] objArr, Object[] objArr2, Object[] objArr3, Object[] objArr4, Object[] objArr5, Object[] objArr6, Object[] objArr7, Object[] objArr8, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6, int[] iArr7, int i11);

    public static final native boolean CUIVenusLive_SetTrackingMode(long j11, CUIVenusLive cUIVenusLive, int i11);

    public static final native boolean CUIVenusLive_StopDecodeApng(long j11, CUIVenusLive cUIVenusLive, int i11);

    public static final native void CUIVenusLive_TrackYUV420Biplanar(long j11, CUIVenusLive cUIVenusLive, byte[] bArr, int i11, int i12, int i13, boolean z11, boolean z12);

    public static final native boolean CUIVenusLive_Update3DEyeBrowTexture(long j11, CUIVenusLive cUIVenusLive, Object obj, Object obj2);

    public static final native boolean CUIVenusLive_UpdateBlush3DTexture(long j11, CUIVenusLive cUIVenusLive, int i11, int i12, byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static final native boolean CUIVenusLive_UpdateFaceArtAndTattooTexture(long j11, CUIVenusLive cUIVenusLive, Object[] objArr, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9);

    public static final native int CUIVenusLive_convertEyebrowModeToEngine(int i11);

    public static final native int CUIVenusPhoto_AnalyzeImage(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer);

    public static final native boolean CUIVenusPhoto_AnalyzeSampleImage(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIFaceRect uIFaceRect, long j14, UIFaceAlignmentData uIFaceAlignmentData, long j15, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, long j16, UIEyebrow3dEditPoints uIEyebrow3dEditPoints2, float f11, float f12);

    public static final native int CUIVenusPhoto_ChangeWigColor(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIWigColor uIWigColor, float f11);

    public static final native int CUIVenusPhoto_ContinueWarpWigInLocal(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFacePoint uIFacePoint);

    public static final native boolean CUIVenusPhoto_DeepDetectHairDyeMask__SWIG_0(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIFaceRectVector uIFaceRectVector, int i11, boolean z11);

    public static final native boolean CUIVenusPhoto_DeepDetectHairDyeMask__SWIG_1(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIFaceRect uIFaceRect, boolean z11);

    public static final native boolean CUIVenusPhoto_DeleteFaceInfo(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceRect uIFaceRect);

    public static final native boolean CUIVenusPhoto_DetectOpenMouth(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceRect uIFaceRect);

    public static final native boolean CUIVenusPhoto_DilateMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, int i11);

    public static final native boolean CUIVenusPhoto_DumpWigOffsetData(long j11, CUIVenusPhoto cUIVenusPhoto, String str);

    public static final native int CUIVenusPhoto_EndWarpWigInLocal(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIWarpedWigImageInfo uIWarpedWigImageInfo, long j13, CImageBuffer cImageBuffer);

    public static final native void CUIVenusPhoto_FlipWig(long j11, CUIVenusPhoto cUIVenusPhoto, boolean z11);

    public static final native int CUIVenusPhoto_GenerateHairDyeThumbnail(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIColor uIColor, int i11);

    public static final native int CUIVenusPhoto_GetAutoWigLuminanceParameter(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceRect uIFaceRect, long j13, UIWigLuminance uIWigLuminance);

    public static final native boolean CUIVenusPhoto_GetConcealerDiffMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer);

    public static final native boolean CUIVenusPhoto_GetDarkCircleDiffMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer);

    public static final native int CUIVenusPhoto_GetEarringNaturalLookingModelAndTranslation(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIFacePoint uIFacePoint, long j14, UIFacePoint uIFacePoint2, long j15, UIFacePoint uIFacePoint3, long j16, UIFacePoint uIFacePoint4, long j17, UIFaceRect uIFaceRect, long j18, UIFaceAlignmentData uIFaceAlignmentData, long j19, UITransform uITransform, long j21, UITransform uITransform2);

    public static final native boolean CUIVenusPhoto_GetEmulationModelVersion(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceModelCacheVector uIFaceModelCacheVector);

    public static final native boolean CUIVenusPhoto_GetEyeBagDiffMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer);

    public static final native boolean CUIVenusPhoto_GetEyebrow3DEditPoints(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, long j13, UIEyebrow3dEditPoints uIEyebrow3dEditPoints2);

    public static final native int CUIVenusPhoto_GetEyebrowOriginalColor(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceRect uIFaceRect, long j13, UIIntVector uIIntVector);

    public static final native int CUIVenusPhoto_GetEyewearNaturalLookingModelAndTranslation(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIFacePoint uIFacePoint, long j14, UIFacePoint uIFacePoint2, int i11, long j15, UIFaceRect uIFaceRect, long j16, UIFaceAlignmentData uIFaceAlignmentData, long j17, UITransform uITransform);

    public static final native int CUIVenusPhoto_GetFaceAlignmentData(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceRect uIFaceRect, long j13, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native boolean CUIVenusPhoto_GetFaceAlignmentDataForSticker(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceRect uIFaceRect, long j13, UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData);

    public static final native int CUIVenusPhoto_GetFaceInfos(long j11, CUIVenusPhoto cUIVenusPhoto, int i11, long j12, UIFaceRectVector uIFaceRectVector);

    public static final native int CUIVenusPhoto_GetFaceLiftInternalModelVersion(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceModelCacheVector uIFaceModelCacheVector);

    public static final native int CUIVenusPhoto_GetFaceMetadata(long j11, CUIVenusPhoto cUIVenusPhoto, int i11, long j12, UIFaceMetadataVector uIFaceMetadataVector);

    public static final native boolean CUIVenusPhoto_GetFacePointFromFaceAlignData(long j11, CUIVenusPhoto cUIVenusPhoto, int i11, long j12, UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData, long j13, UIFacePoint uIFacePoint);

    public static final native boolean CUIVenusPhoto_GetFacePose(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceRect uIFaceRect, long j13, UIPose uIPose);

    public static final native boolean CUIVenusPhoto_GetHairDyeMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer);

    public static final native int CUIVenusPhoto_GetHairbandNaturalLookingModelAndTranslation(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIFacePoint uIFacePoint, long j14, UIFacePoint uIFacePoint2, long j15, UIFaceRect uIFaceRect, long j16, UIFaceAlignmentData uIFaceAlignmentData, long j17, UITransform uITransform);

    public static final native int CUIVenusPhoto_GetHatNaturalLookingModelAndTranslation(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIFacePoint uIFacePoint, long j14, UIFacePoint uIFacePoint2, long j15, UIFaceRect uIFaceRect, long j16, UIFaceAlignmentData uIFaceAlignmentData, long j17, UITransform uITransform);

    public static final native float CUIVenusPhoto_GetInferenceFPS(long j11, CUIVenusPhoto cUIVenusPhoto);

    public static final native int CUIVenusPhoto_GetInternalModelVersion(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceModelCacheVector uIFaceModelCacheVector);

    public static final native int CUIVenusPhoto_GetIrisRadius(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceRect uIFaceRect, long j13, UIIrisRadius uIIrisRadius, long j14, UIIrisRadius uIIrisRadius2);

    public static final native boolean CUIVenusPhoto_GetIsMaskDetected(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIFaceRect uIFaceRect);

    public static final native int CUIVenusPhoto_GetLookParameters(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UILookParameters uILookParameters, int i11, long j13, UILookParameters uILookParameters2);

    public static final native boolean CUIVenusPhoto_GetMakeupImage(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, CImageBuffer cImageBuffer2, long j14, UIVenusPipelineSettings uIVenusPipelineSettings, long j15, UIFaceMetadataVector uIFaceMetadataVector);

    public static final native int CUIVenusPhoto_GetNecklaceNaturalLookingModelAndTranslation(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIFacePoint uIFacePoint, long j14, UIFacePoint uIFacePoint2, long j15, UIFaceRect uIFaceRect, long j16, UIFaceAlignmentData uIFaceAlignmentData, long j17, UITransform uITransform, long j18, UIFacePoint uIFacePoint3, long j19, UIFacePoint uIFacePoint4, float f11);

    public static final native boolean CUIVenusPhoto_GetPoreDiffMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer);

    public static final native boolean CUIVenusPhoto_GetPreciseFaceRect(long j11, CUIVenusPhoto cUIVenusPhoto, int i11, int i12, long j12, UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData, long j13, UIFaceRect uIFaceRect);

    public static final native int CUIVenusPhoto_GetRecommendFaceContour(long j11, CUIVenusPhoto cUIVenusPhoto, String str, long j12, UIIntPoint uIIntPoint, int i11, long j13, UIColorVector uIColorVector, long j14, UIRecommendFaceContour uIRecommendFaceContour);

    public static final native int CUIVenusPhoto_GetRecommendFaceContourPalette(long j11, CUIVenusPhoto cUIVenusPhoto, int i11, long j12, UIColorVector uIColorVector, long j13, UIIntVector uIIntVector, long j14, UIIntVector uIIntVector2);

    public static final native int CUIVenusPhoto_GetRecommendFoundation(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIColorVector uIColorVector);

    public static final native boolean CUIVenusPhoto_GetRednessDiffMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer);

    public static final native boolean CUIVenusPhoto_GetUnevennessDiffMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer);

    public static final native int CUIVenusPhoto_GetWarpedWigModel(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UIWarpedWigImageInfo uIWarpedWigImageInfo, long j13, CImageBuffer cImageBuffer);

    public static final native boolean CUIVenusPhoto_GetWrinkleDiffMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer);

    public static final native boolean CUIVenusPhoto_IsLocalMoveWigDone(long j11, CUIVenusPhoto cUIVenusPhoto);

    public static final native boolean CUIVenusPhoto_IsModelLoaded(long j11, CUIVenusPhoto cUIVenusPhoto);

    public static final native boolean CUIVenusPhoto_LoadWigOffsetData(long j11, CUIVenusPhoto cUIVenusPhoto, String str);

    public static final native int CUIVenusPhoto_ManualGetFaceAlignmentData(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIFacePoint uIFacePoint, long j14, UIFacePoint uIFacePoint2, long j15, UIFacePoint uIFacePoint3, long j16, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native boolean CUIVenusPhoto_ProjectAlignmentDataBackToSourceImage(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, long j13, UIVenusPipelineSettings uIVenusPipelineSettings, long j14, UIFaceBrow uIFaceBrow, long j15, UIFaceBrow uIFaceBrow2);

    public static final native int CUIVenusPhoto_QueryGetMakeupImageProgress(long j11, CUIVenusPhoto cUIVenusPhoto);

    public static final native boolean CUIVenusPhoto_ReleaseMakeupBuffer(long j11, CUIVenusPhoto cUIVenusPhoto);

    public static final native void CUIVenusPhoto_ReleaseWigOffsetData(long j11, CUIVenusPhoto cUIVenusPhoto);

    public static final native boolean CUIVenusPhoto_ResetGetMakeupImageProgress(long j11, CUIVenusPhoto cUIVenusPhoto);

    public static final native void CUIVenusPhoto_ResetWarpWig(long j11, CUIVenusPhoto cUIVenusPhoto);

    public static final native boolean CUIVenusPhoto_SetEmulationModelPaths(long j11, CUIVenusPhoto cUIVenusPhoto, String str, String str2, String str3, String str4, String str5, String str6);

    public static final native int CUIVenusPhoto_SetFaceLiftInternalModelPaths(long j11, CUIVenusPhoto cUIVenusPhoto, String str, String str2, String str3);

    public static final native boolean CUIVenusPhoto_SetHairDyeMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer);

    public static final native int CUIVenusPhoto_SetInternalModelPaths(long j11, CUIVenusPhoto cUIVenusPhoto, String str, String str2, String str3, String str4, String str5, String str6, boolean z11);

    public static final native int CUIVenusPhoto_StartWarpWigInLocal(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, UITransform uITransform, long j13, UIFacePoint uIFacePoint);

    public static final native int CUIVenusPhoto_UpdateHairDyeMask(long j11, CUIVenusPhoto cUIVenusPhoto, long j12, CImageBuffer cImageBuffer, ByteBuffer byteBuffer, boolean z11, int i11);

    public static final native int SAMPLE_MODEL_IMAGE_HEIGHT_get();

    public static final native int SAMPLE_MODEL_IMAGE_WIDTH_get();

    public static final native void UIBoolVector_add(long j11, UIBoolVector uIBoolVector, boolean z11);

    public static final native boolean UIBoolVector_get(long j11, UIBoolVector uIBoolVector, int i11);

    public static final native void UIColorVector_add(long j11, UIColorVector uIColorVector, long j12, UIColor uIColor);

    public static final native void UIColor_setBLevel(long j11, UIColor uIColor, int i11);

    public static final native void UIColor_setGLevel(long j11, UIColor uIColor, int i11);

    public static final native void UIColor_setRLevel(long j11, UIColor uIColor, int i11);

    public static final native long UIEyebrow3dEditPoints_bottom_get(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints);

    public static final native void UIEyebrow3dEditPoints_bottom_set(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, long j12, UIFacePoint uIFacePoint);

    public static final native boolean UIEyebrow3dEditPoints_equals(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, long j12, UIEyebrow3dEditPoints uIEyebrow3dEditPoints2);

    public static final native long UIEyebrow3dEditPoints_inner_get(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints);

    public static final native void UIEyebrow3dEditPoints_inner_set(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, long j12, UIFacePoint uIFacePoint);

    public static final native long UIEyebrow3dEditPoints_outer_get(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints);

    public static final native void UIEyebrow3dEditPoints_outer_set(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, long j12, UIFacePoint uIFacePoint);

    public static final native long UIEyebrow3dEditPoints_topInner_get(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints);

    public static final native void UIEyebrow3dEditPoints_topInner_set(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, long j12, UIFacePoint uIFacePoint);

    public static final native long UIEyebrow3dEditPoints_topMiddle_get(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints);

    public static final native void UIEyebrow3dEditPoints_topMiddle_set(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, long j12, UIFacePoint uIFacePoint);

    public static final native long UIEyebrow3dEditPoints_topOuter_get(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints);

    public static final native void UIEyebrow3dEditPoints_topOuter_set(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, long j12, UIFacePoint uIFacePoint);

    public static final native void UIFaceAlignmentDataAll_push(long j11, UIFaceAlignmentDataAll uIFaceAlignmentDataAll, int i11, long j12, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native boolean UIFaceAlignmentData_equals(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceAlignmentData uIFaceAlignmentData2);

    public static final native long UIFaceAlignmentData_getChin(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getForehead(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getLeftBrow(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getLeftEar(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getLeftEye(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getLeftShape(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getMouth(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getNose(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getRightBrow(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getRightEar(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getRightEye(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long UIFaceAlignmentData_getRightShape(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native void UIFaceAlignmentData_setChin(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceChin uIFaceChin);

    public static final native void UIFaceAlignmentData_setForehead(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceForehead uIFaceForehead);

    public static final native void UIFaceAlignmentData_setLeftBrow(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceBrow uIFaceBrow);

    public static final native void UIFaceAlignmentData_setLeftEar(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceEar uIFaceEar);

    public static final native void UIFaceAlignmentData_setLeftEye(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceEye uIFaceEye);

    public static final native void UIFaceAlignmentData_setLeftShape(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceShape uIFaceShape);

    public static final native void UIFaceAlignmentData_setMouth(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceAlignmentData_setNose(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceNose uIFaceNose);

    public static final native void UIFaceAlignmentData_setRightBrow(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceBrow uIFaceBrow);

    public static final native void UIFaceAlignmentData_setRightEar(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceEar uIFaceEar);

    public static final native void UIFaceAlignmentData_setRightEye(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceEye uIFaceEye);

    public static final native void UIFaceAlignmentData_setRightShape(long j11, UIFaceAlignmentData uIFaceAlignmentData, long j12, UIFaceShape uIFaceShape);

    public static final native long UIFaceBrow_bottom_get(long j11, UIFaceBrow uIFaceBrow);

    public static final native void UIFaceBrow_bottom_set(long j11, UIFaceBrow uIFaceBrow, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceBrow_left_get(long j11, UIFaceBrow uIFaceBrow);

    public static final native void UIFaceBrow_left_set(long j11, UIFaceBrow uIFaceBrow, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceBrow_right_get(long j11, UIFaceBrow uIFaceBrow);

    public static final native void UIFaceBrow_right_set(long j11, UIFaceBrow uIFaceBrow, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceBrow_top_get(long j11, UIFaceBrow uIFaceBrow);

    public static final native void UIFaceBrow_top_set(long j11, UIFaceBrow uIFaceBrow, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceChin_center_get(long j11, UIFaceChin uIFaceChin);

    public static final native void UIFaceChin_center_set(long j11, UIFaceChin uIFaceChin, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceEar_bottom_get(long j11, UIFaceEar uIFaceEar);

    public static final native void UIFaceEar_bottom_set(long j11, UIFaceEar uIFaceEar, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceEar_top_get(long j11, UIFaceEar uIFaceEar);

    public static final native void UIFaceEar_top_set(long j11, UIFaceEar uIFaceEar, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceEye_bottom_get(long j11, UIFaceEye uIFaceEye);

    public static final native void UIFaceEye_bottom_set(long j11, UIFaceEye uIFaceEye, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceEye_center_get(long j11, UIFaceEye uIFaceEye);

    public static final native void UIFaceEye_center_set(long j11, UIFaceEye uIFaceEye, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceEye_left_get(long j11, UIFaceEye uIFaceEye);

    public static final native void UIFaceEye_left_set(long j11, UIFaceEye uIFaceEye, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceEye_right_get(long j11, UIFaceEye uIFaceEye);

    public static final native void UIFaceEye_right_set(long j11, UIFaceEye uIFaceEye, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceEye_top_get(long j11, UIFaceEye uIFaceEye);

    public static final native void UIFaceEye_top_set(long j11, UIFaceEye uIFaceEye, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceForehead_left_get(long j11, UIFaceForehead uIFaceForehead);

    public static final native void UIFaceForehead_left_set(long j11, UIFaceForehead uIFaceForehead, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceForehead_middle_get(long j11, UIFaceForehead uIFaceForehead);

    public static final native void UIFaceForehead_middle_set(long j11, UIFaceForehead uIFaceForehead, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceForehead_right_get(long j11, UIFaceForehead uIFaceForehead);

    public static final native void UIFaceForehead_right_set(long j11, UIFaceForehead uIFaceForehead, long j12, UIFacePoint uIFacePoint);

    public static final native void UIFaceMetadataVector_add(long j11, UIFaceMetadataVector uIFaceMetadataVector, long j12, UIFaceMetadata uIFaceMetadata);

    public static final native long UIFaceMetadataVector_get(long j11, UIFaceMetadataVector uIFaceMetadataVector, int i11);

    public static final native long UIFaceMetadataVector_size(long j11, UIFaceMetadataVector uIFaceMetadataVector);

    public static final native void UIFaceModelCacheVector_add(long j11, UIFaceModelCacheVector uIFaceModelCacheVector, String str);

    public static final native void UIFaceModelCacheVector_clear(long j11, UIFaceModelCacheVector uIFaceModelCacheVector);

    public static final native String UIFaceModelCacheVector_get(long j11, UIFaceModelCacheVector uIFaceModelCacheVector, int i11);

    public static final native boolean UIFaceModelCacheVector_isEmpty(long j11, UIFaceModelCacheVector uIFaceModelCacheVector);

    public static final native long UIFaceModelCacheVector_size(long j11, UIFaceModelCacheVector uIFaceModelCacheVector);

    public static final native int UIFaceModifiedROI_getBottom(long j11, UIFaceModifiedROI uIFaceModifiedROI);

    public static final native int UIFaceModifiedROI_getLeft(long j11, UIFaceModifiedROI uIFaceModifiedROI);

    public static final native int UIFaceModifiedROI_getRight(long j11, UIFaceModifiedROI uIFaceModifiedROI);

    public static final native int UIFaceModifiedROI_getTop(long j11, UIFaceModifiedROI uIFaceModifiedROI);

    public static final native long UIFaceMouth_bottomLip1_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_bottomLip1_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_bottomLip2_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_bottomLip2_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_interpBottomLeft_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_interpBottomLeft_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_interpBottomRight_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_interpBottomRight_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_interpInnerLeft_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_interpInnerLeft_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_interpInnerRight_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_interpInnerRight_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_interpLowerLeft_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_interpLowerLeft_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_interpLowerRight_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_interpLowerRight_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_interpTopLeft_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_interpTopLeft_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_interpTopRight_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_interpTopRight_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_interpUpperLeft_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_interpUpperLeft_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_interpUpperRight_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_interpUpperRight_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_leftCorner_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_leftCorner_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_rightCorner_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_rightCorner_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_topLip1_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_topLip1_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceMouth_topLip2_get(long j11, UIFaceMouth uIFaceMouth);

    public static final native void UIFaceMouth_topLip2_set(long j11, UIFaceMouth uIFaceMouth, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceNose_bottom_get(long j11, UIFaceNose uIFaceNose);

    public static final native void UIFaceNose_bottom_set(long j11, UIFaceNose uIFaceNose, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceNose_bridgeTop_get(long j11, UIFaceNose uIFaceNose);

    public static final native void UIFaceNose_bridgeTop_set(long j11, UIFaceNose uIFaceNose, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceNose_left_get(long j11, UIFaceNose uIFaceNose);

    public static final native void UIFaceNose_left_set(long j11, UIFaceNose uIFaceNose, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceNose_right_get(long j11, UIFaceNose uIFaceNose);

    public static final native void UIFaceNose_right_set(long j11, UIFaceNose uIFaceNose, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceNose_top_get(long j11, UIFaceNose uIFaceNose);

    public static final native void UIFaceNose_top_set(long j11, UIFaceNose uIFaceNose, long j12, UIFacePoint uIFacePoint);

    public static final native float UIFacePoint_x_get(long j11, UIFacePoint uIFacePoint);

    public static final native void UIFacePoint_x_set(long j11, UIFacePoint uIFacePoint, float f11);

    public static final native float UIFacePoint_y_get(long j11, UIFacePoint uIFacePoint);

    public static final native void UIFacePoint_y_set(long j11, UIFacePoint uIFacePoint, float f11);

    public static final native void UIFaceRectVector_add(long j11, UIFaceRectVector uIFaceRectVector, long j12, UIFaceRect uIFaceRect);

    public static final native long UIFaceRectVector_get(long j11, UIFaceRectVector uIFaceRectVector, int i11);

    public static final native boolean UIFaceRectVector_isEmpty(long j11, UIFaceRectVector uIFaceRectVector);

    public static final native long UIFaceRectVector_size(long j11, UIFaceRectVector uIFaceRectVector);

    public static final native int UIFaceRect_getBottom(long j11, UIFaceRect uIFaceRect);

    public static final native int UIFaceRect_getLeft(long j11, UIFaceRect uIFaceRect);

    public static final native int UIFaceRect_getRight(long j11, UIFaceRect uIFaceRect);

    public static final native int UIFaceRect_getTop(long j11, UIFaceRect uIFaceRect);

    public static final native void UIFaceRect_setBottom(long j11, UIFaceRect uIFaceRect, int i11);

    public static final native void UIFaceRect_setLeft(long j11, UIFaceRect uIFaceRect, int i11);

    public static final native void UIFaceRect_setRight(long j11, UIFaceRect uIFaceRect, int i11);

    public static final native void UIFaceRect_setTop(long j11, UIFaceRect uIFaceRect, int i11);

    public static final native long UIFaceShape_shape1_get(long j11, UIFaceShape uIFaceShape);

    public static final native void UIFaceShape_shape1_set(long j11, UIFaceShape uIFaceShape, long j12, UIFacePoint uIFacePoint);

    public static final native long UIFaceShape_shape2_get(long j11, UIFaceShape uIFaceShape);

    public static final native void UIFaceShape_shape2_set(long j11, UIFaceShape uIFaceShape, long j12, UIFacePoint uIFacePoint);

    public static final native void UIFaceTattooColorVector_add(long j11, UIFaceTattooColorVector uIFaceTattooColorVector, long j12, UIFaceTattooColor uIFaceTattooColor);

    public static final native void UIFaceTattooColor_setBRatio(long j11, UIFaceTattooColor uIFaceTattooColor, int i11);

    public static final native void UIFaceTattooColor_setBrightness(long j11, UIFaceTattooColor uIFaceTattooColor, int i11);

    public static final native void UIFaceTattooColor_setColorAdjustable(long j11, UIFaceTattooColor uIFaceTattooColor, boolean z11);

    public static final native void UIFaceTattooColor_setContrastFirstNewy(long j11, UIFaceTattooColor uIFaceTattooColor, int i11);

    public static final native void UIFaceTattooColor_setContrastFirstOldy(long j11, UIFaceTattooColor uIFaceTattooColor, int i11);

    public static final native void UIFaceTattooColor_setContrastSecondNewy(long j11, UIFaceTattooColor uIFaceTattooColor, int i11);

    public static final native void UIFaceTattooColor_setContrastSecondOldy(long j11, UIFaceTattooColor uIFaceTattooColor, int i11);

    public static final native void UIFaceTattooColor_setGRatio(long j11, UIFaceTattooColor uIFaceTattooColor, int i11);

    public static final native void UIFaceTattooColor_setLuminanceParameter(long j11, UIFaceTattooColor uIFaceTattooColor, int i11);

    public static final native void UIFaceTattooColor_setRRatio(long j11, UIFaceTattooColor uIFaceTattooColor, int i11);

    public static final native void UIIntPointVector_add(long j11, UIIntPointVector uIIntPointVector, long j12, UIIntPoint uIIntPoint);

    public static final native void UIIntPoint_x_set(long j11, UIIntPoint uIIntPoint, int i11);

    public static final native void UIIntPoint_y_set(long j11, UIIntPoint uIIntPoint, int i11);

    public static final native void UIIntVector_add(long j11, UIIntVector uIIntVector, int i11);

    public static final native int UIIntVector_get(long j11, UIIntVector uIIntVector, int i11);

    public static final native long UIIntVector_size(long j11, UIIntVector uIIntVector);

    public static final native float UIIrisRadius_getValue(long j11, UIIrisRadius uIIrisRadius);

    public static final native void UIIrisRadius_setValue(long j11, UIIrisRadius uIIrisRadius, float f11);

    public static final native void UILookParameters_dumpDebugString(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getAntiShineIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getBlushIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getDoubleEyelidsIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getEyeBrowHiddenIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getEyeBrowIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getEyeContactsIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getEyeLashIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getEyeLinerIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getEyeShadowCount(long j11, UILookParameters uILookParameters);

    public static final native void UILookParameters_getEyeShadowIntensity(long j11, UILookParameters uILookParameters, long j12, UIIntVector uIIntVector);

    public static final native void UILookParameters_getEyeShadowShimmerIntensity(long j11, UILookParameters uILookParameters, long j12, UIIntVector uIIntVector);

    public static final native int UILookParameters_getFaceContourIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getFaceContourPatternCount(long j11, UILookParameters uILookParameters);

    public static final native void UILookParameters_getFaceContourPatternIntensity(long j11, UILookParameters uILookParameters, long j12, UIIntVector uIIntVector);

    public static final native int UILookParameters_getHairDyeIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getLipStickIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getNoseShadowIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getSkinSmoothIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getSkinToneIntensity(long j11, UILookParameters uILookParameters);

    public static final native int UILookParameters_getSparkleEyeIntensity(long j11, UILookParameters uILookParameters);

    public static final native void UILookParameters_setAntiShineIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setBlushIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setDoubleEyelidsIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setEyeBrowHiddenIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setEyeBrowIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setEyeContactsIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setEyeLashIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setEyeLinerIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setEyeShadowCount(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setEyeShadowIntensity(long j11, UILookParameters uILookParameters, long j12, UIIntVector uIIntVector);

    public static final native void UILookParameters_setEyeShadowShimmerIntensity(long j11, UILookParameters uILookParameters, long j12, UIIntVector uIIntVector);

    public static final native void UILookParameters_setFaceContourIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setFaceContourPatternCount(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setFaceContourPatternIntensity(long j11, UILookParameters uILookParameters, long j12, UIIntVector uIIntVector);

    public static final native void UILookParameters_setHairDyeIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setLipStickIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setNoseShadowIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setSkinSmoothIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setSkinToneIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UILookParameters_setSparkleEyeIntensity(long j11, UILookParameters uILookParameters, int i11);

    public static final native void UIModelBrowEngineRect_head_set(long j11, UIModelBrowEngineRect uIModelBrowEngineRect, long j12, UIFacePoint uIFacePoint);

    public static final native void UIModelBrowEngineRect_tail_set(long j11, UIModelBrowEngineRect uIModelBrowEngineRect, long j12, UIFacePoint uIFacePoint);

    public static final native void UIModelBrowEngineRect_top_set(long j11, UIModelBrowEngineRect uIModelBrowEngineRect, long j12, UIFacePoint uIFacePoint);

    public static final native void UIModelEyeRect_bottom_set(long j11, UIModelEyeRect uIModelEyeRect, long j12, UIFacePoint uIFacePoint);

    public static final native void UIModelEyeRect_left_set(long j11, UIModelEyeRect uIModelEyeRect, long j12, UIFacePoint uIFacePoint);

    public static final native void UIModelEyeRect_right_set(long j11, UIModelEyeRect uIModelEyeRect, long j12, UIFacePoint uIFacePoint);

    public static final native void UIModelEyeRect_top_set(long j11, UIModelEyeRect uIModelEyeRect, long j12, UIFacePoint uIFacePoint);

    public static final native float UIPose_pitchDegree_get(long j11, UIPose uIPose);

    public static final native float UIPose_rollDegree_get(long j11, UIPose uIPose);

    public static final native float UIPose_yawDegree_get(long j11, UIPose uIPose);

    public static final native int UIRecommendFaceContour_colorIndex_get(long j11, UIRecommendFaceContour uIRecommendFaceContour);

    public static final native long UIRecommendFaceContour_intensities_get(long j11, UIRecommendFaceContour uIRecommendFaceContour);

    public static final native int UIShimmer_color_get(long j11, UIShimmer uIShimmer);

    public static final native int UIShimmer_density_get(long j11, UIShimmer uIShimmer);

    public static final native int UIShimmer_granularity_get(long j11, UIShimmer uIShimmer);

    public static final native int UIShimmer_intensity_get(long j11, UIShimmer uIShimmer);

    public static final native long UITransform_copy(long j11, UITransform uITransform);

    public static final native float UITransform_getRotation(long j11, UITransform uITransform);

    public static final native float UITransform_getScale(long j11, UITransform uITransform);

    public static final native float UITransform_getShiftX(long j11, UITransform uITransform);

    public static final native float UITransform_getShiftY(long j11, UITransform uITransform);

    public static final native void UITransform_setRotation(long j11, UITransform uITransform, float f11);

    public static final native void UITransform_setScale(long j11, UITransform uITransform, float f11);

    public static final native void UITransform_setShiftX(long j11, UITransform uITransform, float f11);

    public static final native void UITransform_setShiftY(long j11, UITransform uITransform, float f11);

    public static final native void UIVenusPipelineSettings_configAntiShine(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configAppleCheek(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configAutoSpotRemoval(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_configBlush(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, long j12, UIColor uIColor, String str, String str2, boolean z11);

    public static final native void UIVenusPipelineSettings_configCacheMode(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configCheekbones(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configChinLength(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configChinReshape(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configChinReshapeLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configChinReshapeRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configConcealer(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, long j12, UIColor uIColor);

    public static final native void UIVenusPipelineSettings_configDarkCircleRemoval(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configDoubleEyelid(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, long j12, UIColor uIColor, long j13, UIFaceModelCacheVector uIFaceModelCacheVector, long j14, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configEyeBagRemoval(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeContact(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIColorVector uIColorVector, long j13, UIFaceModelCacheVector uIFaceModelCacheVector, long j14, UIFaceModelCacheVector uIFaceModelCacheVector2);

    public static final native void UIVenusPipelineSettings_configEyeDistance(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeEnlarge(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeEnlargeLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeEnlargeRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeHeight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeInner(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeInnerLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeInnerRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeOuter(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeOuterLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeOuterRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyePosition(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyePositionLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyePositionRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeSlant(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeTail(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeTailLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeTailRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeUnder(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeUnderLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeUnderRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyeWidth(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configEyebrow(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIColor uIColor, long j13, UIFaceModelCacheVector uIFaceModelCacheVector, long j14, UIModelBrowEngineRect uIModelBrowEngineRect, long j15, UIModelBrowEngineRect uIModelBrowEngineRect2, long j16, UIModelBrowEngineRect uIModelBrowEngineRect3, long j17, UIModelBrowEngineRect uIModelBrowEngineRect4, long j18, UIModelBrowEngineRect uIModelBrowEngineRect5, int i13, int i14, long j19, UIFaceBrow uIFaceBrow, long j21, UIFaceBrow uIFaceBrow2, int i15, int i16, int i17, int i18, int i19, int i21, int i22, int i23, boolean z11, boolean z12, boolean z13);

    public static final native void UIVenusPipelineSettings_configEyebrowBasePoints(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UIFaceBrow uIFaceBrow, long j13, UIFaceBrow uIFaceBrow2);

    public static final native void UIVenusPipelineSettings_configEyebrowTattooLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configEyebrowTattooRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configEyelash(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, long j12, UIColor uIColor, long j13, UIFaceModelCacheVector uIFaceModelCacheVector, long j14, UIModelEyeRect uIModelEyeRect, int i12);

    public static final native void UIVenusPipelineSettings_configEyelashTattooLeftLower(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configEyelashTattooLeftUpper(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configEyelashTattooResetAll(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native void UIVenusPipelineSettings_configEyelashTattooRightLower(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configEyelashTattooRightUpper(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configEyeliner(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, long j12, UIColor uIColor, long j13, UIFaceModelCacheVector uIFaceModelCacheVector, long j14, UIModelEyeRect uIModelEyeRect, int i12);

    public static final native void UIVenusPipelineSettings_configEyelinerTattooLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configEyelinerTattooRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configEyeshadow(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UIIntVector uIIntVector, long j13, UIColorVector uIColorVector, long j14, UIIntVector uIIntVector2, long j15, UIFaceModelCacheVector uIFaceModelCacheVector, long j16, UIModelEyeRect uIModelEyeRect, long j17, UIIntVector uIIntVector3, int i11);

    public static final native void UIVenusPipelineSettings_configEyeshadowTattooLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configEyeshadowTattooRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, int i12, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIModelEyeRect uIModelEyeRect);

    public static final native void UIVenusPipelineSettings_configFaceArt(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIIntPointVector uIIntPointVector, long j14, UIFaceTattooColorVector uIFaceTattooColorVector, long j15, UIBoolVector uIBoolVector);

    public static final native void UIVenusPipelineSettings_configFaceArtLayer2(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIIntPointVector uIIntPointVector, long j14, UIFaceTattooColorVector uIFaceTattooColorVector);

    public static final native void UIVenusPipelineSettings_configFaceContourPattern(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, long j12, UIIntVector uIIntVector, long j13, UIIntVector uIIntVector2, long j14, UIColorVector uIColorVector, long j15, UIIntVector uIIntVector3, long j16, UIFaceModelCacheVector uIFaceModelCacheVector, long j17, UIIntPointVector uIIntPointVector, long j18, UIBoolVector uIBoolVector);

    public static final native void UIVenusPipelineSettings_configFaceData(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UIFaceRect uIFaceRect, long j13, UIFaceAlignmentData uIFaceAlignmentData, long j14, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, long j15, UIEyebrow3dEditPoints uIEyebrow3dEditPoints2);

    public static final native void UIVenusPipelineSettings_configFaceLessen(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceLessenLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceLessenRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceMiddle(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceReshape(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceReshapeLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceReshapeRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceShorten(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceWidget(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UIFaceModelCacheVector uIFaceModelCacheVector, long j13, UIIntPointVector uIIntPointVector, long j14, UIFaceTattooColorVector uIFaceTattooColorVector, long j15, UIBoolVector uIBoolVector);

    public static final native void UIVenusPipelineSettings_configFaceWidth(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceliftCheek(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceliftEyes(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceliftForehead(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceliftMouth(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFaceliftShape(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configForehead(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configFoundation(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, long j12, UIColor uIColor, int i12);

    public static final native void UIVenusPipelineSettings_configHairDyeAging(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configHairDye__SWIG_1(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UIIntVector uIIntVector, long j13, UIIntVector uIIntVector2, long j14, UIColorVector uIColorVector, int i11, float f11, float f12, boolean z11, float f13, float f14, float f15, float f16, int i12, long j15, UIFaceModelCacheVector uIFaceModelCacheVector);

    public static final native void UIVenusPipelineSettings_configHeadSize(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configHeadTop(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configInputEditPointValid(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_configJaw(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configLipPeak(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configMouthHeight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configMouthHeightLower(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configMouthHeightUpper(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configMouthOpen(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_configMouthPosition(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configMouthSize(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configMouthWidth(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configNoseBridge(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configNoseLength(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configNoseRootWidth(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configNoseShadow(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configNoseSize(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configNoseTip(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configNoseTipWidth(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configNoseWing(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configOneColorLipstick(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, boolean z11, int i12, int i13, int i14, int i15, int i16, long j12, UIShimmer uIShimmer);

    public static final native void UIVenusPipelineSettings_configPhiltrumLength(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configPointyChin(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configPointyChinLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configPointyChinRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configPoreRemoval(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configRedEyeRemoval(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_configRednessRemoval(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configRetouchLipPlumperFullness(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configRetouchLipPlumperWrinkless(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configSkinSmooth(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configSmile(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configSmileMode(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configSparkleEye(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configTeethWhitening(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11, int i11);

    public static final native void UIVenusPipelineSettings_configTemple(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configTempleLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configTempleRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configTwoColorsLipstick(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11, boolean z11, int i12, boolean z12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i21, int i22, long j12, UIShimmer uIShimmer);

    public static final native void UIVenusPipelineSettings_configUnevenness(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configVShapeFace(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configVShapeFaceLeft(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configVShapeFaceRight(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configWig(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, long j12, UITransform uITransform, long j13, UIWigColor uIWigColor, long j14, UIFaceModelCacheVector uIFaceModelCacheVector, int i11, int i12, long j15, UIWigModelAnchor uIWigModelAnchor, int i13, boolean z16);

    public static final native void UIVenusPipelineSettings_configWigTransform(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UITransform uITransform);

    public static final native void UIVenusPipelineSettings_configWrinkleRemovalAroundEyes(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configWrinkleRemovalForehead(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_configWrinkleRemovalLowerFace(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native void UIVenusPipelineSettings_enableBlush(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableConcealer(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableDoubleEyelid(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableEyeContact(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableEyebrow(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableEyelash(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableEyeliner(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableEyeshadow(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableFaceArt(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableFaceArtLayer2(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableFaceContourPattern(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableFaceWidget(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableFoundation(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableHairDye(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableLipstick(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_enableWig(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native int UIVenusPipelineSettings_getAppleCheekIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getCheekbonesIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getChinLengthIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getChinReshapeIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getChinReshapeLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getChinReshapeRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_getEnableEyeBrow(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_getEnableEyeContact(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_getEnableEyeEnlarge(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_getEnableFaceReshape(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_getEnableHairDye(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_getEnableHeadSize(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_getEnableMouthOpen(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_getEnableSmile(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_getEnableWig(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native long UIVenusPipelineSettings_getEyeContactColor(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeContactIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native long UIVenusPipelineSettings_getEyeContactMaskModelCache(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native long UIVenusPipelineSettings_getEyeContactModelCache(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeContactSizeIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeDistanceIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeEnlargeIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeEnlargeLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeEnlargeRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeHeightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeInnerIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeInnerLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeInnerRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeOuterIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeOuterLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeOuterRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyePositionIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyePositionLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyePositionRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeSlantIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeTailIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeTailLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeTailRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeUnderIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeUnderLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeUnderRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyeWidthIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getEyebrowMode(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceLessenIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceLessenLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceLessenRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceMiddleIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceReshapeIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceReshapeLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceReshapeRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceShortenIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceWidthIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceliftCheek(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceliftEyes(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceliftForehead(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceliftMouth(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getFaceliftShape(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getForeheadIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getHeadSizeIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getHeadTopIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_getIsWigModelChange(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getJawIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getLipPeakIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getMouthHeightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getMouthHeightLowerIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getMouthHeightUpperIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getMouthPositionIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getMouthSizeIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getMouthWidthIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getNoseBridgeIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getNoseLengthIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getNoseRootWidthIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getNoseSizeIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getNoseTipIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getNoseTipWidthIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getNoseWingIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native void UIVenusPipelineSettings_getOriginalEyeBrow(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UIFaceBrow uIFaceBrow, long j13, UIFaceBrow uIFaceBrow2);

    public static final native int UIVenusPipelineSettings_getPhiltrumLengthIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getPointyChinIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getPointyChinLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getPointyChinRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getRetouchLipPlumperFullness(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getRetouchLipPlumperWrinkless(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getSmileIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getTempleIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getTempleLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getTempleRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getVShapeFaceIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getVShapeFaceLeftIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_getVShapeFaceRightIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native void UIVenusPipelineSettings_getWarpedWigImageInfo(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UIWarpedWigImageInfo uIWarpedWigImageInfo);

    public static final native void UIVenusPipelineSettings_getWarpedWigTransform(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UITransform uITransform);

    public static final native int UIVenusPipelineSettings_getWhitenTeethIntensity(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native long UIVenusPipelineSettings_getWigCache(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native void UIVenusPipelineSettings_getWigModelAnchor(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, long j12, UIWigModelAnchor uIWigModelAnchor);

    public static final native long UIVenusPipelineSettings_hasFaceData(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_isEyebrow3dMode(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_isForceApplyWigPosition(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native boolean UIVenusPipelineSettings_isModelEyebrowRectAllZero(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native int UIVenusPipelineSettings_queryCacheMode(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native long UIVenusPipelineSettings_queryEyebrow3dLeftEditPoints(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native long UIVenusPipelineSettings_queryEyebrow3dRightEditPoints(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native long UIVenusPipelineSettings_queryFaceRect(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native long UIVenusPipelineSettings_queryFeaturePoints(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native void UIVenusPipelineSettings_setForceApplyWigPosition(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_setIsWigModelChange(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, boolean z11);

    public static final native void UIVenusPipelineSettings_updateEyebrowMode(long j11, UIVenusPipelineSettings uIVenusPipelineSettings, int i11);

    public static final native int UIWarpedWigImageInfo_height_get(long j11, UIWarpedWigImageInfo uIWarpedWigImageInfo);

    public static final native int UIWarpedWigImageInfo_width_get(long j11, UIWarpedWigImageInfo uIWarpedWigImageInfo);

    public static final native void UIWigColor_setBRatio(long j11, UIWigColor uIWigColor, int i11);

    public static final native void UIWigColor_setBrightness(long j11, UIWigColor uIWigColor, int i11);

    public static final native void UIWigColor_setContrastFirstNewy(long j11, UIWigColor uIWigColor, int i11);

    public static final native void UIWigColor_setContrastFirstOldy(long j11, UIWigColor uIWigColor, int i11);

    public static final native void UIWigColor_setContrastSecondNewy(long j11, UIWigColor uIWigColor, int i11);

    public static final native void UIWigColor_setContrastSecondOldy(long j11, UIWigColor uIWigColor, int i11);

    public static final native void UIWigColor_setGRatio(long j11, UIWigColor uIWigColor, int i11);

    public static final native void UIWigColor_setRRatio(long j11, UIWigColor uIWigColor, int i11);

    public static final native int UIWigLuminance_getValue(long j11, UIWigLuminance uIWigLuminance);

    public static final native void UIWigLuminance_setValue(long j11, UIWigLuminance uIWigLuminance, int i11);

    public static final native long UIWigModelAnchor_leftEyeCenter_get(long j11, UIWigModelAnchor uIWigModelAnchor);

    public static final native void UIWigModelAnchor_leftEyeCenter_set(long j11, UIWigModelAnchor uIWigModelAnchor, long j12, UIFacePoint uIFacePoint);

    public static final native void UIWigModelAnchor_leftFaceShape_set(long j11, UIWigModelAnchor uIWigModelAnchor, long j12, UIFacePoint uIFacePoint);

    public static final native long UIWigModelAnchor_rightEyeCenter_get(long j11, UIWigModelAnchor uIWigModelAnchor);

    public static final native void UIWigModelAnchor_rightEyeCenter_set(long j11, UIWigModelAnchor uIWigModelAnchor, long j12, UIFacePoint uIFacePoint);

    public static final native void UIWigModelAnchor_rightFaceShape_set(long j11, UIWigModelAnchor uIWigModelAnchor, long j12, UIFacePoint uIFacePoint);

    public static final native void delete_CUISkinBeautify(long j11);

    public static final native void delete_CUIVenusLive(long j11);

    public static final native void delete_CUIVenusPhoto(long j11);

    public static final native void delete_UIBoolVector(long j11);

    public static final native void delete_UIColor(long j11);

    public static final native void delete_UIColorVector(long j11);

    public static final native void delete_UIEyebrow3dEditPoints(long j11);

    public static final native void delete_UIFaceAlignmentData(long j11);

    public static final native void delete_UIFaceAlignmentDataAll(long j11);

    public static final native void delete_UIFaceBrow(long j11);

    public static final native void delete_UIFaceChin(long j11);

    public static final native void delete_UIFaceEar(long j11);

    public static final native void delete_UIFaceEye(long j11);

    public static final native void delete_UIFaceForehead(long j11);

    public static final native void delete_UIFaceMetadata(long j11);

    public static final native void delete_UIFaceMetadataVector(long j11);

    public static final native void delete_UIFaceModelCacheVector(long j11);

    public static final native void delete_UIFaceModifiedROI(long j11);

    public static final native void delete_UIFaceMouth(long j11);

    public static final native void delete_UIFaceNose(long j11);

    public static final native void delete_UIFacePoint(long j11);

    public static final native void delete_UIFaceRect(long j11);

    public static final native void delete_UIFaceRectVector(long j11);

    public static final native void delete_UIFaceShape(long j11);

    public static final native void delete_UIFaceTattooColor(long j11);

    public static final native void delete_UIFaceTattooColorVector(long j11);

    public static final native void delete_UIIntPoint(long j11);

    public static final native void delete_UIIntPointVector(long j11);

    public static final native void delete_UIIntVector(long j11);

    public static final native void delete_UIIrisRadius(long j11);

    public static final native void delete_UILookParameters(long j11);

    public static final native void delete_UIMakeupLiveFaceAlignData(long j11);

    public static final native void delete_UIModelBrowEngineRect(long j11);

    public static final native void delete_UIModelEyeRect(long j11);

    public static final native void delete_UIPose(long j11);

    public static final native void delete_UIRecommendFaceContour(long j11);

    public static final native void delete_UIShimmer(long j11);

    public static final native void delete_UITransform(long j11);

    public static final native void delete_UIVenusPipelineSettings(long j11);

    public static final native void delete_UIWarpParameter(long j11);

    public static final native void delete_UIWarpedWigImageInfo(long j11);

    public static final native void delete_UIWigColor(long j11);

    public static final native void delete_UIWigLuminance(long j11);

    public static final native void delete_UIWigModelAnchor(long j11);

    public static final native void delete_VN_BadLightingReport(long j11);

    public static final native void delete_VenusPhotoFaceAlignData(long j11);

    public static final native long new_CUISkinBeautify();

    public static final native long new_CUIVenusLive(long j11, CUIVenusPhoto cUIVenusPhoto);

    public static final native long new_CUIVenusPhoto();

    public static final native long new_UIBoolVector__SWIG_0();

    public static final native long new_UIColorVector__SWIG_0();

    public static final native long new_UIColor__SWIG_0();

    public static final native long new_UIColor__SWIG_1(int i11, int i12, int i13);

    public static final native long new_UIEyebrow3dEditPoints__SWIG_0();

    public static final native long new_UIEyebrow3dEditPoints__SWIG_1(long j11, UIEyebrow3dEditPoints uIEyebrow3dEditPoints);

    public static final native long new_UIFaceAlignmentDataAll__SWIG_0(int i11);

    public static final native long new_UIFaceAlignmentData__SWIG_0();

    public static final native long new_UIFaceAlignmentData__SWIG_1(long j11, UIFaceAlignmentData uIFaceAlignmentData);

    public static final native long new_UIFaceBrow__SWIG_0();

    public static final native long new_UIFaceBrow__SWIG_1(long j11, UIFaceBrow uIFaceBrow);

    public static final native long new_UIFaceChin__SWIG_0();

    public static final native long new_UIFaceEar__SWIG_0();

    public static final native long new_UIFaceEye__SWIG_0();

    public static final native long new_UIFaceForehead__SWIG_0();

    public static final native long new_UIFaceMetadataVector__SWIG_0();

    public static final native long new_UIFaceMetadata__SWIG_0();

    public static final native long new_UIFaceMetadata__SWIG_1(long j11, UIFaceMetadata uIFaceMetadata);

    public static final native long new_UIFaceModelCacheVector__SWIG_0();

    public static final native long new_UIFaceModifiedROI__SWIG_0();

    public static final native long new_UIFaceMouth__SWIG_0();

    public static final native long new_UIFaceNose__SWIG_0();

    public static final native long new_UIFacePoint__SWIG_0();

    public static final native long new_UIFacePoint__SWIG_1(long j11, UIFacePoint uIFacePoint);

    public static final native long new_UIFaceRectVector__SWIG_0();

    public static final native long new_UIFaceRect__SWIG_0();

    public static final native long new_UIFaceRect__SWIG_1(long j11, UIFaceRect uIFaceRect);

    public static final native long new_UIFaceShape__SWIG_0();

    public static final native long new_UIFaceTattooColorVector__SWIG_0();

    public static final native long new_UIFaceTattooColor__SWIG_0();

    public static final native long new_UIIntPointVector__SWIG_0();

    public static final native long new_UIIntPoint__SWIG_0();

    public static final native long new_UIIntVector__SWIG_0();

    public static final native long new_UIIrisRadius__SWIG_0();

    public static final native long new_UIIrisRadius__SWIG_1(long j11, UIIrisRadius uIIrisRadius);

    public static final native long new_UILookParameters__SWIG_0();

    public static final native long new_UIMakeupLiveFaceAlignData__SWIG_0();

    public static final native long new_UIMakeupLiveFaceAlignData__SWIG_1(long j11, UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData);

    public static final native long new_UIModelBrowEngineRect__SWIG_0();

    public static final native long new_UIModelEyeRect__SWIG_0();

    public static final native long new_UIPose__SWIG_0();

    public static final native long new_UIPose__SWIG_1(long j11, UIPose uIPose);

    public static final native long new_UIRecommendFaceContour__SWIG_0();

    public static final native long new_UIShimmer__SWIG_0(int i11, int i12, int i13, int i14);

    public static final native long new_UITransform__SWIG_0();

    public static final native long new_UITransform__SWIG_1(long j11, UITransform uITransform);

    public static final native long new_UIVenusPipelineSettings__SWIG_0();

    public static final native long new_UIVenusPipelineSettings__SWIG_1(long j11, UIVenusPipelineSettings uIVenusPipelineSettings);

    public static final native long new_UIWarpParameter__SWIG_1(long j11, UIFacePoint uIFacePoint, long j12, UIFacePoint uIFacePoint2, int i11);

    public static final native long new_UIWarpedWigImageInfo__SWIG_0();

    public static final native long new_UIWigColor__SWIG_0();

    public static final native long new_UIWigLuminance__SWIG_0();

    public static final native long new_UIWigLuminance__SWIG_1(long j11, UIWigLuminance uIWigLuminance);

    public static final native long new_UIWigModelAnchor__SWIG_0();

    public static final native long new_VN_BadLightingReport__SWIG_0();

    public static final native long new_VenusPhotoFaceAlignData();
}
