package com.example.cupcake.screens

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.Screen
import com.example.cupcake.appTheme.AppTheme
import com.example.cupcake.appTheme.CurrentAppTheme
import com.example.cupcake.model.OrderViewModel


@Composable
fun RootScreenElement(
    isDarkTheme: Boolean,
    viewModel: OrderViewModel,
    sendToAnotherApp: () -> Unit
) {
    CurrentAppTheme(
        isDarkTheme
    ) {

        Surface(color = AppTheme.colors.colorBackground) {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screen.Start
            ) {

                composable<Screen.Start> {
                    StartScreen(
                        onOrderCakesClick = { quantity ->
                            viewModel.setQuantity(quantity)
                            navController.navigate(Screen.Flavor)
                        }
                    )
                }

                composable<Screen.Flavor> {
                    FlavorsScreen(
                        selectedFlavor = when {
                            viewModel.hasNoFlavorSet() -> null
                            else -> viewModel.flavor.value
                        },
                        price = viewModel.price,
                        onFlavorSelected = { viewModel.setFlavor(it) },
                        onCancelClick = { navController.popBackStack() },
                        onNextClick = { navController.navigate(Screen.Pickup) }
                    )
                }

                composable<Screen.Pickup> {
                    PickupScreen(
                        selectedDate = viewModel.date.value ?: viewModel.dateOptions.first(),
                        pickupDates = viewModel.dateOptions,
                        price = viewModel.price,
                        onDateSelected = { viewModel.setDate(it) },
                        onCancelClick = { navController.popBackStack() },
                        onNextClick = { navController.navigate(Screen.Summary) }
                    )
                }

                composable<Screen.Summary> {
                    SummaryScreen(
                        quantity = viewModel.quantity.value ?: 0,
                        flavor = viewModel.flavor.value ?: "",
                        pickupDate = viewModel.date.value ?: "",
                        price = (viewModel.price.value ?: "").toString(),
                        onCancelClick = {
                            navController.popBackStack(Screen.Start, false)
                            viewModel.resetOrder()
                        },
                        onSendClick = { sendToAnotherApp() }
                    )
                }

            }
        }

    }
}