package com.example.cupcake.design
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DiagonalColumn(
    modifier: Modifier = Modifier,
    itemOffsetVertical: Dp = 25.dp,
    itemOffsetHorizontal: Dp = 25.dp,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measures, constraints ->
        val placeable = measures.map { it.measure(constraints) }
        val maxWidth = placeable.maxOf { it.width }
        val maxHeight = placeable.maxOf { it.height }

        layout(
            width = maxWidth + (placeable.size - 1) * itemOffsetHorizontal.roundToPx(),
            height = maxHeight + (placeable.size - 1) * itemOffsetVertical.roundToPx()
        ) {
            placeable.forEachIndexed { index, placeable ->
                val x = index * itemOffsetHorizontal.roundToPx()
                val y = index * itemOffsetVertical.roundToPx()
                placeable.run { placeRelative(x, y) }
            }
        }
    }
}

@Composable
@Preview
fun DiagonalColumnPreview() {
    DiagonalColumn(
        itemOffsetHorizontal = 30.dp,
        itemOffsetVertical = 20.dp
    ) {
        Text("Sharik")
        Text("Bobik")
        Text("Gena")
        Text("Buka")
        Text("Buda")
    }
}