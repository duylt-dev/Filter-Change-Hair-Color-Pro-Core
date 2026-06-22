package com.cyberlink.clgpuimage;

public class CLStickerLiveFilter {
    public static class StickerData {
        public boolean is_enabled;
        final int HOMOGRAPY_SIZE = 9;
        final float[] IDENTICAL_HOMOGRAPHY = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f}
        ;
        public boolean is_face_detected = false;
        public int rotation = 90;
        public boolean is_flip = false;
        public int frame_width = 480;
        public int frame_height = 640;
        public int[] face_sticker_model_width = new int[4];
        public int[] face_sticker_model_height = new int[4];
        public boolean[] is_face_sticker_displayed = new boolean[4];
        public boolean[] reset_decoder = new boolean[4];
        public float[] face_sticker_homography = new float[36];
        public int face_sticker_count = 0;
        public float[] scene_ratio_x = new float[2];
        public float[] scene_ratio_y = new float[2];
        public float[] scene_offset_x = new float[2];
        public float[] scene_offset_y = new float[2];
        public float[] scene_stretch_start_x = new float[2];
        public float[] scene_stretch_end_x = new float[2];
        public float[] scene_stretch_start_y = new float[2];
        public float[] scene_stretch_end_y = new float[2];
        public boolean[] is_scene_displayed = new boolean[2];
        public boolean[] reset_scene_decoder = new boolean[2];
        public int scene_sticker_count = 0;
        public StickerData() {
                    for (int i11 = 0; i11 < 4; i11++) {
                        System.arraycopy(this.IDENTICAL_HOMOGRAPHY, 0, this.face_sticker_homography, i11 * 9, 9);
                    }
                }
    }
}
