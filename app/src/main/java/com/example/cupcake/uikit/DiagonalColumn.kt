package com.example.cupcake.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun DiagonalColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Измеряем и размещаем каждый элемент
        val placeables =
            measurables.map { it.measure(constraints.copy(minHeight = 0, minWidth = 0)) }

        // Размещаем элементы по диагонали
        val maxHeight = placeables.maxOf { it.height } * placeables.size
        layout(constraints.maxWidth, maxHeight) {
            var x = 0
            var y = 0
            placeables.forEach { placeable ->
                if (x + placeable.width > constraints.maxWidth) x = 0
                placeable.place(x, y)
                x += placeable.width
                y += placeable.height
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun PreviewDiagonalColumn() {
    Box(modifier = Modifier.fillMaxSize()) {
        DiagonalColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            repeat(80) {
                Text(text = Random.nextInt(10000, 1000000).toString())
            }
        }
    }
}