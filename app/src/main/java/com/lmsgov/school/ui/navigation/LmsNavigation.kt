package com.lmsgov.school.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lmsgov.school.ui.components.Header
import com.lmsgov.school.ui.components.NavigationDrawerContent
import com.lmsgov.school.ui.screens.AcademicsPage
import com.lmsgov.school.ui.screens.AttendancePage
import com.lmsgov.school.ui.screens.CalendarPage
import com.lmsgov.school.ui.screens.ContactPage
import com.lmsgov.school.ui.screens.DashboardScreen
import com.lmsgov.school.ui.screens.ExaminationPage
import com.lmsgov.school.ui.screens.NoticesPage
import com.lmsgov.school.ui.screens.StudentInfoPage
import kotlinx.coroutines.launch

sealed class Screen(val route: String) {
    data object Dashboard : Screen("/")
    data object Attendance : Screen("/attendance")
    data object Calendar : Screen("/calendar")
    data object Notices : Screen("/notices")
    data object StudentInfo : Screen("/student-info")
    data object Academics : Screen("/academics")
    data object Examination : Screen("/examination")
    data object Contact : Screen("/contact")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LmsApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerContent(
                    navController = navController,
                    onNavigate = { route ->
                        scope.launch { drawerState.close() }
                        navController.navigate(route) {
                            popUpTo(Screen.Dashboard.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                Header(
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            },
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Dashboard.route
                ) {
                    composable(Screen.Dashboard.route) {
                        DashboardScreen(navController = navController)
                    }
                    composable(Screen.Attendance.route) {
                        AttendancePage(navController = navController)
                    }
                    composable(Screen.Calendar.route) {
                        CalendarPage(navController = navController)
                    }
                    composable(Screen.Notices.route) {
                        NoticesPage(navController = navController)
                    }
                    composable(Screen.StudentInfo.route) {
                        StudentInfoPage(navController = navController)
                    }
                    composable(Screen.Academics.route) {
                        AcademicsPage(navController = navController)
                    }
                    composable(Screen.Examination.route) {
                        ExaminationPage(navController = navController)
                    }
                    composable(Screen.Contact.route) {
                        ContactPage(navController = navController)
                    }
                }
            }
        }
    }
}
