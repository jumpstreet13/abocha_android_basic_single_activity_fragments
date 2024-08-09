package com.example.cupcake

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringArrayResource
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui_kit.CommonLayout
import com.example.cupcake.ui_kit.RadioButtonWithLabel

@Composable
fun FlavorScreen(sharedViewModel: OrderViewModel, onCancelOrder: ()->Unit, onClickNext: ()->Unit) {
    val flavors = stringArrayResource(R.array.flavors)
    val selectedFlavor by sharedViewModel.flavor.observeAsState()
    val subtotal by sharedViewModel.price.observeAsState(initial = 0)
    CommonLayout(subtotal = subtotal.toString(), onCancelOrder = onCancelOrder, onClickNext = onClickNext) {
        if (selectedFlavor.isNullOrEmpty()){
            sharedViewModel.setFlavor(flavors[0])
        }
        flavors.forEach { flavor ->
            RadioButtonWithLabel(isSelected = selectedFlavor == flavor, label = flavor) {
                sharedViewModel.setFlavor(it)
            }
        }
    }
}
