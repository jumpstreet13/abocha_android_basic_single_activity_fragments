package com.example.cupcake.ui

import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.dp

@Composable
fun MyColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val resolveConstraint = constraints.copy(minHeight = 0, minWidth = 0)
        val placeables = measurables.map { measurable ->
            measurable.measure(resolveConstraint)
        }
        layout(
            placeables.sumOf { it.width / 2 } + placeables.last().width / 2,
            constraints.constrainHeight(placeables.sumOf { it.height } )
        ) {
            var y = 0
            var x = 0
            placeables.forEach { placeble ->
                placeble.placeRelative(x, y)
                y += placeble.height
                x += placeble.width / 2
            }
        }
    }
}

@Composable
fun AbochaColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier.height(150.dp),
        content = content
    ) { measurables, constraints ->
        val resolveConstraint = constraints.copy(minHeight = 0, minWidth = 0)
        val placeables = measurables.map { measurable ->
            measurable.measure(resolveConstraint)
        }
        layout(
            placeables.maxOf { it.width },
            constraints.constrainHeight(placeables.sumOf { it.height } )
        ) {
            var y = 0
            placeables.forEach { placeble ->
                placeble.placeRelative(x = 0, y = y)
                y += placeble.height
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAbochaColumn() {
    AbochaColumn {
        Text("Otus")
        Text("Android")
        Text("Professional")
    }
}

@Preview
@Composable
private fun PreviewMyColumn() {
    MyColumn {
        Text("Otus")
        Text("Android")
        Text("Professional")
        Text("Compose")
    }
}