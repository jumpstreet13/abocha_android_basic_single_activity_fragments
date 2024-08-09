package com.example.cupcake

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.model.OrderViewModel


@Composable
fun StartScreen(viewModel: OrderViewModel, goToFlavorScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.side_margin))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.side_margin)))
        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.image_size))
                .height(dimensionResource(id = R.dimen.image_size))
                .padding(top = dimensionResource(id = R.dimen.margin_between_elements)),
            contentScale = ContentScale.None
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.side_margin)))
        Text(
            text = stringResource(R.string.order_cupcakes),
            style = MaterialTheme.typography.h4,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.side_margin)))
        Column(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.side_margin))) {
            OrderCupcakeButton(
                text = stringResource(R.string.one_cupcake),
                quantity = integerResource(id = R.integer.one_cup)
            ) {
                viewModel.setQuantity(it)
                goToFlavorScreen.invoke()
            }
            OrderCupcakeButton(
                text = stringResource(R.string.six_cupcakes),
                quantity = integerResource(id = R.integer.six_cups)
            ) {
                viewModel.setQuantity(it)
                goToFlavorScreen.invoke()
            }
            OrderCupcakeButton(
                text = stringResource(R.string.twelve_cupcakes),
                quantity = integerResource(id = R.integer.twelve_cups)
            ) {
                viewModel.setQuantity(it)
                goToFlavorScreen.invoke()
            }
        }

    }
}

@Composable
fun OrderCupcakeButton(text: String, quantity: Int, callback: (Int) -> Unit) {
    Button(
        onClick = {
            callback.invoke(quantity)
        },
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.margin_between_elements))
            .widthIn(min = dimensionResource(id = R.dimen.order_cupcake_button_width))
            .background(MaterialTheme.colors.background)
    ) {
        Text(text = text.uppercase())
    }
}


@Preview
@Composable
fun StartScreenPreview(){
    StartScreen(viewModel = OrderViewModel()) {

    }
}