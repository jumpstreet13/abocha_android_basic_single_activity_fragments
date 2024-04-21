package com.example.cupcake.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em

private val textStyle = TextStyle(
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Proportional,
        trim = LineHeightStyle.Trim.None
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = textStyle.copy(
        fontFamily = FontFamily.SansSerif,
        fontSize = TextDimensions.Title1TextSize,
        lineHeight = TextDimensions.Title1LineHeight,
        letterSpacing = 0.em
    ),
    titleMedium = textStyle.copy(
        fontFamily = FontFamily.SansSerif,
        fontSize = TextDimensions.Title1TextSize,
        lineHeight = TextDimensions.Title1LineHeight,
        letterSpacing = 0.em
    ),
    bodyLarge = textStyle.copy(
        fontFamily = FontFamily.SansSerif,
        fontSize = TextDimensions.Body1TextSize,
        lineHeight = TextDimensions.Body1LineHeight,
        letterSpacing = (-0.023333333).em
    ),
    bodyMedium = textStyle.copy(
        fontFamily = FontFamily.SansSerif,
        fontSize = TextDimensions.Body2TextSize,
        lineHeight = TextDimensions.Body2LineHeight,
        letterSpacing = (-0.02).em
    )
)

