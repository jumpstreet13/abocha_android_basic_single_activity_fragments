package com.example.cupcake.utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cupcake.R
import com.example.cupcake.theme.LocalColor
import com.example.cupcake.theme.LocalTypography

@Composable
fun ColumnScope.Subtotal(
    price: String,
    modifier: Modifier = Modifier
) {
    Text(
        color = LocalColor.current.textColor,
        modifier = modifier.align(Alignment.End),
        text = stringResource(R.string.subtotal_price, price),
        style = LocalTypography.current.textAppearanceSubtitle18
    )
}

@Composable
fun ColumnScope.Total(
    price: String,
    modifier: Modifier = Modifier
) {
    Text(
        color = LocalColor.current.textColor,
        modifier = modifier.align(Alignment.End),
        text = stringResource(R.string.total_price, price),
        style = LocalTypography.current.textAppearanceSubtitle18
    )
}