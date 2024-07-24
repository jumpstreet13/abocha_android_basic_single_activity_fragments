package com.example.cupcake.compose.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun StepsColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val config = LocalConfiguration.current
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val offset = ((config.screenWidthDp.dp.toPx() - placeables.maxOf { it.width }) / placeables.size).roundToInt()

        layout(
            width = constraints.maxWidth,
            height = constraints.constrainHeight(placeables.sumOf { it.height })
        ) {
            var x = 0
            var y = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x, y)
                y += placeable.height
                x += offset
            }
        }
    }
}
