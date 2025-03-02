package com.example.cupcake.navigatoin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberCupcakeNavController(
    navController: NavHostController = rememberNavController()
): CupcakeNavController = remember(navController) {
    CupcakeNavController(navController)
}

@Stable
class CupcakeNavController(
    val controller: NavHostController
) {
    fun upPressed() = controller.navigateUp()

    fun navigateToRoute(route: Destination) {
        if (route.toString() != controller.currentDestination?.route) {
            controller.navigate(route.toString()) {
                launchSingleTop
            }
        }
    }
}