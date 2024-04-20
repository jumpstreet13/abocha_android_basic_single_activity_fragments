package com.example.cupcake

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.compose.FlavorScreen
import com.example.cupcake.compose.StartScreen
import com.example.cupcake.compose.SummaryScreen
import com.example.cupcake.model.OrderViewModel

@Composable
fun CupCakeNavGraph(
    navController: NavHostController,
    viewModel: OrderViewModel,
    onSendOrderClick: (String) -> Unit
) {
    NavHost(navController = navController, startDestination = Navigation.START_SCREEN.route) {
        composable(Navigation.START_SCREEN.route) {
            StartScreen(navController, viewModel)
        }

        composable(Navigation.FLAVOR_SCREEN.route) {
            FlavorScreen(navController, viewModel)
            BackHandler {
                navController.popBackStack()
            }
        }

        composable(Navigation.PICKUP_SCREEN.route) {
            FlavorScreen(navController, viewModel, isFlavorScreen = false)
            BackHandler {
                navController.popBackStack()
            }
        }

        composable(Navigation.SUMMARY_SCREEN.route) {
            SummaryScreen(navController, viewModel, onSendOrderClick)
            BackHandler {
                navController.popBackStack()
            }
        }
    }
}

enum class Navigation(var route: String) {
    START_SCREEN("StartScreen"),
    FLAVOR_SCREEN("FlavorScreen"),
    PICKUP_SCREEN("PickupScreen"),
    SUMMARY_SCREEN("SummaryScreen")
}