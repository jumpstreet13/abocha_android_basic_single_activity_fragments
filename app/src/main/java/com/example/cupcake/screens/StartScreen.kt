package com.example.cupcake.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cupcake.Flavor
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(300.dp),
                    painter = painterResource(R.drawable.cupcake),
                    contentScale = ContentScale.None,
                    contentDescription = "Cupcake"
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Center,
                    color = colorResource(com.google.android.material.R.color.material_on_background_emphasis_medium),
                    style = MaterialTheme.typography.headlineMedium,
                    text = stringResource(R.string.order_cupcakes)
                )

                val vanillaString = stringResource(R.string.vanilla)
                val orderCupcake: (quantity: Int) -> Unit = { quantity ->
                    viewModel.setQuantity(quantity)
                    if (viewModel.hasNoFlavorSet()) {
                        viewModel.setFlavor(vanillaString)
                    }
                    navController.navigate(Flavor)
                }

                CupcakeButton(Modifier, text = stringResource(R.string.one_cupcake)) {
                    orderCupcake(1)
                }
                CupcakeButton(Modifier, text = stringResource(R.string.six_cupcakes)) {
                    orderCupcake(6)
                }
                CupcakeButton(Modifier, text = stringResource(R.string.twelve_cupcakes)) {
                    orderCupcake(12)
                }
            }
        }
    }
}

@Composable
fun CupcakeButton(modifier: Modifier, isWhite: Boolean = false, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .defaultMinSize(minWidth = 250.dp),
        shape = RoundedCornerShape(0.dp),
        colors = buttonColors(containerColor = colorResource(if (isWhite) R.color.white else R.color.pink_400)),
        onClick = onClick
    ) {
        Text(
            text = text.uppercase(),
            color = colorResource(if (isWhite) R.color.pink_400 else R.color.white)
        )
    }
}