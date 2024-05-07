package com.example.cupcake

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cupcake.model.OrderViewModel

@Composable
fun PickupScreen(viewModel: OrderViewModel, navController: NavController) {
    val dateSate by viewModel.date.observeAsState()
    val priceState by viewModel.price.observeAsState()

    Surface(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.side_margin))
    ) {
        Column {
            Column(Modifier.selectableGroup()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val date = viewModel.dateOptions[0]
                    RadioButton(
                        selected = dateSate.equals(date),
                        onClick = { viewModel.setDate(date) }
                    )
                    Text(text = date)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val date = viewModel.dateOptions[1]
                    RadioButton(
                        selected = dateSate.equals(date),
                        onClick = { viewModel.setDate(date) }
                    )
                    Text(text = date)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val date = viewModel.dateOptions[2]
                    RadioButton(
                        selected = dateSate.equals(date),
                        onClick = { viewModel.setDate(date) }
                    )
                    Text(text = date)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val date = viewModel.dateOptions[3]
                    RadioButton(
                        selected = dateSate.equals(date),
                        onClick = { viewModel.setDate(date) }
                    )
                    Text(text = date)
                }
            }

            Divider(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.side_margin)),
                color = Color.LightGray,
                thickness = 1.dp
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.side_margin)),
                    text = stringResource(id = R.string.subtotal_price, priceState.orEmpty())
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.side_margin))
                    .fillMaxWidth()
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        viewModel.resetOrder()
                        navController.popBackStack(route = Screen.Start.name, inclusive = false)
                    }) {
                    Text(text = stringResource(id = R.string.cancel).uppercase())
                }
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.side_margin)))
                Button(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        navController.navigate(route = Screen.Summary.name)
                    }) {
                    Text(text = stringResource(id = R.string.next).uppercase())
                }
            }
        }
    }
}