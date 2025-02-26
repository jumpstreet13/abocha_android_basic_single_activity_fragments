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
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cupcake.compose_screens.ChooseOptionScreen
import com.example.cupcake.compose_screens.Start
import com.example.cupcake.compose_screens.Summary

/**
 * Activity for cupcake order flow.
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /*val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        setupActionBarWithNavController(navController)*/
        setContent {
            Main()
        }
    }

    /**
     * Handle navigation when the user chooses Up from the action bar.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

@Composable
fun Main() {
    val navController = rememberNavController()
    val viewModel: CupcakeViewModel = viewModel(
        factory = CupcakeViewModelFactory(LocalContext.current)
    )
    NavHost(navController = navController, startDestination = NavRouters.Start.route) {
        composable(route = NavRouters.Start.route) {
            Start(
                navigate = { navController.navigate(NavRouters.Flavor.route) },
                viewModel::setAmount,
                viewModel::setEmptyOrder
            )
        }
        composable(route = NavRouters.Flavor.route) {
            ChooseOptionScreen(
                cancelButtonNavigate = {
                    navController.navigate(NavRouters.Start.route) {
                        popUpTo(0)
                    }
                },
                nextButtonNavigate = { navController.navigate(NavRouters.PickUp.route) },
                orderFlow = viewModel.order,
                options = viewModel.ingredientOptions,
                setSelectedOption = viewModel::setAdditionalIngredient
            )
        }
        composable(NavRouters.PickUp.route) {
            ChooseOptionScreen(
                cancelButtonNavigate = {
                    navController.navigate(NavRouters.Start.route) {
                        popUpTo(0)
                    }
                },
                nextButtonNavigate = { navController.navigate(NavRouters.Summary.route) },
                orderFlow = viewModel.order,
                options = viewModel.getDateOptions(),
                setSelectedOption = viewModel::setDate,
            )
        }
        composable(NavRouters.Summary.route) {
            Summary(
                orderFlow = viewModel.order,
                cancelButtonNavigate = {
                    navController.navigate(NavRouters.Start.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}

sealed class NavRouters(val route: String) {
    data object Start : NavRouters("start")
    data object Flavor : NavRouters("flavor")
    data object PickUp : NavRouters("pickUp")
    data object Summary : NavRouters("summary")
}