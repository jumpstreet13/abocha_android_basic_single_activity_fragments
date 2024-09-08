package com.example.cupcake.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun CupcakeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) getDarkColors() else getLightColors(),
        content = content
    )
}

private fun getLightColors() = lightColors(
    primary = Pink600,
    primaryVariant = Pink950,
    onPrimary = White,
    secondary = Purple400,
    secondaryVariant = Purple700,
    onSecondary = Black
)

private fun getDarkColors() = darkColors(
    primary = Pink400,
    primaryVariant = Pink950,
    onPrimary = Black,
    secondary = Purple400,
    secondaryVariant = Purple400,
    onSecondary = Black
)
