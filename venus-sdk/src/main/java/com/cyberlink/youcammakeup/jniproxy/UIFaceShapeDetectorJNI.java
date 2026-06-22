package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceShapeDetectorJNI {
    public static final native void CUIFaceShapeDetector_predictImage(long j11, CUIFaceShapeDetector cUIFaceShapeDetector, Object obj, int i11, int i12, long j12, UIMakeupLiveFaceAlignData uIMakeupLiveFaceAlignData, Object obj2);

    public static final native void delete_CUIFaceShapeDetector(long j11);

    public static final native long new_CUIFaceShapeDetector(String str, String str2);
}
