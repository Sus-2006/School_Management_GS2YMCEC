package com.lmsgov.school.data.model

data class StudentProfile(
    val name: String,
    val rollNo: String,
    val className: String,
    val section: String,
    val schoolName: String,
    val academicYear: String,
    val admissionNo: String,
    val guardianName: String,
    val guardianPhone: String,
    val district: String,
    val state: String
)

data class MonthlyAttendance(
    val month: String,
    val percentage: Double,
    val attended: Int,
    val total: Int
)

data class DailyAttendanceRecord(
    val date: String,
    val status: AttendanceStatus,
    val remarks: String? = null
)

enum class AttendanceStatus { PRESENT, ABSENT, HOLIDAY, WEEKEND }

data class AttendanceData(
    val attendancePercentage: Double,
    val classesAttended: Int,
    val totalClasses: Int,
    val streakDays: Int,
    val lastUpdated: String,
    val monthlyAttendance: List<MonthlyAttendance>,
    val dailyAttendance: List<DailyAttendanceRecord>
)

enum class NoticeCategory { ACADEMIC, EXAMINATION, HOLIDAY, EVENT, NOTICE }

enum class NoticePriority { HIGH, NORMAL, URGENT }

data class Notice(
    val id: String,
    val title: String,
    val date: String,
    val category: NoticeCategory,
    val priority: NoticePriority,
    val summary: String,
    val fullContent: String,
    val issuedBy: String
)

enum class CalendarEventType { HOLIDAY, EXAM, EVENT, SCHOOL_DAY, SPECIAL }

data class CalendarEvent(
    val id: String,
    val date: String,
    val title: String,
    val type: CalendarEventType,
    val description: String,
    val timing: String? = null
)

data class DailyRoutinePeriod(
    val period: Int,
    val time: String,
    val subject: String,
    val teacher: String,
    val room: String,
    val isCurrent: Boolean = false
)

data class WardArrivalStatus(
    val isArrived: Boolean,
    val arrivalTime: String,
    val date: String,
    val statusMessage: String,
    val location: String,
    val lastRecordedMethod: String
)

data class AcademicSubject(
    val id: String,
    val name: String,
    val code: String,
    val teacher: String,
    val topicsCount: Int,
    val completedTopics: Int,
    val progressPercentage: Double,
    val upcomingAssignment: String? = null,
    val description: String
)

data class ExamScheduleItem(
    val id: String,
    val subject: String,
    val date: String,
    val time: String,
    val duration: String,
    val maxMarks: Int,
    val roomNo: String,
    val syllabus: List<String>
)

data class SchoolContactInfo(
    val schoolName: String,
    val address: String,
    val phone: String,
    val emergencyHelpline: String,
    val email: String,
    val officeHours: String,
    val headmaster: String,
    val districtOffice: String
)

data class ContactFormData(
    val name: String,
    val email: String,
    val subject: String,
    val message: String
)

data class ContactFormResponse(
    val success: Boolean,
    val message: String
)
