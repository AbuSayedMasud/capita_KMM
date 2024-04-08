package com.leads.capitabull.android.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.leads.capita.ui.theme.AppTheme
import com.leads.capita.ui.theme.CapitaTheme
import com.leads.capita.ui.theme.Orientation
import com.leads.capita.ui.theme.rememberWindowSizeClass
import com.leads.capitabull.android.sharePreference.PreferencesManager

@Composable
fun LoginScreen(navController: NavHostController, preferencesManager: PreferencesManager) {
    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
        if (AppTheme.orientation == Orientation.Portrait) {
            LoginView(navController, preferencesManager)
        }
    }
}