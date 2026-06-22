package com.pf.makeupcam.camera;

import com.cyberlink.youcammakeup.jniproxy.VN_EyebrowMode;
import proguard.annotation.KeepClassMembers;

/**
 * GL-free carrier copy of the decompiled {@code a0} interface.
 *
 * Purpose: expose the reshape ({@code a0$c}) and earring ({@code a0$b}) parameter
 * fields by exact name/type so a reused native engine's JNI
 * {@code SetMakeupParameters} can resolve them via {@code GetFieldID}.
 *
 * All field names, types and modifiers are copied verbatim from the source.
 * All GL/logic methods have been dropped. The interface declares no abstract
 * methods, so the nested classes implement an empty contract.
 */
public interface a0 {

    public static final class a implements a0 {

        /* renamed from: b, reason: collision with root package name */
        public static final a f61632b = new a();

        /* renamed from: a, reason: collision with root package name */
        public C0671a f61633a = C0671a.f61634n;

        /* renamed from: com.pf.makeupcam.camera.a0$a$a, reason: collision with other inner class name */
        public static final class C0671a {

            /* renamed from: n, reason: collision with root package name */
            public static final C0671a f61634n;

            /* renamed from: o, reason: collision with root package name */
            public static final C0671a f61635o;

            /* renamed from: a, reason: collision with root package name */
            public final VN_EyebrowMode f61636a;

            /* renamed from: b, reason: collision with root package name */
            public final int f61637b;

            /* renamed from: c, reason: collision with root package name */
            public final int f61638c;

            /* renamed from: d, reason: collision with root package name */
            public final int f61639d;

            /* renamed from: e, reason: collision with root package name */
            public final int f61640e;

            /* renamed from: f, reason: collision with root package name */
            public final int f61641f;

            /* renamed from: g, reason: collision with root package name */
            public final int f61642g;

            /* renamed from: h, reason: collision with root package name */
            public final int f61643h;

            /* renamed from: i, reason: collision with root package name */
            public final int f61644i;

            /* renamed from: j, reason: collision with root package name */
            public final int f61645j;

            /* renamed from: k, reason: collision with root package name */
            public final boolean f61646k;

            /* renamed from: l, reason: collision with root package name */
            public final boolean f61647l;

            /* renamed from: m, reason: collision with root package name */
            public final boolean f61648m;

            static {
                VN_EyebrowMode vN_EyebrowMode = VN_EyebrowMode.EYEBROW_ORIGINAL_MODE;
                f61634n = new C0671a(vN_EyebrowMode, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false);
                f61635o = new C0671a(vN_EyebrowMode, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, true);
            }

            public C0671a(VN_EyebrowMode vN_EyebrowMode, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, boolean z11, boolean z12, boolean z13) {
                this.f61636a = vN_EyebrowMode;
                this.f61637b = i11;
                this.f61638c = i12;
                this.f61639d = i13;
                this.f61640e = i14;
                this.f61641f = i15;
                this.f61642g = i16;
                this.f61643h = i17;
                this.f61644i = i18;
                this.f61645j = i19;
                this.f61646k = z11;
                this.f61647l = z12;
                this.f61648m = z13;
            }
        }
    }

    @KeepClassMembers
    public static final class b {

        /* renamed from: x, reason: collision with root package name */
        float f61649x = 0.0f;

        /* renamed from: y, reason: collision with root package name */
        float f61650y = 0.0f;

        /* renamed from: z, reason: collision with root package name */
        float f61651z = 0.0f;
    }

