package com.test.projecttest.model

import java.io.Serializable

data class EventItem (
    var date: Long,
    var description: String,
    var image: String?,
    var longitude: Double?,
    var latitude: Double?,
    var price: Float,
    var title: String,
    var id: Int
) : Serializable