package com.leads.capita.ui.theme.themeactivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ColorSelectionViewModel : ViewModel() {
    var appBarColor by mutableStateOf(Color(0xFF978c21))
    var bottomNavBarColor by mutableStateOf(Color(0xFFFFFFFF))
    var homeSectionBar by mutableStateOf(Color(0xFF978c21))
    var portfolioSectionBar by mutableStateOf(Color(0xFF978c21))
}

