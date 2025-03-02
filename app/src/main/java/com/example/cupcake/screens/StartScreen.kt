package com.example.cupcake.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.CupcakeAppTheme

@Composable
fun StartScreenHost(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel,
    navigateToFlavor: () -> Unit,
) {
    val defaultFlavor = stringResource(R.string.vanilla)
    val orderCupcake = viewModel.buildOrderCupcakeAction(defaultFlavor, navigateToFlavor)

    StartScreen(
        modifier = modifier,
        orderOneCupcake = { orderCupcake(1) },
        orderSixCupcakes = { orderCupcake(6) },
        orderTwelveCupcakes = { orderCupcake(12) }
    )
}

private fun OrderViewModel.buildOrderCupcakeAction(
    defaultFlavor: String,
    navigateToFlavorScreen: () -> Unit
) = fun(quantity: Int) {
    setQuantity(quantity)

    if (hasNoFlavorSet())
        setFlavor(defaultFlavor)

    navigateToFlavorScreen()
}

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    orderOneCupcake: () -> Unit = {},
    orderSixCupcakes: () -> Unit = {},
    orderTwelveCupcakes: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState, enabled = true)
            .padding(horizontal = dimensionResource(R.dimen.side_margin)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.side_margin)))
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_between_elements)))

        Image(
            modifier = Modifier.size(dimensionResource(R.dimen.image_size)),
            painter = painterResource(R.drawable.cupcake),
            contentDescription = null,
            contentScale = ContentScale.None
        )

        Text(
            text = stringResource(R.string.order_cupcakes),
            fontSize = 34.sp,
            fontFamily = FontFamily.SansSerif,
            color = colorResource(com.google.android.material.R.color.material_on_background_emphasis_medium),
        )

        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_between_elements)))

        Button(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = dimensionResource(R.dimen.order_cupcake_button_width)
                ),
            shape = RoundedCornerShape(4.dp),
            onClick = orderOneCupcake,
        ) {
            Text(
                text = stringResource(R.string.one_cupcake).uppercase(),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_between_elements)))

        Button(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = dimensionResource(R.dimen.order_cupcake_button_width)
                ),
            shape = RoundedCornerShape(4.dp),
            onClick = orderSixCupcakes,
        ) {
            Text(
                text = stringResource(R.string.six_cupcakes).uppercase(),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_between_elements)))

        Button(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = dimensionResource(R.dimen.order_cupcake_button_width)
                ),
            shape = RoundedCornerShape(4.dp),
            onClick = orderTwelveCupcakes,
        ) {
            Text(
                text = stringResource(R.string.twelve_cupcakes).uppercase(),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.side_margin)))
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel")
@Composable
fun StartScreenPreview() {
    CupcakeAppTheme {
        StartScreen()
    }
}