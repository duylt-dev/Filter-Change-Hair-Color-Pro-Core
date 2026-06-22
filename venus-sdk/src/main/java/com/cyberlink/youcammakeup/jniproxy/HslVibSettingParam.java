package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class HslVibSettingParam extends IParamBase {

    /* renamed from: c, reason: collision with root package name */
    public transient long f32866c;

    public HslVibSettingParam(long j11, boolean z11) {
        super(UIImageRetouchJNI.HslVibSettingParam_SWIGUpcast(j11), z11);
        this.f32866c = j11;
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public boolean a(IParamBase iParamBase) {
        return UIImageRetouchJNI.HslVibSettingParam_Compare(this.f32866c, this, IParamBase.d(iParamBase), iParamBase);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public String b() {
        return UIImageRetouchJNI.HslVibSettingParam_EncodeString(this.f32866c, this);
    }

    @Override // com.cyberlink.youcammakeup.jniproxy.IParamBase
    public synchronized void c() {
        try {
            long j11 = this.f32866c;
            if (j11 != 0) {
                if (this.f32871b) {
                    this.f32871b = false;
                    UIImageRetouchJNI.delete_HslVibSettingParam(j11);
                }
                this.f32866c = 0L;
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

    public HslVibSettingParam() {
        this(UIImageRetouchJNI.new_HslVibSettingParam__SWIG_0(), true);
    }
}
