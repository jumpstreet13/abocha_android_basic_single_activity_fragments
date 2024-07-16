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
import androidx.lifecycle.map
import com.example.cupcake.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/** Price for a single cupcake */
private const val PRICE_PER_CUPCAKE = 2.00

/** Additional cost for same day pickup of an order */
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

/**
 * [OrderViewModel] holds information about a cupcake order in terms of quantity, flavor, and
 * pickup date. It also knows how to calculate the total price based on these order details.
 */
class OrderViewModel : ViewModel() {

    data class UiState(
        val quantity: Int = 0,
        val flavor: String = "",
        val dateOptions: List<String> = emptyList(),
        val date: String = "",
        val price: Float = 0f,
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        // Set initial values for the order
        resetOrder()
    }

    /**
     * Set the quantity of cupcakes for this order.
     *
     * @param numberCupcakes to order
     */
    fun setQuantity(numberCupcakes: Int) {
        _uiState.value = _uiState.value.copy(quantity = numberCupcakes)
        updatePrice()
    }

    /**
     * Set the flavor of cupcakes for this order. Only 1 flavor can be selected for the whole order.
     *
     * @param desiredFlavor is the cupcake flavor as a string
     */
    fun setFlavor(desiredFlavor: String) {
        _uiState.value = _uiState.value.copy(flavor = desiredFlavor)
    }

    /**
     * Set the pickup date for this order.
     *
     * @param pickupDate is the date for pickup as a string
     */
    fun setDate(pickupDate: String) {
        _uiState.value = _uiState.value.copy(date = pickupDate)
        updatePrice()
    }

    /**
     * Returns true if a flavor has not been selected for the order yet. Returns false otherwise.
     */
    fun hasNoFlavorSet(): Boolean {
        return uiState.value.flavor.isEmpty()
    }

    /**
     * Reset the order by using initial default values for the quantity, flavor, date, and price.
     */
    fun resetOrder() {
        _uiState.value = UiState().copy(
            dateOptions = getPickupOptions()
        )
    }

    /**
     * Updates the price based on the order details.
     */
    private fun updatePrice() {
        var calculatedPrice = uiState.value.quantity * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (uiState.value.dateOptions[0] == uiState.value.date) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _uiState.value = _uiState.value.copy(price = calculatedPrice.toFloat())
    }

    /**
     * Returns a list of date options starting with the current date and the following 3 dates.
     */
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    /**
     * Submit the order by sharing out the order details to another app via an implicit intent.
     */
    fun sendOrder(context: Context) {
        // Construct the order summary text with information from the view model
        val numberOfCupcakes = uiState.value.quantity
        val orderSummary = context.getString(
            R.string.order_details,
            context.resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
            uiState.value.flavor,
            uiState.value.date,
            uiState.value.price.toString()
        )

        // Create an ACTION_SEND implicit intent with order details in the intent extras
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        // Check if there's an app that can handle this intent before launching it
        if (context.packageManager?.resolveActivity(intent, 0) != null) {
            // Start a new activity with the given intent (this may open the share dialog on a
            // device if multiple apps can handle this intent)
            context.startActivity(intent)
        }
    }
}