package com.cyberlink.clgpuimage;

import android.graphics.PointF;

import java.util.ArrayList;

public class CLMakeupLiveFilter {

    public static class LiveDynamicRangeMetadata {
        float min_luma = 0.0f;
        float max_luma = 1.0f;
        float min_luma_for_earring = 0.0f;
        float max_luma_for_earring = 1.0f;
        float[] min_rgb = new float[3];
        float[] max_rgb = new float[3];
        float[] left_min_rgb = new float[3];
        float[] left_max_rgb = new float[3];
        float[] right_min_rgb = new float[3];
        float[] right_max_rgb = new float[3];
    }

    public static class LiveFrameInformation {
        public int face_size;
        public ArrayList<PointF> feature_points = new ArrayList<>();
        public boolean is_flipped;
        public boolean is_mask_detected;
        public float left_iris_radius;
        public float right_iris_radius;
        public int rotation;
    }
}
