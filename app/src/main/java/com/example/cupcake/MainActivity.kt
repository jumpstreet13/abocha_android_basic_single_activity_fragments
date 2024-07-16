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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.nav.CupcakeNavHost
import com.example.cupcake.nav.Destinations
import com.example.cupcake.nav.Start
import com.example.cupcake.ui.CupcakeTheme

/**
 * Activity for cupcake order flow.
 */
class MainActivity : AppCompatActivity() {

   private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen(viewModel)
        }
    }
}

@Composable
fun AppScreen(
    viewModel: OrderViewModel) {
    CupcakeTheme {
        val navController = rememberNavController()
        val backStack by navController.currentBackStackEntryAsState()
        val route = backStack?.destination?.route ?: Start.route
        val destination = Destinations.find { it.route == route} ?: Start

       Scaffold(
           topBar = {
               TopAppBar(
                   title = { Text(stringResource(destination.titleResId)) },
                   navigationIcon = {
                       destination.navIcon?.let {
                           IconButton(onClick = {
                               navController.popBackStack()
                           }) {
                               Icon(it, null)
                           }
                       }
                   }
               )
           }
       ) { innerPadding ->
           CupcakeNavHost(
               viewModel = viewModel,
               navController = navController,
               modifier = Modifier.padding(innerPadding)
           )
       }
    }
}