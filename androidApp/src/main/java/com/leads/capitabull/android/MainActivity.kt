package com.leads.capitabull.android

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.leads.capita.shell.NavGraph
import com.leads.capita.shell.SetStatusBarColor
import com.leads.capita.ui.theme.AppTheme
import com.leads.capita.ui.theme.BackgroundColor
import com.leads.capita.ui.theme.BottomBarColor2
import com.leads.capita.ui.theme.CapitaTheme
import com.leads.capita.ui.theme.Orientation
import com.leads.capita.ui.theme.White
import com.leads.capita.ui.theme.rememberWindowSizeClass
import com.leads.capita.ui.theme.themeactivity.ColorSelectionViewModel
import com.leads.capitabull.Greeting
import com.leads.capitabull.android.sharePreference.PreferencesManager

class MainActivity : FragmentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigationBarColor = if (isSystemInDarkTheme()) BottomBarColor2 else White
            val navController = rememberNavController()
            val preferencesManager = PreferencesManager(applicationContext)
            val colorSelectionViewModel = viewModel<ColorSelectionViewModel>()
            SetStatusBarColor(Color(0xFF877E1D), navigationBarColor)
            val window = rememberWindowSizeClass()
            CapitaTheme(windowSizeClass = window){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (isSystemInDarkTheme()) BackgroundColor else Color.White),
                ) {
                    NavGraph(navController = navController, preferencesManager)
                }
            }
        }
    }
}