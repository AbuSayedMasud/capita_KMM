package com.leads.capita.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getCardColors(): Pair<Color, Color> {
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) CardColor else Color.White
    val contentColor = if (isDarkTheme) Color.White else Color.Black
    return Pair(backgroundColor, contentColor)
}
