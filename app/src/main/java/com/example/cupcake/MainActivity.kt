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
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.NavigationRouts
import com.example.cupcake.screens.FlavorScreen
import com.example.cupcake.screens.PickupScreen
import com.example.cupcake.screens.StartScreen
import com.example.cupcake.screens.SummaryScreen
import com.example.cupcake.viewModel.OrderViewModel

/**
 * Activity for cupcake order flow.
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            BackHandler {
                navController.navigateUp()
            }

            NavHost(
                navController = navController,
                startDestination = NavigationRouts.START.rout,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                composable(NavigationRouts.START.rout) {
                    StartScreen(
                        navHostController = navController,
                        viewModel = viewModel
                    )
                }

                composable(NavigationRouts.FLAVOR.rout) {
                    FlavorScreen(
                        navHostController = navController,
                        viewModel = viewModel,
                    )
                }

                composable(NavigationRouts.PICKUP.rout) {
                    PickupScreen(
                        navHostController = navController,
                        viewModel = viewModel
                    )
                }

                composable(
                    route = NavigationRouts.SUMMARY.rout,
                    enterTransition = {
                        fadeIn(
                            animationSpec = tween(
                                550, easing = LinearEasing
                            )
                        ) + slideIntoContainer(
                            animationSpec = tween(550, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start
                        )
                    }
                ) {
                    SummaryScreen(
                        navHostController = navController,
                        viewModel = viewModel,
                        packageManager = packageManager
                    )
                }
            }
        }
    }
}