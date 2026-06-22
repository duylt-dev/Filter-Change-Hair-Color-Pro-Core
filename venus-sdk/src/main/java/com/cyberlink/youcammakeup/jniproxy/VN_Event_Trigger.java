package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public enum VN_Event_Trigger {
    EVENT_TRIGGER_NONE(0),
    EVENT_TRIGGER_BY_MOUTH_OPEN,
    EVENT_TRIGGER_BY_EYE_BLINK,
    EVENT_TRIGGER_COUNT;

    private final int swigValue;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static int f33072a;

        public static /* synthetic */ int b() {
            int i11 = f33072a;
            f33072a = i11 + 1;
            return i11;
        }
    }

    VN_Event_Trigger() {
        this.swigValue = a.b();
    }

    public static VN_Event_Trigger c(int i11) {
        VN_Event_Trigger[] vN_Event_TriggerArr = (VN_Event_Trigger[]) VN_Event_Trigger.class.getEnumConstants();
        if (i11 < vN_Event_TriggerArr.length && i11 >= 0) {
            VN_Event_Trigger vN_Event_Trigger = vN_Event_TriggerArr[i11];
            if (vN_Event_Trigger.swigValue == i11) {
                return vN_Event_Trigger;
            }
        }
        for (VN_Event_Trigger vN_Event_Trigger2 : vN_Event_TriggerArr) {
            if (vN_Event_Trigger2.swigValue == i11) {
                return vN_Event_Trigger2;
            }
        }
        throw new IllegalArgumentException("No enum " + VN_Event_Trigger.class + " with value " + i11);
    }

    public final int f() {
        return this.swigValue;
    }

    VN_Event_Trigger(int i11) {
        this.swigValue = i11;
        int unused = a.f33072a = i11 + 1;
    }
}
