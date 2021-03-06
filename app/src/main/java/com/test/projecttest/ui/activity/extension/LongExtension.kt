package com.test.projecttest.ui.activity.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateFormatted(): String {
    val dateFormatted = SimpleDateFormat("EEE, dd MMMM yyyy HH:mm")
    val date = Date(this)
    return dateFormatted.format(date).capitalize()
}