package com.example.cupcake

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    startScreenContent: @Composable () -> Unit,
    flavorScreenContent: @Composable () -> Unit,
    pickupScreenContent: @Composable () -> Unit,
    summaryScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Start.route
    ) {
        composable(Screen.Start.route) { startScreenContent() }
        composable(Screen.Flavor.route) { flavorScreenContent() }
        composable(Screen.Pickup.route) { pickupScreenContent() }
        composable(Screen.Summary.route) { summaryScreenContent() }
    }
}