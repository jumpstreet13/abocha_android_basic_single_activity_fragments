package com.example.cupcake.ui

import android.content.Context
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import java.text.NumberFormat

private val flavorResIds = listOf(
    R.string.vanilla,
    R.string.chocolate,
    R.string.red_velvet,
    R.string.salted_caramel,
    R.string.coffee
)

@Composable
fun ChooseFlavorScreen(
    uiState: OrderViewModel.UiState,
    onFlavorSelected: (String) -> Unit,
    onClickNext: () -> Unit,
    onClickCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context: Context = LocalContext.current
    val flavors = remember {
        flavorResIds.map { context.getString(it) }
    }
    val index = remember(uiState.flavor) {
        flavors.indexOf(uiState.flavor)
    }

    Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.side_margin)),
        modifier = modifier
        .fillMaxWidth()
    ) {
        RadioGroup(flavors,
            selectedIndex = index,
            onClick = {
                onFlavorSelected(flavors[it])
            }
        )

        Divider(Modifier.height(1.dp))

        Text(
            text = stringResource(R.string.subtotal_price, NumberFormat.getCurrencyInstance().format(uiState.price)),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CancelButton(onClick =onClickCancel,
                modifier = Modifier.weight(1f)

            )

            Spacer(Modifier.width(dimensionResource(R.dimen.side_margin) * 2))

            NextButton(onClick = onClickNext,
                modifier = Modifier.weight(1f)

            )
        }
    }
}

@Preview(device = Devices.NEXUS_6, backgroundColor = Color.BLUE.toLong())
@Composable
fun ChooseFlavorScreenPreview() {
    CupcakeTheme {
        val uiState = OrderViewModel.UiState().copy(
            flavor = stringResource(R.string.red_velvet),
            price = 27.43f
        )
        ChooseFlavorScreen(uiState,
            onFlavorSelected = {},
            onClickCancel = {},
            onClickNext = {}
        )
    }
}