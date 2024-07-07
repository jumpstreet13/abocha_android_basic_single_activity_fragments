package com.example.cupcake.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints


@Composable
fun DiagonalColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = Box(modifier) {
    Layout(
        content = content,
        measurePolicy = diagonalMeasurePolicy()
    )
}

@Composable
private fun diagonalMeasurePolicy(): MeasurePolicy = remember {
    MeasurePolicy { measurables: List<Measurable>, constraints: Constraints ->
        val placeables = measurables.map { measurable -> measurable.measure(constraints) }

        layout(placeables.sumOf { it.width }, placeables.sumOf { it.height }) {
            var xPosition = 0
            var yPosition = 0

            placeables.forEach { placeable ->
                placeable.placeRelative(x = xPosition, y = yPosition)
                xPosition += placeable.width
                yPosition += placeable.height
            }
        }
    }
}

@Preview(widthDp = 550, heightDp = 420, showBackground = true)
@Composable
fun DiagonalColumnPreview() {
    MaterialTheme {
        DiagonalColumn(Modifier.fillMaxSize()) {
            Text(text = "Sharik")
            Text(text = "Bobik")
            Text(text = "Gena")
            Text(text = "Buka")
            Text(text = "Buba")
        }
    }
}
