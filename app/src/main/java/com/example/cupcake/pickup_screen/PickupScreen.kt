package com.example.cupcake.pickup_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.ui_components.AppRadioButton
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui_components.CupcakeButton
import com.example.cupcake.ui_components.CupcakeOutlinedButton

@Composable
fun PickupScreen(
    getVmFactory: () -> ViewModelProvider.Factory,
    viewModel: OrderViewModel = viewModel(factory = getVmFactory())
) {
    Surface {
        val scrollState = rememberScrollState()
        val dateState = viewModel.date.collectAsState()
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            repeat(OrderViewModel.DAY_COUNT) { i ->
                AppRadioButton(
                    text = viewModel.dateOptions[i],
                    selected = dateState.value == viewModel.dateOptions[i],
                    onClick = {
                        viewModel.setDate(viewModel.dateOptions[i])
                    }
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = stringResource(id = R.string.subtotal_price, viewModel.price.value?: ""))
            }
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CupcakeOutlinedButton(
                    modifier = Modifier.weight(.5f),
                    text = stringResource(id = R.string.cancel),
                    onClick = viewModel::cancelOrder
                )
                CupcakeButton(
                    modifier = Modifier.weight(.5f),
                    text = stringResource(id = R.string.next),
                    onClick = viewModel::navigateToSummary
                )
            }
        }
    }
}