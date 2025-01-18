package com.example.customlayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
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
    // layout children
    layout(widthLayout, constraints.maxHeight, placementBlock = {
        var height = 0  // current row height, calculated for each row
        var count = 0
        var x = 0
        var y = 0
        placables.forEach {
            it.placeRelative(x, y)
            if (it.height > height) {
                height = it.height
            }
            if ((++count % columnCount) == 0) {
                x = 0
                y += height
                height = 0
            }
            else {
                x += width
            }
        }
    })
}
