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
import com.example.cupcake.ui.screens.SummaryScreen
import kotlinx.serialization.Serializable

@Serializable
object StartDestination

@Serializable
object FlavorDestination

@Serializable
object PickupDestination

@Serializable
object SummaryDestination


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
                onNavigateNext = navigateToPickupScreen(navController),
                onNavigateToStart = navigateToStartScreen(navController),
                modifier = modifier
            )
        }

        composable<PickupDestination> { backStackEntry: NavBackStackEntry ->

            val summaryDestination: PickupDestination = backStackEntry.toRoute()

            PickupScreen(
                sharedViewModel = sharedViewModel,
                onNavigateNext = navigateToSummaryScreen(navController),
                onNavigateToStart = navigateToStartScreen(navController),
                modifier = modifier
            )
        }

        composable<SummaryDestination> { navBackStackEntry ->
            SummaryScreen(
                sharedViewModel,
                onNavigateNext = navigateToStartScreen(navController),
                onNavigateToStart = navigateToStartScreen(navController),
                modifier = modifier
            )
        }
    }
}

private fun navigateToFlavorScreen(navController: NavHostController): () -> Unit = {
    navController.navigate(FlavorDestination)
}

private fun navigateToStartScreen(navController: NavHostController): () -> Unit = {
    navController.navigate(StartDestination) {
        popUpTo(StartDestination) { inclusive = true }
    }
}

private fun navigateToPickupScreen(navController: NavHostController): () -> Unit = {
    navController.navigate(PickupDestination)
}

private fun navigateToSummaryScreen(navController: NavHostController): () -> Unit = {
    navController.navigate(SummaryDestination)
}

private fun navigateUp(navController: NavHostController): () -> Unit = {
    navController.navigateUp()
}