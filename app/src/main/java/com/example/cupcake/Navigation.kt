package com.example.cupcake
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.FlavorScreen
import com.example.cupcake.ui.StartScreen
import kotlinx.serialization.Serializable

@Serializable
object StartDestination

@Serializable
object FlavorDestination


@Composable
fun Navigation(sharedViewModel: OrderViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = StartDestination) {
        composable<StartDestination> { backStackEntry: NavBackStackEntry ->

            val start : StartDestination = backStackEntry.toRoute()

            StartScreen(sharedViewModel,
                onNavigateToFlavorScreen = {
                    navController.navigate(FlavorDestination)
                },
                modifier = modifier
            )
        }
        composable<FlavorDestination> { backStackEntry: NavBackStackEntry ->

            val flavorDestination : FlavorDestination = backStackEntry.toRoute()

            FlavorScreen(navController, modifier = modifier)
        }

    }
}

