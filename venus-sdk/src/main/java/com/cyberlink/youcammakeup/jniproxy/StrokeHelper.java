package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class StrokeHelper {
    public static boolean a(Object obj, byte[] bArr) {
        return CommonYCPJNI.StrokeHelper_Apply(obj, bArr);
    }

    public static boolean b(Object obj, byte[] bArr, short s11, float f11, short s12, short s13, short s14, short s15, boolean z11) {
        return CommonYCPJNI.StrokeHelper_ApplyLine(obj, bArr, s11, f11, s12, s13, s14, s15, z11);
    }

    public static boolean c(Object obj, byte[] bArr, Object obj2, boolean z11, boolean z12) {
        return CommonYCPJNI.StrokeHelper_ApplyMask(obj, bArr, obj2, z11, z12);
    }

    public static boolean d(Object obj, byte[] bArr, short s11, float f11, short s12, short s13, boolean z11) {
        return CommonYCPJNI.StrokeHelper_ApplyPoint(obj, bArr, s11, f11, s12, s13, z11);
    }

    public static boolean e(Object obj, byte[] bArr) {
        return CommonYCPJNI.StrokeHelper_InvertMask(obj, bArr);
    }

    public static boolean f(byte[] bArr) {
        return CommonYCPJNI.StrokeHelper_Normalize(bArr);
    }
}
