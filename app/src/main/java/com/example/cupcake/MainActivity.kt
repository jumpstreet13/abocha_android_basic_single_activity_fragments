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
import com.example.cupcake.compose.navigation.CupcakeNavGraph
import com.example.cupcake.compose.theme.CupcakeTheme
import com.example.cupcake.model.OrderViewModel

/**
 * Activity for cupcake order flow.
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeTheme {
                CupcakeNavGraph(
                    viewModel = viewModel,
                    onSendOrderClick = {
                        sendOrder()
                    }
                )
            }
        }
    }

    /**
     * Submit the order by sharing out the order details to another app via an implicit intent.
     */
    private fun sendOrder() {
        // Construct the order summary text with information from the view model
        val numberOfCupcakes = viewModel.quantity.value ?: 0
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
            viewModel.flavor.value.toString(),
            viewModel.date.value.toString(),
            viewModel.price.value.toString()
        )

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