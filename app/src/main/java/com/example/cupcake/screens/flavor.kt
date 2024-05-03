package com.example.cupcake.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.MainTheme
import com.example.cupcake.theme.PrimaryButton
import com.example.cupcake.theme.RadioSelect
import com.example.cupcake.theme.SecondaryButton


@Composable
fun FlavorScreen(
    model: OrderViewModel = viewModel()
) {
    val navController = LocalNavigation.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(R.dimen.side_margin))
        ) {
            // radio buttons
            Column(
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.side_margin))
            ) {
                remember {
                    arrayOf(
                        R.string.vanilla,
                        R.string.chocolate,
                        R.string.red_velvet,
                        R.string.salted_caramel,
                        R.string.coffee
                    )
                }.forEach { id ->
                    val flavor = model.flavor.observeAsState("").value
                    val label = stringResource(id)
                    RadioSelect(
                        label = label,
                        isSelected = flavor == label
                    ) {
                        model.setFlavor(label)
                    }
                }
            }
            Divider()
            Spacer(Modifier.height(dimensionResource(R.dimen.side_margin)))

            val price = model.price.observeAsState("").value
            Text(
                text = stringResource(R.string.subtotal_price, price),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.side_margin))
                    .align(Alignment.End)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.side_margin)
                )
            ) {
                // Cancel the order and start over.
                SecondaryButton(
                    labelId = R.string.cancel,
                    modifier = Modifier.weight(1f)
                ) {
                    // Reset order in view model
                    model.resetOrder()
                    // Navigate back to start over
                    navController.popBackStack(route = Screen.Home.route, inclusive = false)
                }
                // Navigate to the next screen to choose pickup date.
                PrimaryButton(
                    labelId = R.string.next,
                    modifier = Modifier.weight(1f)
                ) {
                    navController.navigate(Screen.Pickup.route)
                }
            }
        }
    }
}

@Preview(
    name = "FlavorScreen",
    showBackground = true,
    backgroundColor = 0xFFFFFF,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun FlavorPreview() {
    MainTheme {
        CompositionLocalProvider(
            LocalNavigation provides rememberNavController()
        ) {
            FlavorScreen(
                model = viewModel<OrderViewModel>().apply {
                    setQuantity(6)
                    setFlavor(stringResource(R.string.red_velvet))
                }
            )
        }
    }
}
