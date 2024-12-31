package com.example.cupcake.compose.ui.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.example.cupcake.R
import com.example.cupcake.compose.ui.util.SIDE_MARGIN
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SelectionScreen(
    items: ImmutableList<String>,
    selectedItem: State<String?>,
    subtotalPrice: State<String?>,
    onSelect: (String) -> Unit,
    onCancelOrder: () -> Unit,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier.padding(SIDE_MARGIN)
    ) {
        items.forEach {
            SelectionRadioButton(
                item = it,
                isSelected = selectedItem.value == it,
                onSelect = { onSelect(it) }
            )
        }

        Spacer(modifier = Modifier.height(SIDE_MARGIN))

        Divider(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(SIDE_MARGIN))

        Text(
            modifier = Modifier.align(Alignment.End),
            text = stringResource(id = R.string.subtotal_price, subtotalPrice.value.orEmpty()),
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.height(SIDE_MARGIN))

        Row {
            CupcakeOutlinedButton(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.cancel),
                onButtonClick = onCancelOrder
            )

            Spacer(modifier = Modifier.width(SIDE_MARGIN))

            CupcakeButton(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.next),
                onButtonClick = onNext
            )
        }
    }
}

@Composable
fun SelectionRadioButton(
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
            onClick = {
                onSelect()
            }
        )

        Text(
            text = item,
            modifier = Modifier.clickable {
                onSelect()
            }
        )
    }
}
