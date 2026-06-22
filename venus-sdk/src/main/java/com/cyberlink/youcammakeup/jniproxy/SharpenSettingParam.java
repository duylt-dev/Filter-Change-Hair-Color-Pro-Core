package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class SharpenSettingParam extends IParamBase {

    /* renamed from: c, reason: collision with root package name */
    public transient long f32893c;

    public SharpenSettingParam(long j11, boolean z11) {
        super(UIImageRetouchJNI.SharpenSettingParam_SWIGUpcast(j11), z11);
        this.f32893c = j11;
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public boolean a(IParamBase iParamBase) {
        return UIImageRetouchJNI.SharpenSettingParam_Compare(this.f32893c, this, IParamBase.d(iParamBase), iParamBase);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public String b() {
        return UIImageRetouchJNI.SharpenSettingParam_EncodeString(this.f32893c, this);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public synchronized void c() {
        try {
            long j11 = this.f32893c;
            if (j11 != 0) {
                if (this.f32871b) {
                    this.f32871b = false;
                    UIImageRetouchJNI.delete_SharpenSettingParam(j11);
                }
                this.f32893c = 0L;
            }
            super.c();
        } catch (Throwable th2) {
            throw th2;
        }
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public void finalize() {
        c();
    }

    public SharpenSettingParam() {
        this(UIImageRetouchJNI.new_SharpenSettingParam__SWIG_0(), true);
    }
}
