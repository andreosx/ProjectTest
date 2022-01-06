package com.test.projecttest.ui.activity.extension

import android.app.Activity
import com.shashank.sony.fancytoastlib.FancyToast

fun Activity.showError(message: String) {
    FancyToast.makeText(this,message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show()
}

fun Activity.showSuccess(message: String) {
    FancyToast.makeText(this,message,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show()
}