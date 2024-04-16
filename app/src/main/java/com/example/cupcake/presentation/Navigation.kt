package com.example.cupcake.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.R
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.presentation.screens.AllScreen
import com.example.cupcake.presentation.screens.FlavorScreen
import com.example.cupcake.presentation.screens.StartScreen
import com.example.cupcake.presentation.screens.SummaryScreen

private const val TIME_FADE = 500
private const val TIME_SLIDE = 1000
@Composable
fun Navigation(viewModel: OrderViewModel, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AllScreen.StartScreen.route,
        enterTransition = {
            fadeIn(animationSpec = tween(TIME_FADE)) +
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(TIME_SLIDE)
                    )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(TIME_FADE)) +
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(TIME_SLIDE)
                    )
            },
        popEnterTransition = {
            fadeIn(animationSpec = tween(TIME_FADE)) +
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(TIME_SLIDE)
                    )
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(TIME_FADE)) +
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(TIME_SLIDE)
                    )
        },
    ) {
        composable(
            route = AllScreen.StartScreen.route,
        ) {
            StartScreen(navController, viewModel)
        }
        composable(route = AllScreen.FlavorScreen.route) {
            val radioOptions =
                listOf(
                    stringResource(id = R.string.flavor),
                    stringResource(id = R.string.chocolate),
                    stringResource(id = R.string.red_velvet),
                    stringResource(id = R.string.salted_caramel),
                    stringResource(id = R.string.coffee),
                )
            FlavorScreen(radioOptions, navController, viewModel, true)
            BackHandler {
                navController.popBackStack()
            }
        }
        composable(route = AllScreen.PickupScreen.route) {
            val radioOptions = viewModel.dateOptions
            FlavorScreen(radioOptions, navController, viewModel, false)
            BackHandler {
                navController.popBackStack()
            }
        }
        composable(route = AllScreen.SummaryScreen.route) {
            SummaryScreen(navController, viewModel)
            BackHandler {
                navController.popBackStack()
            }
        }
    }
}
