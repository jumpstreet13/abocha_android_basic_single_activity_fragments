package com.example.cupcake.ui.view

import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme

@Composable
fun Toolbar(
    text: String,
    onBackButtonClick: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(text = text)
        },
        navigationIcon = if (onBackButtonClick != null) {
            {
                IconButton(onClick = { onBackButtonClick.invoke() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        } else null,
    )
}

@Preview
@Composable
private fun ToolbarWithBackButtonPreview() {
    CupcakeTheme {
        Toolbar(text = "Заголовок", onBackButtonClick = {})
    }
}

@Preview
@Composable
private fun ToolbarWithouBackButtonPreview() {
    CupcakeTheme {
        Toolbar(text = "Заголовок")
    }
}