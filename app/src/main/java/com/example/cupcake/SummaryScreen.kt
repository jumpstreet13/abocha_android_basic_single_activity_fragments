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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cupcake.model.OrderViewModel

@Composable
fun SummaryScreen(sharedViewModel : OrderViewModel = viewModel(),
                  navController : NavHostController? = null,
                  sendOrder : (String) -> Unit) {
    val number = sharedViewModel.quantity.value ?: 0
    val flavor = sharedViewModel.flavor.value ?: ""
    val date = sharedViewModel.date.value ?: ""
    val price = sharedViewModel.getPrice()
    // Construct the order summary text with information from the view model
    val summary = stringResource(id = R.string.order_details, pluralStringResource(R.plurals.cupcakes, number), flavor, date, price)
    val margin = dimensionResource(R.dimen.side_margin)
    val height = dimensionResource(R.dimen.button_height)
    val space = dimensionResource(R.dimen.margin_between_elements)

    fun cancelOrder() {
        //sharedViewModel.resetOrder()
        navController?.navigate(Routes.Start.route)
    }

    Column(modifier = Modifier.fillMaxWidth(1f).padding(margin)) {
        Column(modifier = Modifier.padding(space)) {
            Text(stringResource(R.string.quantity), fontSize = 20.sp)
            Text(number.toString(), fontSize = 24.sp)
        }
        HorizontalDivider()
        Column(modifier = Modifier.padding(space)) {
            Text(stringResource(R.string.flavor), fontSize = 20.sp)
            Text(flavor, fontSize = 24.sp)
        }
        HorizontalDivider()
        Column(modifier = Modifier.padding(space)) {
            Text(stringResource(R.string.pickup_date), fontSize = 20.sp)
            Text(date, fontSize = 24.sp)
        }
        HorizontalDivider()
        Text(text = stringResource(R.string.total_price, price),
            modifier = Modifier
                .height(height)
                .offset(0.dp, space)
                .align(Alignment.End),
            fontSize = 30.sp)

        Button(onClick = { sendOrder(summary) },
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(height)
                .padding(margin, space)) {
            Text(stringResource(R.string.send), fontSize = 24.sp)
        }
        Button(onClick = { cancelOrder() },
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(height)
                .padding(margin, space)) {
            Text(stringResource(R.string.cancel), fontSize = 24.sp)
        }
    }
}

@Preview(apiLevel = 34)
@Composable
fun PreviewSummaryScreen() {
    val sendEmpty : (String) -> Unit = {}
    SummaryScreen(sendOrder = sendEmpty)
}
