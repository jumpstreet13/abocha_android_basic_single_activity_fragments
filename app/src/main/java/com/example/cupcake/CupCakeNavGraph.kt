package com.example.cupcake

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.compose.FlavorScreen
import com.example.cupcake.compose.StartScreen
import com.example.cupcake.compose.SummaryScreen
import com.example.cupcake.model.OrderViewModel

@Composable
fun CupCakeNavGraph(
    navController: NavHostController,
    viewModel: OrderViewModel,
    onSendOrderClick: (String) -> Unit
) {
    NavHost(navController = navController, startDestination = Navigation.START_SCREEN.route) {
        composable(
            route = Navigation.START_SCREEN.route,
            exitTransition = { exitTransition() },
            popEnterTransition = { enterTransition() }
        ) {
            StartScreen(navController, viewModel)
        }

        composable(
            route = Navigation.FLAVOR_SCREEN.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() }
        ) {
            FlavorScreen(navController, viewModel)
            BackHandler {
                navController.popBackStack()
            }
        }

        composable(
            route = Navigation.PICKUP_SCREEN.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() }
        ) {
            FlavorScreen(navController, viewModel, isFlavorScreen = false)
            BackHandler {
                navController.popBackStack()
            }
        }

        composable(
            route = Navigation.SUMMARY_SCREEN.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() }
        ) {
            SummaryScreen(navController, viewModel, onSendOrderClick)
            BackHandler {
                navController.popBackStack()
            }
        }
    }
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() =
    fadeOut(
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION,
            easing = LinearEasing
        )
    ) + slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.End,
        animationSpec = tween(durationMillis = 500, easing = EaseOut)
    )


private fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() =
    fadeIn(
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION,
            easing = LinearEasing
        )
    ) + slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Start,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION,
            easing = EaseIn
        )
    )

private const val ANIMATION_DURATION = 500

enum class Navigation(var route: String) {
    START_SCREEN("StartScreen"),
    FLAVOR_SCREEN("FlavorScreen"),
    PICKUP_SCREEN("PickupScreen"),
    SUMMARY_SCREEN("SummaryScreen")
}