package com.example.cupcake.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.cupcake.R
import com.example.cupcake.theme.LocalColor

@Composable
fun RadioButtonWithTitle(
    flavorSelected: String?,
    title: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.margin_between_elements))
            .clickable { onClick.invoke(title) },
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(flavorSelected == title, null)

        Text(
            color = LocalColor.current.textColor,
            text = title,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.side_margin))
        )
    }

}