package com.example.cupcake.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.navigation.CupcakeDestinations.FLAVOR_SCREEN
import com.example.cupcake.navigation.CupcakeDestinations.PICKUP_SCREEN
import com.example.cupcake.navigation.CupcakeDestinations.START_SCREEN
import com.example.cupcake.navigation.CupcakeDestinations.SUMMARY_SCREEN
import com.example.cupcake.screens.FlavorScreen
import com.example.cupcake.screens.PickupScreen
import com.example.cupcake.screens.StartScreen
import com.example.cupcake.screens.SummaryScreen

@Composable
fun CupcakeNavGraph(
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    navActions: NavigationActions = remember(navHostController) {
        NavigationActions(navHostController)
    }
) {
    val startDestination: String = START_SCREEN

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination,
    ) {
        composable(
            route = START_SCREEN,
            enterTransition = { scaleIntoContainer() },
            exitTransition = { scaleOutOfContainer() }
        ) {
            StartScreen(
                viewModel = viewModel,
                onQuantityCupCakeClick = { navActions.navigateToFlavor() }
            )
        }

        composable(
            route = FLAVOR_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
            exitTransition = { scaleOutOfContainer() },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            FlavorScreen(
                onBackClick = { navHostController.popBackStack() },
                onOpenStartScreen = { navActions.navigateToStart() },
                onOpenPickupScreen = { navActions.navigateToPickup() },
                viewModel = viewModel
            )
        }
        composable(
            route = PICKUP_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = { scaleOutOfContainer() },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            PickupScreen(
                onBackClick = { navHostController.popBackStack() },
                onOpenStartScreen = { navActions.navigateToStart() },
                onOpenSummaryScreen = { navActions.navigateToSummary() },
                viewModel = viewModel
            )
        }

        composable(
            route = SUMMARY_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = { scaleOutOfContainer() },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            SummaryScreen(
                onBackClick = { navHostController.popBackStack() },
                onOpenStartScreen = { navActions.navigateToStart() },
                viewModel = viewModel
            )
        }
    }
}


class NavigationActions(private val navController: NavHostController) {

    fun navigateToStart() {
        navController.navigate(START_SCREEN) {
            popUpTo(START_SCREEN) {
                inclusive = true
            }
        }
    }

    fun navigateToFlavor() {
        navController.navigate(FLAVOR_SCREEN)
    }

    fun navigateToPickup() {
        navController.navigate(PICKUP_SCREEN)
    }

    fun navigateToSummary() {
        navController.navigate(SUMMARY_SCREEN)
    }
}

object CupcakeDestinations {
    const val START_SCREEN = "start"
    const val FLAVOR_SCREEN = "flavor"
    const val PICKUP_SCREEN = "pickup"
    const val SUMMARY_SCREEN = "summary"
}