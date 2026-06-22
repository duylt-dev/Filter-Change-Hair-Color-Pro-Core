package com.cyberlink.clgpuimage;

public class CLMakeupLiveObject3DWarpFilter {
    public static class LiveObject3DWarpMetadata {
        public boolean is_flip;
        public int rotation;
        public boolean is_valid = false;
        public float[] transform_matrix = new float[6];
        public float[] inv_lrt_axis = new float[3];
        public float[] src_center_xy = new float[2];
    }
}
