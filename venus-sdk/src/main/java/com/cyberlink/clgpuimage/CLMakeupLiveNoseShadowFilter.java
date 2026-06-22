package com.cyberlink.clgpuimage;

public class CLMakeupLiveNoseShadowFilter {
    public static class LiveNoseShadowMetadata {
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
        public float[] pose_parameters = new float[6];
        public int m_background_image_height = 0;
        public int m_background_image_width = 0;
        public float m_nose_bridge_line_equation_a = 1.0f;
        public float m_nose_bridge_line_equation_c = 0.0f;
        public float m_nose_bridge_line_equation_b = 0.0f;
        public float m_nose_bridge_half_width = 0.0f;
        public float m_eye_line_equation_b = 1.0f;
        public float m_eye_line_equation_c = 0.0f;
        public float m_eye_line_equation_a = 0.0f;
        public float m_eyelid_half_height = 0.0f;
        public byte[] mp_template_data = null;
        public int m_template_stride = 0;
        public int m_template_height = 0;
        public int m_template_width = 0;
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
        public void AllocTemplateArray(int i11, int i12, int i13) {
                    if (this.m_template_width == i11 && this.m_template_height == i12 && this.m_template_stride == i13) {
                        return;
                    }
                    this.m_template_width = i11;
                    this.m_template_height = i12;
                    this.m_template_stride = i13;
                    this.mp_template_data = new byte[i13 * i12];
                }
    }
}
