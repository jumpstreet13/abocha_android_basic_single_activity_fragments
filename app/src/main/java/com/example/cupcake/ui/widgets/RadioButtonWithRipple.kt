package com.example.cupcake.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Radio button with a text label and ripple effect. The ripple effect is
 * shown on radio button only (not on a text label). The effect is
 * triggered when the radio button or text label is clicked.
 *
 * @param text The label for the radio button.
 * @param selected A boolean indicating whether the radio button is selected.
 * @param modifier A Modifier to apply to the row.
 * @param onClick A lambda function to handle click events.
 */
@Composable
fun RadioButtonWithRipple(
    text: String, selected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val ripple = rememberRipple(bounded = false)

    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = ripple,
                    onClick = onClick
                )
        ) {
            RadioButton(
                selected = selected,
                onClick = null,
                modifier = Modifier.padding(4.dp)
            )
        }
        Text(
            text = text,
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                )
        )
    }
}