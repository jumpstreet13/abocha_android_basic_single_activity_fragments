package com.example.cupcake.nav

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.ChooseFlavorScreen
import com.example.cupcake.ui.PickupDateScreen
import com.example.cupcake.ui.StartScreen
import com.example.cupcake.ui.SummaryScreen

@Composable
fun CupcakeNavHost(
    viewModel: OrderViewModel,
    navController: NavHostController,
    modifier: Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Start.route,
        modifier = modifier
            .padding(dimensionResource(R.dimen.side_margin))
    ) {
        composable(
            route = Start.route,
            enterTransition = {
                fadeIn()
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it }
                )
            }
        ) {
            StartScreen(onAmountSelected = {
                viewModel.setQuantity(it)
                //navController.navigateSingleTopTo(ChooseFlavor.route)
                navController.navigate(ChooseFlavor.route)
            })
        }
        composable(
            route = ChooseFlavor.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }
                )
            },
            exitTransition = {
                ExitTransition.None
            }
        ) {
            ChooseFlavorScreen(uiState,
                onFlavorSelected = {
                    viewModel.setFlavor(it)
                },
                onClickCancel = {
                    viewModel.resetOrder()
                    navController.popBackStack(Start.route, false)
                }, onClickNext = {
                    //navController.navigateSingleTopTo(PickupDate.route)
                    navController.navigate(PickupDate.route)
                }
            )
        }
        composable(route = PickupDate.route) {
            PickupDateScreen(uiState,
                onDateSelected = {
                    viewModel.setDate(it)
                },
                onClickCancel = {
                    viewModel.resetOrder()
                    navController.popBackStack(Start.route, false)
                }, onClickNext = {
                    navController.navigate(Summary.route)
                }
            )
        }
        composable(route = Summary.route) {
            SummaryScreen(uiState,
                onClickCancel = {
                    viewModel.resetOrder()
                    navController.popBackStack(Start.route, false)
                }, onClickSend = {
                    viewModel.sendOrder(context)
                }
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }