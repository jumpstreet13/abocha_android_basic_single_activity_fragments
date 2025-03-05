package com.example.cupcake.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.theme.LocalColor

@Composable
fun BottomRowButtons(
    onClickCancel: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        ButtonCancel(onClick = onClickCancel)
        ButtonNext(onClick = onClickNext)
    }
}

@Composable
fun RowScope.ButtonCancel(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        border = BorderStroke(2.dp, LocalColor.current.colorButtonBorder),
        colors = ButtonColors(
            containerColor = LocalColor.current.colorOnPrimary,
            contentColor = LocalColor.current.colorPrimary,
            disabledContainerColor = LocalColor.current.colorOnSecondary,
            disabledContentColor = LocalColor.current.colorSecondary
        ),
        shape = RoundedCornerShape(2.dp),
        onClick = onClick,
        modifier = modifier
            .padding(end = dimensionResource(R.dimen.margin_between_elements))
            .weight(1f)
    ) {
        Text(text = stringResource(R.string.cancel))
    }

}

@Composable
fun RowScope.ButtonNext(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        colors = ButtonColors(
            containerColor = LocalColor.current.colorPrimary,
            contentColor = LocalColor.current.colorOnPrimary,
            disabledContainerColor = LocalColor.current.colorSecondary,
            disabledContentColor = LocalColor.current.colorOnSecondary
        ),
        shape = RoundedCornerShape(2.dp),
        onClick = onClick,
        modifier = modifier
            .padding(start = dimensionResource(R.dimen.margin_between_elements))
            .weight(1f)
    ) {
        Text(text = stringResource(R.string.next))
    }
}