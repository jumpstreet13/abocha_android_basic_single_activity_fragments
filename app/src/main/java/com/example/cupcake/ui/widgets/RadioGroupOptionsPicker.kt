package com.example.cupcake.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel


@Composable
fun RadioGroupOptionPicker(
    options: List<String>,
    sharedViewModel: OrderViewModel,
    onConfirmSelection: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedOption by sharedViewModel.flavor.observeAsState(options[0])
        FlavorPickerRadioGroup(
            options,
            selectedOption,
            onFlavorSelected = { flavor -> sharedViewModel.setFlavor(flavor) },
            modifier = Modifier.padding(start = 16.dp)
        )

        Divider(modifier = Modifier.padding(horizontal = 16.dp))

        val price by sharedViewModel.price.observeAsState("0.0")
        SubtotalPrice(
            price = price,
            modifier = Modifier
                .padding(top = 0.dp, end = 16.dp)
                .align(alignment = Alignment.End)
        )

        ConfirmCancelButtons(onConfirmSelection, onCancel)
    }
}

@Composable
private fun FlavorPickerRadioGroup(
    flavors: List<String>,
    selectedOption: String,
    onFlavorSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(Modifier.selectableGroup()) {
        flavors.forEach { text ->
            RadioButtonWithRipple(
                text = text,
                selected = (text == selectedOption),
                modifier = modifier.padding(vertical = 16.dp),
                onClick = {
                    onFlavorSelected(text)
                }
            )
        }
    }
}

@Composable
private fun Divider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        thickness = 1.dp
    )
}

@Composable
private fun SubtotalPrice(price: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.subtotal_price, price),
        fontSize = 20.sp,
        modifier = modifier
    )
}

@Composable
private fun ConfirmCancelButtons(
    onConfirmSelection: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp), Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(
            onClick = onCancel,
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }

        RectangularFilledButton(
            buttonText = stringResource(id = R.string.next),
            onClick = onConfirmSelection,
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        )
    }
}