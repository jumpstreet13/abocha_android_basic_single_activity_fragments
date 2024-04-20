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
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.compose.content
import androidx.navigation.fragment.findNavController
import com.example.cupcake.model.OrderViewModel

/**
 * [SummaryFragment] contains a summary of the order details with a button to share the order
 * via another app.
 */
class SummaryFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        MainTheme {
            SummaryScreen(
                orderItems = remember {
                    listOf(
                        R.string.quantity to sharedViewModel.quantity.value.toString(),
                        R.string.flavor to sharedViewModel.flavor.value.toString(),
                        R.string.pickup_date to sharedViewModel.date.value.toString()
                    )
                },
                totalPrice = sharedViewModel.price.observeAsState("").value
            )
        }
    }

    /**
     * Submit the order by sharing out the order details to another app via an implicit intent.
     */
    fun sendOrder() {
        // Construct the order summary text with information from the view model
        val numberOfCupcakes = sharedViewModel.quantity.value ?: 0
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
            sharedViewModel.flavor.value.toString(),
            sharedViewModel.date.value.toString(),
            sharedViewModel.price.value.toString()
        )

        // Create an ACTION_SEND implicit intent with order details in the intent extras
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        // Check if there's an app that can handle this intent before launching it
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            // Start a new activity with the given intent (this may open the share dialog on a
            // device if multiple apps can handle this intent)
            startActivity(intent)
        }
    }

    /**
     * Cancel the order and start over.
     */
    fun cancelOrder() {
        // Reset order in view model
        sharedViewModel.resetOrder()

        // Navigate back to the [StartFragment] to start over
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }


    @Composable
    private fun SummaryScreen(orderItems: List<Pair<Int, String>>, totalPrice: String) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(dimensionResource(R.dimen.side_margin))
            ) {
                // order items
                orderItems.forEach { item ->
                    val (strId, value) = item
                    Text(
                        text = stringResource(strId).toUpperCase(Locale.current),
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(Modifier.height(dimensionResource(R.dimen.order_summary_margin)))
                    Text(
                        text = value,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(
                        modifier = Modifier
                            .padding(vertical = dimensionResource(R.dimen.side_margin))
                    )
                }

                // total price
                Spacer(Modifier.height(dimensionResource(R.dimen.margin_between_elements)))
                Text(
                    text = stringResource(R.string.total_price, totalPrice)
                        .toUpperCase(Locale.current),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.side_margin))
                        .align(Alignment.End)
                )

                // buttons
                PrimaryButton(
                    labelId = R.string.send,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = ::sendOrder
                )
                Spacer(Modifier.height(dimensionResource(R.dimen.margin_between_elements)))
                SecondaryButton(
                    labelId = R.string.cancel,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = ::cancelOrder
                )
            }
        }
    }

    @Preview(
        name = "PickupScreen",
        showBackground = true,
        backgroundColor = 0xFFFFFF,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    private fun PickupPreview() {
        MainTheme {
            SummaryScreen(
                orderItems = listOf(
                    R.string.quantity to "Value 1",
                    R.string.flavor to "Value 2",
                    R.string.pickup_date to "Value 3"
                ),
                totalPrice = "$25.2"
            )
        }
    }


}