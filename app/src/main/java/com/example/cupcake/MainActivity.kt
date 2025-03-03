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
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.navigation.CupcakeNavigation
import com.example.cupcake.navigation.Destination
import com.example.cupcake.navigation.rememberCupcakeNavigation
import com.example.cupcake.screens.FlavorScreen
import com.example.cupcake.screens.PickupScreen
import com.example.cupcake.screens.StartScreen
import com.example.cupcake.screens.SummaryScreen
import com.example.cupcake.theme.CupcakeAppTheme
import com.example.cupcake.usecase.sendOrderToAnotherApp

/**
 * Activity for cupcake order flow.
 */
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<OrderViewModel> {
        OrderViewModel.Factory()
    }
    private var _navigation: CupcakeNavigation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navigation = rememberCupcakeNavigation()
                        .also { _navigation = it }
                    val graph = remember(navigation, viewModel) {
                        navigation.controller
                            .createGraph(Destination.Start) {
                                composable<Destination.Start>(
                                    enterTransition = {
                                        slideIntoContainer(
                                            SlideDirection.End,
                                            tween(300, 0, LinearEasing)
                                        )
                                    },
                                    exitTransition = {
                                        slideOutOfContainer(
                                            SlideDirection.Start,
                                            tween(300, 0, LinearEasing)
                                        )
                                    }
                                ) {
                                    StartScreen(
                                        viewModel = viewModel,
                                        navigateToFlavor = {
                                            navigation.toDestination(Destination.Flavor)
                                        }
                                    )
                                }
                                composable<Destination.Flavor>(
                                    enterTransition = {
                                        slideIntoContainer(
                                            SlideDirection.Start,
                                            tween(300, 0, LinearEasing)
                                        )
                                    },
                                    exitTransition = {
                                        slideOutOfContainer(
                                            SlideDirection.Start,
                                            tween(300, 0, LinearEasing)
                                        )
                                    },
                                    popEnterTransition = {
                                        slideIntoContainer(
                                            SlideDirection.End,
                                            tween(300, 0, LinearEasing)
                                        )
                                    },
                                    popExitTransition = {
                                        slideOutOfContainer(
                                            SlideDirection.End,
                                            tween(300, 0, LinearEasing)
                                        )
                                    }
                                ) {
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
                                composable<Destination.Pickup>(
                                    enterTransition = {
                                        slideIntoContainer(
                                            SlideDirection.Start,
                                            tween(300, 0, LinearEasing)
                                        )
                                    },
                                    exitTransition = {
                                        slideOutOfContainer(
                                            SlideDirection.Start,
                                            tween(300, 0, LinearEasing)
                                        )
                                    },
                                    popEnterTransition = {
                                        slideIntoContainer(
                                            SlideDirection.End,
                                            tween(300, 0, LinearEasing)
                                        )
                                    },
                                    popExitTransition = {
                                        slideOutOfContainer(
                                            SlideDirection.End,
                                            tween(300, 0, LinearEasing)
                                        )
                                    }
                                ) {
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
                                composable<Destination.Summary>(
                                    enterTransition = {
                                        slideIntoContainer(
                                            SlideDirection.Start,
                                            tween(300, 0, LinearEasing)
                                        )
                                    },
                                    exitTransition = {
                                        slideOutOfContainer(
                                            SlideDirection.Start,
                                            tween(300, 0, LinearEasing)
                                        )
                                    },
                                    popEnterTransition = {
                                        slideIntoContainer(
                                            SlideDirection.End,
                                            tween(300, 0, LinearEasing)
                                        )
                                    },
                                    popExitTransition = {
                                        slideOutOfContainer(
                                            SlideDirection.End,
                                            tween(300, 0, LinearEasing)
                                        )
                                    }
                                ) {
                                    SummaryScreen(
                                        viewModel = viewModel,
                                        sendOrder = {
                                            val orderSummary = getString(
                                                R.string.order_details,
                                                resources.getQuantityString(R.plurals.cupcakes, viewModel.quantity.value),
                                                viewModel.flavor.value.toString(),
                                                viewModel.date.value.toString(),
                                                viewModel.price.value.toString()
                                            )
                                            sendOrderToAnotherApp(orderSummary)
                                        },
                                        cancel = {
                                            navigation.backToStart()
                                        }
                                    )
                                }
                            }
                    }

                    NavHost(navigation.controller, graph)
                }
            }
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