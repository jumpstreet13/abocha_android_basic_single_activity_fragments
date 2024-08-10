package com.example.cupcake.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable

@Composable
@NonRestartableComposable
fun getLightColors() = lightColors(
    primary = PINK_600,
    primaryVariant = PINK_950,
    onPrimary = WHITE,
    secondary = PURPLE_400,
    secondaryVariant = PURPLE_700,
    onSecondary = BLACK,
)

@Composable
@NonRestartableComposable
fun getDarkColors() = lightColors(
    primary = PINK_400,
    primaryVariant = PINK_950,
    onPrimary = BLACK,
    secondary = PURPLE_400,
    secondaryVariant = PURPLE_400,
    onSecondary = BLACK,
)

@Composable
fun CupcakeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) getDarkColors() else getLightColors(),
        content = content
    )
}