package com.cyberlink.youcammakeup.jniproxy;

import ev.a;

/* loaded from: classes2.dex */
public class CommonYCPJNI {
    static {
        a.a();
    }

    public static final native void PixelBufferHelper_readPixel__SWIG_0(Object obj, boolean z11);

    public static final native void PixelBufferHelper_readPixel__SWIG_1(Object obj);

    public static final native boolean StrokeHelper_Apply(Object obj, byte[] bArr);

    public static final native boolean StrokeHelper_ApplyLine(Object obj, byte[] bArr, short s11, float f11, short s12, short s13, short s14, short s15, boolean z11);

    public static final native boolean StrokeHelper_ApplyMask(Object obj, byte[] bArr, Object obj2, boolean z11, boolean z12);

    public static final native boolean StrokeHelper_ApplyPoint(Object obj, byte[] bArr, short s11, float f11, short s12, short s13, boolean z11);

    public static final native boolean StrokeHelper_InvertMask(Object obj, byte[] bArr);

    public static final native boolean StrokeHelper_Normalize(byte[] bArr);
}
