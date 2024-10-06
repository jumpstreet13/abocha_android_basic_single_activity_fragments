package com.example.cupcake

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.screens.FlavorScreen
import com.example.cupcake.ui.screens.StartScreen
import com.example.cupcake.ui.screens.PickupScreen
import kotlinx.serialization.Serializable

@Serializable
object StartDestination

@Serializable
object FlavorDestination

@Serializable
object PickupDestination


@Composable
fun Navigation(sharedViewModel: OrderViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = StartDestination) {
        composable<StartDestination> { backStackEntry: NavBackStackEntry ->

            val start: StartDestination = backStackEntry.toRoute()

            StartScreen(
                sharedViewModel,
                onNavigateToFlavorScreen = navigateToFlavorScreen(navController),
                modifier = modifier
            )
        }
        composable<FlavorDestination> { backStackEntry: NavBackStackEntry ->

            val flavorDestination: FlavorDestination = backStackEntry.toRoute()

            FlavorScreen(
                sharedViewModel = sharedViewModel,
                onNavigateToPickupScreen = navigateToPickupScreen(navController),
                onNavigateUp = navigateUp(navController),
                modifier = modifier
            )
        }

        composable<PickupDestination> { backStackEntry: NavBackStackEntry ->

            val summaryDestination: PickupDestination = backStackEntry.toRoute()

            PickupScreen(
                sharedViewModel = sharedViewModel,
                onNavigateToStartScreen = navigateToStartScreen(navController),
                onNavigateUp = navigateUp(navController),
                modifier = modifier
            )
        }

    }
}

private fun navigateToFlavorScreen(navController: NavHostController) = {
    navController.navigate(FlavorDestination)
}

private fun navigateToStartScreen(navController: NavHostController) = {
    navController.navigate(StartDestination) {
        popUpTo(StartDestination) { inclusive = true }
    }
}

private fun navigateToPickupScreen(navController: NavHostController) = {
    navController.navigate(PickupDestination)
}

private fun navigateUp(navController: NavHostController) = {
    navController.navigateUp()
    Unit
}