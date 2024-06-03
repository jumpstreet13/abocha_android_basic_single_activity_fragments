package com.example.cupcake.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp

@Composable
fun CustomGroup(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        // измерить детей
        val resolvedConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val placeables = measurables.map { measurable ->
            measurable.measure(resolvedConstraints)
        }

        // максимальные размеры
        val width = constraints.constrainWidth(
            placeables.sumOf { it.width }
        )
        val height = constraints.constrainHeight(
            placeables.sumOf { it.height }
        )

        layout(width, height) {
            var xPosition = 0
            var yPosition = 0
            // расставить по диагонали
            placeables.forEach { placeable ->
                placeable.placeRelative(x = xPosition, y = yPosition)
                xPosition += placeable.width
                yPosition += placeable.height
            }
        }
    }
}

@Composable
private fun DemoText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier
            .background(
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

@Preview(
    name = "Custom layout 1",
    showSystemUi = true
)
@Composable
fun CustomGroupPreview1() {
    MaterialTheme {
        Surface {
            CustomGroup(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                DemoText("Some 1")
                DemoText("And other 2")
                DemoText("It is 3")
                DemoText("Well 4")
                DemoText("And more 5")
                DemoText("Last one 6")
            }
        }
    }
}

@Preview(
    name = "Custom layout 2",
    showSystemUi = true
)
@Composable
fun CustomGroupPreview2() {
    MaterialTheme {
        Surface {
            CustomGroup(
                modifier = Modifier
                    .width(320.dp)
                    .height(180.dp)
            ) {
                DemoText("Some 1")
                DemoText("And other 2")
                DemoText("It is 3")
                DemoText("Well 4")
                DemoText("And more 5")
                DemoText("Last one 6")
            }
        }
    }
}
