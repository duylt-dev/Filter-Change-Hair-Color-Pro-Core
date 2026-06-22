package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class CUISkinBeautify {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32854a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32855b;

    public CUISkinBeautify(long j11, boolean z11) {
        this.f32855b = z11;
        this.f32854a = j11;
    }

    public int a(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2, int i11, int i12, UIWarpParameter uIWarpParameter, UIFaceModifiedROI uIFaceModifiedROI) {
        return UIVenusJNI.CUISkinBeautify_BodyReshapeNoFace(this.f32854a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, CImageBuffer.D(cImageBuffer2), cImageBuffer2, i11, i12, UIWarpParameter.b(uIWarpParameter), uIWarpParameter, UIFaceModifiedROI.c(uIFaceModifiedROI), uIFaceModifiedROI);
    }

    public boolean b() {
        return UIVenusJNI.CUISkinBeautify_CanRedoReshape(this.f32854a, this);
    }

    public boolean c() {
        return UIVenusJNI.CUISkinBeautify_CanUndoReshape(this.f32854a, this);
    }

    public int d() {
        return UIVenusJNI.CUISkinBeautify_CancelReshape(this.f32854a, this);
    }

    public int e() {
        return UIVenusJNI.CUISkinBeautify_FinishBodyReshapeNoFace(this.f32854a, this);
    }

    public int f() {
        return UIVenusJNI.CUISkinBeautify_GetCurrentReshapeStep(this.f32854a, this);
    }

    public void finalize() {
        p();
    }

    public int g(UIFaceModelCacheVector uIFaceModelCacheVector) {
        return UIVenusJNI.CUISkinBeautify_GetInternalModelVersion(this.f32854a, this, UIFaceModelCacheVector.e(uIFaceModelCacheVector), uIFaceModelCacheVector);
    }

    public int h(CImageBuffer cImageBuffer, UIFaceRect uIFaceRect, UIFaceAlignmentData uIFaceAlignmentData) {
        return UIVenusJNI.CUISkinBeautify_InitBeautify(this.f32854a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, UIFaceRect.c(uIFaceRect), uIFaceRect, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData);
    }

    public int i(int i11, int i12, int i13, UIFaceAlignmentDataAll uIFaceAlignmentDataAll, int i14) {
        return UIVenusJNI.CUISkinBeautify_InitBodyReshape(this.f32854a, this, i11, i12, i13, UIFaceAlignmentDataAll.b(uIFaceAlignmentDataAll), uIFaceAlignmentDataAll, i14);
    }

    public boolean j() {
        return UIVenusJNI.CUISkinBeautify_IsModelLoaded(this.f32854a, this);
    }

    public int k(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2, UIFaceModifiedROI uIFaceModifiedROI, UIFaceAlignmentData uIFaceAlignmentData) {
        return UIVenusJNI.CUISkinBeautify_RedoReshape(this.f32854a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, CImageBuffer.D(cImageBuffer2), cImageBuffer2, UIFaceModifiedROI.c(uIFaceModifiedROI), uIFaceModifiedROI, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData);
    }

    public int l(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2, UIFaceModifiedROI uIFaceModifiedROI) {
        return UIVenusJNI.CUISkinBeautify_ReshapeProduction(this.f32854a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, CImageBuffer.D(cImageBuffer2), cImageBuffer2, UIFaceModifiedROI.c(uIFaceModifiedROI), uIFaceModifiedROI);
    }

    public int m(String str, String str2, boolean z11) {
        return UIVenusJNI.CUISkinBeautify_SetInternalModelPaths(this.f32854a, this, str, str2, z11);
    }

    public int n(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2, UIFaceModifiedROI uIFaceModifiedROI, UIFaceAlignmentData uIFaceAlignmentData) {
        return UIVenusJNI.CUISkinBeautify_UndoReshape(this.f32854a, this, CImageBuffer.D(cImageBuffer), cImageBuffer, CImageBuffer.D(cImageBuffer2), cImageBuffer2, UIFaceModifiedROI.c(uIFaceModifiedROI), uIFaceModifiedROI, UIFaceAlignmentData.c(uIFaceAlignmentData), uIFaceAlignmentData);
    }

    public int o() {
        return UIVenusJNI.CUISkinBeautify_UninitBeautify(this.f32854a, this);
    }

    public synchronized void p() {
        try {
            long j11 = this.f32854a;
            if (j11 != 0) {
                if (this.f32855b) {
                    this.f32855b = false;
                    UIVenusJNI.delete_CUISkinBeautify(j11);
                }
                this.f32854a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public CUISkinBeautify() {
        this(UIVenusJNI.new_CUISkinBeautify(), true);
    }
}
