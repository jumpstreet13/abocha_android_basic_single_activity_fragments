package com.example.cupcake.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.MainTheme
import com.example.cupcake.theme.PrimaryButton

@Composable
fun StartScreen(
    model: OrderViewModel = viewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(R.dimen.side_margin)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // image
            Spacer(Modifier.height(dimensionResource(R.dimen.margin_between_elements)))
            Image(
                painter = painterResource(R.drawable.cupcake),
                contentDescription = null,
                modifier = Modifier.size(
                    dimensionResource(R.dimen.image_size)
                ),
                contentScale = ContentScale.Inside
            )

            // title
            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.medium
            ) {
                Text(
                    text = stringResource(R.string.order_cupcakes),
                    style = MaterialTheme.typography.h4
                )
            }
            Spacer(Modifier.height(dimensionResource(R.dimen.side_margin)))

            // buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.margin_between_elements)
                )
            ) {
                val navController = LocalNavigation.current

                val vanilla = stringResource(R.string.vanilla)

                /**
                 * Start an order with the desired quantity of cupcakes and navigate to the next screen.
                 */
                val orderCupcake = remember {
                    { quantity: Int ->
                        // Update the view model with the quantity
                        model.setQuantity(quantity)
                        // If no flavor is set in the view model yet, select vanilla as default flavor
                        if (model.hasNoFlavorSet()) {
                            model.setFlavor(vanilla)
                        }
                        // Navigate to the next destination to select the flavor of the cupcakes
                        navController.navigate(Screen.Flavor.route)
                    }
                }

                PrimaryButton(labelId = R.string.one_cupcake) {
                    orderCupcake(1)
                }
                PrimaryButton(labelId = R.string.six_cupcakes) {
                    orderCupcake(6)
                }
                PrimaryButton(labelId = R.string.twelve_cupcakes) {
                    orderCupcake(12)
                }
            }
        }
    }
}

@Preview(
    name = "StartScreen",
    showBackground = true,
    backgroundColor = 0xFFFFFF,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun StartPreview() {
    MainTheme {
        CompositionLocalProvider(
            LocalNavigation provides rememberNavController()
        ) {
            StartScreen()
        }
    }
}
