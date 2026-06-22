package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class CImageBuffer extends IImageBuffer {

    /* renamed from: d, reason: collision with root package name */
    public transient long f32843d;

    public CImageBuffer(long j11, boolean z11) {
        super(CommonJNI.CImageBuffer_SWIGUpcast(j11), z11);
        this.f32843d = j11;
    }

    public static boolean B(CImageBuffer cImageBuffer) {
        return CommonJNI.CImageBuffer_SwapColorChannel__SWIG_0(D(cImageBuffer), cImageBuffer);
    }

    public static boolean C(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2) {
        return CommonJNI.CImageBuffer_SwapColorChannel__SWIG_1(D(cImageBuffer), cImageBuffer, D(cImageBuffer2), cImageBuffer2);
    }

    public static long D(CImageBuffer cImageBuffer) {
        if (cImageBuffer == null) {
            return 0L;
        }
        return cImageBuffer.f32843d;
    }

    public static boolean e(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2) {
        return CommonJNI.CImageBuffer_BlendingWhite(D(cImageBuffer), cImageBuffer, D(cImageBuffer2), cImageBuffer2);
    }

    public static boolean g(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2) {
        return CommonJNI.CImageBuffer_ConvertColorDepth(D(cImageBuffer), cImageBuffer, D(cImageBuffer2), cImageBuffer2);
    }

    public static boolean h(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2, UIImageROI uIImageROI) {
        return CommonJNI.CImageBuffer_CopyImageBufferToImageBuffer__SWIG_0(D(cImageBuffer), cImageBuffer, D(cImageBuffer2), cImageBuffer2, UIImageROI.b(uIImageROI), uIImageROI);
    }

    public static boolean q(String str, UICacheFileInfo uICacheFileInfo) {
        return CommonJNI.CImageBuffer_GetCacheFileInfo(str, UICacheFileInfo.b(uICacheFileInfo), uICacheFileInfo);
    }

    public static boolean w(CImageBuffer cImageBuffer, CImageBuffer cImageBuffer2, CImageBuffer cImageBuffer3, boolean z11, CImageBuffer cImageBuffer4) {
        return CommonJNI.CImageBuffer_MaskBlending(D(cImageBuffer), cImageBuffer, D(cImageBuffer2), cImageBuffer2, D(cImageBuffer3), cImageBuffer3, z11, D(cImageBuffer4), cImageBuffer4);
    }

    public static boolean x(CImageBuffer cImageBuffer) {
        return CommonJNI.CImageBuffer_Premultiply(D(cImageBuffer), cImageBuffer);
    }

    public static boolean y(CImageBuffer cImageBuffer) {
        return CommonJNI.CImageBuffer_RemovePremultiply(D(cImageBuffer), cImageBuffer);
    }

    public void A(PixelFormat pixelFormat) {
        CommonJNI.CImageBuffer_SetPixelFormat(this.f32843d, this, pixelFormat.f());
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IImageBuffer, com.cyberlink.youcammakeup.jniproxy.IDestroyable
    public synchronized void a() {
        try {
            long j11 = this.f32843d;
            if (j11 != 0) {
                if (this.f32868b) {
                    this.f32868b = false;
                    CommonJNI.delete_CImageBuffer(j11);
                }
                this.f32843d = 0L;
            }
            super.a();
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public boolean c(Object obj) {
        return CommonJNI.CImageBuffer_ApplyMaskBitmap(this.f32843d, this, obj);
    }

    public boolean d(Object obj) {
        return CommonJNI.CImageBuffer_AttachAndroidBitmap(this.f32843d, this, obj);
    }

    public boolean f(float f11, boolean z11, boolean z12, boolean z13) {
        return CommonJNI.CImageBuffer_ConvertAlphaMask(this.f32843d, this, f11, z11, z12, z13);
    }

    public void finalize() {
        a();
    }

    public void i(int[] iArr, boolean z11) {
        CommonJNI.CImageBuffer_CopyPixelToArray(this.f32843d, this, iArr, z11);
    }

    public boolean j(long j11, long j12, long j13) {
        return CommonJNI.CImageBuffer_CreateBuffer(this.f32843d, this, j11, j12, j13);
    }

    public boolean k(CImageBuffer cImageBuffer, UIImageROI uIImageROI) {
        return CommonJNI.CImageBuffer_CreateFromImageBuffer(this.f32843d, this, D(cImageBuffer), cImageBuffer, UIImageROI.b(uIImageROI), uIImageROI);
    }

    public void l() {
        CommonJNI.CImageBuffer_Destroy(this.f32843d, this);
    }

    public boolean m() {
        return CommonJNI.CImageBuffer_DetachAndroidBitmap(this.f32843d, this);
    }

    public boolean n(String str) {
        return CommonJNI.CImageBuffer_DumpToFile(this.f32843d, this, str);
    }

    public boolean o() {
        return CommonJNI.CImageBuffer_FillAlphaChannelsWithRedColor(this.f32843d, this);
    }

    public long p() {
        return CommonJNI.CImageBuffer_GetBytesPerPixel(this.f32843d, this);
    }

    public long r() {
        return CommonJNI.CImageBuffer_GetHeight(this.f32843d, this);
    }

    public long s() {
        return CommonJNI.CImageBuffer_GetLength(this.f32843d, this);
    }

    public PixelFormat t() {
        return PixelFormat.c(CommonJNI.CImageBuffer_GetPixelFormat(this.f32843d, this));
    }

    public long u() {
        return CommonJNI.CImageBuffer_GetWidth(this.f32843d, this);
    }

    public boolean v(String str) {
        return CommonJNI.CImageBuffer_LoadFromFile(this.f32843d, this, str);
    }

    public void z(AccessMode accessMode) {
        CommonJNI.CImageBuffer_SetAccessMode(this.f32843d, this, accessMode.c());
    }

    public CImageBuffer(PixelFormat pixelFormat) {
        this(CommonJNI.new_CImageBuffer__SWIG_0(pixelFormat.f()), true);
    }

    public CImageBuffer() {
        this(CommonJNI.new_CImageBuffer__SWIG_1(), true);
    }
}
