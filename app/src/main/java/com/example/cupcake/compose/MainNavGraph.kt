package com.example.cupcake.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.model.OrderViewModel

object DestinationMain {
    const val START_ROUTE = "start"
    const val FLAVOR_ROUTE = "flawor"
    const val PICKUP_ROUTE = "pickup"
    const val SUMMARY_ROUTE = "summary"
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
            PickupScreen(
                price = viewModel.priceF.collectAsState(),
                dateOptions = viewModel.dateOptions,
                selectDate = viewModel.dateF,
                setDate = {
                    viewModel.setDate(it)
                },
                cancelOrder = {
                    viewModel.resetOrder()
                    navController.popBackStack(DestinationMain.START_ROUTE, inclusive = false)
                },
                goToNextScreen = {
                    navController.navigate(DestinationMain.SUMMARY_ROUTE)
                }
            )
        }

        composable(DestinationMain.SUMMARY_ROUTE) {

        }
    }
}