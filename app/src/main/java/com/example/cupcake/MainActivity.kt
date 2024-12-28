/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake

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
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.screen.FlavorScreen
import com.example.cupcake.screen.PickupScreen
import com.example.cupcake.screen.StartScreen
import com.example.cupcake.screen.SummaryScreen
import com.example.cupcake.util.enterTransition
import com.example.cupcake.util.exitTransition
import com.example.cupcake.util.popEnterTransition
import com.example.cupcake.util.popExitTransition

/**
 * Activity for cupcake order flow.
 */
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
                StartRoute()
            }
            composable(Route.FLAVOR.name) {
                FlavorRoute()
            }
            composable(Route.PICKUP.name) {
                PickupRoute()
            }
            composable(Route.SUMMARY.name) {
                SummaryRoute()
            }
        }
    }

    @Composable
    fun StartRoute() {
        StartScreen {
            setCupcake(it)
            navController.navigate(Route.FLAVOR.name)
        }
    }

    @Composable
    fun FlavorRoute() {
        FlavorScreen(
            viewModel = viewModel,
            onCancel = { cancel() },
            onNext = { navController.navigate(Route.PICKUP.name) }
        )
    }

    @Composable
    fun PickupRoute() {
        PickupScreen(
            viewModel = viewModel,
            onCancel = { cancel() },
            onNext = { navController.navigate(Route.SUMMARY.name) }
        )
    }

    @Composable
    fun SummaryRoute() {
        SummaryScreen(
            viewModel = viewModel,
            onCancel = { cancel() },
            onSend = { send() }
        )
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