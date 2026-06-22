package com.piontech.venussdk.model

/**
 * A single hair-dye color. RGB is the pure color (engine takes R,G,B; alpha is ignored).
 * [intensity] = color strength 0..100, [shine] = shine strength 0..100 — both fed to configHairDye.
 *
 * Values taken from the original makeup_template.xml `<hair_dye>` palettes (00RRGGBB).
 */
/** Hair-dye rendering mode (photo path). MULTI = full-color pattern (multiColorMode=2). */
enum class HairMode { SINGLE, OMBRE, TWO_COLOR, MULTI }

/** Live camera hair-dye tabs (maps to the original YMK HairDyePatternType). */
enum class LiveMode { SINGLE, GRADIENT, OMBRE, MULTI }

data class HairColor(
    val name: String,
    val r: Int,
    val g: Int,
    val b: Int,
    val intensity: Int = 100,
    val shine: Int = 50,
) {
    /** Opaque ARGB for UI swatches. */
    val argb: Int get() = (0xFF shl 24) or (r shl 16) or (g shl 8) or b
}

object HairPalette {
    val colors: List<HairColor> = listOf(
        HairColor("Nâu sẫm", 0x6F, 0x3D, 0x1B),
        HairColor("Nâu caramel", 0x84, 0x60, 0x30),
        HairColor("Nâu tối", 0x2C, 0x12, 0x12),
        HairColor("Đỏ", 0x9C, 0x02, 0x02),
        HairColor("Vàng đồng", 0xFF, 0x9F, 0x2D),
        HairColor("Xám bạc", 0xC8, 0xC8, 0xC8),
        HairColor("Tím", 0x6A, 0x2C, 0x91),
        HairColor("Xanh dương", 0x1E, 0x5A, 0xA8),
    )
}
