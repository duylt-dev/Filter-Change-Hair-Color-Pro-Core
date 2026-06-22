package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UISkinCareAIJNI {
    public static final native boolean CUISkinCareAI_AnalyzeSkinCareImage(long j11, CUISkinCareAI cUISkinCareAI, long j12, CImageBuffer cImageBuffer, long j13, VenusPhotoFaceAlignData venusPhotoFaceAlignData, int[] iArr, int[] iArr2);

    public static final native boolean CUISkinCareAI_GetOverallScore(long j11, CUISkinCareAI cUISkinCareAI, Object[] objArr, Object obj);

    public static final native boolean CUISkinCareAI_GetSkinAnalysisAIReport(long j11, CUISkinCareAI cUISkinCareAI, long j12, CImageBuffer cImageBuffer, long j13, VenusPhotoFaceAlignData venusPhotoFaceAlignData, Object[] objArr);

    public static final native boolean CUISkinCareAI_GetSkinCareModelVersions(long j11, CUISkinCareAI cUISkinCareAI, Object[] objArr);

    public static final native boolean CUISkinCareAI_ResetSkinAnalysis(long j11, CUISkinCareAI cUISkinCareAI);

    public static final native boolean CUISkinCareAI_SetIndividualSkinCareAIFeatureColor(long j11, CUISkinCareAI cUISkinCareAI, String str, String str2);

    public static final native boolean CUISkinCareAI_SetSkinCareModelPaths(long j11, CUISkinCareAI cUISkinCareAI, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17);

    public static final native boolean CUISkinCareAI_ShowSkinAnalysisAIResult(long j11, CUISkinCareAI cUISkinCareAI, long j12, CImageBuffer cImageBuffer, Object[] objArr);

    public static final native void delete_CUISkinCareAI(long j11);

    public static final native long new_CUISkinCareAI(String str, long j11, CUIVenusPhoto cUIVenusPhoto);
}
