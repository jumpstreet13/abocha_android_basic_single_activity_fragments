package com.example.part_2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CustomColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measures, constraints ->
        val itemSpacer = 4.toDp()
        val placeables = measures.map { it.measure(constraints) }
        val maxWidth = placeables.sumOf { it.width }.plus(
            (placeables.size - 1) * itemSpacer.roundToPx()
        )
        val maxHeight =
            placeables.sumOf { it.height }.plus(
                (placeables.size - 1) * itemSpacer.roundToPx()
            )

        layout(width = maxWidth, height = maxHeight) {
            var x = 0
            var y = 0
            placeables.forEach { placeable ->
                placeable.place(x, y)
                x += placeable.width.plus(itemSpacer.roundToPx())
                y += placeable.height.plus(itemSpacer.roundToPx())
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CustomColumnPreview() {
    CustomColumn {
        Text("One")
        Text("Two")
        Text("Three")
        Text("Four")
        Text("Five")
        Text("Six")
        Text("Seven")
    }
}