package com.example.cupcake.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cupcake.R

data class AppTypography internal constructor(
    val paragraph1: TextStyle = TextStyle(
//        fontFamily = FontFamily(Font(R.font.marvel_regular)),
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    ),

    val materialTypography: Typography = Typography(
        body1 = paragraph1
    )

)