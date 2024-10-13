package com.example.cupcake.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.model.ScreenTransitionHandler
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

    NavHost(navController = navController, startDestination = StartDestination) {
        composable<StartDestination>(
            enterTransition = slideInRightToLeftFullWidth,
            exitTransition = slideOutRightToLeftFullWidth,
            popEnterTransition = {
                getScreenSlideInTransition(sharedViewModel.isOrderCanceled)()
            },
        ) { _ ->
            StartScreen(
                sharedViewModel,
                onNavigateToFlavorScreen = navigateToFlavorScreen(navController, sharedViewModel),
                modifier = modifier
            )
        }
        composable<FlavorDestination>(
            enterTransition = slideInRightToLeftFullWidth,
            exitTransition = slideOutRightToLeftFullWidth,
            popEnterTransition = slideInLeftToRightFullWidth,
            popExitTransition = {
                getScreenSlideOutTransition(sharedViewModel.isOrderCanceled)()
            },
        ) { _ ->
            FlavorScreen(
                sharedViewModel = sharedViewModel,
                onNavigateUp = navigateUp(navController, sharedViewModel),
                onNavigateNext = navigateToPickupScreen(navController, sharedViewModel),
                onNavigateToStart = navigateToStartScreen(navController, sharedViewModel),
                modifier = modifier
            )
        }

        composable<PickupDestination>(
            enterTransition = slideInRightToLeftFullWidth,
            exitTransition = slideOutRightToLeftFullWidth,
            popEnterTransition = slideInLeftToRightFullWidth,
            popExitTransition = {
                getScreenSlideOutTransition(sharedViewModel.isOrderCanceled)()
            },
        ) { _ ->
            PickupScreen(
                sharedViewModel = sharedViewModel,
                onNavigateUp = navigateUp(navController, sharedViewModel),
                onNavigateNext = navigateToSummaryScreen(navController, sharedViewModel),
                onNavigateToStart = navigateToStartScreen(navController, sharedViewModel),
                modifier = modifier
            )
        }

        composable<SummaryDestination>(
            enterTransition = slideInRightToLeftFullWidth,
            exitTransition = slideOutRightToLeftFullWidth,
            popEnterTransition = slideInLeftToRightFullWidth,
            popExitTransition = {
                getScreenSlideOutTransition(sharedViewModel.isOrderCanceled)()
            },
        ) { _ ->
            SummaryScreen(
                sharedViewModel,
                onNavigateUp = navigateUp(navController, sharedViewModel),
                onNavigateToStart = navigateToStartScreen(navController, sharedViewModel),
                modifier = modifier
            )
        }
    }
}

private fun navigateToFlavorScreen(
    navController: NavHostController,
    screenTransitionHandler: ScreenTransitionHandler
): () -> Unit = {
    screenTransitionHandler.setScreenTransitionInProgress(true)
    navController.navigate(FlavorDestination)
}

private fun navigateToStartScreen(
    navController: NavHostController,
    screenTransitionHandler: ScreenTransitionHandler
): () -> Unit = {
    screenTransitionHandler.setScreenTransitionInProgress(true)
    navController.popBackStack(StartDestination, inclusive = false)
}

private fun navigateToPickupScreen(
    navController: NavHostController,
    screenTransitionHandler: ScreenTransitionHandler
): () -> Unit = {
    screenTransitionHandler.setScreenTransitionInProgress(true)
    navController.navigate(PickupDestination)
}

private fun navigateToSummaryScreen(
    navController: NavHostController,
    screenTransitionHandler: ScreenTransitionHandler
): () -> Unit = {
    screenTransitionHandler.setScreenTransitionInProgress(true)
    navController.navigate(SummaryDestination)
}

private fun navigateUp(
    navController: NavHostController,
    screenTransitionHandler: ScreenTransitionHandler
): () -> Unit = {
    screenTransitionHandler.setScreenTransitionInProgress(true)
    navController.navigateUp()
}