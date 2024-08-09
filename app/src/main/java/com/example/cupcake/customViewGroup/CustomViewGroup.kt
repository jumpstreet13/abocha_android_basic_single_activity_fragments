package com.example.cupcake.customViewGroup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R

private var OffsetX = 0
private var OffsetY = 0

@Composable
fun CustomViewGroup(
    modifier: Modifier = Modifier,
    items: List<String>
) {
    Layout(
        modifier = modifier,
        content = {
            items.forEach { text ->
                Item(text)
            }
        }
    ) { measurableList, constraints ->
        val layoutSize = measurableList.map {
            it.measure(constraints)
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            layoutSize.forEach { placeable ->
                placeable.placeRelative(x = OffsetX, y = OffsetY)
                OffsetX += placeable.width
                OffsetY += placeable.height
            }
        }
    }
}

@Composable
private fun Item(text: String) {
    Text(
        modifier = Modifier
            .padding(4.dp),
        text = text
    )
}

@Composable
@Preview
private fun CustomViewGroupPreview() {
    CustomViewGroup(
        modifier = Modifier
            .wrapContentSize()
            .background(colorResource(id = R.color.white)),
        items = listOf("Sharik", "Bobik", "Gena", "Buka", "Buba", "Tapok", "Tiptop")
    )
}

