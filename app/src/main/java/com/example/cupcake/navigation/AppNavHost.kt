package com.example.cupcake.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.screens.FlavorScreen
import com.example.cupcake.screens.PickUpScreen
import com.example.cupcake.screens.StartScreen
import com.example.cupcake.screens.SummaryScreen

@Composable
fun AppNavHost(navController: NavHostController, viewModel: OrderViewModel) =
    NavHost(navController = navController, startDestination = StartDestination.route()) {
        composable(StartDestination.route()) {
            StartScreen(
                viewModel = viewModel,
                onNavigateToFlavor = { navController.navigate(FlavorDestination.route()) }
            )
        }
        composable(FlavorDestination.route()) {
            FlavorScreen(
                viewModel = viewModel,
                onNavigateToBack = { navController.navigateUp() },
                onNavigateToPickUpScreen = { navController.navigate(PickUpDestination.route()) }
            )
        }
        composable(PickUpDestination.route()) {
            PickUpScreen(
                viewModel = viewModel,
                onNavigateToBack = { navController.navigateUp() },
                onCancel = { navController.popBackStack(StartDestination.route(), false) },
                onNavigateToSummary = { navController.navigate(SummaryDestination.route()) }
            )
        }
        composable(SummaryDestination.route()) {
            SummaryScreen(
                viewModel = viewModel,
                onNavigateToBack = { navController.navigateUp() },
                onCancel = { navController.popBackStack(StartDestination.route(), false) }
            )
        }
    }