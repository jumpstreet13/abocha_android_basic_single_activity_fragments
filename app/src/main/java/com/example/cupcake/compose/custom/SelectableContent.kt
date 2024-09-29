package com.example.cupcake.compose.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.compose.theme.CupcakeTheme

@Composable
fun SelectableContent(
    list: List<String>,
    selectedOption: String,
    price: String,
    onOptionSelected: (String) -> Unit,
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SelectableGroup(
            list = list,
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected
        )
        Divider(thickness = 1.dp, modifier = Modifier.padding(16.dp))
        Text(
            text = stringResource(id = R.string.subtotal_price, price),
            modifier = Modifier
                .padding(end = 16.dp)
                .align(alignment = Alignment.End),
            fontSize = 20.sp,
        )
        ButtonsContainer(
            onCancelClick = onCancelClick,
            onNextClick = onNextClick
        )
    }
}

@Composable
private fun SelectableGroup(
    list: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.selectableGroup()
    ) {
        list.forEach { text ->
            SelectableRow(
                text = text,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )
        }
    }
}

@Composable
private fun ButtonsContainer(
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = onCancelClick,
            shape = RectangleShape,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.cancel).uppercase(),
                fontSize = 14.sp
            )
        }

        Button(
            onClick = onNextClick,
            shape = RectangleShape,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.next).uppercase(),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun SelectableRow(
    text: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = text == selectedOption,
                onClick = { onOptionSelected(text) }
            ),
        verticalAlignment = Alignment.CenterVertically)
    {
        RadioButton(
            selected = text == selectedOption,
            onClick = {}
        )
        Text(text = text, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectableContentPreview() {
    CupcakeTheme {
        SelectableContent(
            list = listOf("Vanilla", "Chocolate", "Red Velvet", "Salted Caramel", "Coffee"),
            selectedOption = "Chocolate",
            price = "$5.00",
            onOptionSelected = {},
            onCancelClick = {},
            onNextClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectableGroupPreview() {
    CupcakeTheme {
        SelectableGroup(
            list = listOf("Vanilla", "Chocolate", "Red Velvet", "Salted Caramel", "Coffee"),
            selectedOption = "Chocolate",
            onOptionSelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonsContainerPreview() {
    CupcakeTheme {
        ButtonsContainer(
            onCancelClick = {},
            onNextClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectableRowPreview() {
    CupcakeTheme {
        SelectableRow(
            text = "Vanilla",
            selectedOption = "Vanilla",
            onOptionSelected = {}
        )
    }
}
