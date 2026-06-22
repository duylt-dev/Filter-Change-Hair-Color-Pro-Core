package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum VN_Event_React {
    EVENT_REACT_NONE(0),
    EVENT_REACT_RESET_LOOK,
    EVENT_REACT_SHIFT_LOOK,
    EVENT_REACT_COUNT;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f33066a;

        public static /* synthetic */ int b() {
            int i11 = f33066a;
            f33066a = i11 + 1;
            return i11;
        }
    }

    VN_Event_React() {
        this.swigValue = a.b();
    }

    public static VN_Event_React c(int i11) {
        VN_Event_React[] vN_Event_ReactArr = (VN_Event_React[]) VN_Event_React.class.getEnumConstants();
        if (i11 < vN_Event_ReactArr.length && i11 >= 0) {
            VN_Event_React vN_Event_React = vN_Event_ReactArr[i11];
            if (vN_Event_React.swigValue == i11) {
                return vN_Event_React;
            }
        }
        for (VN_Event_React vN_Event_React2 : vN_Event_ReactArr) {
            if (vN_Event_React2.swigValue == i11) {
                return vN_Event_React2;
            }
        }
        throw new IllegalArgumentException("No enum " + VN_Event_React.class + " with value " + i11);
    }

    public final int f() {
        return this.swigValue;
    }

    VN_Event_React(int i11) {
        this.swigValue = i11;
        int unused = a.f33066a = i11 + 1;
    }
}
