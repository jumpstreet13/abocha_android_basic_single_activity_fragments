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
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.di.ViewModelFactory
import com.example.cupcake.flavor_screen.FlavorScreen
import com.example.cupcake.navigation.Destination
import com.example.cupcake.theme.CupcakeTheme
import com.example.cupcake.start_screen.StartScreen
import com.example.cupcake.navigation.NavigationEffects
import com.example.cupcake.navigation.NavigationObserver
import com.example.cupcake.pickup_screen.PickupScreen
import com.example.cupcake.summary_screen.SummaryScreen
import javax.inject.Inject

/**
 * Activity for cupcake order flow.
 */
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var navigationObserver: NavigationObserver

    private val component by lazy { (application as MainApplication).component }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            val getVmFactory: () -> ViewModelProvider.Factory = remember { { viewModelFactory } }

            CupcakeTheme {
                NavigationEffects(
                    navigationFlow = navigationObserver.navigateState,
                    navHostController = navHostController
                )
                NavHost(
                    navController = navHostController,
                    startDestination = Destination.Start.route
                ) {
                    composable(Destination.Start.route) {
                        StartScreen(getVmFactory = getVmFactory)
                    }
                    composable(Destination.Flavor.route) {
                        FlavorScreen(getVmFactory = getVmFactory)
                    }
                    composable(Destination.Pickup.route) {
                        PickupScreen(getVmFactory = getVmFactory)
                    }
                    composable(Destination.Summary.route) {
                        SummaryScreen(getVmFactory = getVmFactory)
                    }
                }
            }
        }
    }
}