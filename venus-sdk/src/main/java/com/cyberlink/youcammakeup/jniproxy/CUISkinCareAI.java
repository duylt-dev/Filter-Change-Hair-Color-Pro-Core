package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class CUISkinCareAI {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32856a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32857b;

    public CUISkinCareAI(long j11, boolean z11) {
        this.f32857b = z11;
        this.f32856a = j11;
    }

    public boolean a(CImageBuffer cImageBuffer, VenusPhotoFaceAlignData venusPhotoFaceAlignData, int[] iArr, int[] iArr2) {
        return UISkinCareAIJNI.CUISkinCareAI_AnalyzeSkinCareImage(this.f32856a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, VenusPhotoFaceAlignData.b(venusPhotoFaceAlignData), venusPhotoFaceAlignData, iArr, iArr2);
    }

    public boolean b(Object[] objArr, Object obj) {
        return UISkinCareAIJNI.CUISkinCareAI_GetOverallScore(this.f32856a, this, objArr, obj);
    }

    public boolean c(CImageBuffer cImageBuffer, VenusPhotoFaceAlignData venusPhotoFaceAlignData, Object[] objArr) {
        return UISkinCareAIJNI.CUISkinCareAI_GetSkinAnalysisAIReport(this.f32856a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, VenusPhotoFaceAlignData.b(venusPhotoFaceAlignData), venusPhotoFaceAlignData, objArr);
    }

    public boolean d(Object[] objArr) {
        return UISkinCareAIJNI.CUISkinCareAI_GetSkinCareModelVersions(this.f32856a, this, objArr);
    }

    public boolean e() {
        return UISkinCareAIJNI.CUISkinCareAI_ResetSkinAnalysis(this.f32856a, this);
    }

    public boolean f(String str, String str2) {
        return UISkinCareAIJNI.CUISkinCareAI_SetIndividualSkinCareAIFeatureColor(this.f32856a, this, str, str2);
    }

    public void finalize() {
        i();
    }

    public boolean g(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17) {
        return UISkinCareAIJNI.CUISkinCareAI_SetSkinCareModelPaths(this.f32856a, this, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17);
    }

    public boolean h(CImageBuffer cImageBuffer, Object[] objArr) {
        return UISkinCareAIJNI.CUISkinCareAI_ShowSkinAnalysisAIResult(this.f32856a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, objArr);
    }

    public synchronized void i() {
        try {
            long j11 = this.f32856a;
            if (j11 != 0) {
                if (this.f32857b) {
                    this.f32857b = false;
                    UISkinCareAIJNI.delete_CUISkinCareAI(j11);
                }
                this.f32856a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public CUISkinCareAI(String str, CUIVenusPhoto cUIVenusPhoto) {
        this(UISkinCareAIJNI.new_CUISkinCareAI(str, CUIVenusPhoto.n0(cUIVenusPhoto), cUIVenusPhoto), true);
    }
}
