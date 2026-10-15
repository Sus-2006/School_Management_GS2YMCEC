package com.lmsgov.school.ui.screens

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lmsgov.school.data.model.*
import com.lmsgov.school.data.repository.LmsRepository
import com.lmsgov.school.i18n.Language
import com.lmsgov.school.i18n.Translations
import com.lmsgov.school.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarPage(navController: NavController) {
    val language = Language.EN
    val repository = remember { LmsRepository() }

    var events by remember { mutableStateOf<List<CalendarEvent>>(emptyList()) }
    var routine by remember { mutableStateOf<List<DailyRoutinePeriod>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var typeFilter by remember { mutableStateOf("all") }
    var selectedDate by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        events = repository.getCalendarEvents()
        routine = repository.getDailyRoutine()
        isLoading = false
    }

    LaunchedEffect(typeFilter) {
        events = repository.getCalendarEvents(typeFilter)
    }

    if (isLoading) {
        CalendarSkeleton(navController)
        return
    }

    val monthDays = (1..31).toList()
    val startOffset = 6
    val dayHeaders = listOf("Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri")

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onSurface)
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = Translations.t("academicCalendarTitle", language),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = Translations.t("academicSession", language),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "August 2026",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = Translations.t("eventCategories", language),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        dayHeaders.forEach { day ->
                            Text(
                                text = day,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    val totalCells = startOffset + monthDays.size
                    val rows = (totalCells + 6) / 7
                    for (row in 0 until rows) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (col in 0..6) {
                                val cellIndex = row * 7 + col
                                val dayNum = cellIndex - startOffset + 1
                                if (dayNum in 1..31) {
                                    val dateStr = "2026-08-${dayNum.toString().padStart(2, '0')}"
                                    val hasEvent = events.any { it.date == dateStr }
                                    val event = events.find { it.date == dateStr }
                                    val isSelected = selectedDate == dateStr

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1f)
                                            .padding(2.dp)
                                            .clip(CircleShape)
                                            .background(
                                                when {
                                                    isSelected -> MaterialTheme.colorScheme.primary
                                                    hasEvent -> event?.let {
                                                        when (it.type) {
                                                            CalendarEventType.HOLIDAY -> AccentPurple.copy(alpha = 0.2f)
                                                            CalendarEventType.EXAM -> AccentRose.copy(alpha = 0.2f)
                                                            CalendarEventType.SPECIAL -> AccentAmber.copy(alpha = 0.2f)
                                                            CalendarEventType.EVENT -> AccentSky.copy(alpha = 0.2f)
                                                            CalendarEventType.SCHOOL_DAY -> AccentGreen.copy(alpha = 0.2f)
                                                        }
                                                    } ?: MaterialTheme.colorScheme.surface
                                                    else -> MaterialTheme.colorScheme.surface
                                                }
                                            )
                                            .clickable { selectedDate = dateStr },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "$dayNum",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = when {
                                                isSelected -> MaterialTheme.colorScheme.onPrimary
                                                hasEvent -> MaterialTheme.colorScheme.onSurface
                                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                                            }
                                        )
                                    }
                                } else {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                listOf("all", "EXAM", "HOLIDAY", "EVENT", "SPECIAL").forEach { filter ->
                    FilterChip(
                        selected = typeFilter == filter,
                        onClick = { typeFilter = filter },
                        label = {
                            Text(
                                text = Translations.t(
                                    when (filter) {
                                        "all" -> "allEvents"
                                        "EXAM" -> "exams"
                                        "HOLIDAY" -> "holidays"
                                        "EVENT" -> "events"
                                        "SPECIAL" -> "special"
                                        else -> "allEvents"
                                    },
                                    language
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = when (filter) {
                                "EXAM" -> AccentRose.copy(alpha = 0.15f)
                                "HOLIDAY" -> AccentPurple.copy(alpha = 0.15f)
                                "EVENT" -> AccentSky.copy(alpha = 0.15f)
                                "SPECIAL" -> AccentAmber.copy(alpha = 0.15f)
                                else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                            },
                            selectedLabelColor = when (filter) {
                                "EXAM" -> AccentRose
                                "HOLIDAY" -> AccentPurple
                                "EVENT" -> AccentSky
                                "SPECIAL" -> AccentAmber
                                else -> MaterialTheme.colorScheme.primary
                            }
                        )
                    )
                }
            }
        }

        items(events) { event ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                when (event.type) {
                                    CalendarEventType.HOLIDAY -> AccentPurple
                                    CalendarEventType.EXAM -> AccentRose
                                    CalendarEventType.SPECIAL -> AccentAmber
                                    CalendarEventType.EVENT -> AccentSky
                                    CalendarEventType.SCHOOL_DAY -> AccentGreen
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = event.date.takeLast(2),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = event.title,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = event.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if (event.timing != null) {
                            Text(
                                text = event.timing,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
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
                        text = Translations.t("dailyTimetable", language),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    routine.forEach { period ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(if (period.isCurrent) AccentGreen else MaterialTheme.colorScheme.surfaceVariant),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${period.period}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (period.isCurrent) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = period.subject,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${period.teacher} | ${period.room}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Text(
                                text = period.time,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CalendarSkeleton(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onSurface)
        }
        repeat(3) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(100.dp).background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                )
            }
        }
    }
}