    @KeepClassMembers
    public static final class c implements a0 {
        public static final c NULL = new c();
        int apple_cheeks_intensity;
        int cheekbones_intensity;
        int chin_length_intensity;
        int chin_reshape_intensity;
        int chin_reshape_left_intensity;
        int chin_reshape_right_intensity;
        int enlarge_eye_intensity;
        int enlarge_eye_left_intensity;
        int enlarge_eye_right_intensity;
        int eye_distance_intensity;
        int eye_height_intensity;
        int eye_inner_corner_intensity;
        int eye_inner_corner_left_intensity;
        int eye_inner_corner_right_intensity;
        int eye_outer_corner_intensity;
        int eye_outer_corner_left_intensity;
        int eye_outer_corner_right_intensity;
        int eye_position_intensity;
        int eye_position_left_intensity;
        int eye_position_right_intensity;
        int eye_slant_intensity;
        int eye_tail_intensity;
        int eye_tail_left_intensity;
        int eye_tail_right_intensity;
        int eye_under_intensity;
        int eye_under_left_intensity;
        int eye_under_right_intensity;
        int eye_width_intensity;
        int eyebrow_end_intensity;
        int eyebrow_end_left_intensity;
        int eyebrow_end_right_intensity;
        int eyebrow_front_intensity;
        int eyebrow_front_left_intensity;
        int eyebrow_front_right_intensity;
        int eyebrow_lift_intensity;
        int eyebrow_raise_intensity;
        int eyebrow_slant_intensity;
        int eyebrow_slant_left_intensity;
        int eyebrow_slant_right_intensity;
        int face_reshape_intensity;
        int face_reshape_left_intensity;
        int face_reshape_right_intensity;
        int face_width_intensity;
        int forehead_intensity;
        int head_size_intensity;
        int head_top_intensity;
        boolean is_apple_cheeks_enabled;
        boolean is_cheekbones_enabled;
        boolean is_chin_length_enabled;
        boolean is_chin_reshape_enabled;
        boolean is_enlarge_eye_enabled;
        boolean is_eye_distance_enabled;
        boolean is_eye_height_enabled;
        boolean is_eye_inner_corner_enabled;
        boolean is_eye_outer_corner_enabled;
        boolean is_eye_position_enabled;
        boolean is_eye_slant_enabled;
        boolean is_eye_tail_enabled;
        boolean is_eye_under_enabled;
        boolean is_eye_width_enabled;
        boolean is_eyebrow_end_enabled;
        boolean is_eyebrow_front_enabled;
        boolean is_eyebrow_lift_enabled;
        boolean is_eyebrow_raise_enabled;
        boolean is_eyebrow_slant_enabled;
        boolean is_face_reshape_enabled;
        boolean is_face_width_enabled;
        boolean is_forehead_enabled;
        boolean is_head_size_enabled;
        boolean is_head_top_enabled;
        boolean is_jaw_enabled;
        boolean is_lessen_enabled;
        boolean is_middle_face_enabled;
        boolean is_mouth_height_enabled;
        boolean is_mouth_position_enabled;
        boolean is_mouth_size_enabled;
        boolean is_mouth_width_enabled;
        boolean is_mshaped_lip_enabled;
        boolean is_nose_bridge_width_enabled;
        boolean is_nose_length_enabled;
        boolean is_nose_root_width_enabled;
        boolean is_nose_size_enabled;
        boolean is_nose_tip_enabled;
        boolean is_nose_tip_width_enabled;
        boolean is_nose_wing_enabled;
        boolean is_philtrum_length_enabled;
        boolean is_pointy_chin_enabled;
        boolean is_retouch_lip_plumper_enabled;
        boolean is_shorten_enabled;
        boolean is_temple_enabled;
        boolean is_vshape_face_enabled;
        int jaw_intensity;
        int lessen_intensity;
        int lessen_left_intensity;
        int lessen_right_intensity;
        int middle_face_intensity;
        int mouth_height_intensity;
        int mouth_height_lower_intensity;
        int mouth_height_upper_intensity;
        int mouth_position_intensity;
        int mouth_size_intensity;
        int mouth_width_intensity;
        int mshaped_lip_intensity;
        int nose_bridge_width_intensity;
        int nose_length_intensity;
        int nose_root_width_intensity;
        int nose_size_intensity;
        int nose_tip_intensity;
        int nose_tip_width_intensity;
        int nose_wing_intensity;
        int philtrum_length_intensity;
        int pointy_chin_intensity;
        int pointy_chin_left_intensity;
        int pointy_chin_right_intensity;
        int retouch_lip_plumper_fullness;
        int retouch_lip_plumper_wrinkless;
        int shorten_intensity;
        int temple_intensity;
        int temple_left_intensity;
        int temple_right_intensity;
        int vshape_face_intensity;
        int vshape_face_left_intensity;
        int vshape_face_right_intensity;
    }
}
