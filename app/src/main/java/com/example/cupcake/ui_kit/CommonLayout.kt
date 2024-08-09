package com.example.cupcake.ui_kit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cupcake.R
import com.example.cupcake.Routes
import com.example.cupcake.theme.AppColors

@Composable
fun CommonLayout(
    subtotal: String,
    onCancelOrder: ()->Unit,
    onClickNext: ()->Unit,
    radioButtons: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.side_margin))
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.side_margin))
        ) {
            radioButtons.invoke()
        }

        Divider(
            color = AppColors.lines,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.side_margin))
        )

        Text(
            text = stringResource(R.string.subtotal_price, subtotal),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.side_margin))
                .align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.side_margin)))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick =  onCancelOrder,
                colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.background, contentColor = AppColors.primary),
                border = BorderStroke(0.3.dp, AppColors.lines), // Устанавливаем рамку
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.cancel).uppercase())
            }

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.side_margin)))

            Button(
                onClick = onClickNext,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.next).uppercase())
            }
        }
    }
}