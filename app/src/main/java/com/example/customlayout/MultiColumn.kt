package com.example.customlayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.min

// Custom Layout Definition

@Composable
fun MultiColumn(
    columnCount: Int = 1,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = Layout(content = content, modifier = modifier) {
    measurables, constraints ->
    // measure children
    val placables = measurables.map {
        measurable -> measurable.measure(constraints)
    }
    // measure layout itself
    val width = placables.maxOf { it.width }
    val widthLayout = min(constraints.maxWidth, width * columnCount)
    val heightLayout = constraints.maxHeight
    // layout children
    layout(widthLayout, heightLayout, placementBlock = {
        var height = 0  // current row height, calculated for each row
        var count = 0
        var x = 0
        var y = 0
        for (it in placables) {
            if (x < widthLayout) {
                it.placeRelative(x, y)
                if (it.height > height) {
                    height = it.height
                }
            }
            if ((++count % columnCount) == 0) {
                y += height
                if (y >= heightLayout) break;
                height = 0
                x = 0
            }
            else {
                x += width
            }
        }
    })
}

