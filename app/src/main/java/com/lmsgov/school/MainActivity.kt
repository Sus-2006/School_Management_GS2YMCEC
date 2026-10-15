package com.lmsgov.school

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lmsgov.school.ui.navigation.LmsApp
import com.lmsgov.school.ui.theme.LmsGovSchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LmsGovSchoolTheme {
                LmsApp()
            }
        }
    }
}
