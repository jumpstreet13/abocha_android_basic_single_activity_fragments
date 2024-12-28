package com.example.cupcake.util

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

const val duration = 400
const val offsetX = 1000

val enterTransition = slideInHorizontally(
    initialOffsetX = { offsetX },
    animationSpec = tween(duration)
) + fadeIn(animationSpec = tween(duration))

val exitTransition = slideOutHorizontally(
        targetOffsetX = { -offsetX },
        animationSpec = tween(duration)
    ) + fadeOut(animationSpec = tween(duration))

val popEnterTransition = slideInHorizontally(
    initialOffsetX = { -offsetX },
    animationSpec = tween(duration)
) + fadeIn(animationSpec = tween(duration))

val popExitTransition = slideOutHorizontally(
    targetOffsetX = { offsetX },
    animationSpec = tween(duration)
) + fadeOut(animationSpec = tween(duration))