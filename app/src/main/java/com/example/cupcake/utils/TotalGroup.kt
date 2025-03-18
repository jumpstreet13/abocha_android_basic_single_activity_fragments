package com.example.cupcake.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.theme.AppTheme

@Composable
fun TotalGroup(
    title: String,
    modifier: Modifier = Modifier,
    isTotal: Boolean = false
) {

    Column(
        modifier.fillMaxWidth()
    ) {
      if(!isTotal) {
          HorizontalDivider(
              modifier = Modifier
                  .padding(top = dimensionResource(R.dimen.side_margin)),
              thickness = 1.dp,
          )
      }
        Text(
            text = if (isTotal) title.uppercase() else title,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = dimensionResource(R.dimen.side_margin)),
            style = if (isTotal) AppTheme.typography.textAppearanceBold18
            else AppTheme.typography.textAppearanceSubtitle1

        )
    }
}

@Preview
@Composable
fun TotalGroupPreview() {
    val title = stringResource(R.string.subtotal_price, "$5")
    TotalGroup(
        title,
        modifier = Modifier.background(AppTheme.colors.colorBackground),
        isTotal = true
    )
}

