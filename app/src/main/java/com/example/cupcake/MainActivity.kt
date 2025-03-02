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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.navigatoin.CupcakeNavigation
import com.example.cupcake.navigatoin.Destination
import com.example.cupcake.navigatoin.rememberCupcakeNavigation
import com.example.cupcake.screens.FlavorScreen
import com.example.cupcake.screens.PickupScreen
import com.example.cupcake.screens.StartScreenHost
import com.example.cupcake.screens.SummaryScreenContent

/**
 * Activity for cupcake order flow.
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<OrderViewModel> {
        OrderViewModel.Factory()
    }
    private var _navigation: CupcakeNavigation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigation = rememberCupcakeNavigation()
                .also { _navigation = it }
            val graph = remember(navigation, viewModel) {
                navigation.controller
                    .createGraph(Destination.Start) {
                        composable<Destination.Start> {
                            StartScreenHost(
                                viewModel = viewModel,
                                navigateToFlavor = {
                                    navigation.toDestination(Destination.Flavor)
                                }
                            )
                        }
                        composable<Destination.Flavor> {
                            FlavorScreen(
                                viewModel = viewModel,
                                navigateToPickup = {
                                    navigation.toDestination(Destination.Pickup)
                                },
                                cancel = {
                                    navigation.backToStart()
                                }
                            )
                        }
                        composable<Destination.Pickup> {
                            PickupScreen(
                                viewModel = viewModel,
                                navigateToSummary = {
                                    navigation.toDestination(Destination.Summary)
                                },
                                cancel = {
                                    navigation.backToStart()
                                }
                            )
                        }
                        composable<Destination.Summary> {
                            SummaryScreenContent()
                        }
                    }
            }

            NavHost(navigation.controller, graph)
        }
    }

    override fun onDestroy() {
        _navigation = null
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        return _navigation?.upPressed() == true
    }
}