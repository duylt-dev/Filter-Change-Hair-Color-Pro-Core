package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class WBSettingParam extends IParamBase {

    /* renamed from: c, reason: collision with root package name */
    public transient long f33115c;

    public WBSettingParam(long j11, boolean z11) {
        super(UIImageRetouchJNI.WBSettingParam_SWIGUpcast(j11), z11);
        this.f33115c = j11;
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public boolean a(IParamBase iParamBase) {
        return UIImageRetouchJNI.WBSettingParam_Compare(this.f33115c, this, IParamBase.d(iParamBase), iParamBase);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public String b() {
        return UIImageRetouchJNI.WBSettingParam_EncodeString(this.f33115c, this);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public synchronized void c() {
        try {
            long j11 = this.f33115c;
            if (j11 != 0) {
                if (this.f32871b) {
                    this.f32871b = false;
                    UIImageRetouchJNI.delete_WBSettingParam(j11);
                }
                this.f33115c = 0L;
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

    public WBSettingParam() {
        this(UIImageRetouchJNI.new_WBSettingParam__SWIG_0(), true);
    }
}
