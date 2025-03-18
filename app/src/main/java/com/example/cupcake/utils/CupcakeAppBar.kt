package com.example.cupcake.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.theme.AppTheme


@Composable
fun CupcakeAppBar(
    title: String,
    modifier: Modifier = Modifier,
    isVisibleBackIcon: Boolean = true,
    onBackClick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.colorPrimary)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isVisibleBackIcon)
            Icon(
                painter = painterResource(R.drawable.arrow),
                tint = AppTheme.colors.colorOnPrimary,
                contentDescription = "",
                modifier = Modifier
                    .clickable { onBackClick.invoke() }
                    .padding(dimensionResource(R.dimen.margin_between_elements))
            )

        Text(
            modifier = Modifier.padding(start = dimensionResource(R.dimen.side_margin)),
            color = AppTheme.colors.colorOnPrimary,
            text = title,
            style = AppTheme.typography.textAppearanceBold18
        )
    }
}