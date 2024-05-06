package com.example.cupcake

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ViewGroupScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        layout(constraints.maxWidth, constraints.maxHeight) {
            var x = 0
            var y = 0
            placables.forEach { placeable ->
                placeable.placeRelative(x, y)
                x += placeable.width
                y += placeable.height
            }
        }

    }
}

@Preview
@Composable
fun ViewGroupScreen(){
    ViewGroupScreen {
        Text(text = "Sharik")
        Text(text = "Bobik")
        Text(text = "Gena")
        Text(text = "Buka")
        Text(text = "Buba")
    }
}