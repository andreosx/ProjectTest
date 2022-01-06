package com.test.projecttest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.projecttest.model.EventItem
import com.test.projecttest.model.EventResult
import com.test.projecttest.model.PostCheckin
import com.test.projecttest.retrofit.service.Resource
import com.test.projecttest.retrofit.webclient.EventWebClient

class EventRepository(
    private val webclient: EventWebClient = EventWebClient()
) {

    fun getEvents(): LiveData<Resource<List<EventItem>?>> {

        val errorLiveData = MutableLiveData<Resource<List<EventItem>?>>()
        val eventWebApiLiveData = MutableLiveData<Resource<List<EventItem>?>>()

        webclient.getEvents(
            isSuccess = {success ->
                eventWebApiLiveData.value = Resource(data = success, error=null)
            },
            isFail = {error ->
                errorLiveData.value = Resource(data = null, error = error)
            }
        )

        return eventWebApiLiveData
    }

    fun getEventItem(idEvent: Int): LiveData<Resource<EventItem?>> {

        val errorLiveData = MutableLiveData<Resource<List<EventItem>?>>()
        val eventWebApiLiveData = MutableLiveData<Resource<EventItem?>>()

        webclient.getEventItem(
            isSuccess = {success ->
                eventWebApiLiveData.value = Resource(data = success, error=null)
            },
            isFail = {error ->
                errorLiveData.value = Resource(data = null, error = error)
            },
            idEvent
        )

        return eventWebApiLiveData
    }

    fun setCheckin(postCheckin: PostCheckin): LiveData<Resource<EventResult?>> {

        val errorLiveData = MutableLiveData<Resource<List<EventItem>?>>()
        val eventWebApiLiveData = MutableLiveData<Resource<EventResult?>>()

        webclient.setCheckin(
            isSuccess = {success ->
                eventWebApiLiveData.value = Resource(data = success, error=null)
            },
            isFail = {error ->
                errorLiveData.value = Resource(data = null, error = error)
            },
            postCheckin
        )

        return eventWebApiLiveData
    }
}
