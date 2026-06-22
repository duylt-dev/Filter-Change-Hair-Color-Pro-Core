package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIVenusPipelineSettings {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33042a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33043b;

    public UIVenusPipelineSettings(long j11, boolean z11) {
        this.f33043b = z11;
        this.f33042a = j11;
    }

    public static long M1(UIVenusPipelineSettings uIVenusPipelineSettings) {
        if (uIVenusPipelineSettings == null) {
            return 0L;
        }
        return uIVenusPipelineSettings.f33042a;
    }

    public void A(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyePosition(this.f33042a, this, i11);
    }

    public void A0(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_configInputEditPointValid(this.f33042a, this, z11);
    }

    public void A1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableEyelash(this.f33042a, this, z11);
    }

    public int A2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeUnderRightIntensity(this.f33042a, this);
    }

    public int A3() {
        return UIVenusJNI.UIVenusPipelineSettings_getWhitenTeethIntensity(this.f33042a, this);
    }

    public void B(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyePositionLeft(this.f33042a, this, i11);
    }

    public void B0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configJaw(this.f33042a, this, i11);
    }

    public void B1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableEyeliner(this.f33042a, this, z11);
    }

    public int B2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeWidthIntensity(this.f33042a, this);
    }

    public UIFaceModelCacheVector B3() {
        return new UIFaceModelCacheVector(UIVenusJNI.UIVenusPipelineSettings_getWigCache(this.f33042a, this), true);
    }

    public void C(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyePositionRight(this.f33042a, this, i11);
    }

    public void C0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configLipPeak(this.f33042a, this, i11);
    }

    public void C1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableEyeshadow(this.f33042a, this, z11);
    }

    public UIEyebrowMode C2() {
        return UIEyebrowMode.c(UIVenusJNI.UIVenusPipelineSettings_getEyebrowMode(this.f33042a, this));
    }

    public void C3(UIWigModelAnchor uIWigModelAnchor) {
        UIVenusJNI.UIVenusPipelineSettings_getWigModelAnchor(this.f33042a, this, UIWigModelAnchor.b(uIWigModelAnchor), uIWigModelAnchor);
    }

    public void D(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeSlant(this.f33042a, this, i11);
    }

    public void D0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configMouthHeight(this.f33042a, this, i11);
    }

    public void D1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableFaceArt(this.f33042a, this, z11);
    }

    public int D2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceLessenIntensity(this.f33042a, this);
    }

    public UIBoolVector D3() {
        return new UIBoolVector(UIVenusJNI.UIVenusPipelineSettings_hasFaceData(this.f33042a, this), true);
    }

    public void E(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeTail(this.f33042a, this, i11);
    }

    public void E0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configMouthHeightLower(this.f33042a, this, i11);
    }

    public void E1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableFaceArtLayer2(this.f33042a, this, z11);
    }

    public int E2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceLessenLeftIntensity(this.f33042a, this);
    }

    public boolean E3() {
        return UIVenusJNI.UIVenusPipelineSettings_isEyebrow3dMode(this.f33042a, this);
    }

    public void F(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeTailLeft(this.f33042a, this, i11);
    }

    public void F0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configMouthHeightUpper(this.f33042a, this, i11);
    }

    public void F1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableFaceContourPattern(this.f33042a, this, z11);
    }

    public int F2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceLessenRightIntensity(this.f33042a, this);
    }

    public boolean F3() {
        return UIVenusJNI.UIVenusPipelineSettings_isForceApplyWigPosition(this.f33042a, this);
    }

    public void G(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeTailRight(this.f33042a, this, i11);
    }

    public void G0(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_configMouthOpen(this.f33042a, this, z11);
    }

    public void G1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableFaceWidget(this.f33042a, this, z11);
    }

    public int G2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceMiddleIntensity(this.f33042a, this);
    }

    public boolean G3() {
        return UIVenusJNI.UIVenusPipelineSettings_isModelEyebrowRectAllZero(this.f33042a, this);
    }

    public void H(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeUnder(this.f33042a, this, i11);
    }

    public void H0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configMouthPosition(this.f33042a, this, i11);
    }

    public void H1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableFoundation(this.f33042a, this, z11);
    }

    public int H2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceReshapeIntensity(this.f33042a, this);
    }

    public VN_MakeupCacheMode H3() {
        return VN_MakeupCacheMode.c(UIVenusJNI.UIVenusPipelineSettings_queryCacheMode(this.f33042a, this));
    }

    public void I(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeUnderLeft(this.f33042a, this, i11);
    }

    public void I0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configMouthSize(this.f33042a, this, i11);
    }

    public void I1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableHairDye(this.f33042a, this, z11);
    }

    public int I2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceReshapeLeftIntensity(this.f33042a, this);
    }

    public UIEyebrow3dEditPoints I3() {
        return new UIEyebrow3dEditPoints(UIVenusJNI.UIVenusPipelineSettings_queryEyebrow3dLeftEditPoints(this.f33042a, this), true);
    }

    public void J(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeUnderRight(this.f33042a, this, i11);
    }

    public void J0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configMouthWidth(this.f33042a, this, i11);
    }

    public void J1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableLipstick(this.f33042a, this, z11);
    }

    public int J2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceReshapeRightIntensity(this.f33042a, this);
    }

    public UIEyebrow3dEditPoints J3() {
        return new UIEyebrow3dEditPoints(UIVenusJNI.UIVenusPipelineSettings_queryEyebrow3dRightEditPoints(this.f33042a, this), true);
    }

    public void K(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeWidth(this.f33042a, this, i11);
    }

    public void K0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configNoseBridge(this.f33042a, this, i11);
    }

    public void K1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableWig(this.f33042a, this, z11);
    }

    public int K2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceShortenIntensity(this.f33042a, this);
    }

    public UIFaceRect K3() {
        return new UIFaceRect(UIVenusJNI.UIVenusPipelineSettings_queryFaceRect(this.f33042a, this), true);
    }

    public void L(int i11, int i12, UIColor uIColor, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelBrowEngineRect uIModelBrowEngineRect, UIModelBrowEngineRect uIModelBrowEngineRect2, UIModelBrowEngineRect uIModelBrowEngineRect3, UIModelBrowEngineRect uIModelBrowEngineRect4, UIModelBrowEngineRect uIModelBrowEngineRect5, UIEyebrowMode uIEyebrowMode, int i13, UIFaceBrow uIFaceBrow, UIFaceBrow uIFaceBrow2, int i14, int i15, int i16, int i17, int i18, int i19, int i21, int i22, boolean z11, boolean z12, boolean z13) {
        UIVenusJNI.UIVenusPipelineSettings_configEyebrow(this.f33042a, this, i11, i12, UIColor.b(uIColor), uIColor, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelBrowEngineRect.b(uIModelBrowEngineRect), uIModelBrowEngineRect, UIModelBrowEngineRect.b(uIModelBrowEngineRect2), uIModelBrowEngineRect2, UIModelBrowEngineRect.b(uIModelBrowEngineRect3), uIModelBrowEngineRect3, UIModelBrowEngineRect.b(uIModelBrowEngineRect4), uIModelBrowEngineRect4, UIModelBrowEngineRect.b(uIModelBrowEngineRect5), uIModelBrowEngineRect5, uIEyebrowMode.f(), i13, UIFaceBrow.c(uIFaceBrow), uIFaceBrow, UIFaceBrow.c(uIFaceBrow2), uIFaceBrow2, i14, i15, i16, i17, i18, i19, i21, i22, z11, z12, z13);
    }

    public void L0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configNoseLength(this.f33042a, this, i11);
    }

    public int L1() {
        return UIVenusJNI.UIVenusPipelineSettings_getAppleCheekIntensity(this.f33042a, this);
    }

    public int L2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceWidthIntensity(this.f33042a, this);
    }

    public UIFaceAlignmentData L3() {
        return new UIFaceAlignmentData(UIVenusJNI.UIVenusPipelineSettings_queryFeaturePoints(this.f33042a, this), true);
    }

    public void M(UIFaceBrow uIFaceBrow, UIFaceBrow uIFaceBrow2) {
        UIVenusJNI.UIVenusPipelineSettings_configEyebrowBasePoints(this.f33042a, this, UIFaceBrow.c(uIFaceBrow), uIFaceBrow, UIFaceBrow.c(uIFaceBrow2), uIFaceBrow2);
    }

    public void M0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configNoseRootWidth(this.f33042a, this, i11);
    }

    public int M2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceliftCheek(this.f33042a, this);
    }

    public void M3(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_setForceApplyWigPosition(this.f33042a, this, z11);
    }

    public void N(int i11, int i12, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configEyebrowTattooLeft(this.f33042a, this, i11, i12, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void N0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configNoseShadow(this.f33042a, this, i11);
    }

    public int N1() {
        return UIVenusJNI.UIVenusPipelineSettings_getCheekbonesIntensity(this.f33042a, this);
    }

    public int N2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceliftEyes(this.f33042a, this);
    }

    public void N3(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_setIsWigModelChange(this.f33042a, this, z11);
    }

    public void O(int i11, int i12, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configEyebrowTattooRight(this.f33042a, this, i11, i12, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void O0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configNoseSize(this.f33042a, this, i11);
    }

    public int O1() {
        return UIVenusJNI.UIVenusPipelineSettings_getChinLengthIntensity(this.f33042a, this);
    }

    public int O2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceliftForehead(this.f33042a, this);
    }

    public void O3(UIEyebrowMode uIEyebrowMode) {
        UIVenusJNI.UIVenusPipelineSettings_updateEyebrowMode(this.f33042a, this, uIEyebrowMode.f());
    }

    public void P(int i11, UIColor uIColor, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect, int i12) {
        UIVenusJNI.UIVenusPipelineSettings_configEyelash(this.f33042a, this, i11, UIColor.b(uIColor), uIColor, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect, i12);
    }

    public void P0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configNoseTip(this.f33042a, this, i11);
    }

    public int P1() {
        return UIVenusJNI.UIVenusPipelineSettings_getChinReshapeIntensity(this.f33042a, this);
    }

    public int P2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceliftMouth(this.f33042a, this);
    }

    public void Q(int i11, int i12, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configEyelashTattooLeftLower(this.f33042a, this, i11, i12, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void Q0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configNoseTipWidth(this.f33042a, this, i11);
    }

    public int Q1() {
        return UIVenusJNI.UIVenusPipelineSettings_getChinReshapeLeftIntensity(this.f33042a, this);
    }

    public int Q2() {
        return UIVenusJNI.UIVenusPipelineSettings_getFaceliftShape(this.f33042a, this);
    }

    public void R(int i11, int i12, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configEyelashTattooLeftUpper(this.f33042a, this, i11, i12, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void R0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configNoseWing(this.f33042a, this, i11);
    }

    public int R1() {
        return UIVenusJNI.UIVenusPipelineSettings_getChinReshapeRightIntensity(this.f33042a, this);
    }

    public int R2() {
        return UIVenusJNI.UIVenusPipelineSettings_getForeheadIntensity(this.f33042a, this);
    }

    public void S() {
        UIVenusJNI.UIVenusPipelineSettings_configEyelashTattooResetAll(this.f33042a, this);
    }

    public void S0(int i11, boolean z11, int i12, int i13, int i14, int i15, int i16, UIShimmer uIShimmer) {
        UIVenusJNI.UIVenusPipelineSettings_configOneColorLipstick(this.f33042a, this, i11, z11, i12, i13, i14, i15, i16, UIShimmer.b(uIShimmer), uIShimmer);
    }

    public boolean S1() {
        return UIVenusJNI.UIVenusPipelineSettings_getEnableEyeBrow(this.f33042a, this);
    }

    public int S2() {
        return UIVenusJNI.UIVenusPipelineSettings_getHeadSizeIntensity(this.f33042a, this);
    }

    public void T(int i11, int i12, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configEyelashTattooRightLower(this.f33042a, this, i11, i12, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void T0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configPhiltrumLength(this.f33042a, this, i11);
    }

    public boolean T1() {
        return UIVenusJNI.UIVenusPipelineSettings_getEnableEyeContact(this.f33042a, this);
    }

    public int T2() {
        return UIVenusJNI.UIVenusPipelineSettings_getHeadTopIntensity(this.f33042a, this);
    }

    public void U(int i11, int i12, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configEyelashTattooRightUpper(this.f33042a, this, i11, i12, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void U0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configPointyChin(this.f33042a, this, i11);
    }

    public boolean U1() {
        return UIVenusJNI.UIVenusPipelineSettings_getEnableEyeEnlarge(this.f33042a, this);
    }

    public boolean U2() {
        return UIVenusJNI.UIVenusPipelineSettings_getIsWigModelChange(this.f33042a, this);
    }

    public void V(int i11, UIColor uIColor, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect, int i12) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeliner(this.f33042a, this, i11, UIColor.b(uIColor), uIColor, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect, i12);
    }

    public void V0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configPointyChinLeft(this.f33042a, this, i11);
    }

    public boolean V1() {
        return UIVenusJNI.UIVenusPipelineSettings_getEnableFaceReshape(this.f33042a, this);
    }

    public int V2() {
        return UIVenusJNI.UIVenusPipelineSettings_getJawIntensity(this.f33042a, this);
    }

    public void W(int i11, int i12, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configEyelinerTattooLeft(this.f33042a, this, i11, i12, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void W0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configPointyChinRight(this.f33042a, this, i11);
    }

    public boolean W1() {
        return UIVenusJNI.UIVenusPipelineSettings_getEnableHairDye(this.f33042a, this);
    }

    public int W2() {
        return UIVenusJNI.UIVenusPipelineSettings_getLipPeakIntensity(this.f33042a, this);
    }

    public void X(int i11, int i12, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configEyelinerTattooRight(this.f33042a, this, i11, i12, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void X0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configPoreRemoval(this.f33042a, this, i11);
    }

    public boolean X1() {
        return UIVenusJNI.UIVenusPipelineSettings_getEnableHeadSize(this.f33042a, this);
    }

    public int X2() {
        return UIVenusJNI.UIVenusPipelineSettings_getMouthHeightIntensity(this.f33042a, this);
    }

    public void Y(UIIntVector uIIntVector, UIColorVector uIColorVector, UIIntVector uIIntVector2, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect, UIIntVector uIIntVector3, int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeshadow(this.f33042a, this, UIIntVector.d(uIIntVector), uIIntVector, UIColorVector.c(uIColorVector), uIColorVector, UIIntVector.d(uIIntVector2), uIIntVector2, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect, UIIntVector.d(uIIntVector3), uIIntVector3, i11);
    }

    public void Y0(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_configRedEyeRemoval(this.f33042a, this, z11);
    }

    public boolean Y1() {
        return UIVenusJNI.UIVenusPipelineSettings_getEnableMouthOpen(this.f33042a, this);
    }

    public int Y2() {
        return UIVenusJNI.UIVenusPipelineSettings_getMouthHeightLowerIntensity(this.f33042a, this);
    }

    public void Z(int i11, int i12, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeshadowTattooLeft(this.f33042a, this, i11, i12, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void Z0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configRednessRemoval(this.f33042a, this, i11);
    }

    public boolean Z1() {
        return UIVenusJNI.UIVenusPipelineSettings_getEnableSmile(this.f33042a, this);
    }

    public int Z2() {
        return UIVenusJNI.UIVenusPipelineSettings_getMouthHeightUpperIntensity(this.f33042a, this);
    }

    public void a(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configAntiShine(this.f33042a, this, i11);
    }

    public void a0(int i11, int i12, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeshadowTattooRight(this.f33042a, this, i11, i12, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void a1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configRetouchLipPlumperFullness(this.f33042a, this, i11);
    }

    public boolean a2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEnableWig(this.f33042a, this);
    }

    public int a3() {
        return UIVenusJNI.UIVenusPipelineSettings_getMouthPositionIntensity(this.f33042a, this);
    }

    public void b(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configAppleCheek(this.f33042a, this, i11);
    }

    public void b0(UIFaceModelCacheVector uIFaceModelCacheVector, UIIntPointVector uIIntPointVector, UIFaceTattooColorVector uIFaceTattooColorVector, UIBoolVector uIBoolVector) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceArt(this.f33042a, this, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIIntPointVector.c(uIIntPointVector), uIIntPointVector, UIFaceTattooColorVector.c(uIFaceTattooColorVector), uIFaceTattooColorVector, UIBoolVector.d(uIBoolVector), uIBoolVector);
    }

    public void b1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configRetouchLipPlumperWrinkless(this.f33042a, this, i11);
    }

    public UIColorVector b2() {
        return new UIColorVector(UIVenusJNI.UIVenusPipelineSettings_getEyeContactColor(this.f33042a, this), true);
    }

    public int b3() {
        return UIVenusJNI.UIVenusPipelineSettings_getMouthSizeIntensity(this.f33042a, this);
    }

    public void c(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_configAutoSpotRemoval(this.f33042a, this, z11);
    }

    public void c0(UIFaceModelCacheVector uIFaceModelCacheVector, UIIntPointVector uIIntPointVector, UIFaceTattooColorVector uIFaceTattooColorVector) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceArtLayer2(this.f33042a, this, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIIntPointVector.c(uIIntPointVector), uIIntPointVector, UIFaceTattooColorVector.c(uIFaceTattooColorVector), uIFaceTattooColorVector);
    }

    public void c1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configSkinSmooth(this.f33042a, this, i11);
    }

    public int c2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeContactIntensity(this.f33042a, this);
    }

    public int c3() {
        return UIVenusJNI.UIVenusPipelineSettings_getMouthWidthIntensity(this.f33042a, this);
    }

    public void d(int i11, UIColor uIColor, String str, String str2, boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_configBlush(this.f33042a, this, i11, UIColor.b(uIColor), uIColor, str, str2, z11);
    }

    public void d0(int i11, UIIntVector uIIntVector, UIIntVector uIIntVector2, UIColorVector uIColorVector, UIIntVector uIIntVector3, UIFaceModelCacheVector uIFaceModelCacheVector, UIIntPointVector uIIntPointVector, UIBoolVector uIBoolVector) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceContourPattern(this.f33042a, this, i11, UIIntVector.d(uIIntVector), uIIntVector, UIIntVector.d(uIIntVector2), uIIntVector2, UIColorVector.c(uIColorVector), uIColorVector, UIIntVector.d(uIIntVector3), uIIntVector3, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIIntPointVector.c(uIIntPointVector), uIIntPointVector, UIBoolVector.d(uIBoolVector), uIBoolVector);
    }

    public void d1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configSmile(this.f33042a, this, i11);
    }

    public UIFaceModelCacheVector d2() {
        return new UIFaceModelCacheVector(UIVenusJNI.UIVenusPipelineSettings_getEyeContactMaskModelCache(this.f33042a, this), true);
    }

    public int d3() {
        return UIVenusJNI.UIVenusPipelineSettings_getNoseBridgeIntensity(this.f33042a, this);
    }

    public void e(VN_MakeupCacheMode vN_MakeupCacheMode) {
        UIVenusJNI.UIVenusPipelineSettings_configCacheMode(this.f33042a, this, vN_MakeupCacheMode.f());
    }

    public void e0(UIFaceRect uIFaceRect, UIFaceAlignmentData uIFaceAlignmentData, UIEyebrow3dEditPoints uIEyebrow3dEditPoints, UIEyebrow3dEditPoints uIEyebrow3dEditPoints2) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceData(this.f33042a, this, UIFaceRect.c(uIFaceRect), uIFaceRect, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData, UIEyebrow3dEditPoints.d(uIEyebrow3dEditPoints), uIEyebrow3dEditPoints, UIEyebrow3dEditPoints.d(uIEyebrow3dEditPoints2), uIEyebrow3dEditPoints2);
    }

    public void e1(UISmileMode uISmileMode) {
        UIVenusJNI.UIVenusPipelineSettings_configSmileMode(this.f33042a, this, uISmileMode.c());
    }

    public UIFaceModelCacheVector e2() {
        return new UIFaceModelCacheVector(UIVenusJNI.UIVenusPipelineSettings_getEyeContactModelCache(this.f33042a, this), true);
    }

    public int e3() {
        return UIVenusJNI.UIVenusPipelineSettings_getNoseLengthIntensity(this.f33042a, this);
    }

    public void f(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configCheekbones(this.f33042a, this, i11);
    }

    public void f0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceLessen(this.f33042a, this, i11);
    }

    public void f1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configSparkleEye(this.f33042a, this, i11);
    }

    public int f2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeContactSizeIntensity(this.f33042a, this);
    }

    public int f3() {
        return UIVenusJNI.UIVenusPipelineSettings_getNoseRootWidthIntensity(this.f33042a, this);
    }

    public void finalize() {
        u1();
    }

    public void g(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configChinLength(this.f33042a, this, i11);
    }

    public void g0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceLessenLeft(this.f33042a, this, i11);
    }

    public void g1(boolean z11, int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configTeethWhitening(this.f33042a, this, z11, i11);
    }

    public int g2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeDistanceIntensity(this.f33042a, this);
    }

    public int g3() {
        return UIVenusJNI.UIVenusPipelineSettings_getNoseSizeIntensity(this.f33042a, this);
    }

    public void h(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configChinReshape(this.f33042a, this, i11);
    }

    public void h0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceLessenRight(this.f33042a, this, i11);
    }

    public void h1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configTemple(this.f33042a, this, i11);
    }

    public int h2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeEnlargeIntensity(this.f33042a, this);
    }

    public int h3() {
        return UIVenusJNI.UIVenusPipelineSettings_getNoseTipIntensity(this.f33042a, this);
    }

    public void i(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configChinReshapeLeft(this.f33042a, this, i11);
    }

    public void i0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceMiddle(this.f33042a, this, i11);
    }

    public void i1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configTempleLeft(this.f33042a, this, i11);
    }

    public int i2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeEnlargeLeftIntensity(this.f33042a, this);
    }

    public int i3() {
        return UIVenusJNI.UIVenusPipelineSettings_getNoseTipWidthIntensity(this.f33042a, this);
    }

    public void j(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configChinReshapeRight(this.f33042a, this, i11);
    }

    public void j0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceReshape(this.f33042a, this, i11);
    }

    public void j1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configTempleRight(this.f33042a, this, i11);
    }

    public int j2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeEnlargeRightIntensity(this.f33042a, this);
    }

    public int j3() {
        return UIVenusJNI.UIVenusPipelineSettings_getNoseWingIntensity(this.f33042a, this);
    }

    public void k(int i11, UIColor uIColor) {
        UIVenusJNI.UIVenusPipelineSettings_configConcealer(this.f33042a, this, i11, UIColor.b(uIColor), uIColor);
    }

    public void k0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceReshapeLeft(this.f33042a, this, i11);
    }

    public void k1(int i11, boolean z11, int i12, boolean z12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i21, int i22, UIShimmer uIShimmer) {
        UIVenusJNI.UIVenusPipelineSettings_configTwoColorsLipstick(this.f33042a, this, i11, z11, i12, z12, i13, i14, i15, i16, i17, i18, i19, i21, i22, UIShimmer.b(uIShimmer), uIShimmer);
    }

    public int k2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeHeightIntensity(this.f33042a, this);
    }

    public void k3(UIFaceBrow uIFaceBrow, UIFaceBrow uIFaceBrow2) {
        UIVenusJNI.UIVenusPipelineSettings_getOriginalEyeBrow(this.f33042a, this, UIFaceBrow.c(uIFaceBrow), uIFaceBrow, UIFaceBrow.c(uIFaceBrow2), uIFaceBrow2);
    }

    public void l(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configDarkCircleRemoval(this.f33042a, this, i11);
    }

    public void l0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceReshapeRight(this.f33042a, this, i11);
    }

    public void l1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configUnevenness(this.f33042a, this, i11);
    }

    public int l2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeInnerIntensity(this.f33042a, this);
    }

    public int l3() {
        return UIVenusJNI.UIVenusPipelineSettings_getPhiltrumLengthIntensity(this.f33042a, this);
    }

    public void m(int i11, UIColor uIColor, UIFaceModelCacheVector uIFaceModelCacheVector, UIModelEyeRect uIModelEyeRect) {
        UIVenusJNI.UIVenusPipelineSettings_configDoubleEyelid(this.f33042a, this, i11, UIColor.b(uIColor), uIColor, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIModelEyeRect.b(uIModelEyeRect), uIModelEyeRect);
    }

    public void m0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceShorten(this.f33042a, this, i11);
    }

    public void m1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configVShapeFace(this.f33042a, this, i11);
    }

    public int m2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeInnerLeftIntensity(this.f33042a, this);
    }

    public int m3() {
        return UIVenusJNI.UIVenusPipelineSettings_getPointyChinIntensity(this.f33042a, this);
    }

    public void n(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeBagRemoval(this.f33042a, this, i11);
    }

    public void n0(UIFaceModelCacheVector uIFaceModelCacheVector, UIIntPointVector uIIntPointVector, UIFaceTattooColorVector uIFaceTattooColorVector, UIBoolVector uIBoolVector) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceWidget(this.f33042a, this, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIIntPointVector.c(uIIntPointVector), uIIntPointVector, UIFaceTattooColorVector.c(uIFaceTattooColorVector), uIFaceTattooColorVector, UIBoolVector.d(uIBoolVector), uIBoolVector);
    }

    public void n1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configVShapeFaceLeft(this.f33042a, this, i11);
    }

    public int n2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeInnerRightIntensity(this.f33042a, this);
    }

    public int n3() {
        return UIVenusJNI.UIVenusPipelineSettings_getPointyChinLeftIntensity(this.f33042a, this);
    }

    public void o(int i11, int i12, UIColorVector uIColorVector, UIFaceModelCacheVector uIFaceModelCacheVector, UIFaceModelCacheVector uIFaceModelCacheVector2) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeContact(this.f33042a, this, i11, i12, UIColorVector.c(uIColorVector), uIColorVector, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, UIFaceModelCacheVector.e(uIFaceModelCacheVector2), uIFaceModelCacheVector2);
    }

    public void o0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceWidth(this.f33042a, this, i11);
    }

    public void o1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configVShapeFaceRight(this.f33042a, this, i11);
    }

    public int o2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeOuterIntensity(this.f33042a, this);
    }

    public int o3() {
        return UIVenusJNI.UIVenusPipelineSettings_getPointyChinRightIntensity(this.f33042a, this);
    }

    public void p(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeDistance(this.f33042a, this, i11);
    }

    public void p0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceliftCheek(this.f33042a, this, i11);
    }

    public void p1(boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, UITransform uITransform, UIWigColor uIWigColor, UIFaceModelCacheVector uIFaceModelCacheVector, int i11, int i12, UIWigModelAnchor uIWigModelAnchor, UIWigColoringMode uIWigColoringMode, boolean z16) {
        UIVenusJNI.UIVenusPipelineSettings_configWig(this.f33042a, this, z11, z12, z13, z14, z15, UITransform.c(uITransform), uITransform, UIWigColor.b(uIWigColor), uIWigColor, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector, i11, i12, UIWigModelAnchor.b(uIWigModelAnchor), uIWigModelAnchor, uIWigColoringMode.c(), z16);
    }

    public int p2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeOuterLeftIntensity(this.f33042a, this);
    }

    public int p3() {
        return UIVenusJNI.UIVenusPipelineSettings_getRetouchLipPlumperFullness(this.f33042a, this);
    }

    public void q(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeEnlarge(this.f33042a, this, i11);
    }

    public void q0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceliftEyes(this.f33042a, this, i11);
    }

    public void q1(UITransform uITransform) {
        UIVenusJNI.UIVenusPipelineSettings_configWigTransform(this.f33042a, this, UITransform.c(uITransform), uITransform);
    }

    public int q2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeOuterRightIntensity(this.f33042a, this);
    }

    public int q3() {
        return UIVenusJNI.UIVenusPipelineSettings_getRetouchLipPlumperWrinkless(this.f33042a, this);
    }

    public void r(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeEnlargeLeft(this.f33042a, this, i11);
    }

    public void r0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceliftForehead(this.f33042a, this, i11);
    }

    public void r1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configWrinkleRemovalAroundEyes(this.f33042a, this, i11);
    }

    public int r2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyePositionIntensity(this.f33042a, this);
    }

    public int r3() {
        return UIVenusJNI.UIVenusPipelineSettings_getSmileIntensity(this.f33042a, this);
    }

    public void s(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeEnlargeRight(this.f33042a, this, i11);
    }

    public void s0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceliftMouth(this.f33042a, this, i11);
    }

    public void s1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configWrinkleRemovalForehead(this.f33042a, this, i11);
    }

    public int s2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyePositionLeftIntensity(this.f33042a, this);
    }

    public int s3() {
        return UIVenusJNI.UIVenusPipelineSettings_getTempleIntensity(this.f33042a, this);
    }

    public void t(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeHeight(this.f33042a, this, i11);
    }

    public void t0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configFaceliftShape(this.f33042a, this, i11);
    }

    public void t1(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configWrinkleRemovalLowerFace(this.f33042a, this, i11);
    }

    public int t2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyePositionRightIntensity(this.f33042a, this);
    }

    public int t3() {
        return UIVenusJNI.UIVenusPipelineSettings_getTempleLeftIntensity(this.f33042a, this);
    }

    public void u(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeInner(this.f33042a, this, i11);
    }

    public void u0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configForehead(this.f33042a, this, i11);
    }

    public synchronized void u1() {
        try {
            long j11 = this.f33042a;
            if (j11 != 0) {
                if (this.f33043b) {
                    this.f33043b = false;
                    UIVenusJNI.delete_UIVenusPipelineSettings(j11);
                }
                this.f33042a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public int u2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeSlantIntensity(this.f33042a, this);
    }

    public int u3() {
        return UIVenusJNI.UIVenusPipelineSettings_getTempleRightIntensity(this.f33042a, this);
    }

    public void v(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeInnerLeft(this.f33042a, this, i11);
    }

    public void v0(int i11, UIColor uIColor, UIFoundationIntensityMode uIFoundationIntensityMode) {
        UIVenusJNI.UIVenusPipelineSettings_configFoundation(this.f33042a, this, i11, UIColor.b(uIColor), uIColor, uIFoundationIntensityMode.c());
    }

    public void v1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableBlush(this.f33042a, this, z11);
    }

    public int v2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeTailIntensity(this.f33042a, this);
    }

    public int v3() {
        return UIVenusJNI.UIVenusPipelineSettings_getVShapeFaceIntensity(this.f33042a, this);
    }

    public void w(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeInnerRight(this.f33042a, this, i11);
    }

    public void w0(UIIntVector uIIntVector, UIIntVector uIIntVector2, UIColorVector uIColorVector, UIHairDyeMode uIHairDyeMode, float f11, float f12, boolean z11, float f13, float f14, float f15, float f16, int i11, UIFaceModelCacheVector uIFaceModelCacheVector) {
        UIVenusJNI.UIVenusPipelineSettings_configHairDye__SWIG_1(this.f33042a, this, UIIntVector.d(uIIntVector), uIIntVector, UIIntVector.d(uIIntVector2), uIIntVector2, UIColorVector.c(uIColorVector), uIColorVector, uIHairDyeMode.c(), f11, f12, z11, f13, f14, f15, f16, i11, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector);
    }

    public void w1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableConcealer(this.f33042a, this, z11);
    }

    public int w2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeTailLeftIntensity(this.f33042a, this);
    }

    public int w3() {
        return UIVenusJNI.UIVenusPipelineSettings_getVShapeFaceLeftIntensity(this.f33042a, this);
    }

    public void x(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeOuter(this.f33042a, this, i11);
    }

    public void x0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configHairDyeAging(this.f33042a, this, i11);
    }

    public void x1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableDoubleEyelid(this.f33042a, this, z11);
    }

    public int x2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeTailRightIntensity(this.f33042a, this);
    }

    public int x3() {
        return UIVenusJNI.UIVenusPipelineSettings_getVShapeFaceRightIntensity(this.f33042a, this);
    }

    public void y(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeOuterLeft(this.f33042a, this, i11);
    }

    public void y0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configHeadSize(this.f33042a, this, i11);
    }

    public void y1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableEyeContact(this.f33042a, this, z11);
    }

    public int y2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeUnderIntensity(this.f33042a, this);
    }

    public void y3(UIWarpedWigImageInfo uIWarpedWigImageInfo) {
        UIVenusJNI.UIVenusPipelineSettings_getWarpedWigImageInfo(this.f33042a, this, UIWarpedWigImageInfo.b(uIWarpedWigImageInfo), uIWarpedWigImageInfo);
    }

    public void z(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configEyeOuterRight(this.f33042a, this, i11);
    }

    public void z0(int i11) {
        UIVenusJNI.UIVenusPipelineSettings_configHeadTop(this.f33042a, this, i11);
    }

    public void z1(boolean z11) {
        UIVenusJNI.UIVenusPipelineSettings_enableEyebrow(this.f33042a, this, z11);
    }

    public int z2() {
        return UIVenusJNI.UIVenusPipelineSettings_getEyeUnderLeftIntensity(this.f33042a, this);
    }

    public void z3(UITransform uITransform) {
        UIVenusJNI.UIVenusPipelineSettings_getWarpedWigTransform(this.f33042a, this, UITransform.c(uITransform), uITransform);
    }

    public UIVenusPipelineSettings() {
        this(UIVenusJNI.new_UIVenusPipelineSettings__SWIG_0(), true);
    }

    public UIVenusPipelineSettings(UIVenusPipelineSettings uIVenusPipelineSettings) {
        this(UIVenusJNI.new_UIVenusPipelineSettings__SWIG_1(M1(uIVenusPipelineSettings), uIVenusPipelineSettings), true);
    }
}
