package com.test.projecttest.ui.activity.extension

import java.text.NumberFormat
import java.util.*

fun Float.toMoneyFormat(): String {
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}