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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.R
import com.example.cupcake.compose.custom.SelectableContent
import com.example.cupcake.compose.theme.CupcakeTheme
import com.example.cupcake.model.OrderViewModel

@Composable
fun FlavorScreen(
    onBack: () -> Unit,
    onOpenStartScreen: () -> Unit,
    onOpenPickupScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            Surface(elevation = 3.dp) {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.choose_flavor)) },
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

        SelectableContent(
            list = flavorList,
            selectedOption = selectedOption,
            price = price,
            onOptionSelected = viewModel::setFlavor,
            onCancelClick = {
                // Reset order in view model
                viewModel.resetOrder()

                // Navigate back to the [StartScreen] to start over
                onOpenStartScreen()
            },
            onNextClick = onOpenPickupScreen,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview
@Composable
private fun FlavorScreenPreview() {
    CupcakeTheme {
        Surface {
            FlavorScreen(
                onBack = {},
                onOpenStartScreen = {},
                onOpenPickupScreen = {}
            )
        }
    }
}
