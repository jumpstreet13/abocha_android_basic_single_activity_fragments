package com.example.cupcake.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.LocalNavController
import com.example.cupcake.Navigation
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.atom.CupCakeAppBar
import com.example.cupcake.ui.theme.CupCakeTheme

@Composable
fun SummaryScreen(viewModel: OrderViewModel, onSendOrderClick: (String) -> Unit) {
    val navController = LocalNavController.current
    val priceValue by viewModel.price.observeAsState()
    val quantityValue by viewModel.quantity.observeAsState()
    val flavorValue by viewModel.flavor.observeAsState()
    val dateValue by viewModel.date.observeAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        topBar = { CupCakeAppBar(stringResource(id = R.string.order_summary)) { navController.popBackStack() } }
    ) { paddingValues ->
        quantityValue?.let {
            flavorValue?.let { it1 ->
                dateValue?.let { it2 ->
                    Content(
                        modifier = Modifier.padding(paddingValues),
                        quantity = it,
                        flavor = it1,
                        date = it2,
                        price = priceValue.toString(),
                        onCancelClick = {
                            viewModel.resetOrder()
                            navController.navigate(Navigation.START_SCREEN.route)
                        },
                        onSendOrderClick = onSendOrderClick
                    )
                }
            }
        }
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    quantity: Int,
    flavor: String,
    date: String,
    price: String,
    onCancelClick: () -> Unit,
    onSendOrderClick: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Cell(
            title = stringResource(R.string.quantity),
            value = quantity.toString()
        )
        Cell(
            title = stringResource(R.string.flavor),
            value = flavor
        )
        Cell(
            title = stringResource(R.string.pickup_date),
            value = date
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = stringResource(
                    R.string.total_price,
                    price
                ).uppercase(),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        val orderSummary = stringResource(
            R.string.order_details,
            quantity,
            flavor,
            date,
            price
        )

        Button(
            shape = MaterialTheme.shapes.extraSmall,
            onClick = { onSendOrderClick(orderSummary) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.send).uppercase())
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            shape = MaterialTheme.shapes.extraSmall,
            onClick = onCancelClick,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.cancel).uppercase())
        }
    }
}

@Composable
fun Cell(title: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            color = MaterialTheme.colorScheme.onBackground,
            text = title.uppercase()
        )
        Text(
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
            text = value
        )
    }

    HorizontalDivider(
        thickness = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.margin_between_elements))
    )
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewSummaryScreen() {
    CupCakeTheme {
        SummaryScreen(
            viewModel = viewModel(),
            onSendOrderClick = {}
        )
    }
}