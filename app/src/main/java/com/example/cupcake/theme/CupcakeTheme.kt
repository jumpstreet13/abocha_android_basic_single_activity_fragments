package com.example.cupcake.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun CupcakeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: AppTypography = AppTypography(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkThemeColors() else lightThemeColors()
    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalColor provides colors,
        content = content
    )
}

object AppTheme {

    val colors: AppThemeColors
        @Composable get() = LocalColor.current

    val typography: AppTypography
        @Composable get() = LocalTypography.current

}

internal val LocalColor = staticCompositionLocalOf { lightThemeColors() }
internal val LocalTypography = staticCompositionLocalOf { AppTypography() }