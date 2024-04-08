package com.leads.capitabull.android.theme.themeactivity

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    val darkTheme = mutableStateOf(false) // Initialize with system theme

    fun toggleTheme() {
        darkTheme.value = !darkTheme.value
    }
}