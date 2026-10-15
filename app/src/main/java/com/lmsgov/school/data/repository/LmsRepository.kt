package com.lmsgov.school.data.repository

import com.lmsgov.school.data.mock.*
import com.lmsgov.school.data.model.*
import kotlinx.coroutines.delay

class LmsRepository {

    private suspend fun <T> simulateAsync(data: T, delayMs: Long = 80L): T {
        delay(delayMs)
        return data
    }

    suspend fun getStudentProfile(): StudentProfile = simulateAsync(mockStudentProfile)

    suspend fun getAttendanceStats(): AttendanceData = simulateAsync(mockAttendanceData)

    suspend fun getNotices(categoryFilter: String? = null): List<Notice> {
        val data = if (categoryFilter.isNullOrEmpty() || categoryFilter == "All") {
            mockNotices
        } else {
            mockNotices.filter { it.category.name.equals(categoryFilter, ignoreCase = true) }
        }
        return simulateAsync(data)
    }

    suspend fun getCalendarEvents(typeFilter: String? = null): List<CalendarEvent> {
        val data = if (typeFilter.isNullOrEmpty() || typeFilter == "all") {
            mockCalendarEvents
        } else {
            mockCalendarEvents.filter { it.type.name.equals(typeFilter, ignoreCase = true) }
        }
        return simulateAsync(data)
    }

    suspend fun getDailyRoutine(): List<DailyRoutinePeriod> = simulateAsync(mockDailyRoutine)

    suspend fun getWardArrivalStatus(): WardArrivalStatus = simulateAsync(mockWardArrivalStatus)

    suspend fun getAcademicSubjects(): List<AcademicSubject> = simulateAsync(mockAcademicSubjects)

    suspend fun getExamSchedule(): List<ExamScheduleItem> = simulateAsync(mockExamSchedule)

    suspend fun getContactInfo(): SchoolContactInfo = simulateAsync(mockContactInfo)

    suspend fun submitContactQuery(formData: ContactFormData): ContactFormResponse {
        delay(400L)
        return ContactFormResponse(
            success = true,
            message = "Your inquiry has been submitted successfully to the school administration office."
        )
    }
}
