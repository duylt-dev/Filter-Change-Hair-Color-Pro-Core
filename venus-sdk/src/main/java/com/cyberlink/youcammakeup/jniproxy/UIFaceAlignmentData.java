package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class UIFaceAlignmentData {

    /* renamed from: a, reason: collision with root package name */
    public transient long f32918a;

    /* renamed from: b, reason: collision with root package name */
    public transient boolean f32919b;

    public UIFaceAlignmentData(long j11, boolean z11) {
        this.f32919b = z11;
        this.f32918a = j11;
    }

    public static long c(UIFaceAlignmentData uIFaceAlignmentData) {
        if (uIFaceAlignmentData == null) {
            return 0L;
        }
        return uIFaceAlignmentData.f32918a;
    }

    public void A(UIFaceShape uIFaceShape) {
        UIVenusJNI.UIFaceAlignmentData_setRightShape(this.f32918a, this, UIFaceShape.b(uIFaceShape), uIFaceShape);
    }

    public synchronized void a() {
        try {
            long j11 = this.f32918a;
            if (j11 != 0) {
                if (this.f32919b) {
                    this.f32919b = false;
                    UIVenusJNI.delete_UIFaceAlignmentData(j11);
                }
                this.f32918a = 0L;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public boolean b(UIFaceAlignmentData uIFaceAlignmentData) {
        return UIVenusJNI.UIFaceAlignmentData_equals(this.f32918a, this, c(uIFaceAlignmentData), uIFaceAlignmentData);
    }

    public UIFaceChin d() {
        return new UIFaceChin(UIVenusJNI.UIFaceAlignmentData_getChin(this.f32918a, this), true);
    }

    public UIFaceForehead e() {
        return new UIFaceForehead(UIVenusJNI.UIFaceAlignmentData_getForehead(this.f32918a, this), true);
    }

    public UIFaceBrow f() {
        return new UIFaceBrow(UIVenusJNI.UIFaceAlignmentData_getLeftBrow(this.f32918a, this), true);
    }

    public void finalize() {
        a();
    }

    public UIFaceEar g() {
        return new UIFaceEar(UIVenusJNI.UIFaceAlignmentData_getLeftEar(this.f32918a, this), true);
    }

    public UIFaceEye h() {
        return new UIFaceEye(UIVenusJNI.UIFaceAlignmentData_getLeftEye(this.f32918a, this), true);
    }

    public UIFaceShape i() {
        return new UIFaceShape(UIVenusJNI.UIFaceAlignmentData_getLeftShape(this.f32918a, this), true);
    }

    public UIFaceMouth j() {
        return new UIFaceMouth(UIVenusJNI.UIFaceAlignmentData_getMouth(this.f32918a, this), true);
    }

    public UIFaceNose k() {
        return new UIFaceNose(UIVenusJNI.UIFaceAlignmentData_getNose(this.f32918a, this), true);
    }

    public UIFaceBrow l() {
        return new UIFaceBrow(UIVenusJNI.UIFaceAlignmentData_getRightBrow(this.f32918a, this), true);
    }

    public UIFaceEar m() {
        return new UIFaceEar(UIVenusJNI.UIFaceAlignmentData_getRightEar(this.f32918a, this), true);
    }

    public UIFaceEye n() {
        return new UIFaceEye(UIVenusJNI.UIFaceAlignmentData_getRightEye(this.f32918a, this), true);
    }

    public UIFaceShape o() {
        return new UIFaceShape(UIVenusJNI.UIFaceAlignmentData_getRightShape(this.f32918a, this), true);
    }

    public void p(UIFaceChin uIFaceChin) {
        UIVenusJNI.UIFaceAlignmentData_setChin(this.f32918a, this, UIFaceChin.b(uIFaceChin), uIFaceChin);
    }

    public void q(UIFaceForehead uIFaceForehead) {
        UIVenusJNI.UIFaceAlignmentData_setForehead(this.f32918a, this, UIFaceForehead.b(uIFaceForehead), uIFaceForehead);
    }

    public void r(UIFaceBrow uIFaceBrow) {
        UIVenusJNI.UIFaceAlignmentData_setLeftBrow(this.f32918a, this, UIFaceBrow.c(uIFaceBrow), uIFaceBrow);
    }

    public void s(UIFaceEar uIFaceEar) {
        UIVenusJNI.UIFaceAlignmentData_setLeftEar(this.f32918a, this, UIFaceEar.c(uIFaceEar), uIFaceEar);
    }

    public void t(UIFaceEye uIFaceEye) {
        UIVenusJNI.UIFaceAlignmentData_setLeftEye(this.f32918a, this, UIFaceEye.c(uIFaceEye), uIFaceEye);
    }

    public void u(UIFaceShape uIFaceShape) {
        UIVenusJNI.UIFaceAlignmentData_setLeftShape(this.f32918a, this, UIFaceShape.b(uIFaceShape), uIFaceShape);
    }

    public void v(UIFaceMouth uIFaceMouth) {
        UIVenusJNI.UIFaceAlignmentData_setMouth(this.f32918a, this, UIFaceMouth.d(uIFaceMouth), uIFaceMouth);
    }

    public void w(UIFaceNose uIFaceNose) {
        UIVenusJNI.UIFaceAlignmentData_setNose(this.f32918a, this, UIFaceNose.d(uIFaceNose), uIFaceNose);
    }

    public void x(UIFaceBrow uIFaceBrow) {
        UIVenusJNI.UIFaceAlignmentData_setRightBrow(this.f32918a, this, UIFaceBrow.c(uIFaceBrow), uIFaceBrow);
    }

    public void y(UIFaceEar uIFaceEar) {
        UIVenusJNI.UIFaceAlignmentData_setRightEar(this.f32918a, this, UIFaceEar.c(uIFaceEar), uIFaceEar);
    }

    public void z(UIFaceEye uIFaceEye) {
        UIVenusJNI.UIFaceAlignmentData_setRightEye(this.f32918a, this, UIFaceEye.c(uIFaceEye), uIFaceEye);
    }

    public UIFaceAlignmentData() {
        this(UIVenusJNI.new_UIFaceAlignmentData__SWIG_0(), true);
    }

    public UIFaceAlignmentData(UIFaceAlignmentData uIFaceAlignmentData) {
        this(UIVenusJNI.new_UIFaceAlignmentData__SWIG_1(c(uIFaceAlignmentData), uIFaceAlignmentData), true);
    }
}
