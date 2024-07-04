package com.example.cupcake.uikit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.theme.AppColors

@Composable
fun AppRadioGroup(
    choices: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        choices.forEach { choice ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selected == choice,
                    onClick = { onSelect(choice) },
                    colors = RadioButtonDefaults.colors(selectedColor = AppColors.purple400)
                )
                Text(
                    text = choice,
                    modifier = Modifier.clickable { onSelect(choice) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewAppRadioGroup() {
    AppRadioGroup(
        choices = listOf("radio_button_1", "radio_button_2", "radio_button_3"),
        selected = "radio_button_1",
        onSelect = {},
    )
}