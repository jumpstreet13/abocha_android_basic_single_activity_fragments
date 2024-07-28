package com.example.cupcake.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CupcakeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme,
        content = content
    )
}

private val lightColorScheme = lightColorScheme(
    primary = pink_600,
    primaryContainer = pink_950,
    onPrimary = Color.White,
    onPrimaryContainer = Color.White,
    secondary = purple_400,
    onSecondary = Color.Black,
    secondaryContainer = purple_700,
    onSecondaryContainer = Color.Black,
)