package com.cyberlink.clgpuimage;

public class CLMakeupLiveFaceArtFilter {
    public static class LiveFaceArtMetadata {
        public boolean is_flip;
        public int rotation;
        public boolean is_valid = false;
        public boolean is_layer2_valid = false;
        public int num_triangles = 0;
        public float[] vertex_array = null;
        public float[] normal_array = null;
        public float[] texcoord_array = null;
        public float[] line_vertex_array = null;
        public int line_count = 0;
        public boolean is_texcoord_changed = true;
        public boolean is_layer2_texcoord_changed = true;
        public float[] camera_matrix = new float[9];
        public float[] pnp_matrix = new float[12];
        public float[] normal_pnp_matrix = new float[16];
        public boolean is_blend_data_valid = false;
        public float[] left_ear_hori_line = new float[4];
        public float[] left_ear_vert_line = new float[4];
        public float[] left_ear_location_factors = new float[4];
        public float[] right_ear_hori_line = new float[4];
        public float[] right_ear_vert_line = new float[4];
        public float[] right_ear_location_factors = new float[4];
        public void AllocArray(int i11) {
                    if (i11 <= 0) {
                        this.num_triangles = 0;
                        this.vertex_array = null;
                        this.normal_array = null;
                        this.texcoord_array = null;
                        this.line_vertex_array = null;
                        this.line_count = 0;
                        return;
                    }
                    if (this.num_triangles != i11) {
                        this.num_triangles = i11;
                        this.vertex_array = new float[i11 * 9];
                        this.normal_array = new float[i11 * 9];
                        this.texcoord_array = new float[i11 * 6];
                        this.line_vertex_array = new float[i11 * 18];
                        this.line_count = 0;
                    }
                }
    }
}
