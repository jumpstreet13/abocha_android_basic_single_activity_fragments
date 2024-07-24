package com.example.cupcake.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
private fun DiagonalColumnPreview() {
    DiagonalColumn {
        repeat(5) { index ->
            Box {
                Text(text = "Number $index")
            }
        }
    }
}

@Composable
fun DiagonalColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurePolicy, constraints ->
        val placeableList = measurePolicy.map { measurable ->
            measurable.measure(constraints)
        }

        var currentX = 0
        var currentY = 0
        val positions = placeableList.map { placeable ->
            val position = Pair(currentX, currentY)
            currentX += placeable.width
            currentY += placeable.height
            position
        }

        val totalWidth = positions.maxOfOrNull {
            it.first + placeableList[positions.indexOf(it)].width
        } ?: 0
        val totalHeight = positions.maxOfOrNull {
            it.second + placeableList[positions.indexOf(it)].height
        } ?: 0

        layout(totalWidth, totalHeight) {
            placeableList.forEachIndexed { index, placeable ->
                val (x, y) = positions[index]
                placeable.placeRelative(x, y)
            }
        }
    }
}
