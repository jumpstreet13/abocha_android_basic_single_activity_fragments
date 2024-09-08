package com.example.cupcake.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popToFirst
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.essenty.lifecycle.subscribe
import com.example.cupcake.navigation.RootComponent.Configuration.Flavor
import com.example.cupcake.navigation.RootComponent.Configuration.Pickup
import com.example.cupcake.navigation.RootComponent.Configuration.Start
import com.example.cupcake.navigation.RootComponent.Configuration.Summary
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlinx.serialization.Serializable

/** Price for a single cupcake */
private const val PRICE_PER_CUPCAKE = 2.00

/** Additional cost for same day pickup of an order */
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

private const val DEFAULT_FLAVOR = "Vanilla"

class RootComponent(
    componentContext: ComponentContext,
): ComponentContext by componentContext, BackHandlerOwner {

    // Price of the order so far
    private val _price = MutableValue<Double>(0.0)
    val price: Value<String> = _price.map {
        // Format the price into the local currency and return this as LiveData<String>
        NumberFormat.getCurrencyInstance().format(it)
    }
    private val _flavor = MutableValue<String>(DEFAULT_FLAVOR)
    val flavor: Value<String> = _flavor

    // Possible date options
    private val dateOptions: List<String> = getPickupOptions()

    private var quantity = 0

    // Pickup date
    private val _date = MutableValue<String>(dateOptions[0])
    val date: Value<String> = _date

    private val navigation = StackNavigation<Configuration>()

    private val stack = childStack(
        source = navigation,
        initialConfiguration = Start,
        handleBackButton = true,
        childFactory = ::createChild,
        serializer = Configuration.serializer()
    )

    val childStack: Value<ChildStack<*, Child>> = stack

    private val backCallbacks = mapOf(
        Flavor::class to BackCallback {
            _flavor.value = DEFAULT_FLAVOR
            navigation.pop()
        },
        Pickup::class to BackCallback {
            _date.value = dateOptions[0]
            navigation.pop()
        }
    )

    init {
        backCallbacks.values.forEach { callBack -> backHandler.register(callBack) }
    }

    private fun createChild(
        config: Configuration,
        componentContext: ComponentContext,
    ): Child {
        return when (config) {
            is Start -> {
                val startComponent = StartComponent(
                    componentContext = componentContext,
                    onOrderCupcakeClick = { quantity ->
                        this.quantity = quantity
                        updatePrice()
                        navigation.push(Flavor)
                    }
                )
                startComponent.lifecycle.subscribe(
                    onStart = { backCallbacks[config::class]?.isEnabled = true },
                    onPause = { backCallbacks[config::class]?.isEnabled = false }
                )

                Child.Start(startComponent)
            }

            is Flavor -> {
                val flavorComponent = FlavorComponent(
                    componentContext = componentContext,
                    price = price,
                    selectedFlavor = flavor,
                    onFlavorSelected = { selectedFlavor ->
                        _flavor.value = selectedFlavor
                        updatePrice()
                    },
                    onCancelClick = ::cancelOrder,
                    onNextClick = { navigation.push(Pickup) },
                    onBackClick = { backCallbacks[config::class]?.onBack() ?: navigation.pop() },
                )
                flavorComponent.lifecycle.subscribe(
                    onStart = { backCallbacks[config::class]?.isEnabled = true },
                    onPause = { backCallbacks[config::class]?.isEnabled = false }
                )

                Child.Flavor(flavorComponent)
            }

            Pickup -> {
                val component = PickupComponent(
                    componentContext = componentContext,
                    price = price,
                    selectedDate = date,
                    onDateSelected = { selectedDate ->
                        _date.value = selectedDate
                        updatePrice()
                    },
                    dates = dateOptions,
                    onCancelClick = ::cancelOrder,
                    onNextClick = { navigation.push(Summary) },
                    onBackClick = { backCallbacks[config::class]?.onBack() ?: navigation.pop() },
                )
                component.lifecycle.subscribe(
                    onStart = { backCallbacks[config::class]?.isEnabled = true },
                    onPause = { backCallbacks[config::class]?.isEnabled = false }
                )

                Child.Pickup(component)
            }

            Summary -> {
                val component = SummaryComponent(
                    componentContext = componentContext,
                    quantity = quantity,
                    price = price,
                    flavor = flavor,
                    date = date,
                    onCancelClick = ::cancelOrder,
                    onBackClick = { backCallbacks[config::class]?.onBack() ?: navigation.pop() },
                )
                component.lifecycle.subscribe(
                    onStart = { backCallbacks[config::class]?.isEnabled = true },
                    onPause = { backCallbacks[config::class]?.isEnabled = false }
                )

                Child.Summary(component)
            }
        }
    }

    @Serializable
    private sealed interface Configuration {

        @Serializable
        object Start: Configuration

        @Serializable
        object Flavor: Configuration

        @Serializable
        object Pickup: Configuration

        @Serializable
        object Summary: Configuration
    }

    sealed interface Child {
        class Start(val component: StartComponent): Child
        class Flavor(val component: FlavorComponent): Child
        class Pickup(val component: PickupComponent): Child
        class Summary(val component: SummaryComponent): Child
    }

    private fun cancelOrder() {
        navigation.popToFirst()
        quantity = 0
        _flavor.value = DEFAULT_FLAVOR
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    private fun updatePrice() {
        var calculatedPrice = quantity * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

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
}