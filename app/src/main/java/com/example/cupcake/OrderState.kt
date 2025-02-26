package com.example.cupcake

data class OrderState(
    val price: Int = 0,
    val amount: Int = 0,
    val additionalIngredient: String? = null,
    val date: String? = null
)