package com.example.cupcake.ui.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Ladder(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
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

@Preview
@Composable
fun LadderPreview() {
    Ladder {
        Text("Text 1")
        Text("Text 2")
        Text("Text 3")
        Text("Text 4")
    }
}