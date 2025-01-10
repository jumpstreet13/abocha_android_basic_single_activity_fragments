package com.example.cupcake.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.navigation.CupcakeDestinations.FLAVOR_SCREEN
import com.example.cupcake.navigation.CupcakeDestinations.PICKUP_SCREEN
import com.example.cupcake.navigation.CupcakeDestinations.START_SCREEN
import com.example.cupcake.navigation.CupcakeDestinations.SUMMARY_SCREEN
import com.example.cupcake.ui.flavor.FlavorScreen
import com.example.cupcake.ui.pickup.PickupScreen
import com.example.cupcake.ui.start.StartScreen
import com.example.cupcake.ui.summary.SummaryScreen

@Composable
fun CupcakeNavGraph(
    navController: NavHostController,
    viewModel: OrderViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = START_SCREEN,
    ) {
        composable(
            route = START_SCREEN,
            enterTransition = {
                scaleIn(
                    animationSpec = tween(DURATION_MILLIS, DELAY_MILLIS),
                    initialScale = SCALE
                )
            },
            exitTransition = {
                scaleOut(
                    animationSpec = tween(DURATION_MILLIS, DELAY_MILLIS),
                    targetScale = SCALE
                )
            }
        ) {
            StartScreen(
                viewModel = viewModel,
                onQuantityCupCakeClick = {
                    navController.navigate(FLAVOR_SCREEN)
                }
            )
        }

        composable(
            route = FLAVOR_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
            exitTransition = {
                scaleOut(
                    animationSpec = tween(
                        durationMillis = DURATION_MILLIS,
                        delayMillis = DELAY_MILLIS
                    ), targetScale = SCALE
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(DURATION_MILLIS_500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(DURATION_MILLIS_500)
                )
            }
        ) {
            FlavorScreen(
                onBackClick = { navController.popBackStack() },
                onOpenStartScreen = {
                    navController.navigate(START_SCREEN) {
                        popUpTo(START_SCREEN) {
                            inclusive = true
                        }
                    }
                },
                onOpenPickupScreen = {
                    navController.navigate(PICKUP_SCREEN)
                },
                viewModel = viewModel
            )
        }
        composable(
            route = PICKUP_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(DURATION_MILLIS_500)
                )
            },
            exitTransition = {
                scaleOut(
                    animationSpec = tween(
                        durationMillis = DURATION_MILLIS,
                        delayMillis = DELAY_MILLIS
                    ), targetScale = SCALE
                ) + fadeOut(tween(delayMillis = DELAY_MILLIS))
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(DURATION_MILLIS_500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(DURATION_MILLIS_500)
                )
            }
        ) {
            PickupScreen(
                onBackClick = { navController.popBackStack() },
                onOpenStartScreen = {
                    navController.navigate(START_SCREEN) {
                        popUpTo(START_SCREEN) {
                            inclusive = true
                        }
                    }
                },
                onOpenSummaryScreen = {
                    navController.navigate(SUMMARY_SCREEN)
                },
                viewModel = viewModel
            )
        }

        composable(
            route = SUMMARY_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(DURATION_MILLIS_500)
                )
            },
            exitTransition = {
                scaleOut(
                    animationSpec = tween(
                        durationMillis = DURATION_MILLIS,
                        delayMillis = DELAY_MILLIS
                    ), targetScale = SCALE
                ) + fadeOut(tween(delayMillis = DELAY_MILLIS))
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(DURATION_MILLIS_500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(DURATION_MILLIS_500)
                )
            }
        ) {
            SummaryScreen(
                onBackClick = { navController.popBackStack() },
                onOpenStartScreen = {
                    navController.navigate(START_SCREEN) {
                        popUpTo(START_SCREEN) {
                            inclusive = true
                        }
                    }
                },
                viewModel = viewModel
            )
        }
    }
}

object CupcakeDestinations {
    const val START_SCREEN = "cupcake_start"
    const val FLAVOR_SCREEN = "cupcake_flavor"
    const val PICKUP_SCREEN = "cupcake_pickup"
    const val SUMMARY_SCREEN = "cupcake_summary"
}

private const val DURATION_MILLIS_500 = 500
private const val DURATION_MILLIS = 300
private const val DELAY_MILLIS = 100
private const val SCALE = 0.9f