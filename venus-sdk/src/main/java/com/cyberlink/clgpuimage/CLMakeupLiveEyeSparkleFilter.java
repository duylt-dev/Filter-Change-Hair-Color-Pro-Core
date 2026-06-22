package com.cyberlink.clgpuimage;

public class CLMakeupLiveEyeSparkleFilter {
    public static class LiveEyeSparkleMetadata {
        public int left_eye_white_mask_height;
        public int left_eye_white_mask_width;
        public int left_eye_white_roi_height;
        public int left_eye_white_roi_width;
        public int left_eye_white_roi_x;
        public int left_eye_white_roi_y;
        public byte[] p_left_eye_white_buffer;
        public byte[] p_right_eye_white_buffer;
        public int right_eye_white_mask_height;
        public int right_eye_white_mask_width;
        public int right_eye_white_roi_height;
        public int right_eye_white_roi_width;
        public int right_eye_white_roi_x;
        public int right_eye_white_roi_y;
        public int rotation;
        public boolean is_valid = false;
        public boolean is_flipped = false;
        public int analyzing_frame_width = 0;
        public int analyzing_frame_height = 0;
        public float left_eye_white_min = 0.0f;
        public float left_eye_white_max = 0.0f;
        public int left_eye_white_kernel_size = 0;
        public float right_eye_white_min = 0.0f;
        public float right_eye_white_max = 0.0f;
        public int right_eye_white_kernel_size = 0;
        public byte[] p_left_iris_contrast_enhancement_table_buffer = new byte[256];
        public byte[] p_right_iris_contrast_enhancement_table_buffer = new byte[256];
        public int[] smooth_level = new int[2];
        public byte[] p_left_eye_buffer = null;
        public byte[] p_right_eye_buffer = null;
        public int left_eye_mask_width = 0;
        public int left_eye_mask_height = 0;
        public int left_eye_roi_x = 0;
        public int left_eye_roi_y = 0;
        public int left_eye_roi_width = 0;
        public int left_eye_roi_height = 0;
        public int right_eye_mask_width = 0;
        public int right_eye_mask_height = 0;
        public int right_eye_roi_x = 0;
        public int right_eye_roi_y = 0;
        public int right_eye_roi_width = 0;
        public int right_eye_roi_height = 0;
        public void AllocLeftByteArray(int i11, int i12) {
                    if (this.left_eye_mask_width == i11 && this.left_eye_mask_height == i12) {
                        return;
                    }
                    this.left_eye_mask_width = i11;
                    this.left_eye_mask_height = i12;
                    this.p_left_eye_buffer = new byte[i11 * i12];
                }
        public void AllocLeftEyeWhiteByteArray(int i11, int i12) {
                    if (this.left_eye_white_mask_width == i11 && this.left_eye_white_mask_height == i12) {
                        return;
                    }
                    this.left_eye_white_mask_width = i11;
                    this.left_eye_white_mask_height = i12;
                    this.p_left_eye_white_buffer = new byte[i11 * i12];
                }
        public void AllocRightByteArray(int i11, int i12) {
                    if (this.right_eye_mask_width == i11 && this.right_eye_mask_height == i12) {
                        return;
                    }
                    this.right_eye_mask_width = i11;
                    this.right_eye_mask_height = i12;
                    this.p_right_eye_buffer = new byte[i11 * i12];
                }
        public void AllocRightEyeWhiteByteArray(int i11, int i12) {
                    if (this.right_eye_white_mask_width == i11 && this.right_eye_white_mask_height == i12) {
                        return;
                    }
                    this.right_eye_white_mask_width = i11;
                    this.right_eye_white_mask_height = i12;
                    this.p_right_eye_white_buffer = new byte[i11 * i12];
                }
    }
}
