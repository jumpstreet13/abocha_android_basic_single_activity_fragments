package com.example.cupcake.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.R
import com.example.cupcake.theme.AppTheme

@Composable
fun TextRadioButton(
    optionSelected: String,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}

) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            optionSelected == text,
            onClick = onClick,
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.radio_button_padding)),
            colors = RadioButtonColors(
                selectedColor = AppTheme.colors.colorSelectedRadioButton,
                unselectedColor = AppTheme.colors.colorUnselectedRadioButton,
                disabledSelectedColor = AppTheme.colors.colorDisableSelectedRadioButton,
                disabledUnselectedColor = AppTheme.colors.colorDisableUnselectedRadioButton
            )
        )

        Text(
            text,
            style = AppTheme.typography.textAppearanceBody1,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}


@Preview
@Composable
fun TextRadioButtonPreview() {
    val defaultText = stringResource(R.string.vanilla)
    TextRadioButton(
        defaultText,
        defaultText,
        modifier = Modifier.background(AppTheme.colors.colorBackground)
    )
}
