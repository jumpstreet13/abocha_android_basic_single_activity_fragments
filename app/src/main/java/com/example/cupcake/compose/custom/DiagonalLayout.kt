package com.example.cupcake.compose.custom

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DiagonalLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurableList, constraints ->
        val placeableList = measurableList.map { it.measure(constraints) }

        layout(
            width = placeableList.sumOf { it.width },
            height = placeableList.sumOf { it.height }
        ) {
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

@Composable
@Preview(showBackground = true)
private fun DiagonalLayoutPreview() {
    DiagonalLayout {
        Text("Sharik")
        Text("Bobik")
        Text("Gena")
        Text("Buka")
        Text("Buba")
    }
}
