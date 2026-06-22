package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class CUIVenusLive {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32858a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32859b;

    public CUIVenusLive(long j11, boolean z11) {
        this.f32859b = z11;
        this.f32858a = j11;
    }

    public static VN_EyebrowMode p0(UIEyebrowMode uIEyebrowMode) {
        return VN_EyebrowMode.c(UIVenusJNI.CUIVenusLive_convertEyebrowModeToEngine(uIEyebrowMode.f()));
    }

    public static int u() {
        return UIVenusJNI.CUIVenusLive_GetMaxDetectedFaceCount();
    }

    public boolean A(Object obj) {
        return UIVenusJNI.CUIVenusLive_GetSkinCareData(this.f32858a, this, obj);
    }

    public boolean B(int i11, Object obj, Object obj2) {
        return UIVenusJNI.CUIVenusLive_GetSmoothedInfo(this.f32858a, this, i11, obj, obj2);
    }

    public boolean C(int i11, int i12) {
        return UIVenusJNI.CUIVenusLive_InitFaceDistortionModelCommonInfo(this.f32858a, this, i11, i12);
    }

    public boolean D(int i11, int i12) {
        return UIVenusJNI.CUIVenusLive_InitialEyeContactModelCommonInfo(this.f32858a, this, i11, i12);
    }

    public void E(Object[] objArr, int i11, int i12) {
        UIVenusJNI.CUIVenusLive_InitialEyeModelCommonInfo(this.f32858a, this, objArr, i11, i12);
    }

    public boolean F(Object obj, Object obj2, Object obj3, Object obj4) {
        return UIVenusJNI.CUIVenusLive_InitializeFaceContour(this.f32858a, this, obj, obj2, obj3, obj4);
    }

    public boolean G() {
        return UIVenusJNI.CUIVenusLive_IsHairDetected(this.f32858a, this);
    }

    public boolean H() {
        return UIVenusJNI.CUIVenusLive_IsKissDetected(this.f32858a, this);
    }

    public boolean I() {
        return UIVenusJNI.CUIVenusLive_IsModelLoaded(this.f32858a, this);
    }

    public boolean J(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, boolean z11, boolean z12) {
        return UIVenusJNI.CUIVenusLive_LoadEarringModel(this.f32858a, this, str, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, z11, z12);
    }

    public boolean K(String str, Object obj) {
        return UIVenusJNI.CUIVenusLive_LoadObject3DHDR(this.f32858a, this, str, obj);
    }

    public boolean L(String str, String str2, Object obj, boolean z11, boolean z12, Object obj2) {
        return UIVenusJNI.CUIVenusLive_LoadObject3DModel(this.f32858a, this, str, str2, obj, z11, z12, obj2);
    }

    public boolean M(int[] iArr, byte[] bArr, Object[] objArr, int[] iArr2, int i11, int i12, int i13, int i14, int i15, int i16) {
        return UIVenusJNI.CUIVenusLive_PreprocessEyeContactModel(this.f32858a, this, iArr, bArr, objArr, iArr2, i11, i12, i13, i14, i15, i16);
    }

    public boolean N(byte[] bArr, Object[] objArr, int i11, int i12, int i13, int i14) {
        return UIVenusJNI.CUIVenusLive_PreprocessEyelashModel(this.f32858a, this, bArr, objArr, i11, i12, i13, i14);
    }

    public boolean O(int[] iArr, Object[] objArr, int[] iArr2, int[] iArr3, int i11, int i12, int i13) {
        return UIVenusJNI.CUIVenusLive_PreprocessEyelinerModel(this.f32858a, this, iArr, objArr, iArr2, iArr3, i11, i12, i13);
    }

    public boolean P(int[] iArr, Object[] objArr, int[] iArr2, int[] iArr3, int i11, int i12, int i13, int i14, int i15, int[] iArr4, int i16, byte[] bArr, byte[] bArr2) {
        return UIVenusJNI.CUIVenusLive_PreprocessEyeshadowModel(this.f32858a, this, iArr, objArr, iArr2, iArr3, i11, i12, i13, i14, i15, iArr4, i16, bArr, bArr2);
    }

    public boolean Q() {
        return UIVenusJNI.CUIVenusLive_ReleaseInternalModel(this.f32858a, this);
    }

    public boolean R() {
        return UIVenusJNI.CUIVenusLive_ReleaseSkinAnalysisBuffer(this.f32858a, this);
    }

    public boolean S(int i11) {
        return UIVenusJNI.CUIVenusLive_ResetApngDecoder(this.f32858a, this, i11);
    }

    public boolean T(Object obj, int i11, int i12, Object[] objArr, boolean z11, int i13, int i14, UIShimmer uIShimmer) {
        return UIVenusJNI.CUIVenusLive_SetClassicLipstick(this.f32858a, this, obj, i11, i12, objArr, z11, i13, i14, UIShimmer.b(uIShimmer), uIShimmer);
    }

    public boolean U(boolean z11) {
        return UIVenusJNI.CUIVenusLive_SetEnableEyebrowGoldenRatio(this.f32858a, this, z11);
    }

    public boolean V(VN_Event_Trigger vN_Event_Trigger, VN_Event_React vN_Event_React) {
        return UIVenusJNI.CUIVenusLive_SetEventInfo(this.f32858a, this, vN_Event_Trigger.f(), vN_Event_React.f());
    }

    public boolean W(int i11, int i12) {
        return UIVenusJNI.CUIVenusLive_SetEyeContactSize(this.f32858a, this, i11, i12);
    }

    public boolean X(boolean z11) {
        return UIVenusJNI.CUIVenusLive_SetEyebrowMatchOriginalThickness(this.f32858a, this, z11);
    }

    public boolean Y(Object obj, byte[] bArr, int i11) {
        return UIVenusJNI.CUIVenusLive_SetFaceDistortionModel(this.f32858a, this, obj, bArr, i11);
    }

    public boolean Z(int i11) {
        return UIVenusJNI.CUIVenusLive_SetFaceSizeLowerBound(this.f32858a, this, i11);
    }

    public boolean a0(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int i11, int i12, float f11, float f12, float f13, float f14, int i13) {
        return UIVenusJNI.CUIVenusLive_SetHairDyeParameter(this.f32858a, this, iArr, iArr2, iArr3, iArr4, iArr5, i11, i12, f11, f12, f13, f14, i13);
    }

    public boolean b(Object obj, Object obj2, boolean z11, int i11, int i12, int i13, int[] iArr, Object obj3, boolean z12, Object obj4, Object obj5, float f11, float f12, boolean z13, Object obj6, Object obj7, boolean z14) {
        return UIVenusJNI.CUIVenusLive_AnalyzeLiveImage(this.f32858a, this, obj, obj2, z11, i11, i12, i13, iArr, obj3, z12, obj4, obj5, f11, f12, z13, obj6, obj7, z14);
    }

    public int b0(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z11) {
        return UIVenusJNI.CUIVenusLive_SetInternalModelPaths(this.f32858a, this, str, str2, str3, str4, str5, str6, str7, z11);
    }

    public boolean c(int i11, String str, boolean z11, Object obj, Object obj2) {
        return UIVenusJNI.CUIVenusLive_AsyncDecodeApng(this.f32858a, this, i11, str, z11, obj, obj2);
    }

    public boolean c0(boolean z11, int i11, VN_EyebrowMode vN_EyebrowMode, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, boolean z12, boolean z13, boolean[] zArr, int[] iArr, boolean[] zArr2, Object[] objArr, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z21, boolean z22, boolean z23, boolean z24, boolean z25, boolean z26, boolean z27, boolean z28, Object[] objArr2, Object[] objArr3, int[] iArr2, int[] iArr3, int[] iArr4, float[] fArr, Object[] objArr4, Object[] objArr5, Object[] objArr6, float[] fArr2, float[] fArr3, Object[] objArr7, int i21) {
        return UIVenusJNI.CUIVenusLive_SetMakeupParameters(this.f32858a, this, z11, i11, vN_EyebrowMode.f(), i12, i13, i14, i15, i16, i17, i18, i19, z12, z13, zArr, iArr, zArr2, objArr, z14, z15, z16, z17, z18, z19, z21, z22, z23, z24, z25, z26, z27, z28, objArr2, objArr3, iArr2, iArr3, iArr4, fArr, objArr4, objArr5, objArr6, fArr2, fArr3, objArr7, i21);
    }

    public boolean d(UIFaceRect uIFaceRect) {
        return UIVenusJNI.CUIVenusLive_DetectOpenMouth(this.f32858a, this, UIFaceRect.c(uIFaceRect), uIFaceRect);
    }

    public boolean d0(int i11) {
        return UIVenusJNI.CUIVenusLive_SetMaxDetectedFaceNumber(this.f32858a, this, i11);
    }

    public int e(UIFaceRect uIFaceRect, UIWigLuminance uIWigLuminance) {
        return UIVenusJNI.CUIVenusLive_GetAutoWigLuminanceParameter(this.f32858a, this, UIFaceRect.c(uIFaceRect), uIFaceRect, UIWigLuminance.b(uIWigLuminance), uIWigLuminance);
    }

    public boolean e0(ShadeFinderMode shadeFinderMode) {
        return UIVenusJNI.CUIVenusLive_SetShadeFinderMode(this.f32858a, this, shadeFinderMode.c());
    }

    public boolean f(Object obj) {
        return UIVenusJNI.CUIVenusLive_GetBackgroundMetadata(this.f32858a, this, obj);
    }

    public boolean f0(UIColorVector uIColorVector) {
        return UIVenusJNI.CUIVenusLive_SetSkinCareFeatureColor(this.f32858a, this, UIColorVector.c(uIColorVector), uIColorVector);
    }

    public void finalize() {
        q0();
    }

    public boolean g(UIFaceModelCacheVector uIFaceModelCacheVector) {
        return UIVenusJNI.CUIVenusLive_GetBadLightingModelVersion(this.f32858a, this, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector);
    }

    public boolean g0(boolean z11, boolean z12, boolean z13, boolean z14) {
        return UIVenusJNI.CUIVenusLive_SetSkinCareParametersLive(this.f32858a, this, z11, z12, z13, z14);
    }

    public boolean h(VN_BadLightingReport vN_BadLightingReport) {
        return UIVenusJNI.CUIVenusLive_GetBadLightingReport(this.f32858a, this, VN_BadLightingReport.b(vN_BadLightingReport), vN_BadLightingReport);
    }

    public void h0(boolean z11, float f11) {
        UIVenusJNI.CUIVenusLive_SetSkinSmoothFilterStatus(this.f32858a, this, z11, f11);
    }

    public boolean i(int i11, int i12, Object obj, Object obj2, Object obj3) {
        return UIVenusJNI.CUIVenusLive_GetEyeContactModelParameters(this.f32858a, this, i11, i12, obj, obj2, obj3);
    }

    public boolean i0(Object[] objArr, Object[] objArr2, Object[] objArr3, Object[] objArr4, Object[] objArr5, Object[] objArr6, Object[] objArr7, Object[] objArr8, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6, int[] iArr7, int i11) {
        return UIVenusJNI.CUIVenusLive_SetStickerInfo(this.f32858a, this, objArr, objArr2, objArr3, objArr4, objArr5, objArr6, objArr7, objArr8, iArr, iArr2, iArr3, iArr4, iArr5, iArr6, iArr7, i11);
    }

    public int j(UIFaceRect uIFaceRect, UIIntVector uIIntVector) {
        return UIVenusJNI.CUIVenusLive_GetEyebrowOriginalColor(this.f32858a, this, UIFaceRect.c(uIFaceRect), uIFaceRect, UIIntVector.d(uIIntVector), uIIntVector);
    }

    public boolean j0(VN_TrackingMode vN_TrackingMode) {
        return UIVenusJNI.CUIVenusLive_SetTrackingMode(this.f32858a, this, vN_TrackingMode.c());
    }

    public boolean k(Object obj, Object obj2) {
        return UIVenusJNI.CUIVenusLive_GetFaceAlignmentData(this.f32858a, this, obj, obj2);
    }

    public boolean k0(int i11) {
        return UIVenusJNI.CUIVenusLive_StopDecodeApng(this.f32858a, this, i11);
    }

    public boolean l(Object obj) {
        return UIVenusJNI.CUIVenusLive_GetFaceDistortionIntermediateSize(this.f32858a, this, obj);
    }

    public void l0(byte[] bArr, int i11, int i12, int i13, boolean z11, boolean z12) {
        UIVenusJNI.CUIVenusLive_TrackYUV420Biplanar(this.f32858a, this, bArr, i11, i12, i13, z11, z12);
    }

    public boolean m(Object obj, int i11) {
        return UIVenusJNI.CUIVenusLive_GetFaceInfos(this.f32858a, this, obj, i11);
    }

    public boolean m0(Object obj, Object obj2) {
        return UIVenusJNI.CUIVenusLive_Update3DEyeBrowTexture(this.f32858a, this, obj, obj2);
    }

    public boolean n(Object[] objArr) {
        return UIVenusJNI.CUIVenusLive_GetFaceRectangle(this.f32858a, this, objArr);
    }

    public boolean n0(int i11, int i12, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return UIVenusJNI.CUIVenusLive_UpdateBlush3DTexture(this.f32858a, this, i11, i12, bArr, bArr2, bArr3);
    }

    public float o() {
        return UIVenusJNI.CUIVenusLive_GetHairDyeOmbreMappingRange(this.f32858a, this);
    }

    public boolean o0(Object[] objArr, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return UIVenusJNI.CUIVenusLive_UpdateFaceArtAndTattooTexture(this.f32858a, this, objArr, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public float p() {
        return UIVenusJNI.CUIVenusLive_GetHairDyeOmbreY(this.f32858a, this);
    }

    public int q(UIFaceModelCacheVector uIFaceModelCacheVector) {
        return UIVenusJNI.CUIVenusLive_GetInternalModelVersion(this.f32858a, this, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector);
    }

    public synchronized void q0() {
        try {
            long j11 = this.f32858a;
            if (j11 != 0) {
                if (this.f32859b) {
                    this.f32859b = false;
                    UIVenusJNI.delete_CUIVenusLive(j11);
                }
                this.f32858a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public boolean r(Object obj, Object obj2, Object obj3) {
        return UIVenusJNI.CUIVenusLive_GetIrisRadius(this.f32858a, this, obj, obj2, obj3);
    }

    public int s(UILookParameters uILookParameters, int i11, UILookParameters uILookParameters2) {
        return UIVenusJNI.CUIVenusLive_GetLiveLookParameters(this.f32858a, this, UILookParameters.e(uILookParameters), uILookParameters, i11, UILookParameters.e(uILookParameters2), uILookParameters2);
    }

    public boolean t(Object[] objArr, Object[] objArr2, Object[] objArr3, Object[] objArr4, Object[] objArr5, Object[] objArr6, Object[] objArr7, Object[] objArr8, Object[] objArr9, Object[] objArr10, Object[] objArr11, Object[] objArr12, Object[] objArr13, Object[] objArr14, Object[] objArr15, Object[] objArr16, Object[] objArr17, Object[] objArr18, Object[] objArr19, Object[] objArr20, Object[] objArr21, Object[] objArr22, Object[] objArr23, Object[] objArr24, Object[] objArr25, Object[] objArr26, boolean[] zArr) {
        return UIVenusJNI.CUIVenusLive_GetMakeupMetadata(this.f32858a, this, objArr, objArr2, objArr3, objArr4, objArr5, objArr6, objArr7, objArr8, objArr9, objArr10, objArr11, objArr12, objArr13, objArr14, objArr15, objArr16, objArr17, objArr18, objArr19, objArr20, objArr21, objArr22, objArr23, objArr24, objArr25, objArr26, zArr);
    }

    public boolean v(int i11, Object obj, int i12, int i13, Object obj2, Object obj3, Object obj4) {
        return UIVenusJNI.CUIVenusLive_GetNextApngImage(this.f32858a, this, i11, obj, i12, i13, obj2, obj3, obj4);
    }

    public boolean w(Object obj) {
        return UIVenusJNI.CUIVenusLive_GetShadeFinderData(this.f32858a, this, obj);
    }

    public boolean x(int[] iArr, int i11, Object obj) {
        return UIVenusJNI.CUIVenusLive_GetShadeFinderNeighborShade(this.f32858a, this, iArr, i11, obj);
    }

    public boolean y(int i11, int[] iArr, Object[] objArr) {
        return UIVenusJNI.CUIVenusLive_GetShadeFinderShadeMatching(this.f32858a, this, i11, iArr, objArr);
    }

    public boolean z(Object obj) {
        return UIVenusJNI.CUIVenusLive_GetSkinCareCheckResult(this.f32858a, this, obj);
    }

    public CUIVenusLive(CUIVenusPhoto cUIVenusPhoto) {
        this(UIVenusJNI.new_CUIVenusLive(CUIVenusPhoto.n0(cUIVenusPhoto), cUIVenusPhoto), true);
    }
}
