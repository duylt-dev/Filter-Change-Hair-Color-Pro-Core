package com.cyberlink.clgpuimage;

import android.graphics.PointF;

public class CLMakeupLiveEyeContactFilter {
    public static class EyeContactInfo {
        public float catchlight_radius;
        public float catchlight_threshold;
        public float[] contour_shrink_ranges;
        public float iris_radius;
        public float iris_radius_in_image;
        public float[] contour_transform_factors = new float[4];
        public PointF[] contour_transformed_points = new PointF[3];
        public EyeContactInfo() {
                    for (int i11 = 0; i11 < 3; i11++) {
                        this.contour_transformed_points[i11] = new PointF();
                    }
                    this.contour_shrink_ranges = new float[2];
                }
    }

    public static class LiveEyeContactMetadata {
        public int m_analyzing_frame_height;
        public int m_analyzing_frame_width;
        public boolean m_is_flipped;
        public boolean m_is_valid;
        public float m_oval_scale;
        public float m_rotate_degree;
        public int m_rotation;
    }
}
