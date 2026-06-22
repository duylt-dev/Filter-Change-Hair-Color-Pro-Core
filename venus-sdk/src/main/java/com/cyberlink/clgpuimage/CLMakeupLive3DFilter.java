package com.cyberlink.clgpuimage;

import java.lang.reflect.Array;

public class CLMakeupLive3DFilter {
    public static class LiveObject3DMetadata {
        public boolean is_flip;
        public int rotation;
        public boolean is_valid = false;
        public float[] pose_parameters = new float[6];
        public float[] pnp_matrices = new float[36];
        public float[] camera_matrix = new float[9];
        public float[] normal_pnp_matrices = new float[48];
        public float bloom_iir_strength = 0.0f;
    }

    public static class LiveEarringMetadata {
        public float bloom_iir_strength;
        public float[] camera_matrix;
        public int component_count;
        public boolean is_flip;
        public boolean is_valid;
        public float left_earbottom_y;
        public int[] matrix_counts;
        public float[][][] normal_pnp_matrices;
        public float[] occluder_camera_matrix;
        public float[] occluder_normal_pnp_matrix;
        public float[] occluder_pnp_matrix;
        public int occluder_triangle_count;
        public float[] p_occluder_normals;
        public float[] p_occluder_vertices;
        public float[][][] pnp_matrices;
        public float right_earbottom_y;
        public int rotation;
        public LiveEarringMetadata() {
                    Class cls = Float.TYPE;
                    this.pnp_matrices = (float[][][]) Array.newInstance((Class<?>) cls, 20, 101, 12);
                    this.normal_pnp_matrices = (float[][][]) Array.newInstance((Class<?>) cls, 20, 101, 16);
                    this.matrix_counts = new int[20];
                    this.is_valid = false;
                    this.camera_matrix = new float[9];
                    this.component_count = 0;
                    this.occluder_camera_matrix = new float[9];
                    this.occluder_pnp_matrix = new float[12];
                    this.occluder_normal_pnp_matrix = new float[16];
                    this.occluder_triangle_count = 0;
                    this.p_occluder_vertices = null;
                    this.p_occluder_normals = null;
                    this.left_earbottom_y = -1.0f;
                    this.right_earbottom_y = -1.0f;
                    this.bloom_iir_strength = 0.0f;
                }
        public void AllocArray(int i11) {
                    if (i11 <= 0) {
                        this.occluder_triangle_count = 0;
                        this.p_occluder_vertices = null;
                        this.p_occluder_normals = null;
                    } else if (this.occluder_triangle_count != i11) {
                        this.occluder_triangle_count = i11;
                        this.p_occluder_vertices = new float[i11 * 9];
                        this.p_occluder_normals = new float[i11 * 9];
                    }
                }
    }
}
