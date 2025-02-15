package com.example.cupcake

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DiagonalColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measures, constraints ->
        val itemSpacer = 4.toDp()
        val placeable = measures.map { it.measure(constraints) }
        val maxWidth = placeable.sumOf { it.width }.plus(
            (placeable.size - 1) * itemSpacer.roundToPx()
        )
        val maxHeight =
            placeable.sumOf { it.height }.plus(
                (placeable.size - 1) * itemSpacer.roundToPx()
            )

        layout(width = maxWidth, height = maxHeight) {
            var x = 0
            var y = 0
            placeable.forEach { placeable ->
                placeable.place(x, y)
                x += placeable.width.plus(itemSpacer.roundToPx())
                y += placeable.height.plus(itemSpacer.roundToPx())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiagonalColumnPreview() {
    DiagonalColumn(
        modifier = Modifier
    ) {
        Text("Sharik")
        Text("Bobik")
        Text("Gena")
        Text("Buka")
        Text("Buba")
    }
}
