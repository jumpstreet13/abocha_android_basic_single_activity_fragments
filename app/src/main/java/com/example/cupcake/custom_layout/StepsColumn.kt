package com.example.cupcake.custom_layout

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.constrainHeight

@Composable
fun StepsColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val resolveConstraint = constraints.copy(minHeight = 0, minWidth = 0)
        val placeables = measurables.map {
            it.measure(resolveConstraint)
        }
        layout(
            placeables.sumOf { it.width },
            constraints.constrainHeight(placeables.sumOf { it.height })
        ) {
            var y = 0
            var x = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = x, y = y)
                x += placeable.width
                y += placeable.height
            }
        }
    }
}

@Preview
@Composable
fun PreviewStepsColumn() {
    StepsColumn() {
        Text("hello")
        Text("world")
        Text("it")
        Text("is")
        Text("my")
        Text("custom")
        Text("layout")
    }
}