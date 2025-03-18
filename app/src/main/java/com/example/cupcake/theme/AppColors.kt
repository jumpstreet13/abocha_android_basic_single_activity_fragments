package com.example.cupcake.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object AppColors {

    val pink_400 = Color(0xFFEC407A)
    val pink_400_alfa_30 = Color(0x4DEC407A)
    val pink_600 = Color(0xFFD81B60)
    val pink_600_alpha_30 = Color(0x4DD81B60)
    val pink_950 = Color(0xFFB31650)
    val purple_400 = Color(0xFFAB47BC)
    val purple_700 = Color(0xFF7B1FA2)
    val black = Color(0xFF000000)
    val white = Color(0xFFFFFFFF)
    val lightGray = Color.LightGray
    val darkGray = Color.DarkGray
}

data class AppThemeColors(
    val colorPrimary: Color,
    val colorPrimaryVariant: Color,
    val colorOnPrimary: Color,
    val colorSecondary: Color,
    val colorSecondaryVariant: Color,
    val colorOnSecondary: Color,
    val colorText: Color,
    val colorBorder: Color,
    val colorBackground: Color,
    val colorSelectedRadioButton:Color,
    val colorUnselectedRadioButton:Color,
    val colorDisableSelectedRadioButton:Color,
    val colorDisableUnselectedRadioButton:Color

)

fun lightThemeColors() = AppThemeColors(
    colorPrimary = AppColors.pink_600,
    colorPrimaryVariant = AppColors.pink_950,
    colorOnPrimary = AppColors.white,
    colorSecondary = AppColors.purple_400,
    colorSecondaryVariant = AppColors.purple_700,
    colorOnSecondary = AppColors.black,
    colorText = AppColors.black,
    colorBorder = AppColors.darkGray,
    colorBackground = AppColors.white,
    colorSelectedRadioButton = AppColors.pink_600,
    colorUnselectedRadioButton = AppColors.darkGray,
    colorDisableSelectedRadioButton = AppColors.pink_600_alpha_30,
    colorDisableUnselectedRadioButton = AppColors.lightGray
)

fun darkThemeColors() = AppThemeColors(
    colorPrimary = AppColors.pink_400,
    colorPrimaryVariant = AppColors.pink_950,
    colorOnPrimary = AppColors.black,
    colorSecondary = AppColors.purple_400,
    colorSecondaryVariant = AppColors.purple_400,
    colorOnSecondary = AppColors.black,
    colorText = AppColors.white,
    colorBorder = AppColors.lightGray,
    colorBackground = AppColors.black,
    colorSelectedRadioButton = AppColors.pink_400,
    colorUnselectedRadioButton = AppColors.darkGray,
    colorDisableSelectedRadioButton = AppColors.pink_400_alfa_30,
    colorDisableUnselectedRadioButton = AppColors.lightGray
)
