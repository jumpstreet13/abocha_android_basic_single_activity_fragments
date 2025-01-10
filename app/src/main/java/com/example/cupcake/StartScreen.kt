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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cupcake.model.OrderViewModel
import kotlinx.coroutines.delay

@Composable
fun StartScreen(sharedViewModel : OrderViewModel = viewModel(), navController : NavHostController? = null) {
    val size = dimensionResource(R.dimen.image_size)
    val margin = dimensionResource(R.dimen.side_margin)
    val height = dimensionResource(R.dimen.button_height)
    val width = dimensionResource(R.dimen.order_cupcake_button_width)
    val space = dimensionResource(R.dimen.margin_between_elements)

    fun orderCupcake(quantity: Int) {
        sharedViewModel.setQuantity(quantity)
        navController?.navigate(Routes.Flavor.route)
    }

    Column(modifier = Modifier
        .fillMaxWidth(1f)
        .wrapContentWidth(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.cupcake),
            contentDescription = "",
            modifier = Modifier
                .size(size, size)
                .padding(margin)
        )
        Text(text = stringResource(R.string.order_cupcakes),
            color = colorResource(id = com.google.android.material.R.color.material_on_background_emphasis_medium),
            modifier = Modifier
                .height(height)
                .offset(0.dp, space),
            fontSize = 30.sp)

        Button(onClick = { orderCupcake(1) },
            modifier = Modifier
                .width(width)
                .height(height)
                .padding(0.dp, space)) {
            Text(stringResource(R.string.one_cupcake), fontSize = 24.sp)
        }
        Button(onClick = { orderCupcake(6) },
            modifier = Modifier
                .width(width)
                .height(height)
                .padding(0.dp, space)) {
            Text(stringResource(R.string.six_cupcakes), fontSize = 24.sp)
        }
        Button(onClick = { orderCupcake(12) },
            modifier = Modifier
                .width(width)
                .height(height)
                .padding(0.dp, space)) {
            Text(stringResource(R.string.twelve_cupcakes), fontSize = 24.sp)
        }
    }
}

@Preview(apiLevel = 34)
@Composable
fun PreviewStartScreen() {
    StartScreen()
}
