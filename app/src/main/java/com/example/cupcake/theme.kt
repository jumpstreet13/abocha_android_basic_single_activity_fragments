package com.example.cupcake

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

@Composable
fun MainTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) darkColors(
            primary = colorResource(R.color.pink_400),
            primaryVariant = colorResource(R.color.pink_950),
            onPrimary = Color.Black,
            secondary = colorResource(R.color.purple_400),
            secondaryVariant = colorResource(R.color.purple_400),
            onSecondary = Color.Black
        ) else lightColors(
            primary = colorResource(R.color.pink_600),
            primaryVariant = colorResource(R.color.pink_950),
            onPrimary = Color.White,
            secondary = colorResource(R.color.purple_400),
            secondaryVariant = colorResource(R.color.purple_700),
            onSecondary = Color.Black
        ),
        content = content
    )
}
