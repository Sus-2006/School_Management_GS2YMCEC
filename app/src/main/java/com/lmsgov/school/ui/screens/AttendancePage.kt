package com.lmsgov.school.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lmsgov.school.data.model.*
import com.lmsgov.school.data.repository.LmsRepository
import com.lmsgov.school.i18n.Language
import com.lmsgov.school.i18n.Translations
import com.lmsgov.school.ui.theme.*

@Composable
fun AttendancePage(navController: NavController) {
    val language = Language.EN
    val repository = remember { LmsRepository() }

    var attendance by remember { mutableStateOf<AttendanceData?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var statusFilter by remember { mutableStateOf("ALL") }

    LaunchedEffect(Unit) {
        attendance = repository.getAttendanceStats()
        isLoading = false
    }

    if (isLoading) {
        AttendanceSkeleton(navController)
        return
    }

    val data = attendance ?: return

    val filteredDaily = when (statusFilter) {
        "PRESENT" -> data.dailyAttendance.filter { it.status == AttendanceStatus.PRESENT }
        "ABSENT" -> data.dailyAttendance.filter { it.status == AttendanceStatus.ABSENT }
        "HOLIDAY" -> data.dailyAttendance.filter { it.status == AttendanceStatus.HOLIDAY }
        "WEEKEND" -> data.dailyAttendance.filter { it.status == AttendanceStatus.WEEKEND }
        else -> data.dailyAttendance
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = AccentGreen.copy(alpha = 0.1f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = Translations.t("attendanceRecordTitle", language),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = Translations.t("attendanceCompliant", language),
                        style = MaterialTheme.typography.bodyLarge,
                        color = AccentGreen
                    )
                    Text(
                        text = data.lastUpdated,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AttendanceMetricCard(
                    modifier = Modifier.weight(1f),
                    label = Translations.t("overallPercentage", language),
                    value = "${data.attendancePercentage}%",
                    sublabel = Translations.t("minThresholdMsg", language),
                    color = AccentGreen
                )
                AttendanceMetricCard(
                    modifier = Modifier.weight(1f),
                    label = Translations.t("classesAttended", language),
                    value = "${data.classesAttended}/${data.totalClasses}",
                    sublabel = Translations.t("standing", language),
                    color = AccentSky
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AttendanceMetricCard(
                    modifier = Modifier.weight(1f),
                    label = Translations.t("currentStreak", language),
                    value = "${data.streakDays}",
                    sublabel = Translations.t("dayStreak", language),
                    color = AccentAmber
                )
                AttendanceMetricCard(
                    modifier = Modifier.weight(1f),
                    label = Translations.t("absenteeDays", language),
                    value = "${data.totalClasses - data.classesAttended}",
                    sublabel = Translations.t("medicalLeave", language),
                    color = AccentRose
                )
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = Translations.t("monthlyBreakdown", language),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    data.monthlyAttendance.forEach { month ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = month.month,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${month.attended}/${month.total} classes",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Text(
                                text = "${month.percentage}%",
                                style = MaterialTheme.typography.labelLarge,
                                color = AccentGreen
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            LinearProgressIndicator(
                                progress = { (month.percentage / 100.0).toFloat() },
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp)),
                                color = AccentGreen,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        }
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("ALL", "PRESENT", "ABSENT", "HOLIDAY", "WEEKEND").forEach { filter ->
                    FilterChip(
                        selected = statusFilter == filter,
                        onClick = { statusFilter = filter },
                        label = {
                            Text(
                                text = Translations.t(
                                    when (filter) {
                                        "ALL" -> "filterAll"
                                        "PRESENT" -> "filterPresent"
                                        "ABSENT" -> "filterAbsent"
                                        "HOLIDAY" -> "filterHoliday"
                                        "WEEKEND" -> "filterWeekend"
                                        else -> "filterAll"
                                    },
                                    language
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = when (filter) {
                                "PRESENT" -> AccentGreen.copy(alpha = 0.15f)
                                "ABSENT" -> AccentRose.copy(alpha = 0.15f)
                                "HOLIDAY" -> AccentPurple.copy(alpha = 0.15f)
                                "WEEKEND" -> MaterialTheme.colorScheme.surfaceVariant
                                else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                            },
                            selectedLabelColor = when (filter) {
                                "PRESENT" -> AccentGreen
                                "ABSENT" -> AccentRose
                                "HOLIDAY" -> AccentPurple
                                "WEEKEND" -> MaterialTheme.colorScheme.onSurfaceVariant
                                else -> MaterialTheme.colorScheme.primary
                            }
                        )
                    )
                }
            }
        }

        item {
            Text(
                text = Translations.t("dailyAttendanceLog", language),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        items(filteredDaily) { record ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(
                                when (record.status) {
                                    AttendanceStatus.PRESENT -> AccentGreen
                                    AttendanceStatus.ABSENT -> AccentRose
                                    AttendanceStatus.HOLIDAY -> AccentPurple
                                    AttendanceStatus.WEEKEND -> MaterialTheme.colorScheme.onSurfaceVariant
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            when (record.status) {
                                AttendanceStatus.PRESENT -> Icons.Default.Check
                                AttendanceStatus.ABSENT -> Icons.Default.Close
                                AttendanceStatus.HOLIDAY -> Icons.Default.Event
                                AttendanceStatus.WEEKEND -> Icons.Default.DateRange
                            },
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = record.date,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (record.remarks != null) {
                            Text(
                                text = record.remarks,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = when (record.status) {
                            AttendanceStatus.PRESENT -> AccentGreen.copy(alpha = 0.12f)
                            AttendanceStatus.ABSENT -> AccentRose.copy(alpha = 0.12f)
                            AttendanceStatus.HOLIDAY -> AccentPurple.copy(alpha = 0.12f)
                            AttendanceStatus.WEEKEND -> MaterialTheme.colorScheme.surfaceVariant
                        }
                    ) {
                        Text(
                            text = record.status.name,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = when (record.status) {
                                AttendanceStatus.PRESENT -> AccentGreen
                                AttendanceStatus.ABSENT -> AccentRose
                                AttendanceStatus.HOLIDAY -> AccentPurple
                                AttendanceStatus.WEEKEND -> MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AttendanceMetricCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    sublabel: String,
    color: androidx.compose.ui.graphics.Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = color,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = sublabel,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun AttendanceSkeleton(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onSurface)
        }
        repeat(4) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(60.dp).background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                )
            }
        }
    }
}
