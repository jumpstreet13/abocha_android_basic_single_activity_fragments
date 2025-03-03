package com.example.cupcake.navigation

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
        controller.navigate(destination) {
            launchSingleTop = true
            restoreState = true
        }
    }

    fun backToStart() {
        controller.popBackStack(Destination.Start, false)
    }
}