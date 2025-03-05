package com.example.cupcake.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun ComposeSampleAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: AppTypography = AppTypography(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) darkColorPalette() else lightColorPalette()
    CompositionLocalProvider(
        LocalColor provides colors,
        LocalTypography provides typography,
        content = content
    )

}

val LocalColor = staticCompositionLocalOf { lightColorPalette() }
val LocalTypography = staticCompositionLocalOf { AppTypography() }