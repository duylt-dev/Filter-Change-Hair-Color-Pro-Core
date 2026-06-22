package com.cyberlink.clgpuimage;

public class CLMakeupLive3DEyebrowWarpFilter {
    public static class LiveEyebrowWarp3DMetadata {
        public boolean is_valid = false;
        public int rotation = 90;
        public boolean is_flip = false;
        public int num_triangles = 0;
        public float[] vertex_array = null;
        public float[] normal_array = null;
        public float[] texcoord_array = null;
        public float[] camera_matrix = new float[9];
        public float[] pnp_matrix = new float[12];
        public float[] normal_pnp_matrix = new float[16];
        public int definition = 0;
        public int curvature = 0;
        public float[] sample_vector = new float[2];
        public float[] sample_vector_horizontal = new float[2];
        public float[] rotation_degree = new float[3];
        public float[] left_upper_transform_factors = new float[6];
        public float[] left_upper_parabola_side_coords = new float[4];
        public float[] left_upper_parabola_inter_factors = new float[2];
        public float[] left_lower_transform_factors = new float[6];
        public float[] left_lower_parabola_side_coords = new float[4];
        public float[] left_lower_parabola_inter_factors = new float[2];
        public float[] right_upper_transform_factors = new float[6];
        public float[] right_upper_parabola_side_coords = new float[4];
        public float[] right_upper_parabola_inter_factors = new float[2];
        public float[] right_lower_transform_factors = new float[6];
        public float[] right_lower_parabola_side_coords = new float[4];
        public float[] right_lower_parabola_inter_factors = new float[2];
        public float[] left_right_brow_thickness = new float[2];
        public float src_brow_thickness_factor = 0.0f;
        public float left_brow_lower_clear_strength = 0.0f;
        public float right_brow_lower_clear_strength = 0.0f;
        public int frontal_warp_table_width = 0;
        public int frontal_warp_table_height = 0;
        public float[] camera_matrix_pose_scale = new float[2];
        public int frontal_subsample_warp_table_width = 0;
        public int frontal_subsample_warp_table_height = 0;
        public int max_step_amount = 0;
        public float[] warp_transform_factors = new float[6];
        public float[] smooth_left_right_bound = new float[2];
        public void AllocArray(int i11) {
                    if (i11 <= 0) {
                        this.num_triangles = 0;
                        this.vertex_array = null;
                        this.normal_array = null;
                        this.texcoord_array = null;
                        return;
                    }
                    if (this.num_triangles != i11) {
                        this.num_triangles = i11;
                        this.vertex_array = new float[i11 * 9];
                        this.normal_array = new float[i11 * 9];
                        this.texcoord_array = new float[i11 * 6];
                    }
                }
    }
}
