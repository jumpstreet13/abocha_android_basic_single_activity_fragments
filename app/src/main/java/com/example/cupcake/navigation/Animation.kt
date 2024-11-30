package com.example.cupcake.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut

private const val DURATION_MILLIS = 220
private const val DELAY_MILLIS = 90
private const val SCALE = 0.9f

fun scaleIntoContainer(): EnterTransition {
    return scaleIn(
        animationSpec = tween(DURATION_MILLIS, delayMillis = DELAY_MILLIS),
        initialScale = SCALE
    ) + fadeIn(animationSpec = tween(DURATION_MILLIS, delayMillis = DELAY_MILLIS))
}

fun scaleOutOfContainer(): ExitTransition {
    return scaleOut(
        animationSpec = tween(
            durationMillis = DURATION_MILLIS,
            delayMillis = DELAY_MILLIS
        ), targetScale = SCALE
    ) + fadeOut(tween(delayMillis = DELAY_MILLIS))
}