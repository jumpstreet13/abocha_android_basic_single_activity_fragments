package com.example.cupcake.screens

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
import com.example.cupcake.model.OrderViewModel

@Composable
fun SummaryScreen(
    onBackClick: () -> Unit,
    onOpenStartScreen: () -> Unit,
    viewModel: OrderViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            Surface(elevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.order_summary)) },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) { paddingValues ->
        val quantity by viewModel.quantity.observeAsState(0)
        val price by viewModel.price.observeAsState("")
        val flavor by viewModel.flavor.observeAsState("")
        val pickupDate by viewModel.date.observeAsState("")

        SummaryScreenContent(
            quantity = quantity.toString(),
            flavor = flavor,
            pickupDate = pickupDate,
            price = price,
            onSendOrderClick = {
                viewModel.sendOrder()
                onOpenStartScreen()
            },
            onCancelClick = {
                viewModel.resetOrder()
                onOpenStartScreen()
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun SummaryScreenContent(
    quantity: String,
    flavor: String,
    pickupDate: String,
    price: String,
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
            title = stringResource(id = R.string.quantity).uppercase(),
            subtitle = quantity,
        )
        SummaryItem(
            title = stringResource(id = R.string.flavor).uppercase(),
            subtitle = flavor,
        )
        SummaryItem(
            title = stringResource(id = R.string.pickup_date).uppercase(),
            subtitle = pickupDate,
        )
        Text(
            text = stringResource(id = R.string.total_price, price).uppercase(),
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .align(alignment = Alignment.End),
            fontSize = 20.sp,
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
                text = stringResource(id = R.string.send).uppercase(),
                fontSize = 14.sp
            )
        }

        OutlinedButton(
            onClick = onCancelClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            shape = RectangleShape
        ) {
            Text(
                text = stringResource(id = R.string.cancel).uppercase(),
                fontSize = 14.sp
            )
        }

    }
}

@Composable
fun SummaryItem(
    title: String,
    subtitle: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Text(text = title)
        Text(text = subtitle, modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Bold)
        Divider(thickness = 1.dp, modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
@Preview
fun SummaryScreenPreview() {
    SummaryScreenContent(
        quantity = "12",
        flavor = "red velvet",
        pickupDate = "Su Nov 11",
        price = "123",
        onSendOrderClick = {},
        onCancelClick = {},
        modifier = Modifier
    )
}