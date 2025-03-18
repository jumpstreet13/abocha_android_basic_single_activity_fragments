package com.example.cupcake.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.asFlow
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.screen.FlavorScreen
import com.example.cupcake.screen.PickupScreen
import com.example.cupcake.screen.StartScreen
import com.example.cupcake.screen.SummaryScreen

@Composable
fun CupcakeApp(
    viewModel: OrderViewModel,
    sendToAnotherApp: () -> Unit
) {
    Surface {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Start
        ) {
            composable<Start> {
                StartScreen(orderClick = { count ->
                    viewModel.setQuantity(count)
                    navController.navigate(Flavor)
                })
            }
            composable<Flavor> {
                FlavorScreen(
                    viewModel = viewModel,
                    onFlavorClick = { viewModel.setFlavor(it) },
                    onCancelClick = {
                        viewModel.resetOrder()
                        navController.popBackStack(Start, false)
                    },
                    onNextClick = { navController.navigate(Pickup) },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable<Pickup> {
                PickupScreen(
                    viewModel = viewModel,
                    onDateClick = { viewModel.setDate(it) },
                    onCancelClick = {
                        viewModel.resetOrder()
                        navController.popBackStack(Start, false)
                    },
                    onNextClick = { navController.navigate(Summary) },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable<Summary> {

                SummaryScreen(
                    viewModel,
                    onSendClick = {
                        sendToAnotherApp()
                    },
                    onCancelClick = {
                        viewModel.resetOrder()
                        navController.popBackStack(Start, false)
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
