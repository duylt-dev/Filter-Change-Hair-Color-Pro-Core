package com.cyberlink.clgpuimage;

public class CLMakeupLiveFoundationFilter {
    public static class LiveFoundationEyeData {
        int eye_mask_height;
        int eye_mask_width;
        int eye_roi_height;
        int eye_roi_width;
        int eye_roi_x;
        int eye_roi_y;
        byte[] eye_mask = null;
        int eye_mask_size_width = 0;
        int eye_mask_size_height = 0;
        public void AllocEyesMaskBuffer(int i11, int i12) {
                    if (this.eye_mask_size_width == i11 && this.eye_mask_size_height == i12) {
                        return;
                    }
                    this.eye_mask_size_width = i11;
                    this.eye_mask_size_height = i12;
                    this.eye_mask = new byte[i11 * i12];
                }
    }

    public static class LiveFoundationData {
        boolean is_enabled;
        float skin_cb_average;
        float skin_cr_average;
        float skin_luma_average;
        float skin_luma_standard_deviation;
        boolean is_valid = false;
        public byte[] skin_probability_mask = null;
        byte[] forehead_neck_mask = null;
        byte[] highlight_mask = null;
        public float skin_mask_roi_left = 0.0f;
        public float skin_mask_roi_right = 0.0f;
        public float skin_mask_roi_top = 0.0f;
        public float skin_mask_roi_bottom = 0.0f;
        int skin_mask_roi_width = 0;
        int skin_mask_roi_height = 0;
        public int skin_mask_size_width = 0;
        public int skin_mask_size_height = 0;
        byte[] mouth_mask = null;
        int mouth_mask_width = 0;
        int mouth_mask_height = 0;
        int mouth_roi_x = 0;
        int mouth_roi_y = 0;
        int mouth_roi_width = 0;
        int mouth_roi_height = 0;
        int analyzing_frame_width = 0;
        int analyzing_frame_height = 0;
        int rotation = 0;
        boolean is_front_camera = true;
        float skin_region_roi_rect_rotated_cos = 0.0f;
        float skin_region_roi_rect_rotated_sin = 0.0f;
        float skin_mask_forehead_face_boundary = 0.0f;
        LiveFoundationEyeData[] eye_data = new LiveFoundationEyeData[2];
        public LiveFoundationData() {
                    for (int i11 = 0; i11 < 2; i11++) {
                        this.eye_data[i11] = new LiveFoundationEyeData();
                    }
                }
        public void AllocByteArray(int i11, int i12) {
                    if (this.skin_mask_size_width == i11 && this.skin_mask_size_height == i12) {
                        return;
                    }
                    this.skin_mask_size_width = i11;
                    this.skin_mask_size_height = i12;
                    int i13 = i11 * i12;
                    this.skin_probability_mask = new byte[i13];
                    this.forehead_neck_mask = new byte[i13];
                    this.highlight_mask = new byte[i13];
                }
        public void AllocMouthMaskBuffer(int i11, int i12) {
                    if (this.mouth_mask_width == i11 && this.mouth_mask_height == i12) {
                        return;
                    }
                    this.mouth_mask_width = i11;
                    this.mouth_mask_height = i12;
                    this.mouth_mask = new byte[i11 * i12];
                }
    }
}
