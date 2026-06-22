package com.cyberlink.youcammakeup.jniproxy;

import ev.a;

/* loaded from: classes2.dex */
public class UIAthenaJNI {
    static {
        a.a();
    }

    public static final native boolean CUIAthena_addStrokePoint(long j11, CUIAthena cUIAthena, float f11, float f12);

    public static final native boolean CUIAthena_beginStroke(long j11, CUIAthena cUIAthena, int i11, int i12);

    public static final native boolean CUIAthena_clear(long j11, CUIAthena cUIAthena);

    public static final native boolean CUIAthena_endStroke(long j11, CUIAthena cUIAthena);

    public static final native Object CUIAthena_getMask(long j11, CUIAthena cUIAthena, boolean z11);

    public static final native boolean CUIAthena_initialize(long j11, CUIAthena cUIAthena, long j12, CImageBuffer cImageBuffer);

    public static final native boolean CUIAthena_invert(long j11, CUIAthena cUIAthena);

    public static final native void delete_CUIAthena(long j11);

    public static final native long new_CUIAthena__SWIG_0();
}
