package com.leads.capitabull.android

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.leads.capitabull.android.shell.NavGraph
import com.leads.capitabull.android.shell.SetStatusBarColor
import com.leads.capitabull.android.theme.BackgroundColor
import com.leads.capitabull.android.theme.BottomBarColor2
import com.leads.capitabull.android.theme.CapitaTheme
import com.leads.capitabull.android.theme.White
import com.leads.capitabull.android.theme.rememberWindowSizeClass
import com.leads.capitabull.android.theme.themeactivity.ColorSelectionViewModel
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