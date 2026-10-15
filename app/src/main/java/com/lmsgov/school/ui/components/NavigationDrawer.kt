package com.lmsgov.school.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Poll
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lmsgov.school.i18n.Language
import com.lmsgov.school.i18n.Translations
import com.lmsgov.school.ui.navigation.Screen
import com.lmsgov.school.ui.theme.AccentAmber
import com.lmsgov.school.ui.theme.AccentBlue
import com.lmsgov.school.ui.theme.AccentGreen
import com.lmsgov.school.ui.theme.AccentPurple

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
    val subItems: List<SubNavItem>? = null
)

data class SubNavItem(
    val label: String,
    val route: String
)

@Composable
fun NavigationDrawerContent(
    navController: NavHostController,
    onNavigate: (String) -> Unit
) {
    val navItems = listOf(
        NavItem(
            label = Translations.t("homeDashboard", Language.EN),
            icon = Icons.Default.Dashboard,
            route = Screen.Dashboard.route,
            subItems = listOf(
                SubNavItem("Ward Arrival", Screen.Dashboard.route),
                SubNavItem("Attendance Overview", Screen.Attendance.route),
                SubNavItem("Today's Routine", Screen.Calendar.route)
            )
        ),
        NavItem(
            label = Translations.t("attendance", Language.EN),
            icon = Icons.Default.Assignment,
            route = Screen.Attendance.route,
            subItems = listOf(
                SubNavItem("Daily Log", Screen.Attendance.route),
                SubNavItem("Monthly Breakdown", Screen.Attendance.route)
            )
        ),
        NavItem(
            label = Translations.t("schoolRoutine", Language.EN),
            icon = Icons.Default.CalendarMonth,
            route = Screen.Calendar.route
        ),
        NavItem(
            label = Translations.t("importantNotices", Language.EN),
            icon = Icons.Default.Notifications,
            route = Screen.Notices.route,
            subItems = listOf(
                SubNavItem("Academic", Screen.Notices.route),
                SubNavItem("Examination", Screen.Notices.route),
                SubNavItem("Holiday", Screen.Notices.route),
                SubNavItem("Events", Screen.Notices.route)
            )
        ),
        NavItem(
            label = Translations.t("studentInfo", Language.EN),
            icon = Icons.Default.School,
            route = Screen.StudentInfo.route,
            subItems = listOf(
                SubNavItem("Personal Details", Screen.StudentInfo.route),
                SubNavItem("Guardian Info", Screen.StudentInfo.route)
            )
        ),
        NavItem(
            label = Translations.t("academics", Language.EN),
            icon = Icons.Default.MenuBook,
            route = Screen.Academics.route,
            subItems = listOf(
                SubNavItem("Subject Progress", Screen.Academics.route),
                SubNavItem("Syllabus", Screen.Academics.route)
            )
        ),
        NavItem(
            label = Translations.t("examination", Language.EN),
            icon = Icons.Default.Poll,
            route = Screen.Examination.route,
            subItems = listOf(
                SubNavItem("Datesheet", Screen.Examination.route),
                SubNavItem("Guidelines", Screen.Examination.route)
            )
        ),
        NavItem(
            label = Translations.t("contactUs", Language.EN),
            icon = Icons.Default.ContactMail,
            route = Screen.Contact.route,
            subItems = listOf(
                SubNavItem("School Directory", Screen.Contact.route),
                SubNavItem("Send Inquiry", Screen.Contact.route)
            )
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                .padding(20.dp)
        ) {
            Column {
                Text(
                    text = Translations.t("portalTitle", Language.EN),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = Translations.t("schoolName", Language.EN),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        navItems.forEach { item ->
            NavItemRow(
                item = item,
                onItemClick = { onNavigate(item.route) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = Translations.t("prototypeNote", Language.EN),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = Translations.t("prototypeDesc", Language.EN),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun NavItemRow(
    item: NavItem,
    onItemClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val hasSubItems = !item.subItems.isNullOrEmpty()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (hasSubItems) {
                        expanded = !expanded
                    } else {
                        onItemClick()
                    }
                }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = item.label,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            if (hasSubItems) {
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandMore else Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        AnimatedVisibility(visible = expanded && hasSubItems) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 52.dp)
            ) {
                item.subItems?.forEach { subItem ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick() }
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = subItem.label,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        if (!expanded) {
            Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = DividerDefaults.Thickness,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            )
        }
    }
}
