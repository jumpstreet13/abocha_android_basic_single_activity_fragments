package com.example.cupcake.custom_view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomViewScreen() {
    Surface {
        CustomDiagonalView(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Sharik")
            Text("Boba")
            Text("Gena")
            Text(text = "Buka")
            Text(text = "Buba")
        }
    }
}