package com.example.cupcake.compose_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cupcake.OrderState
import com.example.cupcake.R
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ChooseOptionScreen(
    cancelButtonNavigate: () -> Unit,
    nextButtonNavigate: () -> Unit,
    orderFlow: StateFlow<OrderState>,
    options: List<String>,
    setSelectedOption: (value: String) -> Unit
) {
    val order by orderFlow.collectAsState()
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.side_margin))
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        OptionsRadioGroup(
            options = options,
            setSelectedOption = setSelectedOption
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.side_margin)),
            color = Color.Gray,
            thickness = 1.dp
        )
        Text(
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.side_margin))
                .align(Alignment.End),
            text = "Subtotal $${order.price}",
            style = MaterialTheme.typography.h6
        )
        Buttons(
            cancelButtonNavigate,
            nextButtonNavigate
        )
    }
}

@Composable
fun OptionsRadioGroup(options: List<String>, setSelectedOption: (String) -> Unit) {
    val selectedIngredient: MutableState<String> = remember { mutableStateOf(options[0]) }
    Column(Modifier.selectableGroup()) {
        options.forEach { ingredientName ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = ingredientName == selectedIngredient.value,
                    onClick = { selectedIngredient.value = ingredientName }
                )
                Text(text = ingredientName)
            }
        }
    }
    setSelectedOption(selectedIngredient.value)
}

@Composable
fun Buttons(
    cancelButtonNavigate: () -> Unit,
    nextButtonNavigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = dimensionResource(R.dimen.side_margin))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.side_margin))
    ) {
        Button(
            onClick = cancelButtonNavigate,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.cancel))
        }
        Button(
            onClick = nextButtonNavigate,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}