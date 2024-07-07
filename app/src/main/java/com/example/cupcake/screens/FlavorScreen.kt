package com.example.cupcake.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.components.BaseButton
import com.example.cupcake.components.BaseTopBar
import com.example.cupcake.components.ButtonType
import com.example.cupcake.model.OrderViewModel

@Composable
fun FlavorScreen(
    viewModel: OrderViewModel,
    onNavigateToBack: () -> Unit,
    onNavigateToPickUpScreen: () -> Unit
) {
    val priceValue by viewModel.price.observeAsState()
    val flavorValue by viewModel.flavor.observeAsState()
    Scaffold(
        topBar = {
            BaseTopBar(
                title = stringResource(R.string.choose_flavor),
                onBackClick = {
                    viewModel.resetOrder()
                    onNavigateToBack()
                }
            )
        }
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            selectedFlavor = flavorValue ?: "",
            price = priceValue.toString(),
            onCancelClick = {
                viewModel.resetOrder()
                onNavigateToBack()
            },
            onNavigateToPickUpScreen = onNavigateToPickUpScreen,
            onSelectFlavor = viewModel::setFlavor
        )
    }

    BackHandler {
        viewModel.resetOrder()
        onNavigateToBack()
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    selectedFlavor: String,
    price: String,
    onCancelClick: () -> Unit,
    onNavigateToPickUpScreen: () -> Unit,
    onSelectFlavor: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        val flavors = listOf(
            stringResource(R.string.vanilla),
            stringResource(R.string.chocolate),
            stringResource(R.string.red_velvet),
            stringResource(R.string.salted_caramel),
            stringResource(R.string.coffee)
        )

        LaunchedEffect(selectedFlavor) {
            if (selectedFlavor.isBlank()) onSelectFlavor(flavors.first())
        }
        flavors.forEach { flavor ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedFlavor == flavor,
                    onClick = { onSelectFlavor(flavor) },
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
                )
                Text(
                    text = flavor,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        Box(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = stringResource(R.string.subtotal_price, price),
                style = TextStyle(fontSize = 18.sp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth()) {
            BaseButton(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.cancel),
                onClick = onCancelClick,
                type = ButtonType.SECONDARY
            )
            Spacer(modifier = Modifier.width(16.dp))
            BaseButton(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.next),
                onClick = onNavigateToPickUpScreen
            )
        }
    }
}
