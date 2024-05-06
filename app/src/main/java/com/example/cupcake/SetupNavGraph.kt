package com.example.cupcake

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.model.OrderViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController, paddingValues: PaddingValues, viewModel: OrderViewModel
) {

    val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? =
        {
            fadeIn(animationSpec = tween(300, easing = LinearEasing)) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        }
    val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = {
        fadeOut(animationSpec = tween(300, easing = LinearEasing)) + slideOutOfContainer(
            animationSpec = tween(300, easing = EaseOut),
            towards = AnimatedContentTransitionScope.SlideDirection.Left
        )
    }

    val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? =
        {
            fadeIn(animationSpec = tween(300, easing = LinearEasing)) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Right
            )
        }

    val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? =
        {
            fadeOut(animationSpec = tween(300, easing = LinearEasing)) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.Right
            )
        }

    NavHost(navController, startDestination = NavScreen.StartScreenNav.route) {
        composable(
            NavScreen.StartScreenNav.route,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            StartScreen(navController, viewModel)
        }
        composable(
            NavScreen.FlavorScreenNav.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            FlavorScreen(listOf(
                stringResource(R.string.vanilla),
                stringResource(R.string.chocolate),
                stringResource(R.string.red_velvet),
                stringResource(R.string.salted_caramel),
                stringResource(R.string.coffee)
            ), navController, viewModel)
        }
        composable(
            NavScreen.PickupScreenNav.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            PickupScreen(navController, viewModel)
        }
        composable(
            NavScreen.SummaryScreenNav.route, enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            SummaryScreen(navController, viewModel)
        }
    }
}

fun cancelOrder(navController: NavHostController, sharedViewModel: OrderViewModel) {
    // Reset order in view model
    sharedViewModel.resetOrder()

    navController.clearBackStack(NavScreen.StartScreenNav.route)

    // Navigate back to the [StartScreen] to start over
    navController.navigate(NavScreen.StartScreenNav.route) {
        popUpTo(0) {
            inclusive = true
        }
    }
}

fun goToNextScreen(navController: NavHostController, route: String) {
    navController.navigate(route)
}