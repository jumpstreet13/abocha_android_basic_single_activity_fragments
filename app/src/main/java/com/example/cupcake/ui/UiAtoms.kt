package com.example.cupcake.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = ColorPalette.primary,
            contentColor = ColorPalette.onPrimary
        ),
        modifier = modifier
            .defaultMinSize(minWidth = dimensionResource(R.dimen.order_cupcake_button_width))
    ) {
        Text(text.uppercase())
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = ColorPalette.onPrimary,
            contentColor = ColorPalette.primary
        ),
        border = BorderStroke(1.dp, Color.Gray),
        modifier = modifier
            //.border(BorderStroke(1.dp, Color.Blue))
            .defaultMinSize(minWidth = dimensionResource(R.dimen.order_cupcake_button_width))
    ) {
        Text(text.uppercase())
    }
}

@Composable
fun NextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PrimaryButton(
        text = stringResource(R.string.next),
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun CancelButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SecondaryButton(
        text = stringResource(R.string.cancel),
        onClick = onClick,
        modifier = modifier
    )
}

@Preview(device = Devices.NEXUS_6)
@Composable
private fun Preview() {
    CupcakeTheme {
        Column(Modifier.padding(16.dp)) {
            val bg = Color.Green.copy(alpha = 0.1f)
            Box(Modifier
                .background(bg)
                .padding(10.dp)
            ) {
                NextButton(onClick = { /*TODO*/ })
            }

            Spacer(Modifier.height(10.dp))

            Box(Modifier
                .background(bg)
                .padding(10.dp)
            ) {
                CancelButton(onClick = { /*TODO*/ })
            }
        }
    }
}

@Composable
fun RadioGroup(
    items: List<String>,
    selectedIndex: Int,
    onClick: (Int) -> Unit,
) {
    Column {
        items.forEachIndexed { index, item ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = index == selectedIndex,
                        onClick = { onClick(index) }
                    )
            ) {
                RadioButton(selected = index == selectedIndex, onClick = { onClick(index) })
                Text(item)
            }
        }
    }
}
