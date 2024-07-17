package com.example.cupcake.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

object DestinationMain {
    const val START_ROUTE = "start"
    const val FLAVOR_ROUTE = "flawor"
    const val PICKUP_ROUTE = "pickup"
}

@Composable
fun MainNavGraph(
    navController: NavHostController,
    viewModel: OrderViewModel
) {
    NavHost(
        navController = navController,
        startDestination = DestinationMain.START_ROUTE
    ) {
        composable(DestinationMain.START_ROUTE) {
            StartScreen {
                viewModel.setQuantity(it)
                navController.navigate(DestinationMain.FLAVOR_ROUTE)
            }
        }

        composable(DestinationMain.FLAVOR_ROUTE) {
            FlavorScreen(
                price = viewModel.priceF.collectAsState(),
                setFlavor = {
                    viewModel.setFlavor(it)
                },
                cancelOrder = {
                    viewModel.resetOrder()
                    navController.popBackStack(DestinationMain.START_ROUTE, inclusive = false)
                },
                goToNextScreen = {
                    navController.navigate(DestinationMain.PICKUP_ROUTE)
                }
            )
        }

        composable(DestinationMain.PICKUP_ROUTE) {

        }
    }
}