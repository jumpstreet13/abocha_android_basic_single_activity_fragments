package com.example.cupcake.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


data class AppTypography internal constructor(
    val textAppearanceHeadline4: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 32.sp,
    ),

    val textAppearanceButton: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 32.sp
    ),

    val textAppearanceSubtitle1: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 18.sp
    ),

    val textAppearanceBold18: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    val textAppearanceBody1: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    )

)