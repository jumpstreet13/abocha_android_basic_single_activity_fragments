package com.example.cupcake.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.cupcake.PickerContent
import com.example.cupcake.Pickup
import com.example.cupcake.model.OrderViewModel

@Composable
fun FlavorScreen(
    navController: NavController, viewModel: OrderViewModel
) {
    val additionalProducts = listOf<Int>(
        com.example.cupcake.R.string.vanilla,
        com.example.cupcake.R.string.chocolate,
        com.example.cupcake.R.string.red_velvet,
        com.example.cupcake.R.string.salted_caramel,
        com.example.cupcake.R.string.coffee
    ).map {
        stringResource(it)
    }

    val stateSelectedFlavor = viewModel.flavor.observeAsState<String>().value
    val subtotalPrice = viewModel.price.observeAsState<String>().value

    PickerContent(
        products = additionalProducts,
        selectedProduct = stateSelectedFlavor ?: "",
        subtotalPrice = subtotalPrice ?: "",
        onRadioButtonClick = { viewModel.setFlavor(it) },
        canselAction = {
            viewModel.resetOrder()
            navController.popBackStack()
        },
        nextAction = { navController.navigate(Pickup) })
}

@Composable
fun CustomRadioButton(
    modifier: Modifier, selected: Boolean, text: String, onClick: (String) -> Unit
) {
    Row(
        modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected, colors = RadioButtonColors(
                selectedColor = colorResource(com.example.cupcake.R.color.pink_400),
                unselectedColor = RadioButtonDefaults.colors().unselectedColor,
                disabledSelectedColor = RadioButtonDefaults.colors().disabledSelectedColor,
                disabledUnselectedColor = RadioButtonDefaults.colors().disabledUnselectedColor
            ), onClick = {
                onClick(text)
            })
        Text(text)
    }
}