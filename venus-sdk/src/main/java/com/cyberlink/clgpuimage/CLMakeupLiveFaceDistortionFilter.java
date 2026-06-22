package com.cyberlink.clgpuimage;

public class CLMakeupLiveFaceDistortionFilter {
    public static class LiveFaceDistortionMetadata {
        public boolean is_flip;
        public int rotation;
        public int view_frame_height;
        public int view_frame_width;
        public float warp_value_factor;
        public boolean is_valid = false;
        public float[] gpu_float_factors = new float[4];
        public float[] transform_factors = new float[30];
        public float[] hori_trans_factors = new float[3];
        public float[] hori_offsets = new float[6];
        public float[] boundary_factors = new float[6];
    }
}
