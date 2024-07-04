package com.example.cupcake.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.uikit.AppButton
import com.example.cupcake.uikit.AppRadioGroup
import com.example.cupcake.uikit.AppSecondaryButton

@Composable
fun OrderComponent(
    choices: List<String>,
    selected: String,
    price: String,
    onNextButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit,
    onSelect: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AppRadioGroup(choices = choices, selected = selected, onSelect = onSelect)
        HorizontalDivider()
        Text(
            text = stringResource(id = R.string.subtotal_price, price),
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            AppSecondaryButton(
                text = stringResource(id = R.string.cancel),
                onClick = onCancelButtonClick,
                modifier = Modifier.weight(1f)
            )
            AppButton(
                text = stringResource(id = R.string.next),
                onClick = onNextButtonClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewOrderComponent() {
    AppScreenWrapper(title = "Screen title") {
        OrderComponent(
            choices = listOf("radio_button_1", "radio_button_2", "radio_button_3"),
            selected = "radio_button_1",
            price = "12$",
            onNextButtonClick = { },
            onCancelButtonClick = { },
            onSelect = {})
    }
}