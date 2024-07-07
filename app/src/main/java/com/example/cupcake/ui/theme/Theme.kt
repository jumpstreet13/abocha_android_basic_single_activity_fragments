package com.example.cupcake.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em

data object TextDimensions {
    val Title1TextSize: TextUnit = 20.sp
    val Body1TextSize: TextUnit = 15.sp
    val Body2TextSize: TextUnit = 17.sp
    val Title1LineHeight: TextUnit = 24.sp
    val Body1LineHeight: TextUnit = 20.sp
    val Body2LineHeight: TextUnit = 24.sp
}

data object Paddings {
    val medium = 16.dp
    val xsmall = 8.dp
}

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

val Pink600 = Color(0xFFD81B60)
val Purple400 = Color(0xFFAB47BC)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

val DividerHorizontal = Color(0xFF888888)

private val DarkColorPalette = darkColorScheme(
    primary = Pink600,
    onPrimary = Black,
    secondary = Purple400,
    onSecondary = Black,
    background = Black
)

private val LightColorPalette = lightColorScheme(
    primary = Pink600,
    onPrimary = White,
    secondary = Purple400,
    onSecondary = White,
    background = White,
)

@Composable
fun CupCakeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    CompositionLocalProvider(
        LocalColor provides colors
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = Typography,
            content = content
        )
    }
}


internal val LocalColor = staticCompositionLocalOf { LightColorPalette }