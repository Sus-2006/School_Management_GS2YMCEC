package com.lmsgov.school.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmsgov.school.data.model.CalendarEvent
import com.lmsgov.school.data.model.DailyRoutinePeriod
import com.lmsgov.school.data.repository.LmsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CalendarViewModel : ViewModel() {
    private val repository = LmsRepository()

    var events by mutableStateOf<List<CalendarEvent>>(emptyList())
        private set
    var routine by mutableStateOf<List<DailyRoutinePeriod>>(emptyList())
        private set
    var typeFilter by mutableStateOf("all")
        private set
    var selectedDate by mutableStateOf<String?>(null)
        private set
    var isLoading by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            delay(80)
            events = repository.getCalendarEvents()
            routine = repository.getDailyRoutine()
            isLoading = false
        }
    }

    fun updateTypeFilter(filter: String) {
        typeFilter = filter
        viewModelScope.launch {
            isLoading = true
            events = repository.getCalendarEvents(filter)
            isLoading = false
        }
    }

    fun updateSelectedDate(date: String?) {
        selectedDate = date
    }
}
