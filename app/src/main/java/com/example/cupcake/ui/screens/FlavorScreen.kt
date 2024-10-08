package com.example.cupcake.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.theme.CupcakeTheme
import com.example.cupcake.ui.widgets.CupcakeTopBar
import com.example.cupcake.ui.widgets.RadioGroupOptionPicker

@Composable
fun FlavorScreen(
    sharedViewModel: OrderViewModel,
    onNavigateUp: () -> Unit,
    onNavigateNext: () -> Unit,
    onNavigateToStart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CupcakeTopBar(
                title = stringResource(id = R.string.choose_flavor),
                showUpArrow = true,
                onNavigateUp = onNavigateUp
            )
        }
    )
    { paddingValues ->
        Surface(modifier = modifier
            .padding(paddingValues).padding(dimensionResource(id = R.dimen.side_margin))
            .verticalScroll(rememberScrollState())) {
            FlavorScreenContent(
                sharedViewModel = sharedViewModel,
                onNavigateToPickupScreen = onNavigateNext,
                onNavigateToStart = onNavigateToStart,
            )
        }
    }
}

@Composable
private fun FlavorScreenContent(
    sharedViewModel: OrderViewModel,
    onNavigateToPickupScreen: () -> Unit,
    onNavigateToStart: () -> Unit,
    modifier: Modifier = Modifier
) {
    val flavors = listOf(
        stringResource(R.string.vanilla),
        stringResource(R.string.chocolate),
        stringResource(R.string.red_velvet),
        stringResource(R.string.salted_caramel),
        stringResource(R.string.coffee),
    )

    val selectedOption: String by sharedViewModel.flavor.observeAsState(flavors[0])
    val price by sharedViewModel.price.observeAsState("0.0")

    RadioGroupOptionPicker(
        options = flavors,
        selectedOption = selectedOption,
        price = price,
        onOptionSelected = { flavor  -> sharedViewModel.setFlavor(flavor) },
        onConfirmSelection = onNavigateToPickupScreen,
        onCancel = cancelOrder(sharedViewModel, onNavigateToStart),
        modifier = modifier
    )
}

@Preview(
    name = "Dark Mode With System UI",
    uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED,
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun FlavorScreenContentDarkWithSystemUi() {
    CupcakeTheme(darkTheme = true) {
        Surface {
            FlavorScreenContent(
                sharedViewModel = OrderViewModel(),
                onNavigateToPickupScreen = {},
                onNavigateToStart = {})
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun FlavorScreenContentLightPreview() {
    CupcakeTheme(darkTheme = false) {
        FlavorScreenContent(sharedViewModel = OrderViewModel(),
            onNavigateToPickupScreen = {},
            onNavigateToStart = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun FlavorScreenContentDarkPreview() {
    CupcakeTheme(darkTheme = true) {
        FlavorScreenContent(sharedViewModel = OrderViewModel(),
            onNavigateToPickupScreen = {},
            onNavigateToStart = {}
        )
    }
}
