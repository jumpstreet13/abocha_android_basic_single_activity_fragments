package com.example.cupcake.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

data class AppTypography(
    val textAppearanceHeadline34: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        letterSpacing = TextUnit(0.007352941f, TextUnitType.Unspecified),
        fontSize = 34.sp,
    ),
    val textAppearanceSubtitle18: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        letterSpacing = TextUnit(0.007352941f, TextUnitType.Unspecified),
        fontSize = 18.sp,
    ),
    val textAppearanceSubtitle16: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        letterSpacing = TextUnit(0.007352941f, TextUnitType.Unspecified),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    val textAppearanceSubtitle16Bold: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        letterSpacing = TextUnit(0.007352941f, TextUnitType.Unspecified),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
)
