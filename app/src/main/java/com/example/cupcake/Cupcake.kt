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
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.cupcake.model.OrderViewModel

sealed class Routes(val route: String) {
    object Start : Routes("Start")
    object Flavor : Routes("Flavor")
    object Pickup : Routes("Pickup")
    object Summary : Routes("Summary")
}

@Composable
fun AnimatedStartScreen(viewModel : OrderViewModel, navController : NavHostController) {
    val state = remember {
        MutableTransitionState(false).apply { targetState = true }
    }
    AnimatedVisibility(visibleState = state,
            //enter = slideInVertically(tween(2000)),
            enter = scaleIn(tween(2000)),
            exit = ExitTransition.None) {
        StartScreen(viewModel, navController)
    }
}

@Composable
fun AnimatedFlavorScreen(viewModel : OrderViewModel, navController : NavHostController) {
    val state = remember {
        MutableTransitionState(false).apply { targetState = true }
    }
    AnimatedVisibility(visibleState = state,
            enter = expandVertically(tween(1000)),
            exit = ExitTransition.None) {
        FlavorScreen(viewModel, navController)
    }
}

@Composable
fun AnimatedPickupScreen(viewModel : OrderViewModel, navController : NavHostController) {
    val state = remember {
        MutableTransitionState(false).apply { targetState = true }
    }
    AnimatedVisibility(visibleState = state,
            enter = expandVertically(tween(1000)),
            exit = ExitTransition.None) {
        PickupScreen(viewModel, navController)
    }
}

@Composable
fun AnimatedSummaryScreen(viewModel : OrderViewModel, navController : NavHostController, sendOrder : (String) -> Unit) {
    val state = remember {
        MutableTransitionState(false).apply { targetState = true }
    }
    AnimatedVisibility(visibleState = state,
            enter = slideInVertically(tween(1000), initialOffsetY = { -it }),
            exit = fadeOut(tween(1000), targetAlpha = 0f)) {
        SummaryScreen(viewModel, navController, sendOrder)
    }
}

@Composable
fun CupcakeOrder(activity : AppCompatActivity, viewModel : OrderViewModel = viewModel()) {
    val navController : NavHostController = rememberNavController()
    val title = stringResource(R.string.send)
    val order = stringResource(R.string.new_cupcake_order)

    fun sendOrder(summary : String) {
        // Create an ACTION_SEND implicit intent with order details in the intent extras
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, order)
            .putExtra(Intent.EXTRA_TEXT, summary)

        // Check if there's an app that can handle this intent before launching it
        activity.setTitle(title)
        if (activity.packageManager?.resolveActivity(intent,0) != null) {
            // Start a new activity with the given intent (this may open the share dialog on a
            // device if multiple apps can handle this intent)
            activity.startActivity(intent)
        }
        else {
            navController.navigate(Routes.Start.route)
        }
    }

    NavHost(navController = navController, startDestination = Routes.Start.route) {
        composable(Routes.Start.route) {
            activity.setTitle(stringResource(id = R.string.app_name))
            AnimatedStartScreen(viewModel, navController)
        }
        composable(Routes.Flavor.route) {
            activity.setTitle(stringResource(id = R.string.choose_flavor))
            AnimatedFlavorScreen(viewModel, navController)
        }
        composable(Routes.Pickup.route) {
            activity.setTitle(stringResource(id = R.string.choose_pickup_date))
            AnimatedPickupScreen(viewModel, navController)
        }
        composable(Routes.Summary.route) {
            activity.setTitle(stringResource(id = R.string.order_summary))
            AnimatedSummaryScreen(viewModel, navController, sendOrder = { sendOrder(it) })
        }
    }
}

