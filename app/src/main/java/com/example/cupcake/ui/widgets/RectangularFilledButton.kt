package com.example.cupcake.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import com.example.cupcake.R

@Composable
fun RectangularFilledButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RectangleShape,
        modifier = modifier
            .widthIn(min = dimensionResource(id = R.dimen.order_cupcake_button_width))
            .padding(dimensionResource(id = R.dimen.margin_between_elements))
    ) {
        Text(buttonText.uppercase())
    }
}