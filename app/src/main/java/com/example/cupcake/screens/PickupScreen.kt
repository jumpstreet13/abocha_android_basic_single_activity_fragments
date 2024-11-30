package com.example.cupcake.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.screens.util.SelectableItemList

@Composable
fun PickupScreen(
    onBackClick: () -> Unit,
    onOpenStartScreen: () -> Unit,
    onOpenSummaryScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            Surface(elevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.choose_pickup_date)) },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        val datesList = listOf(
            viewModel.dateOptions[0],
            viewModel.dateOptions[1],
            viewModel.dateOptions[2],
            viewModel.dateOptions[3],
        )
        val selectedDate by viewModel.date.observeAsState(datesList[0])
        val price by viewModel.price.observeAsState("0")

        PickupScreenContent(
            list = datesList,
            selectedDate = selectedDate,
            price = price,
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
        SelectableItemList(
            list = list,
            selectedItem = selectedDate,
            onItemSelected = onDateSelected,
        )
        Divider(thickness = 1.dp, modifier = Modifier.padding(16.dp))
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
                shape = RectangleShape,
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
                shape = RectangleShape,
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