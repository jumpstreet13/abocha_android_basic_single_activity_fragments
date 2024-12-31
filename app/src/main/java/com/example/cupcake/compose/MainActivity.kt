package com.example.cupcake.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.compose.feature.FlavorRoute
import com.example.cupcake.compose.feature.PickupRoute
import com.example.cupcake.compose.feature.StartRoute
import com.example.cupcake.compose.feature.SummaryRoute
import com.example.cupcake.compose.ui.util.enterTransition
import com.example.cupcake.compose.ui.util.exitTransition
import com.example.cupcake.compose.ui.util.popEnterTransition
import com.example.cupcake.compose.ui.util.popExitTransition
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.utils.Route

class MainActivity : AppCompatActivity() {

    private val viewModel: OrderViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }

    @Composable
    fun AppNavigation() {
        navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.START.name,
            enterTransition = { enterTransition },
            exitTransition = { exitTransition },
            popEnterTransition = { popEnterTransition },
            popExitTransition = { popExitTransition }
        ) {
            composable(Route.START.name) {
                StartRoute { navController.navigate(Route.FLAVOR.name) }
            }
            composable(Route.FLAVOR.name) {
                FlavorRoute(viewModel = viewModel,
                            onCancelOrder = {cancel()},
                            onNext = {navController.navigate(Route.PICKUP.name)})
            }
            composable(Route.PICKUP.name) {
                PickupRoute(viewModel = viewModel,
                            onCancelOrder = {cancel()},
                            onNext = {navController.navigate(Route.SUMMARY.name)})
            }
            composable(Route.SUMMARY.name) {
                SummaryRoute(viewModel = viewModel,
                             onCancelOrder = {cancel()},
                             onSendOrder = {send()})
            }
        }
    }

    private fun setCupcake(
        quantity: Int
    ) {
        viewModel.setQuantity(quantity)
        if (viewModel.hasNoFlavorSet()) {
            viewModel.setFlavor(getString(R.string.vanilla))
        }
    }

    private fun cancel() {
        viewModel.resetOrder()
        navController.popBackStack(Route.START.name, false)
    }


    private fun send() {
        // Construct the order summary text with information from the view model
        val numberOfCupcakes = viewModel.quantity.value ?: 0
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
            viewModel.flavor.value,
            viewModel.date.value,
            viewModel.price.value
        )

        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        if (packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }
}