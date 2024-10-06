package com.example.cupcake.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    onNavigateToPickupScreen: () -> Unit,
    onNavigateUp: () -> Unit,
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
        FlavorScreenContent(
            sharedViewModel,
            onNavigateToPickupScreen,
            onNavigateUp,
            modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun FlavorScreenContent(
    sharedViewModel: OrderViewModel,
    onNavigateToPickupScreen: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val flavors = listOf(
        stringResource(R.string.vanilla),
        stringResource(R.string.chocolate),
        stringResource(R.string.red_velvet),
        stringResource(R.string.salted_caramel),
        stringResource(R.string.coffee),
    )

    RadioGroupOptionPicker(flavors, sharedViewModel, onNavigateToPickupScreen, onNavigateUp, modifier)
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
                onNavigateUp = {})
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun FlavorScreenContentLightPreview() {
    CupcakeTheme(darkTheme = false) {
        FlavorScreenContent(sharedViewModel = OrderViewModel(),
            onNavigateToPickupScreen = {},
            onNavigateUp = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun FlavorScreenContentDarkPreview() {
    CupcakeTheme(darkTheme = true) {
        FlavorScreenContent(sharedViewModel = OrderViewModel(),
            onNavigateToPickupScreen = {},
            onNavigateUp = {}
        )
    }
}
