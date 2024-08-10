package com.example.cupcake.compose.ui.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DiagonalLayout(
    modifier: Modifier = Modifier,
    itemOffset: Dp = 20.dp,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val maxWidth = placeables.maxOf { it.width }
        val maxHeight = placeables.maxOf { it.height }

        layout(
            width = maxWidth + (placeables.size - 1) * itemOffset.roundToPx(),
            height = maxHeight + (placeables.size - 1) * itemOffset.roundToPx()
        ) {
            placeables.forEachIndexed { index, placeable ->
                val x = index * itemOffset.roundToPx()
                val y = index * itemOffset.roundToPx()
                placeable.run { placeRelative(x, y) }
            }
        }
    }
}

@Composable
@Preview
fun DiagonalLayoutPreview() {
    DiagonalLayout(
        itemOffset = 50.dp
    ) {
        Box(modifier = Modifier.size(50.dp).background(Color.Red))
        Box(modifier = Modifier.size(50.dp).background(Color.Green))
        Box(modifier = Modifier.size(50.dp).background(Color.Blue))
    }
}

@Composable
@Preview
fun DiagonalTextLayoutPreview() {
    DiagonalLayout {
        Text("Sharik")
        Text("Bobik")
        Text("Gena")
        Text("Buka")
    }
}