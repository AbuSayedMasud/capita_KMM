// HomeScreen.kt
package com.leads.capitabull.android.home

import HomeNewsView
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.leads.capita.home.HomeBalanceView
import com.leads.capitabull.android.R
import com.leads.capitabull.android.shell.MyAppBar
import com.leads.capitabull.android.theme.BackgroundColor
import com.leads.capitabull.android.theme.CapitaTheme
import com.leads.capitabull.android.theme.rememberWindowSizeClass


@Composable
fun HomeScreen(navController: NavHostController) {
    val profilePhoto = painterResource(id = R.drawable.profile)
    val onProfileClick: () -> Unit = {
    }
    val currentRoute = remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }

    Column {
        Surface(
            elevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(6.dp),
        ) {
            MyAppBar(
                navController = navController,
                context = LocalContext.current,
                title = "SHANTA",
                onSearch = { searchText ->
                },
                showSearchBar = false,
                onProfileClick = onProfileClick,
                profilePhoto = profilePhoto,
                showSearchIcon = false,
                showNotificationIcon = true,
                currentRoute = currentRoute,
                showArrow = false,
            )
        }
        // Box to hold the main content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))
                .padding(bottom = 40.dp),
        ) {
            val window = rememberWindowSizeClass()
            CapitaTheme(window) {
                // LazyColumn to lazily compose items
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 0.dp),
                ) {
                    item {
                        HomeBalanceView(navController)
                    }
                    item {
                        HomeInstrumentView(navController)
                    }
                    item {
                        HomeReceivableView(navController)
                    }
                    item {
                        HomeTransactionView(navController)
                    }
                    item {
                        HomeNewsView()
                    }
                }
            }
        }
    }
}
