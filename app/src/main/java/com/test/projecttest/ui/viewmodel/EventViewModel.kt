package com.test.projecttest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.projecttest.model.EventItem
import com.test.projecttest.repository.EventRepository
import com.test.projecttest.retrofit.service.Resource

class EventViewModel(private val repository: EventRepository) : ViewModel() {

    fun getEvents() : LiveData<Resource<List<EventItem>?>> {
        return repository.getEvents()
    }

    fun getEventItem(idEvent: Int) : LiveData<Resource<EventItem?>> {
        return repository.getEventItem(idEvent)
    }

}