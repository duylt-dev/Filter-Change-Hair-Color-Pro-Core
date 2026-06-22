package com.cyberlink.clgpuimage;

public class CLMakeupLive3DEyebrowFilter {
    public enum EyebrowMode {
        EYEBROW_ORIGINAL_MODE,
        EYEBROW_2D_STYLE_MODE,
        EYEBROW_ART_DESIGN_MODE
    }

    public static class LiveEyebrow3DMetadata {
        public boolean is_valid = false;
        public int rotation = 90;
        public boolean is_flip = false;
        public int num_triangles = 0;
        public float[] vertex_array = null;
        public float[] normal_array = null;
        public float[] texcoord_array = null;
        public boolean is_texcoord_changed = true;
        public float[] camera_matrix = new float[9];
        public float[] pnp_matrix = new float[12];
        public float[] normal_pnp_matrix = new float[16];
        public int intensity = 0;
        public EyebrowMode eyebrow_mode = EyebrowMode.EYEBROW_ORIGINAL_MODE;
        public int[] left_brow_color = new int[3];
        public int[] right_brow_color = new int[3];
        public float[] left_skin_color_luma = new float[6];
        public float[] right_skin_color_luma = new float[6];
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
