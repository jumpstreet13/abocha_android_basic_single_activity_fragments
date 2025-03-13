package com.example.cupcake.appTheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun CurrentAppTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    textStyles: TextStyles = TextStyles(),
    content: @Composable () -> Unit
) {

    val colors = when {
        isDark -> darkThemeColors()
        else -> lightThemeColors()
    }

    CompositionLocalProvider(
        LocalTypography provides textStyles,
        LocalColors provides colors,
        content = content
    )
}

object AppTheme {

    val colors: AppThemeColors
        @Composable get() = LocalColors.current

    val typography: TextStyles
        @Composable get() = LocalTypography.current

}

internal val LocalTypography = staticCompositionLocalOf { TextStyles() }
internal val LocalColors = staticCompositionLocalOf { lightThemeColors() }