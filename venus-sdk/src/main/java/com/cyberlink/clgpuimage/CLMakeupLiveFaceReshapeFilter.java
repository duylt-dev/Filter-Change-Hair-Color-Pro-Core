package com.cyberlink.clgpuimage;

public class CLMakeupLiveFaceReshapeFilter {
    public static class LiveFaceReshapeMetadata {
        public boolean is_flip;
        public byte[] out_of_face_mask;
        public int rotation;
        public int view_frame_height;
        public int view_frame_width;
        public byte[] warp_table;
        public byte[] warp_table_for_head_size;
        public float warp_value_factor;
        public boolean is_valid = false;
        int view_frame_roi_width = 0;
        int view_frame_roi_height = 0;
        int view_frame_roi_start_x = 0;
        int view_frame_roi_start_y = 0;
        int view_frame_roi_width_for_head_size = 0;
        int view_frame_roi_height_for_head_size = 0;
        int view_frame_roi_start_x_for_head_size = 0;
        int view_frame_roi_start_y_for_head_size = 0;
        public int table_width = 0;
        public int table_height = 0;
        int table_width_for_head_size = 0;
        int table_height_for_head_size = 0;
        public int num_triangles = 0;
        public float[] vertex_array = null;
        public float[] normal_array = null;
        public float[] texcoord_array = null;
        public boolean is_texcoord_changed = true;
        public float[] camera_matrix = new float[9];
        public float[] pnp_matrix = new float[12];
        public float[] normal_pnp_matrix = new float[16];
        byte[] template_data = null;
        public int template_width = 0;
        public int template_height = 0;
        int retouch_lip_plumper_fullness = 0;
        int retouch_lip_plumper_wrinkless = 0;
        int retouch_lip_plumper_kernel_size = 0;
        int view_frame_mask_width = 0;
        int view_frame_mask_height = 0;
        int view_frame_mask_start_x = 0;
        int view_frame_mask_start_y = 0;
        int mask_width = 0;
        int mask_height = 0;
        int mask_stride = 0;
        public boolean is_large_scale_warping_enabled = false;
        public void AllocateArray(int i11, int i12) {
                    if (this.table_width == i11 && this.table_height == i12) {
                        return;
                    }
                    this.table_width = i11;
                    this.table_height = i12;
                    this.warp_table = new byte[i11 * i12 * 4];
                }
        public void AllocateArrayFaceMesh(int i11) {
                    this.num_triangles = i11;
                    this.vertex_array = new float[i11 * 9];
                    this.normal_array = new float[i11 * 9];
                    this.texcoord_array = new float[i11 * 6];
                }
        public void AllocateArrayForHeadSize(int i11, int i12) {
                    if (this.table_width_for_head_size == i11 && this.table_height_for_head_size == i12) {
                        return;
                    }
                    this.table_width_for_head_size = i11;
                    this.table_height_for_head_size = i12;
                    this.warp_table_for_head_size = new byte[i11 * i12 * 4];
                }
        public void AllocateArrayOutOfFaceMask(int i11, int i12, int i13) {
                    if (this.mask_width == i11 && this.mask_height == i12 && this.mask_stride == i13) {
                        return;
                    }
                    this.mask_width = i11;
                    this.mask_height = i12;
                    this.mask_stride = i13;
                    this.out_of_face_mask = new byte[i11 * i12];
                }
        public void AllocateArrayTemplate(int i11, int i12) {
                    this.template_data = new byte[i11 * i12 * 4];
                    this.template_width = i11;
                    this.template_height = i12;
                }
    }
}
