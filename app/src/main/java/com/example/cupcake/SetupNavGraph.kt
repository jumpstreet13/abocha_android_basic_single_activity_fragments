package com.example.cupcake

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.model.OrderViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: OrderViewModel
) {
    NavHost(navController, startDestination = NavScreen.StartScreenNav.route) {
        composable(NavScreen.StartScreenNav.route) {
            StartScreen(navController, viewModel)
        }
        composable(NavScreen.FlavorScreenNav.route) {
            FlavorScreen(navController, viewModel)
        }
        composable(NavScreen.PickupScreenNav.route) {
            PickupScreen(navController, viewModel)
        }
        composable(NavScreen.SummaryScreenNav.route) {
            SummaryScreen(navController, viewModel)
        }
    }
}

fun cancelOrder(navController: NavHostController, sharedViewModel: OrderViewModel) {
    // Reset order in view model
    sharedViewModel.resetOrder()

    // Navigate back to the [StartScreen] to start over
    navController.navigate(NavScreen.StartScreenNav.route)
}

fun goToNextScreen(navController: NavHostController, route: String) {
    navController.navigate(route)
}