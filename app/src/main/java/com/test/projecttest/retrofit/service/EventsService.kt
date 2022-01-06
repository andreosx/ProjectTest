package com.test.projecttest.retrofit.service

import com.test.projecttest.model.EventItem
import com.test.projecttest.model.EventResult
import com.test.projecttest.model.PostCheckin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsService {

    @GET("events")
    fun getEvents() : Call<List<EventItem>>

    @GET("events/{id}")
    fun getEventItem(@Path("id") id: Int) : Call<EventItem>

    @POST("checkin")
    fun setCheckin(@Body postCheckin: PostCheckin): Call<EventResult>

}