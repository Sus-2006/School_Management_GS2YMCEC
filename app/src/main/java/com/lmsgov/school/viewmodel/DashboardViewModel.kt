package com.lmsgov.school.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmsgov.school.data.model.AttendanceData
import com.lmsgov.school.data.model.DailyRoutinePeriod
import com.lmsgov.school.data.model.Notice
import com.lmsgov.school.data.model.StudentProfile
import com.lmsgov.school.data.repository.LmsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val repository = LmsRepository()

    var student by mutableStateOf<StudentProfile?>(null)
        private set
    var attendance by mutableStateOf<AttendanceData?>(null)
        private set
    var notices by mutableStateOf<List<Notice>>(emptyList())
        private set
    var routine by mutableStateOf<List<DailyRoutinePeriod>>(emptyList())
        private set
    var isLoading by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            delay(80)
            student = repository.getStudentProfile()
            attendance = repository.getAttendanceStats()
            notices = repository.getNotices()
            routine = repository.getDailyRoutine()
            isLoading = false
        }
    }
}
