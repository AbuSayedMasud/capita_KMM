package com.leads.capita.android.service

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.leads.capita.android.shell.ServiceNavGraph
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.rememberWindowSizeClass

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ServiceParentScreen(navController: NavHostController) {
    val childNavController = rememberNavController()
    Scaffold(
    ) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            val window = rememberWindowSizeClass()
            CapitaTheme(window) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))
                ) {
                    ServiceNavGraph(navController = childNavController)
                }
            }
        }
    }
}