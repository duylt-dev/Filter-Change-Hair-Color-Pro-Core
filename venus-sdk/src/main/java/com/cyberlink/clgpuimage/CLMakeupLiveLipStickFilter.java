package com.cyberlink.clgpuimage;

public class CLMakeupLiveLipStickFilter {
    public static class LipstickData {
        public int m_background_image_height;
        public int m_background_image_width;
        public boolean m_enable_color_shimmer;
        public int m_force_bright_threshold;
        public float m_gloss_contrast_scale;
        public int m_gloss_contrast_shift;
        public float m_gloss_contrast_shrink;
        public boolean m_is_flipped;
        public boolean m_is_upper_lower_omber;
        public int m_layer_count;
        public int m_mask_height;
        public int m_mask_width;
        public byte[] m_median_data;
        public byte[] m_mouth_layer_mask_data;
        public byte[] m_mouth_mask_data;
        public int m_roi_height;
        public int m_roi_width;
        public int m_roi_x;
        public int m_roi_y;
        public int m_rotation;
        public byte[] m_shimmer_data;
        public int m_shimmer_height;
        public float m_shimmer_intensity;
        public float m_shimmer_normalize_factor;
        public int m_shimmer_width;
        public boolean m_use_median;
        public byte[] m_blend_weight_and_lvel_map = new byte[1024];
        public int[] m_color_r = new int[2];
        public int[] m_color_g = new int[2];
        public int[] m_color_b = new int[2];
        public int[] m_shimmer_color = new int[3];
        public void AllocByteArray(int i11, int i12, int i13, int i14) {
                    if (this.m_mask_width != i11 || this.m_mask_height != i12) {
                        this.m_mask_width = i11;
                        this.m_mask_height = i12;
                        int i15 = i11 * i12;
                        int i16 = i15 * 4;
                        this.m_mouth_mask_data = new byte[i16];
                        this.m_mouth_layer_mask_data = new byte[i16];
                        this.m_median_data = new byte[i15];
                    }
                    if (this.m_shimmer_width == i13 && this.m_shimmer_height == i14) {
                        return;
                    }
                    this.m_shimmer_width = i13;
                    this.m_shimmer_height = i14;
                    this.m_shimmer_data = new byte[i13 * i14];
                }
    }
}
