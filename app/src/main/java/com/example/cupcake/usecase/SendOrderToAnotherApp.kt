package com.example.cupcake.usecase

import android.content.Context
import android.content.Intent
import com.example.cupcake.R

fun Context.sendOrderToAnotherApp(orderSummary: String) {
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
        .putExtra(Intent.EXTRA_TEXT, orderSummary)

    val isThereAnyCompatibleApp = packageManager.resolveActivity(intent, 0) != null

    if (isThereAnyCompatibleApp)
        startActivity(intent)
}