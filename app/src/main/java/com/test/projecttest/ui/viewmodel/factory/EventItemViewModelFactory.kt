package com.test.projecttest.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.projecttest.repository.EventRepository
import com.test.projecttest.ui.viewmodel.EventItemViewModel

class EventItemViewModelFactory(
    private val repository: EventRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventItemViewModel(repository) as T
    }
}