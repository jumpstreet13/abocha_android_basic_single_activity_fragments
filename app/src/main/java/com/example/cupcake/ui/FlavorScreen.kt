package com.example.cupcake.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.theme.CupcakeTheme
import com.example.cupcake.ui.widgets.CupcakeTopBar
import com.example.cupcake.ui.widgets.RadioButtonWithRipple
import com.example.cupcake.ui.widgets.RectangularFilledButton

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
        FlavorScreenContent(sharedViewModel, onNavigateToPickupScreen, onNavigateUp,  modifier.padding(paddingValues))
    }
}

@Composable
private fun FlavorScreenContent(
    sharedViewModel: OrderViewModel,
    onNavigateToPickupScreen: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val flavors = listOf(
            stringResource(R.string.vanilla),
            stringResource(R.string.chocolate),
            stringResource(R.string.red_velvet),
            stringResource(R.string.salted_caramel),
            stringResource(R.string.coffee),
        )

        val selectedOption by sharedViewModel.flavor.observeAsState(flavors[0])
        FlavorPickerRadioGroup(
            flavors,
            selectedOption,
            onFlavorSelected = { flavor -> sharedViewModel.setFlavor(flavor) },
            modifier = Modifier.padding(start = 16.dp)
        )

        Divider(modifier = Modifier.padding(horizontal = 16.dp))

        val price by sharedViewModel.price.observeAsState("0.0")
        SubtotalPrice(
            price = price,
            modifier = Modifier
                .padding(top = 0.dp, end = 16.dp)
                .align(alignment = Alignment.End)
        )

        NavigationButtons(onNavigateToPickupScreen, onNavigateUp)
    }
}

@Composable
private fun FlavorPickerRadioGroup(
    flavors: List<String>,
    selectedOption: String,
    onFlavorSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(Modifier.selectableGroup()) {
        flavors.forEach { text ->
            RadioButtonWithRipple(
                text = text,
                selected = (text == selectedOption),
                modifier = modifier.padding(vertical = 16.dp),
                onClick = {
                    onFlavorSelected(text)
                }
            )
        }
    }
}

@Composable
fun Divider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        thickness = 1.dp
    )
}

@Composable
fun SubtotalPrice(price: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.subtotal_price, price),
        fontSize = 20.sp,
        modifier = modifier
    )
}

@Composable
private fun NavigationButtons(
    onNavigateToPickupScreen: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp), Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(
            onClick = onNavigateUp,
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }

        RectangularFilledButton(
            buttonText = stringResource(id = R.string.next),
            onClick = onNavigateToPickupScreen,
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        )
    }
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
