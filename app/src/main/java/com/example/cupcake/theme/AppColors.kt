package com.example.cupcake.theme

import androidx.compose.ui.graphics.Color

data class ColorPalette(
    val colorPrimary: Color,
    val colorPrimaryVariant: Color,
    val colorOnPrimary: Color,
    val colorSecondary: Color,
    val colorSecondaryVariant: Color,
    val colorOnSecondary: Color,
    val colorButtonBorder: Color,
    val textColor: Color
)

object AppColors {
    val pink_400 = Color(0xFFEC407A)
    val pink_600 = Color(0xFFD81B60)
    val pink_950 = Color(0xFFB31650)
    val purple_400 = Color(0xFFAB47BC)
    val purple_700 = Color(0xFF7B1FA2)
    val black = Color(0xFF000000)
    val white = Color(0xFFFFFFFF)
    val button_border_light = Color(0xFFD3D3D3)
    val button_border_dark = Color(0xFFE8E8E8)
}

fun lightColorPalette(): ColorPalette = ColorPalette(
    colorPrimary = AppColors.pink_600,
    colorPrimaryVariant = AppColors.pink_950,
    colorOnPrimary = AppColors.white,
    colorSecondary = AppColors.purple_400,
    colorSecondaryVariant = AppColors.purple_700,
    colorOnSecondary = AppColors.black,
    colorButtonBorder = AppColors.button_border_light,
    textColor = AppColors.black
)

fun darkColorPalette(): ColorPalette = ColorPalette(
    colorPrimary = AppColors.pink_400,
    colorPrimaryVariant = AppColors.pink_950,
    colorOnPrimary = AppColors.black,
    colorSecondary = AppColors.purple_400,
    colorSecondaryVariant = AppColors.purple_400,
    colorOnSecondary = AppColors.black,
    colorButtonBorder = AppColors.button_border_dark,
    textColor = AppColors.white
)
