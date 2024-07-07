package com.example.cupcake.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.screens.FlavorScreen
import com.example.cupcake.screens.PickUpScreen
import com.example.cupcake.screens.StartScreen
import com.example.cupcake.screens.SummaryScreen

@Composable
fun AppNavHost(navController: NavHostController, viewModel: OrderViewModel) =
    NavHost(
        navController = navController,
        startDestination = StartDestination.route(),
        enterTransition = { scaleIntoAnimation() },
        exitTransition = { scaleOutAnimation() }
    ) {
        composable(
            route = StartDestination.route(),
        ) {
            StartScreen(
                viewModel = viewModel,
                onNavigateToFlavor = { navController.navigate(FlavorDestination.route()) }
            )
        }
        composable(FlavorDestination.route()) {
            FlavorScreen(
                viewModel = viewModel,
                onNavigateToBack = { navController.navigateUp() },
                onNavigateToPickUpScreen = { navController.navigate(PickUpDestination.route()) }
            )
        }
        composable(PickUpDestination.route()) {
            PickUpScreen(
                viewModel = viewModel,
                onNavigateToBack = { navController.navigateUp() },
                onCancel = { navController.popBackStack(StartDestination.route(), false) },
                onNavigateToSummary = { navController.navigate(SummaryDestination.route()) }
            )
        }
        composable(SummaryDestination.route()) {
            SummaryScreen(
                viewModel = viewModel,
                onNavigateToBack = { navController.navigateUp() },
                onCancel = { navController.popBackStack(StartDestination.route(), false) }
            )
        }
    }

private fun scaleIntoAnimation(): EnterTransition =
    scaleIn(animationSpec = tween(durationMillis = 220, delayMillis = 90)) +
            fadeIn(animationSpec = tween(durationMillis = 220, delayMillis = 90))

private fun scaleOutAnimation(): ExitTransition =
    scaleOut(animationSpec = tween(durationMillis = 220, delayMillis = 90)) +
            fadeOut(tween(delayMillis = 90))