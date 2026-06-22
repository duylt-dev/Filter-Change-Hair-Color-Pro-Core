package com.cyberlink.youcammakeup.jniproxy;

import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class CUIVenusPhoto {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32860a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32861b;

    public CUIVenusPhoto(long j11, boolean z11) {
        this.f32861b = z11;
        this.f32860a = j11;
    }

    public static long n0(CUIVenusPhoto cUIVenusPhoto) {
        if (cUIVenusPhoto == null) {
            return 0L;
        }
        return cUIVenusPhoto.f32860a;
    }

    public int A(int i11, UIFaceMetadataVector uIFaceMetadataVector) {
        return UIVenusJNI.CUIVenusPhoto_GetFaceMetadata(this.f32860a, this, i11, UIFaceMetadataVector.d(uIFaceMetadataVector), uIFaceMetadataVector);
    }

    public boolean B(int i11, UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData, UIFacePoint uIFacePoint) {
        return UIVenusJNI.CUIVenusPhoto_GetFacePointFromFaceAlignData(this.f32860a, this, i11, UIMakeupLiveFaceAlignData.b(uIMakeupLiveFaceAlignData), uIMakeupLiveFaceAlignData, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public boolean C(UIFaceRect uIFaceRect, UIPose uIPose) {
        return UIVenusJNI.CUIVenusPhoto_GetFacePose(this.f32860a, this, UIFaceRect.c(uIFaceRect), uIFaceRect, UIPose.b(uIPose), uIPose);
    }

    public boolean D(CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_GetHairDyeMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public int E(CImageBuffer cImageBuffer, UIFacePoint uIFacePoint, UIFacePoint uIFacePoint2, UIFaceRect uIFaceRect, UIFaceAlignmentData uIFaceAlignmentData, UITransform uITransform) {
        return UIVenusJNI.CUIVenusPhoto_GetHairbandNaturalLookingModelAndTranslation(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIFacePoint.b(uIFacePoint), uIFacePoint, UIFacePoint.b(uIFacePoint2), uIFacePoint2, UIFaceRect.c(uIFaceRect), uIFaceRect, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData, UITransform.c(uITransform), uITransform);
    }

    public int F(CImageBuffer cImageBuffer, UIFacePoint uIFacePoint, UIFacePoint uIFacePoint2, UIFaceRect uIFaceRect, UIFaceAlignmentData uIFaceAlignmentData, UITransform uITransform) {
        return UIVenusJNI.CUIVenusPhoto_GetHatNaturalLookingModelAndTranslation(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIFacePoint.b(uIFacePoint), uIFacePoint, UIFacePoint.b(uIFacePoint2), uIFacePoint2, UIFaceRect.c(uIFaceRect), uIFaceRect, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData, UITransform.c(uITransform), uITransform);
    }

    public float G() {
        return UIVenusJNI.CUIVenusPhoto_GetInferenceFPS(this.f32860a, this);
    }

    public int H(UIFaceModelCacheVector uIFaceModelCacheVector) {
        return UIVenusJNI.CUIVenusPhoto_GetInternalModelVersion(this.f32860a, this, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector);
    }

    public int I(UIFaceRect uIFaceRect, UIIrisRadius uIIrisRadius, UIIrisRadius uIIrisRadius2) {
        return UIVenusJNI.CUIVenusPhoto_GetIrisRadius(this.f32860a, this, UIFaceRect.c(uIFaceRect), uIFaceRect, UIIrisRadius.b(uIIrisRadius), uIIrisRadius, UIIrisRadius.b(uIIrisRadius2), uIIrisRadius2);
    }

    public boolean J(UIFaceRect uIFaceRect) {
        return UIVenusJNI.CUIVenusPhoto_GetIsMaskDetected(this.f32860a, this, UIFaceRect.c(uIFaceRect), uIFaceRect);
    }

    public int K(UILookParameters uILookParameters, int i11, UILookParameters uILookParameters2) {
        return UIVenusJNI.CUIVenusPhoto_GetLookParameters(this.f32860a, this, UILookParameters.e(uILookParameters), uILookParameters, i11, UILookParameters.e(uILookParameters2), uILookParameters2);
    }

    public boolean L(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2, UIVenusPipelineSettings uIVenusPipelineSettings, UIFaceMetadataVector uIFaceMetadataVector) {
        return UIVenusJNI.CUIVenusPhoto_GetMakeupImage(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, CImageBuffer.D(cImageBuffer2), cImageBuffer2, UIVenusPipelineSettings.M1(uIVenusPipelineSettings), uIVenusPipelineSettings, UIFaceMetadataVector.d(uIFaceMetadataVector), uIFaceMetadataVector);
    }

    public int M(CImageBuffer cImageBuffer, UIFacePoint uIFacePoint, UIFacePoint uIFacePoint2, UIFaceRect uIFaceRect, UIFaceAlignmentData uIFaceAlignmentData, UITransform uITransform, UIFacePoint uIFacePoint3, UIFacePoint uIFacePoint4, float f11) {
        return UIVenusJNI.CUIVenusPhoto_GetNecklaceNaturalLookingModelAndTranslation(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIFacePoint.b(uIFacePoint), uIFacePoint, UIFacePoint.b(uIFacePoint2), uIFacePoint2, UIFaceRect.c(uIFaceRect), uIFaceRect, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData, UITransform.c(uITransform), uITransform, UIFacePoint.b(uIFacePoint3), uIFacePoint3, UIFacePoint.b(uIFacePoint4), uIFacePoint4, f11);
    }

    public boolean N(CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_GetPoreDiffMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public boolean O(int i11, int i12, UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData, UIFaceRect uIFaceRect) {
        return UIVenusJNI.CUIVenusPhoto_GetPreciseFaceRect(this.f32860a, this, i11, i12, UIMakeupLiveFaceAlignData.b(uIMakeupLiveFaceAlignData), uIMakeupLiveFaceAlignData, UIFaceRect.c(uIFaceRect), uIFaceRect);
    }

    public int P(String str, UIIntPoint uIIntPoint, int i11, UIColorVector uIColorVector, UIRecommendFaceContour uIRecommendFaceContour) {
        return UIVenusJNI.CUIVenusPhoto_GetRecommendFaceContour(this.f32860a, this, str, UIIntPoint.b(uIIntPoint), uIIntPoint, i11, UIColorVector.c(uIColorVector), uIColorVector, UIRecommendFaceContour.b(uIRecommendFaceContour), uIRecommendFaceContour);
    }

    public int Q(int i11, UIColorVector uIColorVector, UIIntVector uIIntVector, UIIntVector uIIntVector2) {
        return UIVenusJNI.CUIVenusPhoto_GetRecommendFaceContourPalette(this.f32860a, this, i11, UIColorVector.c(uIColorVector), uIColorVector, UIIntVector.d(uIIntVector), uIIntVector, UIIntVector.d(uIIntVector2), uIIntVector2);
    }

    public int R(UIColorVector uIColorVector) {
        return UIVenusJNI.CUIVenusPhoto_GetRecommendFoundation(this.f32860a, this, UIColorVector.c(uIColorVector), uIColorVector);
    }

    public boolean S(CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_GetRednessDiffMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public boolean T(CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_GetUnevennessDiffMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public int U(UIWarpedWigImageInfo uIWarpedWigImageInfo, CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_GetWarpedWigModel(this.f32860a, this, UIWarpedWigImageInfo.b(uIWarpedWigImageInfo), uIWarpedWigImageInfo, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public boolean V(CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_GetWrinkleDiffMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public boolean W() {
        return UIVenusJNI.CUIVenusPhoto_IsLocalMoveWigDone(this.f32860a, this);
    }

    public boolean X() {
        return UIVenusJNI.CUIVenusPhoto_IsModelLoaded(this.f32860a, this);
    }

    public boolean Y(String str) {
        return UIVenusJNI.CUIVenusPhoto_LoadWigOffsetData(this.f32860a, this, str);
    }

    public int Z(CImageBuffer cImageBuffer, UIFacePoint uIFacePoint, UIFacePoint uIFacePoint2, UIFacePoint uIFacePoint3, UIFaceAlignmentData uIFaceAlignmentData) {
        return UIVenusJNI.CUIVenusPhoto_ManualGetFaceAlignmentData(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIFacePoint.b(uIFacePoint), uIFacePoint, UIFacePoint.b(uIFacePoint2), uIFacePoint2, UIFacePoint.b(uIFacePoint3), uIFacePoint3, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData);
    }

    public int a(CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_AnalyzeImage(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public boolean a0(CImageBuffer cImageBuffer, UIVenusPipelineSettings uIVenusPipelineSettings, UIFaceBrow uIFaceBrow, UIFaceBrow uIFaceBrow2) {
        return UIVenusJNI.CUIVenusPhoto_ProjectAlignmentDataBackToSourceImage(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIVenusPipelineSettings.M1(uIVenusPipelineSettings), uIVenusPipelineSettings, UIFaceBrow.c(uIFaceBrow), uIFaceBrow, UIFaceBrow.c(uIFaceBrow2), uIFaceBrow2);
    }

    public boolean b(CImageBuffer cImageBuffer, UIFaceRect uIFaceRect, UIFaceAlignmentData uIFaceAlignmentData, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, UIEyebrow3dEditPoints uIEyebrow3dEditPoints2, float f11, float f12) {
        return UIVenusJNI.CUIVenusPhoto_AnalyzeSampleImage(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIFaceRect.c(uIFaceRect), uIFaceRect, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData, UIEyebrow3dEditPoints.d(uIEyebrow3dEditPoints), uIEyebrow3dEditPoints, UIEyebrow3dEditPoints.d(uIEyebrow3dEditPoints2), uIEyebrow3dEditPoints2, f11, f12);
    }

    public int b0() {
        return UIVenusJNI.CUIVenusPhoto_QueryGetMakeupImageProgress(this.f32860a, this);
    }

    public int c(UIWigColor uIWigColor, float f11) {
        return UIVenusJNI.CUIVenusPhoto_ChangeWigColor(this.f32860a, this, UIWigColor.b(uIWigColor), uIWigColor, f11);
    }

    public boolean c0() {
        return UIVenusJNI.CUIVenusPhoto_ReleaseMakeupBuffer(this.f32860a, this);
    }

    public int d(UIFacePoint uIFacePoint) {
        return UIVenusJNI.CUIVenusPhoto_ContinueWarpWigInLocal(this.f32860a, this, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void d0() {
        UIVenusJNI.CUIVenusPhoto_ReleaseWigOffsetData(this.f32860a, this);
    }

    public boolean e(CImageBuffer cImageBuffer, UIFaceRect uIFaceRect, boolean z11) {
        return UIVenusJNI.CUIVenusPhoto_DeepDetectHairDyeMask__SWIG_1(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIFaceRect.c(uIFaceRect), uIFaceRect, z11);
    }

    public boolean e0() {
        return UIVenusJNI.CUIVenusPhoto_ResetGetMakeupImageProgress(this.f32860a, this);
    }

    public boolean f(CImageBuffer cImageBuffer, UIFaceRectVector uIFaceRectVector, int i11, boolean z11) {
        return UIVenusJNI.CUIVenusPhoto_DeepDetectHairDyeMask__SWIG_0(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIFaceRectVector.d(uIFaceRectVector), uIFaceRectVector, i11, z11);
    }

    public void f0() {
        UIVenusJNI.CUIVenusPhoto_ResetWarpWig(this.f32860a, this);
    }

    public void finalize() {
        m0();
    }

    public boolean g(UIFaceRect uIFaceRect) {
        return UIVenusJNI.CUIVenusPhoto_DeleteFaceInfo(this.f32860a, this, UIFaceRect.c(uIFaceRect), uIFaceRect);
    }

    public boolean g0(String str, String str2, String str3, String str4, String str5, String str6) {
        return UIVenusJNI.CUIVenusPhoto_SetEmulationModelPaths(this.f32860a, this, str, str2, str3, str4, str5, str6);
    }

    public boolean h(UIFaceRect uIFaceRect) {
        return UIVenusJNI.CUIVenusPhoto_DetectOpenMouth(this.f32860a, this, UIFaceRect.c(uIFaceRect), uIFaceRect);
    }

    public int h0(String str, String str2, String str3) {
        return UIVenusJNI.CUIVenusPhoto_SetFaceLiftInternalModelPaths(this.f32860a, this, str, str2, str3);
    }

    public boolean i(CImageBuffer cImageBuffer, int i11) {
        return UIVenusJNI.CUIVenusPhoto_DilateMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, i11);
    }

    public boolean i0(CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_SetHairDyeMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public boolean j(String str) {
        return UIVenusJNI.CUIVenusPhoto_DumpWigOffsetData(this.f32860a, this, str);
    }

    public int j0(String str, String str2, String str3, String str4, String str5, String str6, boolean z11) {
        return UIVenusJNI.CUIVenusPhoto_SetInternalModelPaths(this.f32860a, this, str, str2, str3, str4, str5, str6, z11);
    }

    public int k(UIWarpedWigImageInfo uIWarpedWigImageInfo, CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_EndWarpWigInLocal(this.f32860a, this, UIWarpedWigImageInfo.b(uIWarpedWigImageInfo), uIWarpedWigImageInfo, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public int k0(UITransform uITransform, UIFacePoint uIFacePoint) {
        return UIVenusJNI.CUIVenusPhoto_StartWarpWigInLocal(this.f32860a, this, UITransform.c(uITransform), uITransform, UIFacePoint.b(uIFacePoint), uIFacePoint);
    }

    public void l(boolean z11) {
        UIVenusJNI.CUIVenusPhoto_FlipWig(this.f32860a, this, z11);
    }

    public int l0(CImageBuffer cImageBuffer, ByteBuffer byteBuffer, boolean z11, int i11) {
        return UIVenusJNI.CUIVenusPhoto_UpdateHairDyeMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, byteBuffer, z11, i11);
    }

    public int m(CImageBuffer cImageBuffer, UIColor uIColor, UIHairDyeMode uIHairDyeMode) {
        return UIVenusJNI.CUIVenusPhoto_GenerateHairDyeThumbnail(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIColor.b(uIColor), uIColor, uIHairDyeMode.c());
    }

    public synchronized void m0() {
        try {
            long j11 = this.f32860a;
            if (j11 != 0) {
                if (this.f32861b) {
                    this.f32861b = false;
                    UIVenusJNI.delete_CUIVenusPhoto(j11);
                }
                this.f32860a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public int n(UIFaceRect uIFaceRect, UIWigLuminance uIWigLuminance) {
        return UIVenusJNI.CUIVenusPhoto_GetAutoWigLuminanceParameter(this.f32860a, this, UIFaceRect.c(uIFaceRect), uIFaceRect, UIWigLuminance.b(uIWigLuminance), uIWigLuminance);
    }

    public boolean o(CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_GetConcealerDiffMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public boolean p(CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_GetDarkCircleDiffMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public int q(CImageBuffer cImageBuffer, UIFacePoint uIFacePoint, UIFacePoint uIFacePoint2, UIFacePoint uIFacePoint3, UIFacePoint uIFacePoint4, UIFaceRect uIFaceRect, UIFaceAlignmentData uIFaceAlignmentData, UITransform uITransform, UITransform uITransform2) {
        return UIVenusJNI.CUIVenusPhoto_GetEarringNaturalLookingModelAndTranslation(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIFacePoint.b(uIFacePoint), uIFacePoint, UIFacePoint.b(uIFacePoint2), uIFacePoint2, UIFacePoint.b(uIFacePoint3), uIFacePoint3, UIFacePoint.b(uIFacePoint4), uIFacePoint4, UIFaceRect.c(uIFaceRect), uIFaceRect, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData, UITransform.c(uITransform), uITransform, UITransform.c(uITransform2), uITransform2);
    }

    public boolean r(UIFaceModelCacheVector uIFaceModelCacheVector) {
        return UIVenusJNI.CUIVenusPhoto_GetEmulationModelVersion(this.f32860a, this, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector);
    }

    public boolean s(CImageBuffer cImageBuffer) {
        return UIVenusJNI.CUIVenusPhoto_GetEyeBagDiffMask(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer);
    }

    public boolean t(UIEyebrow3dEditPoints uIEyebrow3dEditPoints, UIEyebrow3dEditPoints uIEyebrow3dEditPoints2) {
        return UIVenusJNI.CUIVenusPhoto_GetEyebrow3DEditPoints(this.f32860a, this, UIEyebrow3dEditPoints.d(uIEyebrow3dEditPoints), uIEyebrow3dEditPoints, UIEyebrow3dEditPoints.d(uIEyebrow3dEditPoints2), uIEyebrow3dEditPoints2);
    }

    public int u(UIFaceRect uIFaceRect, UIIntVector uIIntVector) {
        return UIVenusJNI.CUIVenusPhoto_GetEyebrowOriginalColor(this.f32860a, this, UIFaceRect.c(uIFaceRect), uIFaceRect, UIIntVector.d(uIIntVector), uIIntVector);
    }

    public int v(CImageBuffer cImageBuffer, UIFacePoint uIFacePoint, UIFacePoint uIFacePoint2, int i11, UIFaceRect uIFaceRect, UIFaceAlignmentData uIFaceAlignmentData, UITransform uITransform) {
        return UIVenusJNI.CUIVenusPhoto_GetEyewearNaturalLookingModelAndTranslation(this.f32860a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIFacePoint.b(uIFacePoint), uIFacePoint, UIFacePoint.b(uIFacePoint2), uIFacePoint2, i11, UIFaceRect.c(uIFaceRect), uIFaceRect, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData, UITransform.c(uITransform), uITransform);
    }

    public int w(UIFaceRect uIFaceRect, UIFaceAlignmentData uIFaceAlignmentData) {
        return UIVenusJNI.CUIVenusPhoto_GetFaceAlignmentData(this.f32860a, this, UIFaceRect.c(uIFaceRect), uIFaceRect, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData);
    }

    public boolean x(UIFaceRect uIFaceRect, UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData) {
        return UIVenusJNI.CUIVenusPhoto_GetFaceAlignmentDataForSticker(this.f32860a, this, UIFaceRect.c(uIFaceRect), uIFaceRect, UIMakeupLiveFaceAlignData.b(uIMakeupLiveFaceAlignData), uIMakeupLiveFaceAlignData);
    }

    public int y(int i11, UIFaceRectVector uIFaceRectVector) {
        return UIVenusJNI.CUIVenusPhoto_GetFaceInfos(this.f32860a, this, i11, UIFaceRectVector.d(uIFaceRectVector), uIFaceRectVector);
    }

    public int z(UIFaceModelCacheVector uIFaceModelCacheVector) {
        return UIVenusJNI.CUIVenusPhoto_GetFaceLiftInternalModelVersion(this.f32860a, this, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector);
    }

    public CUIVenusPhoto() {
        this(UIVenusJNI.new_CUIVenusPhoto(), true);
    }
}
