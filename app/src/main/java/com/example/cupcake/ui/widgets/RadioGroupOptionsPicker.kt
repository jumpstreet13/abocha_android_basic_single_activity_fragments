package com.example.cupcake.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.cupcake.R


@Composable
fun RadioGroupOptionPicker(
    options: List<String>,
    selectedOption: String,
    price: String,
    onOptionSelected: (String) -> Unit,
    onConfirmSelection: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlavorPickerRadioGroup(
            options,
            selectedOption,
            onOptionSelected = onOptionSelected,
            modifier = Modifier
        )

        CupcakeDivider(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.side_margin))
        )

        SubtotalPrice(
            stringResource(id = R.string.subtotal_price, price),
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.side_margin))
                .align(alignment = Alignment.End)
        )

        ConfirmCancelButtons(onConfirmSelection, onCancel)
    }
}

@Composable
private fun FlavorPickerRadioGroup(
    flavors: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(Modifier.selectableGroup()) {
        flavors.forEach { text ->
            RadioButtonWithRipple(
                text = text,
                selected = (text == selectedOption),
                modifier = modifier
                    .padding(vertical = dimensionResource(id = R.dimen.margin_between_elements)),
                onClick = {
                    onOptionSelected(text)
                }
            )
        }
    }
}

@Composable
private fun SubtotalPrice(result: String, modifier: Modifier = Modifier) {
    Text(
        text = result,
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
            .padding(top = dimensionResource(id = R.dimen.side_margin)),
    ) {
        OutlinedButton(
            onClick = onCancel,
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .padding(end = dimensionResource(id = R.dimen.side_margin))
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }

        RectangularFilledButton(
            buttonText = stringResource(id = R.string.next),
            onClick = onConfirmSelection,
            modifier = Modifier
                .weight(1f)
        )
    }
}