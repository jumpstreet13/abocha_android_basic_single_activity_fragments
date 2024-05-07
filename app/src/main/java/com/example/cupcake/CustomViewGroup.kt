package com.example.cupcake

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomViewGroup(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map {
            it.measure(constraints)
        }
        layout(width = placeables.sumOf { it.width }, height = placeables.sumOf { it.height }) {
            var y = 0
            var x = 0
            placeables.forEach {
                it.placeRelative(x = x, y = y)
                x += it.width
                y += it.height
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomViewGroupPreview() {
    CustomViewGroup {
        Text(text = "Sharik")
        Text(text = "Bobik")
        Text(text = "Gena")
        Text(text = "Buka")
        Text(text = "Buba")
    }
}