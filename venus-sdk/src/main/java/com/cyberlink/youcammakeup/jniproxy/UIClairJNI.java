package com.cyberlink.youcammakeup.jniproxy;

import ev.a;

/* loaded from: classes2.dex */
public class UIClairJNI {
    static {
        a.a();
    }

    public static final native boolean CUIClair_GetSegmentation(long j11, CUIClair cUIClair, long j12, CImageBuffer cImageBuffer, long j13, CImageBuffer cImageBuffer2, String str, String str2, boolean z11);

    public static final native void delete_CUIClair(long j11);

    public static final native long new_CUIClair(String str, String str2);
}
