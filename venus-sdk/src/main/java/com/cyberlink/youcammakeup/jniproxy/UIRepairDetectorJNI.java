package com.cyberlink.youcammakeup.jniproxy;

import ev.a;

/* loaded from: classes2.dex */
public class UIRepairDetectorJNI {
    static {
        a.a();
    }

    public static final native void CUIRepairDetector_Predict(long j11, CUIRepairDetector cUIRepairDetector, long j12, CImageBuffer cImageBuffer, float[] fArr, float[] fArr2);

    public static final native void delete_CUIRepairDetector(long j11);

    public static final native long new_CUIRepairDetector(String str, String str2, int i11);
}
