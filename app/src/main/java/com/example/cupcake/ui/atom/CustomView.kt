package com.example.cupcake.ui.atom

import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Layout(modifier = modifier, content = content) { measures, constraints ->
        val placeable = measures.map { measurable ->
            measurable.measure(
                Constraints(
                    maxWidth = constraints.maxWidth / measures.size,
                    maxHeight = constraints.maxHeight / measures.size
                )
            )
        }
        layout(
            width = placeable.fold(0) { acc, n -> acc + n.width },
            height = placeable.fold(0) { acc, n -> acc + n.height }
        ) {
            var x = 0
            var y = 0
            placeable.forEach { placeable ->
                placeable.place(x, y)
                x += placeable.width
                y += placeable.height
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomLayout() {
    Surface {
        CustomLayout(modifier = Modifier.size(500.dp)) {
            Text(text = "Hello")
            Text(text = "World")
            Text(text = "with")
            Text(text = "Custom")
            Text(text = "Compose")
        }
    }
}