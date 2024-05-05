package com.example.cupcake

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.cupcake.model.OrderViewModel

@Composable
fun StartScreen(viewModel: OrderViewModel, navController: NavController) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.side_margin))
    ) {
        val buttonModifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.margin_between_elements))
            .defaultMinSize(
                minWidth = dimensionResource(id = R.dimen.order_cupcake_button_width)
            )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.margin_between_elements))
                    .size(dimensionResource(id = R.dimen.image_size)),
                contentScale = ContentScale.Inside,
                imageVector = ImageVector.vectorResource(id = R.drawable.cupcake),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.side_margin)),
                text = stringResource(id = R.string.order_cupcakes),
                style = MaterialTheme.typography.h4,
                color = colorResource(id = com.google.android.material.R.color.material_on_background_emphasis_medium)
            )
            Button(
                modifier = buttonModifier,
                onClick = { orderCupcake(context, 1, viewModel, navController) }) {
                Text(text = stringResource(id = R.string.one_cupcake).uppercase())
            }
            Button(
                modifier = buttonModifier,
                onClick = { orderCupcake(context, 6, viewModel, navController) }) {
                Text(text = stringResource(id = R.string.six_cupcakes).uppercase())
            }
            Button(
                modifier = buttonModifier,
                onClick = { orderCupcake(context, 12, viewModel, navController) }) {
                Text(text = stringResource(id = R.string.twelve_cupcakes).uppercase())
            }
        }
    }
}

fun orderCupcake(
    context: Context,
    quantity: Int,
    viewModel: OrderViewModel,
    navController: NavController
) {
    // Update the view model with the quantity
    viewModel.setQuantity(quantity)

    // If no flavor is set in the view model yet, select vanilla as default flavor
    if (viewModel.hasNoFlavorSet()) {
        viewModel.setFlavor(context.getString(R.string.vanilla))
    }

    // Navigate to the next destination to select the flavor of the cupcakes
    navController.navigate(Screen.Flavor.name)
}