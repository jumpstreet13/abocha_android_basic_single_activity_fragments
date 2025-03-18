package com.example.cupcake.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
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
import com.example.cupcake.theme.AppTheme
import com.example.cupcake.theme.CupcakeTheme
import com.example.cupcake.theme.LocalColor
import com.example.cupcake.utils.CupcakeAppBar


@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    orderClick: (Int) -> Unit = {},
) {
    Column(modifier = modifier) {
        CupcakeAppBar(
            title = stringResource(R.string.app_name),
            isVisibleBackIcon = false,
            onBackClick = {}
        )

        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.side_margin)),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            CupcakeImage()
            CupcakeTitle()
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.margin_between_elements))
            ) {
                OrderButton(
                    text = stringResource(R.string.one_cupcake),
                    onClick = { orderClick.invoke(1) }
                )
                OrderButton(
                    text = stringResource(R.string.six_cupcakes),
                    onClick = { orderClick.invoke(6) }
                )
                OrderButton(
                    text = stringResource(R.string.twelve_cupcakes),
                    onClick = { orderClick.invoke(12) }
                )
            }

        }
    }

}

@Composable
private fun CupcakeImage(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.cupcake),
        contentDescription = "Cupcake logo",
        modifier = modifier
            .padding(top = dimensionResource(R.dimen.margin_between_elements))
            .size(300.dp)
    )
}

@Composable
fun CupcakeTitle(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.order_cupcakes),
        style = AppTheme.typography.textAppearanceHeadline4,
        color = colorResource(com.google.android.material.R.color.material_on_background_emphasis_medium),
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen.side_margin))

    )
}

@Composable
fun OrderButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(dimensionResource(R.dimen.order_cupcake_button_width)),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            containerColor = LocalColor.current.colorPrimary,
            contentColor = LocalColor.current.colorOnPrimary,
            disabledContainerColor = LocalColor.current.colorSecondary,
            disabledContentColor = LocalColor.current.colorOnSecondary
        )
    ) {
        Text(
            text = text.uppercase()
        )
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    CupcakeTheme {
        Surface(color = AppTheme.colors.colorBackground) {
            StartScreen(modifier = Modifier.fillMaxSize())
        }
    }
}