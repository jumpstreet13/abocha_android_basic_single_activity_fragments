package com.example.cupcake.navigation

import androidx.navigation.NavController
import com.example.cupcake.model.ScreenTransitionHandler

class NavigationActions(navController: NavController, screenTransitionHandler: ScreenTransitionHandler) {
    val navigateToFlavorScreen: () -> Unit = {
        screenTransitionHandler.setScreenTransitionInProgress(true)
        navController.navigate(FlavorDestination)
    }

    val navigateToStartScreen: () -> Unit = {
        screenTransitionHandler.setScreenTransitionInProgress(true)
        navController.popBackStack(StartDestination, inclusive = false)
    }

    val navigateToPickupScreen: () -> Unit = {
        screenTransitionHandler.setScreenTransitionInProgress(true)
        navController.navigate(PickupDestination)
    }

    val navigateToSummaryScreen: () -> Unit = {
        screenTransitionHandler.setScreenTransitionInProgress(true)
        navController.navigate(SummaryDestination)
    }

    val navigateUp: () -> Unit = {
        screenTransitionHandler.setScreenTransitionInProgress(true)
        navController.navigateUp()
    }
}