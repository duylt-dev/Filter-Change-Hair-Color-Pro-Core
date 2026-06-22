package com.piontech.venussdk.gl

/**
 * Exact vertex + fragment GLSL ported verbatim from the original
 * com.cyberlink.clgpuimage.CLMakeupLiveHairDyeFilter (the two args to its super(...) call).
 * Only whitespace/newlines were reformatted; tokens are unchanged.
 */
internal object HairDyeShaders {

    val VERTEX = """
attribute vec4 position;
attribute vec4 inputTextureCoordinate;
attribute vec4 input_hair_texture_coordinate;
uniform float center_x;
uniform float center_y;
uniform float background_image_width;
uniform float background_image_height;
uniform float hair_width;
uniform float hair_height;
uniform vec2 pixel_step;
uniform vec2 jump_pixel_step;
varying vec2 textureCoordinate;
varying vec2 hair_texture_coordinate;
varying vec2 hair_texture_coordinate_left_bottom;
varying vec2 hair_texture_coordinate_half_bottom;
varying vec2 hair_texture_coordinate_bottom;
varying vec2 hair_texture_coordinate_right_bottom;
varying vec2 hair_texture_coordinate_jump_top;
varying vec2 hair_texture_coordinate_jump_bottom;
varying vec2 hair_texture_coordinate_jump_left;
varying vec2 hair_texture_coordinate_jump_right;
varying vec2 texture_coordinate_jump_top;
varying vec2 texture_coordinate_jump_bottom;
varying vec2 texture_coordinate_jump_left;
varying vec2 texture_coordinate_jump_right;
void main(){
    gl_Position = position;
    textureCoordinate = inputTextureCoordinate.xy;
    hair_texture_coordinate.x = (input_hair_texture_coordinate.x - center_x) * background_image_width / hair_width + 0.5;
    hair_texture_coordinate.y = (input_hair_texture_coordinate.y - center_y) * background_image_height / hair_height + 0.5;
    hair_texture_coordinate_left_bottom = vec2(hair_texture_coordinate.x - pixel_step.x, hair_texture_coordinate.y + pixel_step.y);
    hair_texture_coordinate_half_bottom = vec2(hair_texture_coordinate.x, hair_texture_coordinate.y + pixel_step.y * 0.5);
    hair_texture_coordinate_bottom = vec2(hair_texture_coordinate.x, hair_texture_coordinate.y + pixel_step.y);
    hair_texture_coordinate_right_bottom = vec2(hair_texture_coordinate.x + pixel_step.x, hair_texture_coordinate.y + pixel_step.y);
    texture_coordinate_jump_top = vec2(textureCoordinate.x, textureCoordinate.y - jump_pixel_step.y);
    texture_coordinate_jump_bottom = vec2(textureCoordinate.x, textureCoordinate.y + jump_pixel_step.y);
    texture_coordinate_jump_left = vec2(textureCoordinate.x - jump_pixel_step.x, textureCoordinate.y);
    texture_coordinate_jump_right = vec2(textureCoordinate.x + jump_pixel_step.x, textureCoordinate.y);
    hair_texture_coordinate_jump_top = vec2(hair_texture_coordinate.x, hair_texture_coordinate.y - jump_pixel_step.y);
    hair_texture_coordinate_jump_bottom = vec2(hair_texture_coordinate.x, hair_texture_coordinate.y + jump_pixel_step.y);
    hair_texture_coordinate_jump_left = vec2(hair_texture_coordinate.x - jump_pixel_step.x, hair_texture_coordinate.y);
    hair_texture_coordinate_jump_right = vec2(hair_texture_coordinate.x + jump_pixel_step.x, hair_texture_coordinate.y);
}
""".trimIndent()

