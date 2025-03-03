package com.example.cupcake.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val pink600 = Color(0xFFEC407A)
val pink950 = Color(0xFF880E4F)
val white = Color.White
val purple400 = Color(0xFFAB47BC)
val purple700 = Color(0xFF7B1FA2)
val black = Color.Black
val pink400 = Color(0xFFF06292)

val LightColorScheme = lightColorScheme(
    primary = pink600,
    onPrimary = white,
    primaryContainer = pink950,
    onPrimaryContainer = white,
    secondary = purple400,
    onSecondary = black,
    secondaryContainer = purple700,
    onSecondaryContainer = white
)

val DarkColorScheme = darkColorScheme(
    primary = pink400,
    onPrimary = black,
    primaryContainer = pink950,
    onPrimaryContainer = black,
    secondary = purple400,
    onSecondary = black,
    secondaryContainer = purple400,
    onSecondaryContainer = black
)

@Composable
fun CupcakeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}