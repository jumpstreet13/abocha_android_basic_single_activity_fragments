package com.example.customlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cupcake.R

// Example

@Composable
fun CustomLayout() {
    val scales = listOf(1.0f, 1.1f, 0.9f, 1.1f, 0.8f, 1.0f, 0.9f, 1.0f)
    val imageSize = 100.0f
    MultiColumn(3, modifier = Modifier.padding(0.dp, 16.dp)) {
        for (scale in scales) {
            val size = Dp(imageSize * scale)
            Image(painter = painterResource(id = R.drawable.cupcake),
                contentDescription = "",
                modifier = Modifier.size(size, size).padding(8.dp)
            )
        }
    }
}

