package com.leads.capita.android.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.leads.capita.android.theme.AppTheme
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.Orientation
import com.leads.capita.android.theme.rememberWindowSizeClass
import com.leads.capita.android.sharePreference.PreferencesManager

@Composable
fun LoginScreen(navController: NavHostController, preferencesManager: PreferencesManager) {
    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
        if (AppTheme.orientation == Orientation.Portrait) {
            LoginView(navController, preferencesManager)
        }
    }
}