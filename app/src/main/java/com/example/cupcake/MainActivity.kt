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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.navigatoin.CupcakeNavController
import com.example.cupcake.navigatoin.Destination
import com.example.cupcake.navigatoin.rememberCupcakeNavController

/**
 * Activity for cupcake order flow.
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<OrderViewModel> {
        OrderViewModel.Factory()
    }
    private var _navController: CupcakeNavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberCupcakeNavController()
                .also {
                    _navController = it
                }
            val navGraph = remember(navController) {
                navController.controller.createGraph(Destination.Start) {
                    composable<Destination.Start> {

                    }
                    composable<Destination.Flavor> {

                    }
                    composable<Destination.Pickup> {

                    }
                    composable<Destination.Summary> {

                    }
                }
            }

            NavHost(navController.controller, navGraph)
        }
    }

    override fun onDestroy() {
        _navController = null
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        return _navController?.upPressed() == true
    }
}