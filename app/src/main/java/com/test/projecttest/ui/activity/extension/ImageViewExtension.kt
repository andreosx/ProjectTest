package com.test.projecttest.ui.activity.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.test.projecttest.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(
    imageUrl: String?,
    @DrawableRes placeholderRes: Int = R.drawable.ic_place_holder,
    @DrawableRes imageErrorRes: Int = R.drawable.ic_place_holder
) {
    val requestOptions = RequestOptions().apply {
        placeholder(placeholderRes)
        error(imageErrorRes)
    }
    Glide.with(this.context)
        .setDefaultRequestOptions(requestOptions)
        .load(imageUrl)
        .into(this)
}