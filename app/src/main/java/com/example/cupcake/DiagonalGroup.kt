package com.example.cupcake

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DiagonalGroup(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurableList, constraints ->
        val placeableList = measurableList.map { it.measure(constraints) }

        val totalWidth = placeableList.sumOf { it.width }
        val totalHeight = placeableList.sumOf { it.height }

        layout(width = totalWidth, height = totalHeight) {
            var x = 0
            var y = 0
            placeableList.forEach { placeable ->
                placeable.place(x, y)
                x += placeable.width
                y += placeable.height
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiagonalGroupPreview() {
    DiagonalGroup {
        Text("Sharik")
        Text("Bobik")
        Text("Gena")
        Text("Buka")
        Text("Buba")

    }
}