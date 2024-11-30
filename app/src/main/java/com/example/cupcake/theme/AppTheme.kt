package com.example.cupcake.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.cupcake.theme.AppColors.Black
import com.example.cupcake.theme.AppColors.Pink400
import com.example.cupcake.theme.AppColors.Pink950
import com.example.cupcake.theme.AppColors.Purple400
import com.example.cupcake.theme.AppColors.Purple700
import com.example.cupcake.theme.AppColors.White

@Composable
fun CupcakeAppTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) darkColorSet() else lightColorSet()
    MaterialTheme(
        colors = colors,
        content = content
    )
}

private fun lightColorSet() = lightColors(
    primary = Pink400,
    primaryVariant = Pink950,
    onPrimary = White,
    secondary = Purple400,
    secondaryVariant = Purple700,
    onSecondary = Black
)

private fun darkColorSet() = lightColors(
    primary = Pink400,
    primaryVariant = Pink950,
    onPrimary = Black,
    secondary = Purple400,
    secondaryVariant = Purple700,
    onSecondary = White
)