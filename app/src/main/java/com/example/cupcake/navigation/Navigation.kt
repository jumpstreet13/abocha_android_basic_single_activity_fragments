package com.example.cupcake.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.animation.getScreenSlideInTransition
import com.example.cupcake.ui.animation.getScreenSlideOutTransition
import com.example.cupcake.ui.animation.slideInLeftToRightFullWidth
import com.example.cupcake.ui.animation.slideInRightToLeftFullWidth
import com.example.cupcake.ui.animation.slideOutRightToLeftFullWidth
import com.example.cupcake.ui.screens.FlavorScreen
import com.example.cupcake.ui.screens.PickupScreen
import com.example.cupcake.ui.screens.StartScreen
import com.example.cupcake.ui.screens.SummaryScreen
import kotlinx.serialization.Serializable


@Serializable
object StartDestination

@Serializable
object FlavorDestination

@Serializable
object PickupDestination

@Serializable
object SummaryDestination


@Composable
fun Navigation(sharedViewModel: OrderViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val actions = remember(navController) { NavigationActions(navController, sharedViewModel) }

    NavHost(navController = navController, startDestination = StartDestination) {
        composable<StartDestination>(
            enterTransition = slideInRightToLeftFullWidth,
            exitTransition = slideOutRightToLeftFullWidth,
            popEnterTransition = {
                getScreenSlideInTransition(sharedViewModel.isOrderCanceled.value)()
            },
        ) { _ ->
            StartScreen(
                sharedViewModel,
                onNavigateToFlavorScreen = actions.navigateToFlavorScreen,
                modifier = modifier
            )
        }
        composable<FlavorDestination>(
            enterTransition = slideInRightToLeftFullWidth,
            exitTransition = slideOutRightToLeftFullWidth,
            popEnterTransition = slideInLeftToRightFullWidth,
            popExitTransition = {
                getScreenSlideOutTransition(sharedViewModel.isOrderCanceled.value)()
            },
        ) { _ ->
            FlavorScreen(
                sharedViewModel = sharedViewModel,
                onNavigateUp = actions.navigateUp,
                onNavigateNext = actions.navigateToPickupScreen,
                onNavigateToStart = actions.navigateToStartScreen,
                modifier = modifier
            )
        }

        composable<PickupDestination>(
            enterTransition = slideInRightToLeftFullWidth,
            exitTransition = slideOutRightToLeftFullWidth,
            popEnterTransition = slideInLeftToRightFullWidth,
            popExitTransition = {
                getScreenSlideOutTransition(sharedViewModel.isOrderCanceled.value)()
            },
        ) { _ ->
            PickupScreen(
                sharedViewModel = sharedViewModel,
                onNavigateUp = actions.navigateUp,
                onNavigateNext = actions.navigateToSummaryScreen,
                onNavigateToStart = actions.navigateToStartScreen,
                modifier = modifier
            )
        }

        composable<SummaryDestination>(
            enterTransition = slideInRightToLeftFullWidth,
            exitTransition = slideOutRightToLeftFullWidth,
            popEnterTransition = slideInLeftToRightFullWidth,
            popExitTransition = {
                getScreenSlideOutTransition(sharedViewModel.isOrderCanceled.value)()
            },
        ) { _ ->
            SummaryScreen(
                sharedViewModel,
                onNavigateUp = actions.navigateUp,
                onNavigateToStart = actions.navigateToStartScreen,
                modifier = modifier
            )
        }
    }
}