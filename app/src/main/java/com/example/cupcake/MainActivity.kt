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
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.FlavorScreen
import com.example.cupcake.ui.StartScreen
import com.example.cupcake.ui.SummaryScreen

/**
 * Activity for cupcake order flow.
 */
/*class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve NavController from the NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Set up the action bar for use with the NavController
        setupActionBarWithNavController(navController)
    }

    /**
     * Handle navigation when the user chooses Up from the action bar.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}*/

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("NavHostController not provided")
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val sharedViewModel: OrderViewModel by viewModels()
            CompositionLocalProvider(LocalNavController provides navController) {
                NavGraph(sharedViewModel) { sendOrderClick(it) }
            }



        }
    }

    private fun sendOrderClick(orderSummary: String) {
        // Create an ACTION_SEND implicit intent with order details in the intent extras
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        // Check if there's an app that can handle this intent before launching it
        if (packageManager?.resolveActivity(intent, 0) != null) {
            // Start a new activity with the given intent (this may open the share dialog on a
            // device if multiple apps can handle this intent)
            startActivity(intent)
        }
    }
}

@Composable
fun NavGraph(
    viewModel: OrderViewModel,
    onSendOrderClick: (String) -> Unit
) {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = Navigation.START_SCREEN.route) {
        composable(Navigation.START_SCREEN.route) {
            StartScreen(viewModel)
        }

        composable(Navigation.FLAVOR_SCREEN.route) {
            FlavorScreen(viewModel)
            BackHandler {
                navController.popBackStack()
            }
        }

        composable(Navigation.PICKUP_SCREEN.route) {
            FlavorScreen(viewModel, isFlavorScreen = false)
            BackHandler {
                navController.popBackStack()
            }
        }

        composable(Navigation.SUMMARY_SCREEN.route) {
            SummaryScreen(viewModel, onSendOrderClick)
            BackHandler {
                navController.popBackStack()
            }
        }
    }
}

enum class Navigation(var route: String) {
    START_SCREEN("StartScreen"),
    FLAVOR_SCREEN("FlavorScreen"),
    PICKUP_SCREEN("PickupScreen"),
    SUMMARY_SCREEN("SummaryScreen")
}