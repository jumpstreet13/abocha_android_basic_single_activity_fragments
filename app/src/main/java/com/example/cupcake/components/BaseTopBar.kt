package com.example.cupcake.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R

@Composable
fun BaseTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = colorResource(R.color.pink_600),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                onBackClick?.let { onClick ->
                    Icon(
                        modifier = Modifier.clickable(onClick = onClick),
                        painter = painterResource(R.drawable.ic_back),
                        tint = colorResource(R.color.white),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                }
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = colorResource(R.color.white),
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    )
}