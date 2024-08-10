package com.example.cupcake.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.compose.feature.FLAVOR_ROUTE
import com.example.cupcake.compose.feature.PICKUP_ROUTE
import com.example.cupcake.compose.feature.START_ROUTE
import com.example.cupcake.compose.feature.SUMMARY_ROUTE
import com.example.cupcake.compose.feature.flavorScreen
import com.example.cupcake.compose.feature.pickupScreen
import com.example.cupcake.compose.feature.startScreen
import com.example.cupcake.compose.feature.summaryRoute
import com.example.cupcake.model.OrderViewModel

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
            navController.popBackStack(START_ROUTE, false)
        }
    }

    NavHost(
        navController = navController,
        startDestination = START_ROUTE
    ) {
        startScreen {
            onOrderCupcake.invoke(it)
            navController.navigate(FLAVOR_ROUTE)
        }

        flavorScreen(
            viewModel,
            { cancelOrder() },
            { navController.navigate(PICKUP_ROUTE) }
        )

        pickupScreen(
            viewModel,
            { cancelOrder() },
            { navController.navigate(SUMMARY_ROUTE)  }
        )

        summaryRoute(
            viewModel,
            { cancelOrder() },
            onSendOrder
        )
    }
}
