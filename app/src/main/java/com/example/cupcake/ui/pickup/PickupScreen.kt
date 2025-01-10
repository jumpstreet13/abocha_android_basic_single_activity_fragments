package com.example.cupcake.ui.pickup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.components.ChooseItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onOpenStartScreen: () -> Unit,
    onOpenSummaryScreen: () -> Unit,
    viewModel: OrderViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = stringResource(id = R.string.choose_pickup_date)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                    }
                },
            )
        },
    ) { paddingValues ->
        val datesList = listOf(
            viewModel.dateOptions[0],
            viewModel.dateOptions[1],
            viewModel.dateOptions[2],
            viewModel.dateOptions[3],
        )
        val selectedDate by viewModel.date.collectAsStateWithLifecycle(datesList[0])
        val price by viewModel.price.collectAsStateWithLifecycle("0")

        PickupScreenContent(
            list = datesList,
            selectedDate = selectedDate,
            price = price.toString(),
            onDateSelected = viewModel::setDate,
            onCancelClick = {
                viewModel.resetOrder()
                onOpenStartScreen()
            },
            onNextClick = onOpenSummaryScreen,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun PickupScreenContent(
    list: List<String>,
    selectedDate: String,
    price: String,
    onDateSelected: (String) -> Unit,
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ChooseItems(
            list = list,
            selectedItem = selectedDate,
            onItemSelected = onDateSelected,
        )
        HorizontalDivider(modifier = Modifier.padding(16.dp), thickness = 1.dp)
        Text(
            text = stringResource(id = R.string.subtotal_price, price),
            modifier = Modifier
                .padding(end = 16.dp)
                .align(alignment = Alignment.End),
            fontSize = 20.sp
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = onCancelClick,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.cancel).uppercase(),
                    fontSize = 14.sp
                )
            }

            Button(
                onClick = onNextClick,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.next).uppercase(),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
@Preview
fun PickupScreenPreview() {
    PickupScreen(
        onBackClick = {},
        onOpenStartScreen = {},
        onOpenSummaryScreen = {}
    )
}