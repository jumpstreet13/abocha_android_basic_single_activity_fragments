package com.example.cupcake.navigatoin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberCupcakeNavigation(
    navController: NavHostController = rememberNavController(),
): CupcakeNavigation = remember(navController) {
    CupcakeNavigation(navController)
}

@Stable
class CupcakeNavigation(
    val controller: NavHostController,
) {
    fun upPressed() = controller.navigateUp()

    fun toDestination(destination: Destination) {
        val route = destination
        if (route.toString() != controller.currentDestination?.route) {
            controller.navigate(route) {
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    fun backToStart() {
        controller.navigate(Destination.Start) {
            launchSingleTop = true
            restoreState = true
            popUpTo(Destination.Start) {
                inclusive = true
            }
        }
    }
}