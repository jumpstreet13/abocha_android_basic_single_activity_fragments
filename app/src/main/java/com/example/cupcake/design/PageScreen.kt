package com.example.cupcake.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cupcake.util.MARGIN
import com.example.cupcake.util.MARGIN_2
import com.example.cupcake.R

@Composable
fun PageScreen(
    items: List<String>,
    selectedItem: State<String?>,
    subtotalPrice: State<String?>,
    onSelect: (String) -> Unit,
    onCancel: () -> Unit,
    onNext: () -> Unit
) {
    Column(content = function(items, selectedItem, onSelect, subtotalPrice, onCancel, onNext))
}

@Composable
private fun function(
    items: List<String>,
    selectedItem: State<String?>,
    onSelect: (String) -> Unit,
    subtotalPrice: State<String?>,
    onCancel: () -> Unit,
    onNext: () -> Unit
): @Composable() (ColumnScope.() -> Unit) =
    {
        items.forEach {
            RadioButton(
                item = it,
                isSelected = selectedItem.value == it,
                onSelect = { onSelect(it) }
            )
        }

        Spacer(modifier = Modifier.height(MARGIN))

        Divider(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(MARGIN))

        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = MARGIN_2),
            text = stringResource(id = R.string.subtotal_price, subtotalPrice.value.orEmpty()),
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.height(MARGIN))

        Row(
            modifier = Modifier.padding(
                start = MARGIN_2,
                end = MARGIN_2
            )
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.cancel),
                onClick = onCancel
            )

            Spacer(modifier = Modifier.width(MARGIN))

            Button(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.next),
                onClick = onNext
            )
        }
    }

@Composable
fun RadioButton(
    item: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colors.primary
            ),
            onClick = { onSelect() }
        )
        Text(
            text = item
        )
    }
}