package com.example.cupcake.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.custom_layout.CustomLayout
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.presentation.flavor.FlavorScreen
import com.example.cupcake.presentation.pickup.PickupScreen
import com.example.cupcake.presentation.start.StartScreen
import com.example.cupcake.presentation.summary.SummaryScreen

@Composable
fun NavGraph(
    sendOtherApp: (String) -> Unit = {}
) {
    val navHostController = rememberNavController()
    val viewModel: OrderViewModel = viewModel()

    NavHost(navController = navHostController, startDestination = Screen.Start) {

        composable<Screen.Start> {
            StartScreen(
                goCustom = {
                    navHostController.navigate(Screen.Custom)
                },
                onClickOrderButton = {
                    viewModel.setQuantity(it)
                    navHostController.navigate(Screen.Flavor)
                })
        }
        composable<Screen.Flavor> {
            FlavorScreen(
                viewModel = viewModel,
                onClickBack = navHostController::popBackStack,
                onClickNext = { navHostController.navigate(Screen.Pickup) }
            )
        }
        composable<Screen.Pickup> {
            PickupScreen(
                viewModel = viewModel,
                onClickBack = navHostController::popBackStack,
                onClickCancel = { navHostController.popBackStack(Screen.Start, false) },
                onClickNext = { navHostController.navigate(Screen.Summary) }
            )
        }
        composable<Screen.Summary> {
            SummaryScreen(
                viewModel = viewModel,
                onClickBack = navHostController::popBackStack,
                onClickCancel = { navHostController.popBackStack(Screen.Start, false) },
                onClickSend = sendOtherApp
            )
        }
        composable<Screen.Custom> {
            CustomLayout(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Text("asasa")
                Text("bbbba")
                Text("ccccc")
                Text("ddddd")
                Text("fffff")
            }
        }
    }


}