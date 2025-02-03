package com.example.cupcake

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.model.OrderViewModel

@Composable
fun ScreenLauncher(viewModel: OrderViewModel = viewModel(), sendOrder: (String) -> Unit) {
    val navigationState = rememberNavigationState()

    AppNavGraph(
        navHostController = navigationState.navHostController,
        startScreenContent = {
            StartScreen(
                viewModel = viewModel,
                navigationState = navigationState
            )
        },
        flavorScreenContent = {
            FlavorScreen(
                viewModel = viewModel,
                navigationState = navigationState
            )
        },
        pickupScreenContent = {
            PickupScreen(
                viewModel = viewModel,
                navigationState = navigationState
            )
        },
        summaryScreenContent = {
            SummaryScreen(
                viewModel = viewModel,
                navigationState = navigationState,
                sendOrder = sendOrder
            )
        }
    )
}