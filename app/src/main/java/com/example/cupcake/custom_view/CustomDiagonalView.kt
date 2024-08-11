package com.example.cupcake.custom_view

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp

@Composable
fun CustomDiagonalView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val resolveConstraint = constraints.copy(minHeight = 0, minWidth = 0)
        val placeables = measurables.map { it.measure(resolveConstraint) }
        layout(
            width = constraints.constrainWidth(placeables.sumOf { it.width }),
            height = constraints.constrainHeight(placeables.sumOf { it.height })
        ) {
            var x = 0
            var y = 0
            placeables.forEach { placeable ->
                placeable.place(x, y)
                y += placeable.height
                x += placeable.width
            }
        }
    }
}

@Preview
@Composable
fun CustomPreview() {
    CustomDiagonalView(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
    ) {
        Text(text = "Sharik")
        Text("Boba")
        Text("Gena")
        Text(text = "Buka")
        Text(text = "Buba")
    }
}