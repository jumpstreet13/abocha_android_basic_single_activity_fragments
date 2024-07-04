package com.example.cupcake

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DiagonalColumn(
    modifier: Modifier = Modifier,
    verticalPadding: Dp = 0.dp,
    horizontalPadding: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier.padding(vertical = verticalPadding, horizontal = horizontalPadding)
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
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

@Preview(showBackground = true)
@Composable
fun DiagonalColumnPreview() {
    DiagonalColumn(
        verticalPadding = 16.dp, horizontalPadding = 16.dp
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .padding(4.dp)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {}
        Box(
            modifier = Modifier
                .size(70.dp)
                .padding(4.dp)
                .background(Color.Red),
            contentAlignment = Alignment.Center,
        ) {}
        Box(
            modifier = Modifier
                .size(90.dp)
                .padding(4.dp)
                .background(Color.Green),
            contentAlignment = Alignment.Center,
        ) {}
    }
}
