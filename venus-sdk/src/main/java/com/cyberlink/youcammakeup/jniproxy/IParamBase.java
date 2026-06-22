package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class IParamBase {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32870a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32871b;

    public IParamBase(long j11, boolean z11) {
        this.f32871b = z11;
        this.f32870a = j11;
    }

    public static long d(IParamBase iParamBase) {
        if (iParamBase == null) {
            return 0L;
        }
        return iParamBase.f32870a;
    }

    public boolean a(IParamBase iParamBase) {
        return UIImageRetouchJNI.IParamBase_Compare(this.f32870a, this, d(iParamBase), iParamBase);
    }

    public String b() {
        return UIImageRetouchJNI.IParamBase_EncodeString(this.f32870a, this);
    }

    public synchronized void c() {
        try {
            long j11 = this.f32870a;
            if (j11 != 0) {
                if (this.f32871b) {
                    this.f32871b = false;
                    UIImageRetouchJNI.delete_IParamBase(j11);
                }
                this.f32870a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void finalize() {
        c();
    }
}
