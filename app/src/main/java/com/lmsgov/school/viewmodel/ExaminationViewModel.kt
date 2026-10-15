package com.lmsgov.school.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmsgov.school.data.model.ExamScheduleItem
import com.lmsgov.school.data.repository.LmsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExaminationViewModel : ViewModel() {
    private val repository = LmsRepository()

    var exams by mutableStateOf<List<ExamScheduleItem>>(emptyList())
        private set
    var isLoading by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            delay(80)
            exams = repository.getExamSchedule()
            isLoading = false
        }
    }
}
