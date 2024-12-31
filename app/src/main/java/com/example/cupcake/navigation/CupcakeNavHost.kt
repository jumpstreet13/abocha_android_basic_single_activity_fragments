package com.example.cupcake.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.compose.feature.flavorScreen
import com.example.cupcake.compose.feature.pickupScreen
import com.example.cupcake.compose.feature.startScreen
import com.example.cupcake.compose.feature.summaryRoute
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.utils.Route

@Composable
fun CupcakeNavHost(
    viewModel: OrderViewModel,
    onOrderCupcake: (Int) -> Unit,
    onSendOrder: () -> Unit
) {
    val navController = rememberNavController()

    val cancelOrder = remember {
        {
            viewModel.resetOrder()
            navController.popBackStack(
                Route.START.toString(),
                false,
                saveState = false
            )
        }
    }

    NavHost(
        navController = navController,
        startDestination = Route.START.toString()
    ) {
        startScreen {
            onOrderCupcake.invoke(it)
            navController.navigate(Route.FLAVOR.toString())
        }

        flavorScreen(
            viewModel,
            { cancelOrder() },
            { navController.navigate(Route.PICKUP.toString()) }
        )

        pickupScreen(
            viewModel,
            { cancelOrder() },
            { navController.navigate(Route.SUMMARY.toString())  }
        )

        summaryRoute(
            viewModel,
            { cancelOrder() },
            onSendOrder
        )
    }
}
