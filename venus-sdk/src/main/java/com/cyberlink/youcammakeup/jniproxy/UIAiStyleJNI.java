package com.cyberlink.youcammakeup.jniproxy;

import ev.a;

/* loaded from: classes2.dex */
public class UIAiStyleJNI {
    static {
        a.a();
    }

    public static final native void AiStyleApi_Apply(long j11, AiStyleApi aiStyleApi, long j12, CImageBuffer cImageBuffer, Object obj);

    public static final native void delete_AiStyleApi(long j11);

    public static final native long new_AiStyleApi(String str, String str2, int i11);
}
