package com.example.cupcake

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.theme.ButtonPink


@Composable
fun StartScreen(
    navController: NavHostController,
    sharedViewModel: OrderViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.side_margin)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.cupcake),
            "image_cupcake",
            Modifier
                .size(dimensionResource(R.dimen.image_size))
                .padding(start = dimensionResource(R.dimen.margin_between_elements))
        )
        Text(stringResource(R.string.order_cupcakes))
        Button(
            onClick = { orderCupcake( 1, navController, sharedViewModel) },
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .defaultMinSize(minWidth = dimensionResource(R.dimen.order_cupcake_button_width)),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonPink)
        ) {
            Text(stringResource(R.string.one_cupcake))
        }
        Button(
            onClick = { orderCupcake( 6, navController, sharedViewModel) },
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .defaultMinSize(minWidth = dimensionResource(R.dimen.order_cupcake_button_width)),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonPink)
        ) {
            Text(stringResource(R.string.six_cupcakes))
        }
        Button(
            onClick = { orderCupcake( 12, navController, sharedViewModel) },
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .defaultMinSize(minWidth = dimensionResource(R.dimen.order_cupcake_button_width)),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonPink)
        ) {
            Text(stringResource(R.string.twelve_cupcakes))
        }
    }
}

fun orderCupcake(
    quantity: Int,
    navController: NavHostController,
    sharedViewModel: OrderViewModel
) {
    // Update the view model with the quantity
    sharedViewModel.setQuantity(quantity)

    // If no flavor is set in the view model yet, select vanilla as default flavor
    if (sharedViewModel.hasNoFlavorSet()) {
        sharedViewModel.setFlavor(MainActivity.appContext.resources.getString(R.string.vanilla))
    }

    // Navigate to the next destination to select the flavor of the cupcakes
    goToNextScreen(navController, NavScreen.FlavorScreenNav.route)
}