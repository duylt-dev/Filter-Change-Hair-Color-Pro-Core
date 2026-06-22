package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class ToneSettingParam extends IParamBase {

    /* renamed from: c, reason: collision with root package name */
    public transient long f32894c;

    public ToneSettingParam(long j11, boolean z11) {
        super(UIImageRetouchJNI.ToneSettingParam_SWIGUpcast(j11), z11);
        this.f32894c = j11;
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public boolean a(IParamBase iParamBase) {
        return UIImageRetouchJNI.ToneSettingParam_Compare(this.f32894c, this, IParamBase.d(iParamBase), iParamBase);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public String b() {
        return UIImageRetouchJNI.ToneSettingParam_EncodeString(this.f32894c, this);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public synchronized void c() {
        try {
            long j11 = this.f32894c;
            if (j11 != 0) {
                if (this.f32871b) {
                    this.f32871b = false;
                    UIImageRetouchJNI.delete_ToneSettingParam(j11);
                }
                this.f32894c = 0L;
            }
            super.c();
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void e(String str) {
        UIImageRetouchJNI.ToneSettingParam_DecodeString(this.f32894c, this, str);
    }

    public void f(IParamBase iParamBase) {
        UIImageRetouchJNI.ToneSettingParam_InitFrom(this.f32894c, this, IParamBase.d(iParamBase), iParamBase);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public void finalize() {
        c();
    }

    public ToneSettingParam() {
        this(UIImageRetouchJNI.new_ToneSettingParam__SWIG_0(), true);
    }
}
