package com.example.cupcake.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.R.string
import com.example.cupcake.model.NavigationRouts
import com.example.cupcake.screens.base.BaseScreen
import com.example.cupcake.viewModel.OrderViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SummaryScreen(
    navHostController: NavHostController,
    viewModel: OrderViewModel,
    packageManager: PackageManager?,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val price = remember {
        mutableStateOf("")
    }
    scope.launch {
        viewModel.price.collect {
            price.value = it
        }
    }
    val numberOfCupcakes = viewModel.quantity.value ?: 0
    val emailMassage = getEmailText(
        viewModel, numberOfCupcakes, price
    )
    val emailTitle = stringResource(id = string.new_cupcake_order)
    scope.launch {
        viewModel.price.collect {
            price.value = it
        }
    }

    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.side_margin),
                    vertical = dimensionResource(id = R.dimen.margin_between_elements)
                )
        ) {
            FieldData(
                title = stringResource(id = string.quantity),
                valueText = viewModel.quantity.value.toString()
            )
            FieldData(
                title = stringResource(id = string.flavor),
                valueText = viewModel.flavor.value.orEmpty()
            )
            FieldData(
                title = stringResource(id = string.pickup_date),
                valueText = viewModel.date.value.orEmpty()
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = dimensionResource(id = R.dimen.side_margin)),
                text = price.value,
                color = colorResource(id = R.color.black),
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.End
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.pink_600),
                    disabledBackgroundColor = colorResource(id = R.color.black)
                ),
                onClick = {
                    packageManager?.let {
                        sendOrder(
                            emailTitle = emailTitle,
                            emailMassage = emailMassage,
                            context = context,
                            packageManager = it
                        )
                    }
                }
            ) {
                Text(
                    text = stringResource(id = R.string.next).uppercase(),
                    color = colorResource(id = R.color.white)
                )
            }
            Spacer(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.margin_between_elements))
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.white),
                    disabledBackgroundColor = colorResource(id = R.color.black)
                ),
                onClick = {
                    viewModel.resetOrder()
                    navHostController.navigate(NavigationRouts.START.rout)
                }
            ) {
                Text(
                    text = stringResource(id = string.cancel).uppercase(),
                    color = colorResource(id = R.color.pink_600)
                )
            }
        }
    }

}

@Composable
private fun FieldData(
    title: String,
    valueText: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        text = title.uppercase(),
        style = TextStyle(fontSize = 18.sp),
        color = colorResource(R.color.material_on_background_emphasis_medium)
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = valueText,
        style = TextStyle(fontSize = 18.sp),
        fontWeight = FontWeight.Bold,
        color = colorResource(R.color.black)
    )
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(colorResource(id = R.color.cardview_dark_background))
    )
}

/**
 * Submit the order by sharing out the order details to another app via an implicit intent.
 */
@SuppressLint("QueryPermissionsNeeded")
fun sendOrder(
    emailTitle: String,
    emailMassage: String,
    packageManager: PackageManager,
    context: Context
) {
    // Create an ACTION_SEND implicit intent with order details in the intent extras
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, emailTitle)
        putExtra(Intent.EXTRA_TEXT, emailMassage)
    }
    // Check if there's an app that can handle this intent before launching it
    if (intent.resolveActivity(packageManager) != null) {
        // Start a new activity with the given intent (this may open the share dialog on a
        // device if multiple apps can handle this intent)
        startActivity(context, intent, Bundle())
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun getEmailText(
    viewModel: OrderViewModel,
    numberOfCupcakes: Int,
    price: MutableState<String>
): String {
    return stringResource(
        string.order_details,
        pluralStringResource(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
        viewModel.flavor.value.toString(),
        viewModel.date.value.toString(),
        price.value
    )
}

@Composable
@Preview
private fun PickupScreenPreview() {
    SummaryScreen(rememberNavController(), OrderViewModel(), null)
}