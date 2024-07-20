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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.compose.core.CupcakeTheme
import com.example.cupcake.compose.screens.FlavorScreen
import com.example.cupcake.compose.screens.PickupScreen
import com.example.cupcake.compose.screens.StartScreen
import com.example.cupcake.compose.screens.SummaryScreen
import com.example.cupcake.model.OrderViewModel

/**
 * Activity for cupcake order flow.
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            val cancelOrder = remember {
                {
                    viewModel.resetOrder()
                    navController.popBackStack(Screens.START.value, false)
                }
            }

            CupcakeTheme {
                NavHost(navController = navController, startDestination = Screens.START.value) {
                    composable(Screens.START.value) {
                        StartScreen(onButtonClick = {
                            orderCupcake(quantity = it)
                            navController.navigate(Screens.FLAVOR.value)
                        })
                    }

                    composable(Screens.FLAVOR.value) {
                        val selectedFlavor = viewModel.flavor.observeAsState()
                        val subtotalPrice = viewModel.price.observeAsState()

                        FlavorScreen(
                            selectedFlavor = selectedFlavor,
                            subtotalPrice = subtotalPrice,
                            onSelect = viewModel::setFlavor,
                            onCancelOrder = {
                                cancelOrder()
                            },
                            onNext = {
                                navController.navigate(Screens.PICKUP.value)
                            }
                        )
                    }

                    composable(Screens.PICKUP.value) {
                        val dates = remember { viewModel.dateOptions }
                        val selectedDay = viewModel.date.observeAsState()
                        val subtotalPrice = viewModel.price.observeAsState()

                        PickupScreen(
                            dates = dates,
                            selectedDay = selectedDay,
                            subtotalPrice = subtotalPrice,
                            onSelect = viewModel::setDate,
                            onCancelOrder = {
                                cancelOrder()
                            },
                            onNext = {
                                navController.navigate(Screens.SUMMARY.value)
                            }
                        )
                    }

                    composable(Screens.SUMMARY.value) {
                        val quantity = viewModel.quantity.observeAsState()
                        val flavor = viewModel.flavor.observeAsState()
                        val date = viewModel.date.observeAsState()
                        val price = viewModel.price.observeAsState()
                        SummaryScreen(
                            quantity = quantity,
                            flavor = flavor,
                            pickupDate = date,
                            totalPrice = price,
                            onCancelOrder = {
                                cancelOrder()
                            },
                            onSendOrder = {
                                sendOrder()
                            }
                        )
                    }
                }
            }
        }
    }

    private fun orderCupcake(quantity: Int) {
        viewModel.setQuantity(quantity)

        if (viewModel.hasNoFlavorSet()) {
            viewModel.setFlavor(getString(R.string.vanilla))
        }
    }

    private fun sendOrder() {
        val numberOfCupcakes = viewModel.quantity.value ?: 0
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
            viewModel.flavor.value.toString(),
            viewModel.date.value.toString(),
            viewModel.price.value.toString()
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