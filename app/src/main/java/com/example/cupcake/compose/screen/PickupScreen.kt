package com.example.cupcake.compose.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.compose.custom.SelectableContent
import com.example.cupcake.model.OrderViewModel

@Composable
fun PickupScreen(
    onBack: () -> Unit,
    onOpenStartScreen: () -> Unit,
    onOpenSummaryScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            Surface(elevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.choose_pickup_date)) },
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
        val datesList = listOf(
            viewModel.dateOptions[0],
            viewModel.dateOptions[1],
            viewModel.dateOptions[2],
            viewModel.dateOptions[3]
        )

        val selectedOption by viewModel.date.observeAsState(datesList[0])
        val price by viewModel.price.observeAsState("0")

        SelectableContent(
            list = datesList,
            selectedOption = selectedOption,
            price = price,
            onOptionSelected = viewModel::setDate,
            onCancelClick = {
                // Reset order in view model
                viewModel.resetOrder()

                // Navigate back to the [StartScreen] to start over
                onOpenStartScreen()
            },
            onNextClick = onOpenSummaryScreen,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
