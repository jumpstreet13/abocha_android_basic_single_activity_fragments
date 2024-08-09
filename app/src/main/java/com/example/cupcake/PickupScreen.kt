package com.example.cupcake

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui_kit.CommonLayout
import com.example.cupcake.ui_kit.RadioButtonWithLabel


@Composable
fun PickupScreen(
    sharedViewModel: OrderViewModel,
    onCancelOrder: () -> Unit,
    onClickNext:()->Unit
) {
    val selectedDate by sharedViewModel.date.observeAsState(initial = "")
    val subtotal by sharedViewModel.price.observeAsState(initial = 0)
    CommonLayout(
        subtotal = subtotal.toString(),
        onCancelOrder = onCancelOrder,
        onClickNext = onClickNext) {
        sharedViewModel.dateOptions.forEach { date ->
            RadioButtonWithLabel(isSelected = selectedDate == date, label = date) {
                sharedViewModel.setDate(it)
            }
        }
    }
}
