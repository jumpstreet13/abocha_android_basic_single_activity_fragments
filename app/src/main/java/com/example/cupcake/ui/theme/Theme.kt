package com.example.cupcake.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

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