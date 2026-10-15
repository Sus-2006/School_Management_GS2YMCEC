package com.lmsgov.school.data.mock

import com.lmsgov.school.data.model.*

val mockStudentProfile = StudentProfile(
    name = "Aarav Sharma",
    rollNo = "14",
    className = "Class 8",
    section = "Section A",
    schoolName = "Govt. High Secondary School, Rampur",
    academicYear = "2026-2027",
    admissionNo = "GSS-2023-0842",
    guardianName = "Rajesh Sharma",
    guardianPhone = "+91 98765 43210",
    district = "Rampur District",
    state = "Uttar Pradesh, India"
)

val mockAttendanceData = AttendanceData(
    attendancePercentage = 94.6,
    classesAttended = 123,
    totalClasses = 129,
    streakDays = 14,
    lastUpdated = "Today at 08:17 AM",
    monthlyAttendance = listOf(
        MonthlyAttendance("April 2026", 96.0, 24, 25),
        MonthlyAttendance("May 2026", 95.2, 20, 21),
        MonthlyAttendance("June 2026", 92.8, 26, 28),
        MonthlyAttendance("July 2026", 95.8, 23, 24),
        MonthlyAttendance("August 2026", 93.3, 30, 31)
    ),
    dailyAttendance = listOf(
        DailyAttendanceRecord("2026-08-01", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-02", AttendanceStatus.WEEKEND, "Sunday"),
        DailyAttendanceRecord("2026-08-03", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-04", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-05", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-06", AttendanceStatus.ABSENT, "Sick leave (Informed)"),
        DailyAttendanceRecord("2026-08-07", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-08", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-09", AttendanceStatus.WEEKEND, "Sunday"),
        DailyAttendanceRecord("2026-08-10", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-11", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-12", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-13", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-14", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-15", AttendanceStatus.HOLIDAY, "Independence Day Holiday"),
        DailyAttendanceRecord("2026-08-16", AttendanceStatus.WEEKEND, "Sunday"),
        DailyAttendanceRecord("2026-08-17", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-18", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-19", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-20", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-21", AttendanceStatus.HOLIDAY, "Special Event / School Sports Day"),
        DailyAttendanceRecord("2026-08-22", AttendanceStatus.PRESENT, "On time"),
        DailyAttendanceRecord("2026-08-23", AttendanceStatus.WEEKEND, "Sunday"),
        DailyAttendanceRecord("2026-08-24", AttendanceStatus.PRESENT, "Exam Day - Mathematics"),
        DailyAttendanceRecord("2026-08-25", AttendanceStatus.PRESENT, "Exam Day - Science"),
        DailyAttendanceRecord("2026-08-26", AttendanceStatus.PRESENT, "Exam Day - Social Studies"),
        DailyAttendanceRecord("2026-08-27", AttendanceStatus.PRESENT, "Exam Day - Hindi"),
        DailyAttendanceRecord("2026-08-28", AttendanceStatus.PRESENT, "Exam Day - English"),
        DailyAttendanceRecord("2026-08-29", AttendanceStatus.HOLIDAY, "National Sports Day"),
        DailyAttendanceRecord("2026-08-30", AttendanceStatus.WEEKEND, "Sunday"),
        DailyAttendanceRecord("2026-08-31", AttendanceStatus.PRESENT, "On time")
    )
)

val mockCalendarEvents = listOf(
    CalendarEvent("e1", "2026-08-15", "Independence Day", CalendarEventType.HOLIDAY, "National Holiday & Flag Hoisting Ceremony at 8:00 AM in school ground."),
    CalendarEvent("e2", "2026-08-18", "Parent-Teacher Meeting", CalendarEventType.EVENT, "Quarterly review with parents and discussion on term exam readiness.", "09:30 AM - 01:00 PM"),
    CalendarEvent("e3", "2026-08-21", "School Sports Day & Science Fair", CalendarEventType.SPECIAL, "Annual inter-house sports competitions and district science model display.", "08:00 AM - 02:00 PM"),
    CalendarEvent("e4", "2026-08-24", "Mathematics Examination", CalendarEventType.EXAM, "First Term Exam - Mathematics Paper 1 (Algebra & Geometry).", "09:00 AM - 11:30 AM"),
    CalendarEvent("e5", "2026-08-25", "Science Examination", CalendarEventType.EXAM, "First Term Exam - Science (Physics, Chemistry & Biology).", "09:00 AM - 11:30 AM"),
    CalendarEvent("e6", "2026-08-26", "Social Science Examination", CalendarEventType.EXAM, "First Term Exam - History, Geography & Civics.", "09:00 AM - 11:30 AM"),
    CalendarEvent("e7", "2026-08-27", "Hindi Language Examination", CalendarEventType.EXAM, "First Term Exam - Hindi Literature & Grammar.", "09:00 AM - 11:30 AM"),
    CalendarEvent("e8", "2026-08-28", "English Language Examination", CalendarEventType.EXAM, "First Term Exam - English Composition & Reading.", "09:00 AM - 11:30 AM"),
    CalendarEvent("e9", "2026-08-29", "National Sports Day", CalendarEventType.HOLIDAY, "School holiday in honor of Major Dhyan Chand.")
)

val mockDailyRoutine = listOf(
    DailyRoutinePeriod(1, "08:30 - 09:15 AM", "Mathematics", "Shri R.K. Verma", "Room 8-A"),
    DailyRoutinePeriod(2, "09:15 - 10:00 AM", "Science", "Smt. Anita Devi", "Lab 2 / Room 8-A", isCurrent = true),
    DailyRoutinePeriod(3, "10:00 - 10:45 AM", "Hindi Literature", "Shri S.N. Tripathi", "Room 8-A"),
    DailyRoutinePeriod(4, "10:45 - 11:15 AM", "Recess / Meal Time", "School Staff", "Dining Hall"),
    DailyRoutinePeriod(5, "11:15 - 12:00 PM", "Social Studies", "Shri Manoj Kumar", "Room 8-A"),
    DailyRoutinePeriod(6, "12:00 - 12:45 PM", "English Grammar", "Smt. Priya Singh", "Room 8-A"),
    DailyRoutinePeriod(7, "12:45 - 01:30 PM", "Physical Education / Library", "Shri Vikram Singh", "Playground")
)

val mockNotices = listOf(
    Notice("n1", "First Term Examination Schedule Released (2026-27)", "12 Aug 2026", NoticeCategory.EXAMINATION, NoticePriority.URGENT, "The date sheet for Class 6 to 10 First Term Examinations has been published. Exams commence on August 24, 2026.", "All students are hereby informed that the First Term Examinations for the Academic Session 2026-27 will commence from August 24, 2026. The detailed subject-wise timetable is updated on the Examination Portal and posted on the school main notice board. Admit slips will be distributed by class teachers on August 20.", "Examination Controller"),
    Notice("n2", "School Holiday Announcement: Independence Day", "10 Aug 2026", NoticeCategory.HOLIDAY, NoticePriority.NORMAL, "School will remain closed on August 15, 2026 on account of Independence Day. Flag hoisting ceremony at 8:00 AM.", "On the occasion of India's Independence Day, August 15, 2026, regular classes will remain suspended. All students, teachers, and guardians are cordially invited to attend the Flag Hoisting and Cultural Program in the school assembly ground starting at 8:00 AM.", "Headmaster Office"),
    Notice("n3", "Parent-Teacher Meeting (PTM) Scheduled", "08 Aug 2026", NoticeCategory.NOTICE, NoticePriority.HIGH, "Quarterly Parent-Teacher Meeting will be held on August 18, 2026 from 9:30 AM to 1:00 PM.", "We invite all parents/guardians to attend the quarterly PTM to discuss student academic progress, attendance records, and preparation for upcoming term exams. Refreshments will be provided for visiting guardians.", "Student Welfare Committee"),
    Notice("n4", "Annual District Science Fair & Exhibition", "05 Aug 2026", NoticeCategory.EVENT, NoticePriority.NORMAL, "Submissions open for student science models and projects. District event scheduled for August 21.", "Students interested in participating in the District Rural Science Fair are encouraged to submit project ideas to the Science Department teacher by August 18. Outstanding models will receive district commendations.", "Science Club Supervisor"),
    Notice("n5", "Distribution of Free Textbooks & Uniform Allowance", "01 Aug 2026", NoticeCategory.ACADEMIC, NoticePriority.NORMAL, "Government scheme textbook distribution for Class 8 students will take place in the school library hall.", "Under the State Education Welfare Scheme, supplementary learning textbooks and uniform allowances will be disbursed to registered students on August 17. Guardians are requested to bring student ID card.", "Administration Office")
)

val mockAcademicSubjects = listOf(
    AcademicSubject("sub-math", "Mathematics", "MATH-801", "Shri R.K. Verma", 16, 11, 68.7, "Linear Equations Exercises (Due Aug 22)", "Covering Rational Numbers, Linear Equations, Quadrilaterals, Data Handling, Square Roots & Algebraic Expressions."),
    AcademicSubject("sub-sci", "Science & Technology", "SCI-802", "Smt. Anita Devi", 18, 14, 77.8, "Cell Structure Diagram & Observations (Due Aug 20)", "Covering Crop Production, Microorganisms, Synthetic Fibres, Metals/Non-Metals, Cell Structure & Friction."),
    AcademicSubject("sub-sst", "Social Studies", "SST-803", "Shri Manoj Kumar", 15, 10, 66.7, "Indian Constitution Rights Summary (Due Aug 23)", "Covering History (Modern India), Geography (Resources & Agriculture), and Civics (Indian Constitution & Secularism)."),
    AcademicSubject("sub-hin", "Hindi Literature & Grammar", "HIN-804", "Shri S.N. Tripathi", 14, 12, 85.7, "Essay writing on Rural Education (Due Aug 19)", "NCERT Vasant Part-3, Hindi Prose, Poetry, Grammar, Essay Writing and Comprehension."),
    AcademicSubject("sub-eng", "English Language & Reader", "ENG-805", "Smt. Priya Singh", 14, 10, 71.4, "Honeydew Chapter 4 Q&A (Due Aug 21)", "NCERT Honeydew & It So Happened readers, English Grammar, Letter Writing & Spoken English drills.")
)

val mockExamSchedule = listOf(
    ExamScheduleItem("ex-1", "Mathematics", "24 Aug 2026", "09:00 AM - 11:30 AM", "2 Hours 30 Mins", 80, "Hall A (Ground Floor)", listOf("Rational Numbers", "Linear Equations in One Variable", "Understanding Quadrilaterals", "Data Handling")),
    ExamScheduleItem("ex-2", "Science", "25 Aug 2026", "09:00 AM - 11:30 AM", "2 Hours 30 Mins", 80, "Hall A (Ground Floor)", listOf("Crop Production & Management", "Microorganisms: Friend & Foe", "Synthetic Fibres & Plastics", "Cell Structure & Functions")),
    ExamScheduleItem("ex-3", "Social Science", "26 Aug 2026", "09:00 AM - 11:30 AM", "2 Hours 30 Mins", 80, "Hall B (First Floor)", listOf("How, When and Where (History)", "Resources (Geography)", "The Indian Constitution (Civics)", "Understanding Secularism")),
    ExamScheduleItem("ex-4", "Hindi", "27 Aug 2026", "09:00 AM - 11:30 AM", "2 Hours 30 Mins", 80, "Hall B (First Floor)", listOf("Dhwani (Poem)", "Lakh ki Choodiyan", "Bus ki Yatra", "Hindi Grammar & Letter Writing")),
    ExamScheduleItem("ex-5", "English", "28 Aug 2026", "09:00 AM - 11:30 AM", "2 Hours 30 Mins", 80, "Hall B (First Floor)", listOf("The Best Christmas Present in the World", "The Ant and the Cricket", "Glimpses of the Past", "Grammar & Unseen Passage"))
)

val mockContactInfo = SchoolContactInfo(
    schoolName = "Govt. Higher Secondary School, Rampur",
    address = "Station Road, Ward No. 4, Rampur Block, District Rampur, Uttar Pradesh - 244901",
    phone = "+91 595 234 5678",
    emergencyHelpline = "+91 1800 180 5522 (Toll Free Education Helpline)",
    email = "headmaster.ghss.rampur@upgov.edu.in",
    officeHours = "Monday - Saturday: 08:00 AM - 02:00 PM (Closed on Sundays & Public Holidays)",
    headmaster = "Dr. Ramesh Chandra Pandey (M.Sc., Ph.D.)",
    districtOffice = "District Inspector of Schools (DIOS) Office, Rampur"
)

val mockWardArrivalStatus = WardArrivalStatus(
    isArrived = true,
    arrivalTime = "08:17 AM",
    date = "August 15, 2026",
    statusMessage = "Your ward has arrived at school safely.",
    location = "Main Gate Biometric Scanner",
    lastRecordedMethod = "Biometric Attendance System"
)
