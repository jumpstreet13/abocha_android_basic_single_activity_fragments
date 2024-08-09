package com.example.cupcake.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import java.text.NumberFormat

@Composable
internal fun ListStep(
    radioOptions: List<String>,
    selectedOption: MutableState<String?>,
    totalSum: MutableState<String>,
    onCancelButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit

) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.side_margin))
    ) {
        RadioButtons(radioOptions, selectedOption)
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.side_margin)))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(colorResource(id = R.color.cardview_dark_background))
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = dimensionResource(id = R.dimen.side_margin)),
            text = totalSum.value,
            color = colorResource(id = R.color.black),
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.End
        )
        ActionPanel(
            onCancelButtonClick = onCancelButtonClick,
            onNextButtonClick = onNextButtonClick
        )
    }
}


@Composable
private fun RadioButtons(
    radioOptions: List<String>,
    selectedOption: MutableState<String?>
) {
    Column {
        radioOptions.forEach { title ->
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (title == selectedOption.value),
                    onClick = { selectedOption.value = title },
                )
                Text(
                    text = title,
                    color = colorResource(id = R.color.black),
                )
            }
        }
    }
}


@Composable
private fun ActionPanel(
    onCancelButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.white),
                disabledBackgroundColor = colorResource(id = R.color.black)
            ),
            onClick = onCancelButtonClick
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                color = colorResource(id = R.color.pink_600)
            )
        }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.side_margin)))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.pink_600),
                disabledBackgroundColor = colorResource(id = R.color.black)
            ),
            onClick = onNextButtonClick
        ) {
            Text(
                text = stringResource(id = R.string.next),
                color = colorResource(id = R.color.white)
            )
        }
    }
}