package com.lmsgov.school.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmsgov.school.data.model.AttendanceData
import com.lmsgov.school.data.repository.LmsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AttendanceViewModel : ViewModel() {
    private val repository = LmsRepository()

    var attendance by mutableStateOf<AttendanceData?>(null)
        private set
    var statusFilter by mutableStateOf("All")
        private set
    var isLoading by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            delay(80)
            attendance = repository.getAttendanceStats()
            isLoading = false
        }
    }

    fun updateStatusFilter(filter: String) {
        statusFilter = filter
    }
}
