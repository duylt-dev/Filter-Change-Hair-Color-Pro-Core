package com.cyberlink.clgpuimage;

import android.graphics.PointF;

public class CLMakeupLiveEyeFilter {

    public static class LiveEyeMakeupMetadata {
        int m_analyzing_frame_height;
        int m_analyzing_frame_width;
        public float m_ear_line_distance_limit;
        public float m_ear_line_equation_a;
        public float m_ear_line_equation_b;
        public float m_ear_line_equation_c;
        PointF[] m_eye_points = new PointF[4];
        public float m_eye_scale_left_to_right;
        public boolean m_is_flipped;
        public EyeMode m_mode;
        PointF[] m_oriented_eye_centers;
        PointF[] m_oriented_eye_points;
        float[] m_parabolic_polar_transform_bottom_left_dst_aligned_coeff;
        PointF m_parabolic_polar_transform_bottom_left_dst_center;
        float[] m_parabolic_polar_transform_bottom_left_src_aligned_coeff;
        PointF m_parabolic_polar_transform_bottom_left_src_center;
        float[] m_parabolic_polar_transform_bottom_right_dst_aligned_coeff;
        PointF m_parabolic_polar_transform_bottom_right_dst_center;
        float[] m_parabolic_polar_transform_bottom_right_src_aligned_coeff;
        PointF m_parabolic_polar_transform_bottom_right_src_center;
        float[] m_parabolic_polar_transform_top_left_dst_aligned_coeff;
        PointF m_parabolic_polar_transform_top_left_dst_center;
        float[] m_parabolic_polar_transform_top_left_src_aligned_coeff;
        PointF m_parabolic_polar_transform_top_left_src_center;
        float[] m_parabolic_polar_transform_top_right_dst_aligned_coeff;
        PointF m_parabolic_polar_transform_top_right_dst_center;
        float[] m_parabolic_polar_transform_top_right_src_aligned_coeff;
        PointF m_parabolic_polar_transform_top_right_src_center;
        public float m_ratio_of_actual_lower_lid_height_to_limited_height;
        public float m_ratio_of_actual_upper_lid_height_to_limited_height;
        public int m_rotation;
        float m_target_eye_lower_lid_luma;
        float m_target_level_orientation_cos;
        float m_target_level_orientation_sin;

        public enum EyeMode {
            NORMAL,
            BLINK
        }

        // Native (GetMakeupMetadata) does GetObjectClass on these geometry fields for EVERY
        // detected face (independent of eye-makeup being on), so they MUST be non-null.
        // Ported verbatim from the original CLMakeupLiveEyeFilter$LiveEyeMakeupMetadata ctor.
        public LiveEyeMakeupMetadata() {
            for (int i11 = 0; i11 < 4; i11++) {
                this.m_eye_points[i11] = new PointF();
            }
            this.m_oriented_eye_points = new PointF[4];
            for (int i12 = 0; i12 < 4; i12++) {
                this.m_oriented_eye_points[i12] = new PointF();
            }
            this.m_oriented_eye_centers = new PointF[2];
            for (int i13 = 0; i13 < 2; i13++) {
                this.m_oriented_eye_centers[i13] = new PointF();
            }
            this.m_parabolic_polar_transform_top_left_src_center = new PointF();
            this.m_parabolic_polar_transform_top_left_dst_center = new PointF();
            this.m_parabolic_polar_transform_top_left_src_aligned_coeff = new float[2];
            this.m_parabolic_polar_transform_top_left_dst_aligned_coeff = new float[2];
            this.m_parabolic_polar_transform_top_right_src_center = new PointF();
            this.m_parabolic_polar_transform_top_right_dst_center = new PointF();
            this.m_parabolic_polar_transform_top_right_src_aligned_coeff = new float[2];
            this.m_parabolic_polar_transform_top_right_dst_aligned_coeff = new float[2];
            this.m_parabolic_polar_transform_bottom_left_src_center = new PointF();
            this.m_parabolic_polar_transform_bottom_left_dst_center = new PointF();
            this.m_parabolic_polar_transform_bottom_left_src_aligned_coeff = new float[2];
            this.m_parabolic_polar_transform_bottom_left_dst_aligned_coeff = new float[2];
            this.m_parabolic_polar_transform_bottom_right_src_center = new PointF();
            this.m_parabolic_polar_transform_bottom_right_dst_center = new PointF();
            this.m_parabolic_polar_transform_bottom_right_src_aligned_coeff = new float[2];
            this.m_parabolic_polar_transform_bottom_right_dst_aligned_coeff = new float[2];
            this.m_eye_scale_left_to_right = 1.0f;
        }
    }
}
