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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.compose.content
import androidx.navigation.fragment.findNavController
import com.example.cupcake.model.OrderViewModel

/**
 * This is the first screen of the Cupcake app. The user can choose how many cupcakes to order.
 */
class StartFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        MainTheme {
            StartScreen()
        }
    }

    /**
     * Start an order with the desired quantity of cupcakes and navigate to the next screen.
     */
    private fun orderCupcake(quantity: Int) {
        // Update the view model with the quantity
        sharedViewModel.setQuantity(quantity)

        // If no flavor is set in the view model yet, select vanilla as default flavor
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        // Navigate to the next destination to select the flavor of the cupcakes
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    @Composable
    private fun StartScreen() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(dimensionResource(R.dimen.side_margin)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // image
                Spacer(Modifier.height(dimensionResource(R.dimen.margin_between_elements)))
                Image(
                    painter = painterResource(R.drawable.cupcake),
                    contentDescription = null,
                    modifier = Modifier.size(
                        dimensionResource(R.dimen.image_size)
                    ),
                    contentScale = ContentScale.Inside
                )

                // title
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium
                ) {
                    Text(
                        text = stringResource(R.string.order_cupcakes),
                        style = MaterialTheme.typography.h4
                    )
                }
                Spacer(Modifier.height(dimensionResource(R.dimen.side_margin)))

                // buttons
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.margin_between_elements)
                    )
                ) {
                    PrimaryButton(labelId = R.string.one_cupcake) {
                        orderCupcake(1)
                    }
                    PrimaryButton(labelId = R.string.six_cupcakes) {
                        orderCupcake(6)
                    }
                    PrimaryButton(labelId = R.string.twelve_cupcakes) {
                        orderCupcake(12)
                    }
                }
            }
        }
    }

    @Preview(
        name = "StartScreen",
        showBackground = true,
        backgroundColor = 0xFFFFFF,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    private fun StartPreview() {
        MainTheme {
            StartScreen()
        }
    }

}