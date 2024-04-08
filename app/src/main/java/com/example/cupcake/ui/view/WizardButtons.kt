package com.example.cupcake.ui.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.ui.theme.CupcakeTheme

@Composable
fun WizardButtons(onCancelClick: () -> Unit, onNextClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        OutlinedButton(
            modifier = Modifier.weight(0.5f),
            onClick = onCancelClick
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            modifier = Modifier.weight(0.5f),
            onClick = onNextClick
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview
@Composable
private fun WizardButtonsPreview() {
    CupcakeTheme {
        WizardButtons(
            onCancelClick = {},
            onNextClick = {}
        )
    }
}