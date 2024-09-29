package com.example.cupcake.compose.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.compose.screen.FlavorScreen
import com.example.cupcake.compose.screen.PickupScreen
import com.example.cupcake.compose.screen.StartScreen
import com.example.cupcake.compose.screen.SummaryScreen
import com.example.cupcake.model.OrderViewModel

@Composable
fun CupcakeNavGraph(
    viewModel: OrderViewModel,
    onSendOrderClick: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = CupcakeDestinations.START_ROUTE,
    navActions: CupcakeNavigationActions = remember(navController) {
        CupcakeNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = CupcakeDestinations.START_ROUTE,
            enterTransition = {
                scaleIntoContainer()
            },
            exitTransition = {
                scaleOutOfContainer()
            },
        ) {
            StartScreen(
                viewModel = viewModel,
                onOpenFlavorScreen = { navActions.navigateToFlavor() }
            )
        }
        composable(
            route = CupcakeDestinations.FLAVOR_ROUTE,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                scaleOutOfContainer()
            },
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
                onBack = { navController.popBackStack() },
                onOpenStartScreen = { navActions.navigateToStart() },
                onOpenPickupScreen = { navActions.navigateToPickup() },
                viewModel = viewModel
            )
        }
        composable(
            route = CupcakeDestinations.PICKUP_ROUTE,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                scaleOutOfContainer()
            },
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
                onBack = { navController.popBackStack() },
                onOpenStartScreen = { navActions.navigateToStart() },
                onOpenSummaryScreen = { navActions.navigateToSummary() },
                viewModel = viewModel
            )
        }
        composable(
            route = CupcakeDestinations.SUMMARY_ROUTE,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                scaleOutOfContainer()
            },
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
                onBack = { navController.popBackStack() },
                onSendOrderClick = onSendOrderClick,
                onOpenStartScreen = { navActions.navigateToStart() },
                viewModel = viewModel
            )
        }
    }
}
