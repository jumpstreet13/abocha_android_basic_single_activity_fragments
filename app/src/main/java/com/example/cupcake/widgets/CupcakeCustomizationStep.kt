package com.example.cupcake.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R

@Composable
fun CupcakeCustomizationStep(
    modifier: Modifier = Modifier,
    currentPrice: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    options: List<String>,
    onNext: () -> Unit,
    onCancel: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState, enabled = true)
            .padding(horizontal = dimensionResource(R.dimen.side_margin))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.side_margin)))

        RadioGroup(
            modifier = Modifier.fillMaxWidth(),
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected,
            options = options
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(modifier = Modifier.height(1.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = currentPrice,
                fontSize = 18.sp,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                modifier = Modifier.weight(0.8f),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)),
                onClick = onCancel,
            ) {
                Text(text = stringResource(R.string.cancel).uppercase())
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                modifier = Modifier.weight(0.8f),
                shape = RoundedCornerShape(4.dp),
                onClick = onNext,
            ) {
                Text(text = stringResource(R.string.next).uppercase())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}