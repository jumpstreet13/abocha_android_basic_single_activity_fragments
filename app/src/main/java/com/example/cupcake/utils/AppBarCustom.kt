package com.example.cupcake.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.theme.LocalColor

@Composable
fun AppBarCustom(
    modifier: Modifier = Modifier,
    title: String,
    isVisibleBackIcon: Boolean = true,
    onClickBack: () -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isVisibleBackIcon)
            Icon(
                painter = painterResource(R.drawable.baseline_arrow_back_24),
                tint = LocalColor.current.textColor,
                contentDescription = "",
                modifier = Modifier.clickable { onClickBack.invoke() }
            )

        Text(
            modifier = Modifier.padding(start = dimensionResource(R.dimen.side_margin)),
            color = LocalColor.current.textColor,
            text = title,
            fontSize = 18.sp
        )
    }
}