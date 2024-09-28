package com.example.cupcake.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.compose.theme.CupcakeTheme
import com.example.cupcake.model.OrderViewModel

@Composable
fun SummaryScreen(
    onBack: () -> Unit,
    onSendOrderClick: () -> Unit,
    onOpenStartScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            Surface(elevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.order_summary)) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        val quantity by viewModel.quantity.observeAsState(0)
        val flavor by viewModel.flavor.observeAsState("")
        val pickupDate by viewModel.date.observeAsState("")
        val price by viewModel.price.observeAsState("")

        SummaryContent(
            quantity = quantity.toString(),
            flavor = flavor,
            pickupDate = pickupDate,
            total = price,
            onSendOrderClick = onSendOrderClick,
            onCancelClick = {
                // Reset order in view model
                viewModel.resetOrder()

                // Navigate back to the [StartScreen] to start over
                onOpenStartScreen()
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun SummaryContent(
    quantity: String,
    flavor: String,
    pickupDate: String,
    total: String,
    onSendOrderClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SummaryItem(
            name = stringResource(R.string.quantity).uppercase(),
            value = quantity
        )
        SummaryItem(
            name = stringResource(R.string.flavor).uppercase(),
            value = flavor
        )
        SummaryItem(
            name = stringResource(R.string.pickup_date).uppercase(),
            value = pickupDate
        )
        Text(
            text = stringResource(R.string.total_price, total).uppercase(),
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .align(alignment = Alignment.End),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = onSendOrderClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            shape = RectangleShape
        ) {
            Text(
                text = stringResource(R.string.send).uppercase(),
                fontSize = 14.sp
            )
        }
        OutlinedButton(
            onClick = onCancelClick,
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.cancel).uppercase(),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun SummaryItem(
    name: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Text(name)
        Text(
            text = value,
            modifier = Modifier.padding(top = 4.dp),
            fontWeight = FontWeight.Bold
        )
        Divider(thickness = 1.dp, modifier = Modifier.padding(top = 16.dp))
    }
}

@Preview
@Composable
private fun SummaryScreenPreview() {
    CupcakeTheme {
        Surface {
            SummaryScreen(
                onBack = {},
                onSendOrderClick = {},
                onOpenStartScreen = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SummaryContentPreview() {
    CupcakeTheme {
        SummaryContent(
            quantity = "1",
            flavor = "Vanilla",
            pickupDate = "Mon Sep 2",
            total = "$5.00",
            onSendOrderClick = {},
            onCancelClick = {},
        )
    }
}
