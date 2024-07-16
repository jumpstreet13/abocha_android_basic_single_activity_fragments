package com.example.cupcake.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CupcakeTheme(content: @Composable () -> Unit) {

    MaterialTheme(colors = ColorPalette, content = content)
}

val Pink400 = Color(0xFFEC407A)
val Pink600 = Color(0xFFD81B60)
val Pink950 = Color(0xFFB31650)
val Purple400 = Color(0xFFAB47BC)
val Purple700 = Color(0xFF7B1FA2)

// Rally is always dark themed.
val ColorPalette = lightColors(
    primary = Pink600,
    primaryVariant = Pink950,
    onPrimary = Color.White,

    secondary = Purple400,
    secondaryVariant = Purple700,
    onSecondary = Color.Black,

    surface = Color.White,
    onSurface = Color.Black,

    background = Color.White,
    onBackground = Color.Black
)

