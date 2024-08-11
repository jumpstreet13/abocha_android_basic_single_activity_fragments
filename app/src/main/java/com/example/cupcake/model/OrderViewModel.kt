/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake.model

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cupcake.R
import com.example.cupcake.di.ApplicationScope
import com.example.cupcake.navigation.Destination
import com.example.cupcake.navigation.NavigationObserver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

/** Price for a single cupcake */
private const val PRICE_PER_CUPCAKE = 2.00

/** Additional cost for same day pickup of an order */
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

/**
 * [OrderViewModel] holds information about a cupcake order in terms of quantity, flavor, and
 * pickup date. It also knows how to calculate the total price based on these order details.
 */
@ApplicationScope
class OrderViewModel @Inject constructor(
    private val navigationObserver: NavigationObserver
): ViewModel() {

    // Quantity of cupcakes in this order
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    // Cupcake flavor for this order
    private val _flavor = MutableStateFlow("Vanilla")
    val flavor = _flavor.asStateFlow()

    // Possible date options
    val dateOptions: List<String> = getPickupOptions()

    // Pickup date
    private val _date = MutableStateFlow<String?>(null)
    val date = _date.asStateFlow()

    // Price of the order so far
    private val _price = MutableLiveData<String>()
    val price: LiveData<String> = _price

    init {
        // Set initial values for the order
        resetOrder()
    }

    fun orderCupcake(quantity: Int) {
        setQuantity(quantity)
        navigateToFlavor()
    }

    fun cancelOrder() {
        resetOrder()
        viewModelScope.launch {
            navigationObserver.navigateTo(Destination.Start.route)
        }
    }

    private fun navigateToFlavor() {
        viewModelScope.launch {
            navigationObserver.navigateTo(Destination.Flavor.route)
        }
    }

    fun navigateToPickup() {
        viewModelScope.launch {
            navigationObserver.navigateTo(Destination.Pickup.route)
        }
    }

    fun navigateToSummary() {
        viewModelScope.launch {
            navigationObserver.navigateTo(Destination.Summary.route)
        }
    }

    fun send(context: Context) {
        val numberOfCupcakes = quantity.value ?: 0
        val orderSummary = context.getString(
            R.string.order_details,
            context.resources.getQuantityString(
                R.plurals.cupcakes,
                numberOfCupcakes,
                numberOfCupcakes
            ),
            flavor.value,
            date.value.toString(),
            price.value.toString()
        )

        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)
        if (context.packageManager?.resolveActivity(intent, 0) != null) {
            context.startActivity(intent)
        }
    }

    /**
     * Set the quantity of cupcakes for this order.
     *
     * @param numberCupcakes to order
     */
    private fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    /**
     * Set the flavor of cupcakes for this order. Only 1 flavor can be selected for the whole order.
     *
     * @param desiredFlavor is the cupcake flavor as a string
     */
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    /**
     * Set the pickup date for this order.
     *
     * @param pickupDate is the date for pickup as a string
     */
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    /**
     * Reset the order by using initial default values for the quantity, flavor, date, and price.
     */
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = "Vanilla"
        _date.value = dateOptions[0]
        _price.value = "0.0"
    }

    /**
     * Updates the price based on the order details.
     */
    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice.toString()
    }

    /**
     * Returns a list of date options starting with the current date and the following 3 dates.
     */
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(DAY_COUNT) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    companion object {
        const val DAY_COUNT = 4
    }
}