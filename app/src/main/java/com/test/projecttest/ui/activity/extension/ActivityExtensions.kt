package com.test.projecttest.ui.activity.extension

import android.app.Activity
import android.widget.Toast

fun Activity.showError(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()
}