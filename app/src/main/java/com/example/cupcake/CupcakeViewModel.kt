package com.example.cupcake

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CupcakeViewModel(
    context: Context
) : ViewModel() {

    val ingredientOptions = listOf(
        context.resources.getString(R.string.vanilla),
        context.resources.getString(R.string.chocolate),
        context.resources.getString(R.string.red_velvet),
        context.resources.getString(R.string.salted_caramel),
        context.resources.getString(R.string.coffee)
    )

    private val _order = MutableStateFlow(OrderState())
    val order: StateFlow<OrderState> = _order

    fun setAmount(amount: Int) {
        _order.value = _order.value.copy(amount = amount)
        updatePrice()
    }

    fun setAdditionalIngredient(ingredient: String) {
        _order.value = _order.value.copy(additionalIngredient = ingredient)
    }

    fun setDate(date: String?) {
        _order.value = _order.value.copy(date = date)
        updatePrice()
    }

    fun setEmptyOrder() {
        viewModelScope.launch {
            _order.value = OrderState()
        }
    }

    fun getDateOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    private fun updatePrice() {
        val amount = _order.value.amount
        val date = _order.value.date
        _order.value = _order.value.copy(
            price = (amount * PRICE_PER_CUPCAKE + if (date == getDateOptions()[0]) {
                3
            } else {
                0
            }).toInt()
        )
    }

    companion object {
        private const val PRICE_PER_CUPCAKE = 2.00
    }
}