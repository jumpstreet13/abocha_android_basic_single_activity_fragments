package com.example.cupcake.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.theme.AppTheme
import com.example.cupcake.theme.LocalColor

@Composable
fun NavigateButtonsGroup(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.side_margin))
    ) {
        OutlinedButton(
            onClick = onCancelClick,
            modifier = Modifier
                .weight(1f)
                .padding(end = dimensionResource(R.dimen.margin_between_elements)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                stringResource(R.string.cancel).uppercase(),
                color = AppTheme.colors.colorPrimaryVariant,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier
                .weight(1f)
                .padding(start = dimensionResource(R.dimen.margin_between_elements)),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonColors(
                containerColor = LocalColor.current.colorPrimary,
                contentColor = LocalColor.current.colorOnPrimary,
                disabledContainerColor = LocalColor.current.colorSecondary,
                disabledContentColor = LocalColor.current.colorOnSecondary
            )
        ) {
            Text(
                stringResource(R.string.next).uppercase(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }

}

@Preview
@Composable
fun NavigateButtonsGroupPreview() {
    NavigateButtonsGroup(modifier = Modifier.background(AppTheme.colors.colorBackground))
}