package com.example.cupcake.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.cupcake.R

@Composable
fun DiagonalColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { measurable ->
                measurable.measure(
                    Constraints(
                        maxWidth = constraints.maxWidth / measurables.size,
                        maxHeight = constraints.maxHeight / measurables.size,
                    )
                )
            }

            layout(
                width = constraints.maxWidth,
                height = constraints.maxHeight,
            ) {
                var x = 0
                var y = 0
                placeables.forEach { placeable ->
                    placeable.place(x, y)
                    x += placeable.width
                    y += placeable.height
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DiagonalColumnPreview() {
    DiagonalColumn{
        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null,
            contentScale = ContentScale.None,
            modifier = Modifier.size(400.dp)
        )
    }
}