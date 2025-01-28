package com.example.cupcake

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val pink400 = Color(0xFFEC407A)
val pink600 = Color(0xFFD81B60)
val pink950 = Color(0xFFB31650)
val purple400 = Color(0xFFAB47BC)
val purple700 = Color(0xFF7B1FA2)
val black = Color(0xFF000000)
val white = Color(0xFFFFFFFF)

private val LightColorPalette = lightColorScheme(
    primary = pink600,
    primaryContainer = pink950,
    onPrimary = white,
    secondary = purple400,
    secondaryContainer = purple700,
    onSecondary = black,
    background = white
)

private val DarkColorPalette = darkColorScheme(
    primary = pink400,
    primaryContainer = pink950,
    onPrimary = black,
    secondary = purple400,
    secondaryContainer = purple400,
    onSecondary = black,
    background = black
)

@Composable
fun Theme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette,
        content = content
    )
}