    val FRAGMENT = """
#ifdef GL_FRAGMENT_PRECISION_HIGH
precision highp float;
#else
precision mediump float;
#endif
varying vec2 textureCoordinate;
varying vec2 hair_texture_coordinate;
varying vec2 hair_texture_coordinate_left_bottom;
varying vec2 hair_texture_coordinate_half_bottom;
varying vec2 hair_texture_coordinate_bottom;
varying vec2 hair_texture_coordinate_right_bottom;
varying vec2 hair_texture_coordinate_jump_top;
varying vec2 hair_texture_coordinate_jump_bottom;
varying vec2 hair_texture_coordinate_jump_left;
varying vec2 hair_texture_coordinate_jump_right;
varying vec2 texture_coordinate_jump_top;
varying vec2 texture_coordinate_jump_bottom;
varying vec2 texture_coordinate_jump_left;
varying vec2 texture_coordinate_jump_right;
uniform sampler2D inputImageTexture;
uniform sampler2D hairTexture;
uniform sampler2D hairPostProcessed;
uniform sampler2D hairAverageGray;
uniform sampler2D hairLocalSmoothedY;
uniform sampler2D gammaTexture;
uniform sampler2D shineTexture;
uniform sampler2D hair_full_color_texture;
uniform float median_gray;
uniform float quantile_25_gray;
uniform int target_color_count;
uniform vec3 target_color[4];
uniform float alpha_adjuster[4];
uniform float shine_ratio[4];
uniform float shine_alpha_jump_step_x;
uniform float shine_alpha_jump_step_y;
uniform int kernel_radius;
uniform float salon_mode_only_boundary_improvement;
uniform vec2 mask_center;
uniform vec2 hair_top;
uniform vec2 hair_bottom;
uniform float hair_ombre_y;
uniform float hair_ombre_range;
uniform float hair_ombre_mapping_range;
uniform int algorithm_index;
uniform int use_ymk_algorithm;
uniform int multi_color_hair_dye_mode;
uniform float hair_purepigments_ratio_value;
uniform float hair_purepigments_silkness;
uniform float target_h[4];
uniform float target_s[4];
uniform float target_l[4];
uniform float skin_h;
uniform float skin_l;
uniform int bypass;
const float flt_epsilon = 0.001;
void main(){
    vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;
    if (bypass == 1) { gl_FragColor = vec4(source, 1.0); return; }
    vec3 source_top = texture2D(inputImageTexture, texture_coordinate_jump_top).rgb;
    vec3 source_bottom = texture2D(inputImageTexture, texture_coordinate_jump_bottom).rgb;
    vec3 source_left = texture2D(inputImageTexture, texture_coordinate_jump_left).rgb;
    vec3 source_right = texture2D(inputImageTexture, texture_coordinate_jump_right).rgb;
    float ombre_line;
    ombre_line = hair_ombre_y;
    if (any(greaterThan(abs(hair_texture_coordinate - vec2(0.5)), vec2(0.5))))
    {
        gl_FragColor = vec4(source, 1.0);
    }
    else
    {
        int max_target_color_count = 4;
        float split_coordinate;
        float hair_height = hair_bottom.y - hair_top.y;
        float hair_width = hair_bottom.x - hair_top.x;
        split_coordinate = (hair_texture_coordinate.y - ombre_line) * (hair_ombre_mapping_range / hair_height);
        float alpha = texture2D(hairTexture, hair_texture_coordinate).r;
        float _alpha_top = texture2D(hairTexture, hair_texture_coordinate_jump_top).r;
        float _alpha_bottom = texture2D(hairTexture, hair_texture_coordinate_jump_bottom).r;
        float _alpha_left = texture2D(hairTexture, hair_texture_coordinate_jump_left).r;
        float _alpha_right = texture2D(hairTexture, hair_texture_coordinate_jump_right).r;
        float alpha_shine = alpha;
        for (int window_y = 0 - kernel_radius; window_y <= kernel_radius; window_y++)
        {
            for (int window_x = 0 - kernel_radius; window_x <= kernel_radius; window_x++)
            {
                float window_hair_texture_coordinate_x = min(max(hair_texture_coordinate.x + float(window_x) * shine_alpha_jump_step_x, 0.0), 1.0);
                float window_hair_texture_coordinate_y = min(max(hair_texture_coordinate.y + float(window_y) * shine_alpha_jump_step_y, 0.0), 1.0);
                vec2 window_hair_texture_coordinate = vec2(window_hair_texture_coordinate_x, window_hair_texture_coordinate_y);
                alpha_shine = min(alpha_shine, texture2D(hairTexture, window_hair_texture_coordinate).r);
            }
        }
        float gray = (5.0 * (source.r + source.g + source.b) + source.g) / 16.0;
        if (multi_color_hair_dye_mode == 1)
        {
            float diff = max(max(max(abs(alpha - _alpha_top), abs(alpha - _alpha_bottom)), abs(alpha - _alpha_right)), abs(alpha- _alpha_left));
            source = mix(source, (source * alpha + source_top * _alpha_top + source_bottom * _alpha_bottom + source_left * _alpha_left + source_right * _alpha_right) / (alpha + _alpha_top + _alpha_bottom + _alpha_left + _alpha_right + flt_epsilon), alpha * hair_purepigments_silkness * pow((1.0 - diff), 2.0));
        }
        float smoothed_y = texture2D(hairLocalSmoothedY, hair_texture_coordinate).r;
        float erode_ratio_pixel_diff = abs(median_gray - gray);
        float erode_confidence = 0.25 + min(max(erode_ratio_pixel_diff - 0.03, 0.0), 0.05) / 0.1;
        float threshold_gray = median_gray;
        float switched_gray[2];
        float blend_range[2];
        switched_gray[0] = hair_purepigments_ratio_value;
        switched_gray[1] = median_gray;
        blend_range[0] = 0.1;
        blend_range[1] = 0.0;
        float weight[2];
        weight[0] = 0.0;
        weight[1] = 0.0;
        if (target_color_count == 1)
        {
            weight[0] = 1.0;
        }
        else if (target_color_count == 2)
        {
            if (multi_color_hair_dye_mode == 1)
            {
                weight[0] = smoothstep(switched_gray[0] - blend_range[0], switched_gray[0] + blend_range[0], smoothed_y);
                weight[1] = 1.0 - weight[0];
            }
            else
            {
                weight[0] = 1.0 - clamp((split_coordinate * (hair_ombre_mapping_range/hair_ombre_range) + 1.0 / float(target_color_count)), (1.0 - hair_ombre_mapping_range)/2.0, 1.0 - (1.0 - hair_ombre_mapping_range)/2.0);
                weight[1] = 1.0 - weight[0];
            }
        }
        float radius = max(min(mask_center.x, 1.0 - mask_center.x), 0.01);
        float left_boundary = mask_center.x - radius;
        float right_boundary = mask_center.x + radius;
        float distance_to_center = abs(hair_texture_coordinate.x - mask_center.x);
        float erode_ratio_horizontal = smoothstep(0.0, 0.8 * radius, radius - distance_to_center);
        float erode_ratio_vertical = smoothstep(0.0, 0.5, 1.0 - hair_texture_coordinate.y) * 0.5;
        float erode_ratio = erode_ratio_horizontal * erode_ratio_vertical;
        float bound_gradient_alpha = texture2D(hairPostProcessed, hair_texture_coordinate).r;
        float out_bound_gradient_value = texture2D(hairPostProcessed, hair_texture_coordinate).g;
        float average_source_gray = texture2D(hairPostProcessed, hair_texture_coordinate).b;
        vec4 average_target_gray = texture2D(hairAverageGray, hair_texture_coordinate).rgba;
        float target_gray_1 = 0.299 * target_color[0].r + 0.587 * target_color[0].g + 0.114 * target_color[0].b;
        float target_gray_2 = 0.299 * target_color[1].r + 0.587 * target_color[1].g + 0.114 * target_color[1].b;
        float target_gray = target_gray_1 * weight[0] + target_gray_2 * weight[1];
        float target_gray_ratio = min(max(target_gray - 0.35, 0.0), 0.05) / 0.05;
        erode_confidence = mix(erode_confidence, 1.0, target_gray_ratio);
        vec2 new_coordinate_left_bottom = mix(hair_texture_coordinate, hair_texture_coordinate_left_bottom, erode_confidence);
        vec2 new_coordinate_half_bottom = mix(hair_texture_coordinate, hair_texture_coordinate_half_bottom, erode_confidence);
        vec2 new_coordinate_bottom = mix(hair_texture_coordinate, hair_texture_coordinate_bottom, erode_confidence);
        vec2 new_coordinate_right_bottom = mix(hair_texture_coordinate, hair_texture_coordinate_right_bottom, erode_confidence);
        float alpha_left_bottom = texture2D(hairTexture, new_coordinate_left_bottom).r;
        float alpha_half_bottom = texture2D(hairTexture, new_coordinate_half_bottom).r;
        float alpha_bottom = texture2D(hairTexture, new_coordinate_bottom).r;
        float alpha_right_bottom = texture2D(hairTexture, new_coordinate_right_bottom).r;
        float erode_alpha = min(min(min(alpha, alpha_left_bottom), min(alpha_bottom, alpha_right_bottom)), alpha_half_bottom);
        float adaptive_erode_alpha = mix(alpha, erode_alpha, erode_ratio);
        float gamma_alpha;
        float white_ratio = min(1.0, max(0.0, (gray - median_gray) / max(1.0 - median_gray, 0.001)));
        float gamma;
        vec3 dst[4];
        dst[0] = vec3(0.0, 0.0, 0.0);
        dst[1] = vec3(0.0, 0.0, 0.0);
        dst[2] = vec3(0.0, 0.0, 0.0);
        dst[3] = vec3(0.0, 0.0, 0.0);
        for (int i = 0; i < 2; i++)
        {
            float texture_pos = (float(i)*(1.0/float(2 * max_target_color_count)) + float(i+1)*(1.0/float(2 * max_target_color_count)))/2.0;
            gamma = texture2D(gammaTexture, vec2(gray, texture_pos)).a;
            float target_median_gray = texture2D(gammaTexture, vec2(median_gray, texture_pos)).a;
            float texture_pos_dark = 0.5 + (float(i)*(1.0/float(2 * max_target_color_count)) + float(i+1)*(1.0/float(2 * max_target_color_count)))/2.0;
            float gamma_dark = texture2D(gammaTexture, vec2(gray, texture_pos_dark)).a;
            float weight_to_dark = 1.0 - max(min((average_source_gray - quantile_25_gray) / (median_gray - quantile_25_gray + flt_epsilon), 1.0), 0.0);
            float diff_source_target = abs(target_median_gray - median_gray);
            weight_to_dark = weight_to_dark * (min(max((0.471 - diff_source_target) / 0.392, 0.0), 1.0));
            weight_to_dark = weight_to_dark * (min(max((0.784 - target_median_gray) / 0.392, 0.0), 1.0));
            gamma = mix(gamma, gamma_dark, weight_to_dark);
            if (use_ymk_algorithm != 0 || salon_mode_only_boundary_improvement > 0.5)
            {
                gamma_alpha = bound_gradient_alpha;
            }
            else
            {
                gamma_alpha = adaptive_erode_alpha;
            }
            if (use_ymk_algorithm != 0)
            {
                float gamma_alpha_reduce_weight;
                gamma_alpha_reduce_weight = smoothstep(0.2 * hair_width, 0.5 * hair_width + flt_epsilon, hair_texture_coordinate.y - hair_top.y);
                float dist_h = min(min(abs(target_h[i] - skin_h), abs((target_h[i] + 6.0) - skin_h)), abs((target_h[i] - 6.0) - skin_h));
                gamma_alpha_reduce_weight = gamma_alpha_reduce_weight * 0.85 * (1.0 - min(dist_h / 0.75, 1.0) * min(max(1.0 - (0.6 - target_s[i]) / 0.3, 0.2), 1.0));
                gamma_alpha_reduce_weight = gamma_alpha_reduce_weight * (1.0 - max(min((target_l[i] - skin_l - 0.25) * 5.0, 1.0), 0.0));
                gamma_alpha = mix(gamma_alpha, alpha, gamma_alpha_reduce_weight);
            }
            gamma = mix(gray, gamma, gamma_alpha * alpha_adjuster[i]);
            if (use_ymk_algorithm != 0 && target_median_gray > median_gray)
            {
                float compress_ratio = 0.5;
                float target_gray_bound;
                if (i == 0)
                    target_gray_bound = mix(median_gray, average_target_gray.r, alpha_adjuster[i]);
                else if (i == 1)
                    target_gray_bound = mix(median_gray, average_target_gray.g, alpha_adjuster[i]);
                else if (i == 2)
                    target_gray_bound = mix(median_gray, average_target_gray.b, alpha_adjuster[i]);
                else
                    target_gray_bound = mix(median_gray, average_target_gray.a, alpha_adjuster[i]);
                if (gamma > target_gray_bound)
                {
                    float gamma_boundary = mix(gamma, max(gray, target_gray_bound), compress_ratio);
                    float boundary_weight = alpha - out_bound_gradient_value;
                    gamma = mix(gamma, gamma_boundary, boundary_weight);
                }
            }
            float alpha_reduce_weight = smoothstep(0.2 * hair_width, 0.66 * hair_width + flt_epsilon, hair_texture_coordinate.y - hair_top.y) * 0.5 + 0.25;
            alpha_reduce_weight = alpha_reduce_weight * min(target_s[i] * 2.0, 1.0);
            if (use_ymk_algorithm == 0)
                alpha_reduce_weight = 0.0;
            float reduced_erode_alpha = mix(adaptive_erode_alpha, min(adaptive_erode_alpha, gamma_alpha), alpha_reduce_weight);
            dst[i] = texture2D(gammaTexture, vec2(gamma, texture_pos)).rgb;
            vec3 shine_rgb;
            shine_rgb = texture2D(shineTexture, vec2(gamma, texture_pos)).rgb;
            float enhance_ratio = shine_ratio[i] * white_ratio * alpha;
            dst[i] = mix(dst[i], shine_rgb, enhance_ratio);
            dst[i] = mix(source, dst[i], alpha_adjuster[i] * reduced_erode_alpha);
            vec3 shine_y;
            shine_y = vec3(texture2D(shineTexture, vec2(dst[i].r, texture_pos)).a, texture2D(shineTexture, vec2(dst[i].g, texture_pos)).a, texture2D(shineTexture, vec2(dst[i].b, texture_pos)).a);
            float adjust_scale;
            float mapped_gray;
            if (multi_color_hair_dye_mode == -1 || multi_color_hair_dye_mode == 0)
            {
                dst[i] = mix(dst[i], shine_y, alpha_shine);
            }
            else if (multi_color_hair_dye_mode == 1)
            {
                if (target_color_count == 2)
                {
                    dst[i] = mix(dst[i], shine_y, alpha_shine);
                }
                else
                {
                    if (gray < threshold_gray)
                    {
                        mapped_gray = (gray - threshold_gray) * 0.5 / (threshold_gray);
                        adjust_scale = 1.0 / (1.0 + exp(-5.0 * mapped_gray));
                        adjust_scale = adjust_scale * threshold_gray / 0.5;
                        dst[i] = mix(dst[i], shine_y*(1.0 - (gray - adjust_scale)), alpha_shine);
                    }
                    else
                    {
                        mapped_gray = (gray - threshold_gray) * 0.5 / (1.0 - threshold_gray);
                        adjust_scale = 1.0 / (1.0 + exp(-5.0 * mapped_gray));
                        adjust_scale = (adjust_scale - 0.5) * (1.0 - threshold_gray) / 0.5;
                        dst[i] = mix(dst[i], shine_y*(1.0 + adjust_scale), alpha_shine);
                    }
                }
            }
        }
        gl_FragColor = vec4(dst[0] * weight[0] + dst[1] * weight[1], 1.0);
        if (multi_color_hair_dye_mode == 2)
        {
            vec2 hair_full_color_coordinate = (hair_texture_coordinate - hair_top) / max(vec2(0.001), (hair_bottom - hair_top));
            vec3 background = texture2D(hair_full_color_texture, hair_full_color_coordinate).rgb;
            float s_l = (source.r + source.g + source.b) / 3.0;
            vec3 s_c = source.rgb - vec3(s_l);
            float b_l = (background.r + background.g + background.b) / 3.0;
            vec3 b_c = background.rgb - vec3(b_l);
            float gamma = texture2D(gammaTexture, vec2(s_l, 0.0)).a;
            gamma = mix(s_l, gamma, alpha);
            float res_l = mix(gamma, b_l, alpha / 5.0);
            vec3 res_c = mix(s_c, b_c, 2.0 * alpha / 3.0);
            vec3 result = vec3(res_l) + res_c.rgb;
            gl_FragColor = vec4(result, 1.0);
            return;
        }
    }
}
""".trimIndent()
}
