package com.lmsgov.school.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmsgov.school.data.model.StudentProfile
import com.lmsgov.school.data.model.WardArrivalStatus
import com.lmsgov.school.data.repository.LmsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StudentInfoViewModel : ViewModel() {
    private val repository = LmsRepository()

    var student by mutableStateOf<StudentProfile?>(null)
        private set
    var arrivalStatus by mutableStateOf<WardArrivalStatus?>(null)
        private set
    var isLoading by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            delay(80)
            student = repository.getStudentProfile()
            arrivalStatus = repository.getWardArrivalStatus()
            isLoading = false
        }
    }
}
