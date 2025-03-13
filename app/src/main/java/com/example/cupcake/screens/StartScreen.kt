package com.example.cupcake.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.appTheme.LocalColors
import com.example.cupcake.appTheme.LocalTypography

@Preview
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onOrderCakesClick: (Int) -> Unit = {}
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(dimensionResource(R.dimen.side_margin)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        TitleImage(modifier)

        TitleText(modifier)

        OrderCakesButton(
            modifier,
            { onOrderCakesClick.invoke(1) },
            stringResource(R.string.one_cupcake)
        )

        OrderCakesButton(
            modifier,
            { onOrderCakesClick.invoke(6) },
            stringResource(R.string.six_cupcakes)
        )

        OrderCakesButton(
            modifier,
            { onOrderCakesClick.invoke(12) },
            stringResource(R.string.twelve_cupcakes)
        )
    }
}


@Composable
fun TitleImage(modifier: Modifier) {

    Image(
        painter = painterResource(R.drawable.cupcake),
        contentDescription = "cace",
        modifier = modifier
            .size(300.dp, 300.dp)
            .padding(50.dp)
    )
}

@Composable
fun TitleText(modifier: Modifier) {
    Text(
        text = stringResource(R.string.order_cupcakes),
        modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen.side_margin)),
        color = colorResource(R.color.material_on_background_emphasis_medium),
        textAlign = TextAlign.Center,
        style = LocalTypography.current.textAppearanceHeadline4
    )
}

@Composable
fun OrderCakesButton(
    modifier: Modifier,
    onClick: () -> Unit,
    buttonText: String
) {

    Button(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(dimensionResource(R.dimen.order_cupcake_button_width))
            .padding(dimensionResource(R.dimen.margin_between_elements)),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonColors(
            containerColor = LocalColors.current.colorPrimary,
            contentColor = LocalColors.current.colorOnPrimary,
            disabledContainerColor = LocalColors.current.colorSecondary,
            disabledContentColor = LocalColors.current.colorOnSecondary
        )
    ) {
        Text(
            buttonText.uppercase(),
            modifier
        )
    }

}
