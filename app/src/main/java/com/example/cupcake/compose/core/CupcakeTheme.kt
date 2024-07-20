package com.example.cupcake.compose.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.res.colorResource
import com.example.cupcake.R

@Composable
@NonRestartableComposable
fun getLightColors() = lightColors(
    primary = colorResource(id = R.color.pink_600),
    primaryVariant = colorResource(id = R.color.pink_950),
    onPrimary = colorResource(id = R.color.white),
    secondary = colorResource(id = R.color.purple_400),
    secondaryVariant = colorResource(id = R.color.purple_700),
    onSecondary = colorResource(id = R.color.black),
)

@Composable
@NonRestartableComposable
fun getDarkColors() = lightColors(
    primary = colorResource(id = R.color.pink_400),
    primaryVariant = colorResource(id = R.color.pink_950),
    onPrimary = colorResource(id = R.color.black),
    secondary = colorResource(id = R.color.purple_400),
    secondaryVariant = colorResource(id = R.color.purple_400),
    onSecondary = colorResource(id = R.color.black),
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
