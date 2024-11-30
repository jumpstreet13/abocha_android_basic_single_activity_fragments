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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.screens.util.SelectableItemList

@Composable
fun FlavorScreen(
    onBackClick: () -> Unit,
    onOpenStartScreen: () -> Unit,
    onOpenPickupScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            Surface(elevation = 3.dp) {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.choose_flavor))
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        val resources = LocalContext.current.resources
        val flavorList = listOf(
            resources.getString(R.string.vanilla),
            resources.getString(R.string.chocolate),
            resources.getString(R.string.red_velvet),
            resources.getString(R.string.salted_caramel),
            resources.getString(R.string.coffee)
        )

        val selectedOption by viewModel.flavor.observeAsState(flavorList[0])
        val price by viewModel.price.observeAsState("0")

        FlavorScreenContent(
            list = flavorList,
            selectedFlavor = selectedOption,
            price = price,
            onFlavorSelected = viewModel::setFlavor,
            onCancelClick = {
                viewModel.resetOrder()
                onOpenStartScreen()
            },
            onNextClick = onOpenPickupScreen,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun FlavorScreenContent(
    list: List<String>,
    selectedFlavor: String,
    price: String,
    onFlavorSelected: (String) -> Unit,
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
            selectedItem = selectedFlavor,
            onItemSelected = onFlavorSelected,
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
fun FlavorScreenPreview() {
    FlavorScreen(
        onBackClick = {},
        onOpenStartScreen = {},
        onOpenPickupScreen = {}
    )
}