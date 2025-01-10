package com.example.cupcake.ui.flavor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
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
fun FlavorScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onOpenStartScreen: () -> Unit,
    onOpenPickupScreen: () -> Unit,
    viewModel: OrderViewModel = viewModel()
) {
    Scaffold(
        topBar = {
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

        val selectedOption by viewModel.flavor.collectAsStateWithLifecycle(flavorList[0])
        val price by viewModel.price.collectAsStateWithLifecycle("0")

        FlavorScreenContent(
            modifier = Modifier.padding(paddingValues),
            list = flavorList,
            selectedFlavor = selectedOption,
            price = price.toString(),
            onFlavorSelected = viewModel::setFlavor,
            onCancelClick = {
                viewModel.resetOrder()
                onOpenStartScreen()
            },
            onNextClick = onOpenPickupScreen,
        )
    }
}

@Composable
fun FlavorScreenContent(
    modifier: Modifier = Modifier,
    list: List<String>,
    selectedFlavor: String,
    price: String,
    onFlavorSelected: (String) -> Unit,
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .verticalScroll(scrollState)
    ) {
        ChooseItems(
            list = list,
            selectedItem = selectedFlavor,
            onItemSelected = onFlavorSelected,
        )
        HorizontalDivider(modifier = Modifier.padding(16.dp), thickness = 1.dp)
        Text(
            modifier = Modifier
                .padding(end = 16.dp)
                .align(alignment = Alignment.End),
            text = stringResource(id = R.string.subtotal_price, price),
            fontSize = 20.sp
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedButton(
                modifier = Modifier
                    .weight(1f),
                onClick = onCancelClick,
            ) {
                Text(
                    text = stringResource(R.string.cancel).uppercase(),
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNextClick,
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