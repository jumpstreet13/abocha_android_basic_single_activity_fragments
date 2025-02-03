package com.example.cupcake


import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0
                )
            )
        }

        val offset = placeables.minOf { it.height } / 2

        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
            var xPos = 0
            var yPos = offset
            placeables.forEach { placeable ->
                placeable.place(xPos, yPos)
                xPos += placeable.width
                yPos += placeable.height + offset
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLayoutPreview() {
    Surface {
        CustomLayout(modifier = Modifier.size(300.dp)) {
            Text(text = "Sharik")
            Text(text = "Bobik")
            Text(text = "Gena")
            Text(text = "Buka")
            Text(text = "Buba")
        }
    }


}