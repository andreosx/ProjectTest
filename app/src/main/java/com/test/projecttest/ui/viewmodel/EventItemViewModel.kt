package com.test.projecttest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.projecttest.model.EventItem
import com.test.projecttest.model.EventResult
import com.test.projecttest.model.PostCheckin
import com.test.projecttest.repository.EventRepository
import com.test.projecttest.retrofit.service.Resource

class EventItemViewModel(private val repository: EventRepository) : ViewModel() {

    fun getEventItem(idEvent: Int) : LiveData<Resource<EventItem?>> {
        return repository.getEventItem(idEvent)
    }

    fun setCheckin(idEvent: Int,name: String, email: String) : LiveData<Resource<EventResult?>> {
        return repository.setCheckin(PostCheckin(idEvent, name, email))
    }
}