package com.test.projecttest.retrofit.webclient

import com.test.projecttest.model.EventItem
import com.test.projecttest.model.EventResult
import com.test.projecttest.model.PostCheckin
import com.test.projecttest.retrofit.AppRetrofit
import com.test.projecttest.retrofit.service.EventsService

class EventWebClient (private val service: EventsService = AppRetrofit().serviceService)
{

    fun getEvents(
        isSuccess: (eventItem: List<EventItem>?) -> Unit,
        isFail: (error: String?) -> Unit
    ) {
        AppRetrofit().callRequisition(
            service.getEvents(),
            isSuccess,
            isFail
        )
    }

    fun getEventItem(
        isSuccess: (eventItem: EventItem?) -> Unit,
        isFail: (error: String?) -> Unit,
        idEvent: Int
    ) {
        AppRetrofit().callRequisition(
            service.getEventItem(idEvent),
            isSuccess,
            isFail
        )
    }

    fun setCheckin(
        isSuccess: (eventResult: EventResult?) -> Unit,
        isFail: (error: String?) -> Unit,
        postCheckin: PostCheckin
    ) {
        AppRetrofit().callRequisition(
            service.setCheckin(postCheckin),
            isSuccess,
            isFail
        )
    }
}