package com.cyberlink.clgpuimage;

public class CLMakeupLiveTeethWhitenFilter {
    public static class TeethWhitenMetaData {
        public int m_background_image_height;
        public int m_background_image_width;
        public boolean m_is_flipped;
        public boolean m_is_valid;
        public int m_mask_height;
        public int m_mask_width;
        public byte[] m_mouth_mask_data;
        public int m_roi_height;
        public int m_roi_width;
        public int m_roi_x;
        public int m_roi_y;
        public int m_rotation;
        public void AllocByteArray(int i11, int i12) {
                    if (this.m_mask_width == i11 && this.m_mask_height == i12) {
                        return;
                    }
                    this.m_mask_width = i11;
                    this.m_mask_height = i12;
                    this.m_mouth_mask_data = new byte[i11 * i12];
                }
    }
}
