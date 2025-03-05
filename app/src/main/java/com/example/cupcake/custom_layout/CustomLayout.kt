package com.example.cupcake.custom_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Layout(
        modifier = modifier.offset(),
        content = content,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map {
                it.measure(constraints)
            }
            var x = 0
            var y = 0
            layout(
                width = constraints.maxWidth, height = constraints.maxHeight
            ) {
                placeables.forEach {
                    it.placeRelative(x, y)
                    x += it.width
                    y += it.height
                }

            }
        }
    )
}

@Preview
@Composable
fun PreviewCustomLayout(

) {
    CustomLayout(
        modifier = Modifier
            .background(Color.White)
    ) {
        Text("asasa")
        Text("bbbba")
        Text("ccccc")
        Text("ddddd")
        Text("ddddd")
        Text("fffff")
    }
}