package com.cyberlink.clgpuimage;

public class CLMakeupLiveAppleCheeksFilter {
    public static class LiveAppleCheeksMetadata {
        public boolean is_enabled = false;
        public int intensity = 0;
        public float shadow_color_b = 0.0f;
        public float shadow_color_g = 0.0f;
        public float shadow_color_r = 0.0f;
        public float highlight_dark_face_adjuster = 0.0f;
        public byte[] p_template_data = null;
        public int template_width = 0;
        public int template_height = 0;
        public int template_stride = 0;
        public void AllocTemplateArray(int i11, int i12, int i13) {
                    if (this.template_width == i11 && this.template_height == i12 && this.template_stride == i13) {
                        return;
                    }
                    this.template_width = i11;
                    this.template_height = i12;
                    this.template_stride = i13;
                    this.p_template_data = new byte[i13 * i12];
                }
    }
}
