package com.example.cupcake.compose.navigation

import androidx.navigation.NavHostController
import com.example.cupcake.compose.navigation.CupcakeDestinations.PICKUP_ROUTE
import com.example.cupcake.compose.navigation.CupcakeDestinations.SUMMARY_ROUTE
import com.example.cupcake.compose.navigation.CupcakeScreens.FLAVOR_SCREEN
import com.example.cupcake.compose.navigation.CupcakeScreens.PICKUP_SCREEN
import com.example.cupcake.compose.navigation.CupcakeScreens.START_SCREEN
import com.example.cupcake.compose.navigation.CupcakeScreens.SUMMARY_SCREEN

private object CupcakeScreens {
    const val START_SCREEN = "start"
    const val FLAVOR_SCREEN = "flavor"
    const val PICKUP_SCREEN = "pickup"
    const val SUMMARY_SCREEN = "summary"
}

object CupcakeDestinations {
    const val START_ROUTE = START_SCREEN
    const val FLAVOR_ROUTE = FLAVOR_SCREEN
    const val PICKUP_ROUTE = PICKUP_SCREEN
    const val SUMMARY_ROUTE = SUMMARY_SCREEN
}

class CupcakeNavigationActions(private val navController: NavHostController) {

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
        navController.navigate(PICKUP_ROUTE)
    }

    fun navigateToSummary() {
        navController.navigate(SUMMARY_ROUTE)
    }
}
