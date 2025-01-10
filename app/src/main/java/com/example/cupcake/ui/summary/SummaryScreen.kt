package com.example.cupcake.ui.summary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(
    onBackClick: () -> Unit,
    onOpenStartScreen: () -> Unit,
    viewModel: OrderViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = stringResource(id = R.string.order_summary)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                    }
                },
            )
        },
    ) { paddingValues ->
        val quantity by viewModel.quantity.collectAsStateWithLifecycle(0)
        val price by viewModel.price.collectAsStateWithLifecycle(0.0)
        val flavor by viewModel.flavor.collectAsStateWithLifecycle("")
        val pickupDate by viewModel.date.collectAsStateWithLifecycle("")

        SummaryScreenContent(
            modifier = Modifier.padding(paddingValues),
            quantity = quantity.toString(),
            flavor = flavor,
            pickupDate = pickupDate,
            price = price.toString(),
            onSendOrderClick = {
                onOpenStartScreen()
            },
            onCancelClick = {
                viewModel.resetOrder()
                onOpenStartScreen()
            },
        )
    }
}

@Composable
fun SummaryScreenContent(
    modifier: Modifier = Modifier,
    quantity: String,
    flavor: String,
    pickupDate: String,
    price: String,
    onSendOrderClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
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
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .align(alignment = Alignment.End),
            text = stringResource(id = R.string.total_price, price).uppercase(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            onClick = onSendOrderClick,
        ) {
            Text(
                text = stringResource(id = R.string.send).uppercase(),
                fontSize = 14.sp
            )
        }

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp),
            onClick = onCancelClick,
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
        Text(
            text = subtitle,
            modifier = Modifier.padding(vertical = 4.dp),
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(modifier = Modifier.padding(top = 16.dp), thickness = 1.dp)
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