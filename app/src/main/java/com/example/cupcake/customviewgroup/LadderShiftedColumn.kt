package com.example.cupcake.customviewgroup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LadderShiftedColumn(
    modifier: Modifier = Modifier,
    horizontalSpacing: Dp = 0.dp,
    verticalSpacing: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        val layoutWidth =
            placeables.sumOf { it.width } + (placeables.size - 1) * horizontalSpacing.roundToPx()
        val layoutHeight =
            placeables.sumOf { it.height } + (placeables.size - 1) * verticalSpacing.roundToPx()

        layout(layoutWidth, layoutHeight) {
            var yPosition = 0
            var xPosition = 0

            placeables.forEach { placeable ->
                placeable.place(x = xPosition, y = yPosition)

                xPosition  += placeable.width + horizontalSpacing.roundToPx()
                yPosition += placeable.height + verticalSpacing.roundToPx()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LadderShiftedColumnNoSpacingPreview() {
    LadderShiftedColumn {
        Text("Sharik")
        Text("Bobik")
        Text("Gena")
        Text("Buka")
        Text("Buba")
    }
}

@Preview(showBackground = true)
@Composable
fun LadderShiftedColumnVerticalSpacingPreview() {
    LadderShiftedColumn(verticalSpacing = 8.dp) {
        Text("Sharik")
        Text("Bobik")
        Text("Gena")
        Text("Buka")
        Text("Buba")
    }
}

@Preview(showBackground = true)
@Composable
fun LadderShiftedColumnHorizontalSpacingPreview() {
    LadderShiftedColumn(horizontalSpacing = 16.dp) {
        Text("Sharik")
        Text("Bobik")
        Text("Gena")
        Text("Buka")
        Text("Buba")
    }
}

@Preview(showBackground = true)
@Composable
fun LadderShiftedColumnSpacingPreview() {
    LadderShiftedColumn(horizontalSpacing = 16.dp, verticalSpacing = 8.dp) {
        Text("Sharik")
        Text("Bobik")
        Text("Gena")
        Text("Buka")
        Text("Buba")
    }
}