package com.cyberlink.clgpuimage;

/**
 * GL-FREE struct carrier. The native engine (libperfect.so) FindClass'es
 * `com.cyberlink.clgpuimage.CLMakeupLiveHairDyeFilter$HairDyeData` and writes its fields via
 * GetFieldID during CUIVenusLive_GetMakeupMetadata. Only the nested data class + its EXACT field
 * names are load-bearing — the original OpenGL filter code is intentionally omitted.
 *
 * Field set copied verbatim from the decompiled CLMakeupLiveHairDyeFilter.HairDyeData
 * (m_gamma_table size 8192 was Utility.DEFAULT_STREAM_BUFFER_SIZE; inlined here).
 */
public class CLMakeupLiveHairDyeFilter {

    public static class HairDyeData {
        public static final int m_max_target_color_count = 4;
        public int m_analyzing_frame_height;
        public int m_analyzing_frame_width;
        public byte[] m_average_target_gray_map_data;
        public int m_average_target_gray_map_height;
        public int m_average_target_gray_map_width;
        public float m_hair_bottom_x;
        public float m_hair_bottom_y;
        public float m_hair_ombre_mapping_range;
        public float m_hair_ombre_range;
        public float m_hair_ombre_y;
        public int m_hair_purepigments_ratio_value;
        public float m_hair_purepigments_silkness;
        public float m_hair_top_x;
        public float m_hair_top_y;
        public byte[] m_local_smoothed_y_map_data;
        public float m_mask_center_x;
        public float m_mask_center_y;
        public int m_median_gray;
        public int m_multi_color_hair_dye_mode;
        public byte[] m_post_processed_map_data;
        public int m_quantile_25_gray;
        public int m_roi_height;
        public int m_roi_width;
        public int m_roi_x;
        public int m_roi_y;
        public boolean m_salon_mode_only_boundary_improvement;
        public byte[] m_segment_map_data;
        public int m_segment_map_height;
        public int m_segment_map_width;
        public float m_skin_h;
        public float m_skin_l;
        public int m_target_color_count;
        public int m_use_ymk_algorithm;
        public int[] m_target_color_r = new int[4];
        public int[] m_target_color_g = new int[4];
        public int[] m_target_color_b = new int[4];
        public int[] m_alpha_adjuster = new int[4];
        public int[] m_shine_ratio = new int[4];
        public float[] m_target_h = new float[4];
        public float[] m_target_s = new float[4];
        public float[] m_target_l = new float[4];
        public byte[] m_gamma_table = new byte[8192];
        public byte[] m_misc_tables = new byte[1024];
        public byte[] m_shine_table = new byte[4096];
        public boolean m_is_enabled = false;
        public int m_rotation = 90;
        public boolean m_is_flip = false;

        public void AllocByteArray(int w, int h) {
            if (this.m_segment_map_width == w && this.m_segment_map_height == h) {
                return;
            }
            this.m_segment_map_width = w;
            this.m_segment_map_height = h;
            int n = w * h;
            this.m_segment_map_data = new byte[n];
            this.m_post_processed_map_data = new byte[n * 4];
            this.m_average_target_gray_map_data = new byte[n];
            this.m_local_smoothed_y_map_data = new byte[n];
        }

        public int segmentWidth() { return m_segment_map_width; }
        public int segmentHeight() { return m_segment_map_height; }
    }
}
