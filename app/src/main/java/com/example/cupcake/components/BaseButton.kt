package com.example.cupcake.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.cupcake.R

enum class ButtonType {
    PRIMARY, SECONDARY
}

@Composable
fun BaseButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: ButtonType = ButtonType.PRIMARY
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(
                when (type) {
                    ButtonType.PRIMARY -> R.color.pink_600
                    ButtonType.SECONDARY -> R.color.white
                }
            )
        ),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        border = when (type) {
            ButtonType.PRIMARY -> null
            ButtonType.SECONDARY -> BorderStroke(width = 1.dp, color = Color.LightGray.copy(alpha = 0.5f))
        }
    ) {
        Text(
            text = text.uppercase(),
            color = colorResource(
                when (type) {
                    ButtonType.PRIMARY -> R.color.white
                    ButtonType.SECONDARY -> R.color.pink_600
                }
            ),
        )
    }
}
