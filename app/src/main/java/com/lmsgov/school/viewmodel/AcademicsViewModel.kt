package com.lmsgov.school.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmsgov.school.data.model.AcademicSubject
import com.lmsgov.school.data.repository.LmsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AcademicsViewModel : ViewModel() {
    private val repository = LmsRepository()

    var subjects by mutableStateOf<List<AcademicSubject>>(emptyList())
        private set
    var isLoading by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            delay(80)
            subjects = repository.getAcademicSubjects()
            isLoading = false
        }
    }
}
