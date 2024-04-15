package com.example.cupcake

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.theme.ButtonPink

@Composable
fun FlavorScreen(navController: NavHostController, sharedViewModel: OrderViewModel) {

    val flavorItems = listOf(
        stringResource(R.string.vanilla),
        stringResource(R.string.chocolate),
        stringResource(R.string.red_velvet),
        stringResource(R.string.salted_caramel),
        stringResource(R.string.coffee)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.side_margin))
    ) {
        val priceText = mutableStateOf("${sharedViewModel.price.value}")
        MultipleRadioButtons(sharedViewModel, "flavor", flavorItems, priceText)
        Text(
            text = "${stringResource(R.string.subtotal_price)} ${priceText.value}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { cancelOrder(navController, sharedViewModel) },
                modifier = Modifier.fillMaxWidth(0.5F),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonPink)
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Button(
                onClick = { goToNextScreen(navController, NavScreen.PickupScreenNav.route) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonPink)
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Composable
fun MultipleRadioButtons(
    sharedViewModel: OrderViewModel,
    valueName: String,
    items: List<String>,
    priceText: MutableState<String>
) {
    val selectedValue = remember {
        mutableStateOf(
            if (valueName == "flavor") {
                sharedViewModel.flavor.value
            } else {
                sharedViewModel.date.value
            }
        )
    }
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    Column(Modifier.padding(2.dp)) {
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = isSelectedItem(item),
                        onClick = {
                            onChangeState(item)
                            if (valueName.equals("flavor")) sharedViewModel.setFlavor(item) else {
                                sharedViewModel.setDate(item)
                                priceText.value = sharedViewModel.price.value.toString()
                            }
                        },
                        role = Role.RadioButton
                    )
            ) {
                RadioButton(
                    selected = isSelectedItem(item),
                    onClick = {
                        onChangeState(item)
                        if (valueName.equals("flavor")) sharedViewModel.setFlavor(item) else {
                            sharedViewModel.setDate(item)
                            priceText.value = sharedViewModel.price.value.toString()
                        }
                    },
                )
                Text(
                    text = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

