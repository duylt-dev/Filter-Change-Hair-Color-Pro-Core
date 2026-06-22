package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UILookParameters {

    /* renamed from: a, reason: collision with root package name */
    public transient long f33010a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f33011b;

    public UILookParameters(long j11, boolean z11) {
        this.f33011b = z11;
        this.f33010a = j11;
    }

    public static long e(UILookParameters uILookParameters) {
        if (uILookParameters == null) {
            return 0L;
        }
        return uILookParameters.f33010a;
    }

    public void A(int i11) {
        UIVenusJNI.UILookParameters_setEyeBrowHiddenIntensity(this.f33010a, this, i11);
    }

    public void B(int i11) {
        UIVenusJNI.UILookParameters_setEyeBrowIntensity(this.f33010a, this, i11);
    }

    public void C(int i11) {
        UIVenusJNI.UILookParameters_setEyeContactsIntensity(this.f33010a, this, i11);
    }

    public void D(int i11) {
        UIVenusJNI.UILookParameters_setEyeLashIntensity(this.f33010a, this, i11);
    }

    public void E(int i11) {
        UIVenusJNI.UILookParameters_setEyeLinerIntensity(this.f33010a, this, i11);
    }

    public void F(int i11) {
        UIVenusJNI.UILookParameters_setEyeShadowCount(this.f33010a, this, i11);
    }

    public void G(UIIntVector uIIntVector) {
        UIVenusJNI.UILookParameters_setEyeShadowIntensity(this.f33010a, this, UIIntVector.d(uIIntVector), uIIntVector);
    }

    public void H(UIIntVector uIIntVector) {
        UIVenusJNI.UILookParameters_setEyeShadowShimmerIntensity(this.f33010a, this, UIIntVector.d(uIIntVector), uIIntVector);
    }

    public void I(int i11) {
        UIVenusJNI.UILookParameters_setFaceContourIntensity(this.f33010a, this, i11);
    }

    public void J(int i11) {
        UIVenusJNI.UILookParameters_setFaceContourPatternCount(this.f33010a, this, i11);
    }

    public void K(UIIntVector uIIntVector) {
        UIVenusJNI.UILookParameters_setFaceContourPatternIntensity(this.f33010a, this, UIIntVector.d(uIIntVector), uIIntVector);
    }

    public void L(int i11) {
        UIVenusJNI.UILookParameters_setHairDyeIntensity(this.f33010a, this, i11);
    }

    public void M(int i11) {
        UIVenusJNI.UILookParameters_setLipStickIntensity(this.f33010a, this, i11);
    }

    public void N(int i11) {
        UIVenusJNI.UILookParameters_setNoseShadowIntensity(this.f33010a, this, i11);
    }

    public void O(int i11) {
        UIVenusJNI.UILookParameters_setSkinSmoothIntensity(this.f33010a, this, i11);
    }

    public void P(int i11) {
        UIVenusJNI.UILookParameters_setSkinToneIntensity(this.f33010a, this, i11);
    }

    public void Q(int i11) {
        UIVenusJNI.UILookParameters_setSparkleEyeIntensity(this.f33010a, this, i11);
    }

    public synchronized void a() {
        try {
            long j11 = this.f33010a;
            if (j11 != 0) {
                if (this.f33011b) {
                    this.f33011b = false;
                    UIVenusJNI.delete_UILookParameters(j11);
                }
                this.f33010a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void b() {
        UIVenusJNI.UILookParameters_dumpDebugString(this.f33010a, this);
    }

    public int c() {
        return UIVenusJNI.UILookParameters_getAntiShineIntensity(this.f33010a, this);
    }

    public int d() {
        return UIVenusJNI.UILookParameters_getBlushIntensity(this.f33010a, this);
    }

    public int f() {
        return UIVenusJNI.UILookParameters_getDoubleEyelidsIntensity(this.f33010a, this);
    }

    public void finalize() {
        a();
    }

    public int g() {
        return UIVenusJNI.UILookParameters_getEyeBrowHiddenIntensity(this.f33010a, this);
    }

    public int h() {
        return UIVenusJNI.UILookParameters_getEyeBrowIntensity(this.f33010a, this);
    }

    public int i() {
        return UIVenusJNI.UILookParameters_getEyeContactsIntensity(this.f33010a, this);
    }

    public int j() {
        return UIVenusJNI.UILookParameters_getEyeLashIntensity(this.f33010a, this);
    }

    public int k() {
        return UIVenusJNI.UILookParameters_getEyeLinerIntensity(this.f33010a, this);
    }

    public int l() {
        return UIVenusJNI.UILookParameters_getEyeShadowCount(this.f33010a, this);
    }

    public void m(UIIntVector uIIntVector) {
        UIVenusJNI.UILookParameters_getEyeShadowIntensity(this.f33010a, this, UIIntVector.d(uIIntVector), uIIntVector);
    }

    public void n(UIIntVector uIIntVector) {
        UIVenusJNI.UILookParameters_getEyeShadowShimmerIntensity(this.f33010a, this, UIIntVector.d(uIIntVector), uIIntVector);
    }

    public int o() {
        return UIVenusJNI.UILookParameters_getFaceContourIntensity(this.f33010a, this);
    }

    public int p() {
        return UIVenusJNI.UILookParameters_getFaceContourPatternCount(this.f33010a, this);
    }

    public void q(UIIntVector uIIntVector) {
        UIVenusJNI.UILookParameters_getFaceContourPatternIntensity(this.f33010a, this, UIIntVector.d(uIIntVector), uIIntVector);
    }

    public int r() {
        return UIVenusJNI.UILookParameters_getHairDyeIntensity(this.f33010a, this);
    }

    public int s() {
        return UIVenusJNI.UILookParameters_getLipStickIntensity(this.f33010a, this);
    }

    public int t() {
        return UIVenusJNI.UILookParameters_getNoseShadowIntensity(this.f33010a, this);
    }

    public int u() {
        return UIVenusJNI.UILookParameters_getSkinSmoothIntensity(this.f33010a, this);
    }

    public int v() {
        return UIVenusJNI.UILookParameters_getSkinToneIntensity(this.f33010a, this);
    }

    public int w() {
        return UIVenusJNI.UILookParameters_getSparkleEyeIntensity(this.f33010a, this);
    }

    public void x(int i11) {
        UIVenusJNI.UILookParameters_setAntiShineIntensity(this.f33010a, this, i11);
    }

    public void y(int i11) {
        UIVenusJNI.UILookParameters_setBlushIntensity(this.f33010a, this, i11);
    }

    public void z(int i11) {
        UIVenusJNI.UILookParameters_setDoubleEyelidsIntensity(this.f33010a, this, i11);
    }

    public UILookParameters() {
        this(UIVenusJNI.new_UILookParameters__SWIG_0(), true);
    }
}
