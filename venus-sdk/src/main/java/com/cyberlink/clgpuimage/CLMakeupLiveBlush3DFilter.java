package com.cyberlink.clgpuimage;

public class CLMakeupLiveBlush3DFilter {
    public static class LiveBlush3DMetadata {
        public boolean is_flip;
        public int rotation;
        public boolean is_valid = false;
        public int num_triangles = 0;
        public float[] vertex_array = null;
        public float[] normal_array = null;
        public float[] texcoord_array = null;
        public boolean is_texcoord_changed = true;
        public float[] camera_matrix = new float[9];
        public float[] pnp_matrix = new float[12];
        public float[] normal_pnp_matrix = new float[16];
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
