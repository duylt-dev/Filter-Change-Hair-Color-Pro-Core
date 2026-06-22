package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class SaturationSettingParam extends IParamBase {

    /* renamed from: c, reason: collision with root package name */
    public transient long f32883c;

    public SaturationSettingParam(long j11, boolean z11) {
        super(UIImageRetouchJNI.SaturationSettingParam_SWIGUpcast(j11), z11);
        this.f32883c = j11;
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public boolean a(IParamBase iParamBase) {
        return UIImageRetouchJNI.SaturationSettingParam_Compare(this.f32883c, this, IParamBase.d(iParamBase), iParamBase);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public String b() {
        return UIImageRetouchJNI.SaturationSettingParam_EncodeString(this.f32883c, this);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public synchronized void c() {
        try {
            long j11 = this.f32883c;
            if (j11 != 0) {
                if (this.f32871b) {
                    this.f32871b = false;
                    UIImageRetouchJNI.delete_SaturationSettingParam(j11);
                }
                this.f32883c = 0L;
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

    public SaturationSettingParam() {
        this(UIImageRetouchJNI.new_SaturationSettingParam__SWIG_0(), true);
    }
}
