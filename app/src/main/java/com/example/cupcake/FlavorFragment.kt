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

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.compose.content
import androidx.navigation.fragment.findNavController
import com.example.cupcake.model.OrderViewModel

/**
 * [FlavorFragment] allows a user to choose a cupcake flavor for the order.
 */
class FlavorFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        MainTheme {
            FlavorScreen(
                flavor = sharedViewModel.flavor.observeAsState("").value,
                price = sharedViewModel.price.observeAsState("").value
            )
        }
    }

    /**
     * Navigate to the next screen to choose pickup date.
     */
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    /**
     * Cancel the order and start over.
     */
    fun cancelOrder() {
        // Reset order in view model
        sharedViewModel.resetOrder()

        // Navigate back to the [StartFragment] to start over
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
    }

    @Composable
    private fun FlavorScreen(flavor: String, price: String) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(dimensionResource(R.dimen.side_margin))
            ) {
                // radio buttons
                Column(
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.side_margin))
                ) {
                    remember {
                        arrayOf(
                            R.string.vanilla,
                            R.string.chocolate,
                            R.string.red_velvet,
                            R.string.salted_caramel,
                            R.string.coffee
                        )
                    }.forEach { id ->
                        val label = stringResource(id)
                        RadioSelect(
                            label = label,
                            isSelected = flavor == label
                        ) {
                            sharedViewModel.setFlavor(label)
                        }
                    }
                }
                Divider()
                Spacer(Modifier.height(dimensionResource(R.dimen.side_margin)))
                Text(
                    text = stringResource(R.string.subtotal_price, price),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.side_margin))
                        .align(Alignment.End)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.side_margin)
                    )
                ) {
                    SecondaryButton(
                        labelId = R.string.cancel,
                        modifier = Modifier.weight(1f),
                        onClick = ::cancelOrder
                    )
                    PrimaryButton(
                        labelId = R.string.next,
                        modifier = Modifier.weight(1f),
                        onClick = ::goToNextScreen
                    )
                }
            }
        }
    }

    @Preview(
        name = "FlavorScreen",
        showBackground = true,
        backgroundColor = 0xFFFFFF,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    private fun FlavorPreview() {
        MainTheme {
            FlavorScreen(
                flavor = stringResource(R.string.red_velvet),
                price = "$25.2"
            )
        }
    }

}