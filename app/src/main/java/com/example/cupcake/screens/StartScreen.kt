package com.example.cupcake.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.model.NavigationRouts
import com.example.cupcake.viewModel.OrderViewModel
import com.example.cupcake.screens.base.BaseScreen


@Composable
fun StartScreen(
    navHostController: NavHostController,
    viewModel: OrderViewModel
) {
    val defaultFlavor = stringResource(R.string.vanilla)
    BaseScreen {
        Box(
            modifier = Modifier.padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 48.dp, bottom = 16.dp, end = 16.dp, start = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(300.dp),
                    painter = painterResource(R.drawable.cupcake),
                    contentDescription = "cupcake"
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
                    text = stringResource(R.string.order_cupcakes),
                    color = colorResource(R.color.material_on_background_emphasis_medium),
                    style = TextStyle(fontSize = 36.sp)
                )
                PinkButton(stringResource(R.string.one_cupcake)) {
                    navigateNext(
                        navHostController,
                        1,
                        defaultFlavor,
                        viewModel
                    )
                }
                PinkButton(stringResource(R.string.six_cupcakes)) {
                    navigateNext(
                        navHostController,
                        6,
                        defaultFlavor,
                        viewModel
                    )
                }
                PinkButton(stringResource(R.string.twelve_cupcakes)) {
                    navigateNext(
                        navHostController,
                        12,
                        defaultFlavor,
                        viewModel
                    )
                }
            }
        }
    }
}

private fun navigateNext(
    navHostController: NavHostController,
    cupcakesCount: Int,
    defaultFlavor: String,
    viewModel: OrderViewModel,
) {
    viewModel.setQuantity(cupcakesCount)
    if (viewModel.hasNoFlavorSet()) {
        viewModel.setFlavor(defaultFlavor)
    }

    navHostController.navigate(NavigationRouts.FLAVOR.rout)
}

@Composable
private fun PinkButton(
    text: String,
    onButtonClick: () -> Unit
) {
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_between_elements)))
    Button(
        modifier = Modifier
            .wrapContentSize()
            .defaultMinSize(minWidth = dimensionResource(id = R.dimen.order_cupcake_button_width)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.pink_600),
            disabledBackgroundColor = colorResource(id = R.color.black)
        ),
        onClick = onButtonClick
    ) {
        Text(text = text.uppercase(), color = colorResource(id = R.color.white))
    }
}


@Composable
@Preview
private fun StartScreenPreview() {
    StartScreen(rememberNavController(), OrderViewModel())
}