package com.example.cupcake.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.presentation.screens.addFlavorDestination
import com.example.cupcake.presentation.screens.addPickupDestination
import com.example.cupcake.presentation.screens.addStartDestination
import com.example.cupcake.presentation.screens.addSummaryDestination

@Composable
fun AppNavHost(sharedViewModel: OrderViewModel) {
    val navHostController = rememberNavController()

    CompositionLocalProvider(LocalSharedViewModel provides sharedViewModel) {
        NavHost(
            navController = navHostController,
            startDestination = Destination.StartScreen
        ) {
            addStartDestination(navHostController, sharedViewModel)
            addFlavorDestination(navHostController, sharedViewModel)
            addPickupDestination(navHostController, sharedViewModel)
            addSummaryDestination(navHostController, sharedViewModel)
        }
    }
}

val LocalSharedViewModel = compositionLocalOf<OrderViewModel> { error("No OrderViewModel found!") }

fun getScreenEnterTransition():(AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards EnterTransition?) = {
    fadeIn(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(300, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
    )
}

fun getScreenExitTransition():  (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards ExitTransition?) = {
    fadeOut(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(300, easing = EaseOut),
        towards = AnimatedContentTransitionScope.SlideDirection.End
    )
}