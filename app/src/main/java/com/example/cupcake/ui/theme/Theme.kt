package com.example.cupcake.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Pink600,
    primaryVariant = Pink950,
    onPrimary = White,
    secondary = Purple400,
    secondaryVariant = Purple700,
    onSecondary = Black,
    background = White,
    surface = White,
    onBackground = Black,
    onSurface = Black
)

private val DarkColorPalette = darkColors(
    primary = Pink400,
    primaryVariant = Pink950,
    onPrimary = Black,
    secondary = Purple400,
    secondaryVariant = Purple400,
    onSecondary = Black,
    background = Black,
    surface = Black,
    onBackground = White,
    onSurface = White
)

@Composable
fun CupcakeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
