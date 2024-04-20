package com.example.cupcake

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp


@Composable
fun PrimaryButton(
    @StringRes
    labelId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .then(modifier)
            .width(dimensionResource(R.dimen.order_cupcake_button_width)),
        onClick = onClick
    ) {
        Text(text = stringResource(labelId).toUpperCase(Locale.current))
    }
}

@Composable
fun SecondaryButton(
    @StringRes
    labelId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .then(modifier)
            .width(dimensionResource(R.dimen.order_cupcake_button_width)),
        onClick = onClick
    ) {
        Text(text = stringResource(labelId).toUpperCase(Locale.current))
    }
}

@Composable
fun RadioSelect(
    label: String,
    isSelected: Boolean,
    isEnabled: Boolean = true,
    action: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = MaterialTheme.shapes.medium)
            .selectable(
                selected = isSelected,
                enabled = isEnabled,
                role = Role.RadioButton,
                onClick = action
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = action,
            enabled = isEnabled
        )
        Spacer(
            modifier = Modifier
                .height(52.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.weight(1f)
        )
    }
}
