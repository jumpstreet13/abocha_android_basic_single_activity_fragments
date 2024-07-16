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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import java.text.NumberFormat

@Composable
fun SummaryScreen(
    uiState: OrderViewModel.UiState,
    onClickSend: () -> Unit,
    onClickCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.side_margin)),
        modifier = modifier
            .fillMaxWidth()
    ) {
        TitleAndDescription(
            stringResource(R.string.quantity),
            uiState.quantity.toString()
        )

        TitleAndDescription(
            stringResource(R.string.flavor),
            uiState.flavor
        )

        TitleAndDescription(
            stringResource(R.string.pickup_date),
            uiState.date
        )

        Text(
            text = stringResource(R.string.total_price,
                NumberFormat.getCurrencyInstance().format(uiState.price)).uppercase(),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
        )

        PrimaryButton(
            stringResource(R.string.send),
            onClick = onClickSend,
            modifier = Modifier.fillMaxWidth()
        )

        CancelButton(
            onClick = onClickCancel,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TitleAndDescription(
    title: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )

        Divider(Modifier.height(1.dp))
    }
}

@Preview(device = Devices.NEXUS_6, backgroundColor = Color.BLUE.toLong())
@Composable
fun SummaryPreview() {
    CupcakeTheme {
        val uiState = OrderViewModel.UiState().copy(
            flavor = "Vanila",
            date = "Tomorrow",
            price = 27.43f
        )
        SummaryScreen(uiState,
            onClickCancel = {},
            onClickSend = {}
        )
    }
}