package com.example.cupcake.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.presentation.screens.AllScreen
import com.example.cupcake.presentation.screens.StartScreen
import com.example.cupcake.presentation.screens.FlavorScreen
import com.example.cupcake.presentation.screens.PickupScreen
import com.example.cupcake.presentation.screens.SummaryScreen

@Composable
fun Navigation(viewModel: OrderViewModel, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AllScreen.StartScreen.route
    ) {
        composable(route = AllScreen.StartScreen.route) {
            StartScreen(navController, viewModel)
        }
        composable(route = AllScreen.FlavorScreen.route) {
            val radioOptions = listOf("Vanilla", "Chocolate", "Red Velvet", "Salted Caramel", "Coffee")
            FlavorScreen( radioOptions,navController, viewModel, true)
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
