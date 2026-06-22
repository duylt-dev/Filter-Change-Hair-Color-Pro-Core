package com.cyberlink.clgpuimage;

public class CLMakeupLiveCubeEyewearFilter {
    public static class LiveCubeEyewearMetaData {
        public boolean is_flip;
        public float pd_scale;
        public int rotation;
        public boolean is_valid = false;
        public float[] pose_parameters = new float[6];
        public float[] pnp_matrices = new float[12];
        public float[] camera_matrix = new float[9];
        public float[] normal_pnp_matrices = new float[16];
    }
}
