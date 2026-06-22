package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class CUIImageRetouch {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32850a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32851b;

    public CUIImageRetouch(long j11, boolean z11) {
        this.f32851b = z11;
        this.f32850a = j11;
    }

    public String a(int i11, int i12) {
        return UIImageRetouchJNI.CUIImageRetouch_Detect_AutoTone(this.f32850a, this, i11, i12);
    }

    public int b(int i11, int i12) {
        return UIImageRetouchJNI.CUIImageRetouch_Image_ClearTask(this.f32850a, this, i11, i12);
    }

    public int c(int i11, IParamBase iParamBase, IParamBase iParamBase2) {
        return UIImageRetouchJNI.CUIImageRetouch_Image_PrepareTask(this.f32850a, this, i11, IParamBase.d(iParamBase), iParamBase, IParamBase.d(iParamBase2), iParamBase2);
    }

    public int d(int i11, int i12) {
        return UIImageRetouchJNI.CUIImageRetouch_Image_PushTask(this.f32850a, this, i11, i12);
    }

    public int e(int i11, int i12, int i13, ROIParam rOIParam, BufferDataParam bufferDataParam) {
        return UIImageRetouchJNI.CUIImageRetouch_Image_RunAllTask(this.f32850a, this, i11, i12, i13, ROIParam.b(rOIParam), rOIParam, BufferDataParam.b(bufferDataParam), bufferDataParam);
    }

    public int f(int i11, int i12, float f11, BufferDataParam bufferDataParam) {
        return UIImageRetouchJNI.CUIImageRetouch_RegisterImageRatio(this.f32850a, this, i11, i12, f11, BufferDataParam.b(bufferDataParam), bufferDataParam);
    }

    public void finalize() {
        k();
    }

    public int g() {
        return UIImageRetouchJNI.CUIImageRetouch_ReleaseAllImage(this.f32850a, this);
    }

    public int h(int i11) {
        return UIImageRetouchJNI.CUIImageRetouch_ReleaseImage(this.f32850a, this, i11);
    }

    public int i(int i11, BufferDataParam bufferDataParam) {
        return UIImageRetouchJNI.CUIImageRetouch_SetImage(this.f32850a, this, i11, BufferDataParam.b(bufferDataParam), bufferDataParam);
    }

    public int j(int i11, int i12) {
        return UIImageRetouchJNI.CUIImageRetouch_UnregisterImageRatio(this.f32850a, this, i11, i12);
    }

    public synchronized void k() {
        try {
            long j11 = this.f32850a;
            if (j11 != 0) {
                if (this.f32851b) {
                    this.f32851b = false;
                    UIImageRetouchJNI.delete_CUIImageRetouch(j11);
                }
                this.f32850a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public CUIImageRetouch(String str) {
        this(UIImageRetouchJNI.new_CUIImageRetouch__SWIG_0(str), true);
    }
}
