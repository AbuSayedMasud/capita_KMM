package com.leads.capitabull.android.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.leads.capitabull.android.R
import com.leads.capitabull.android.shell.MyAppBar
import com.leads.capitabull.android.theme.BackgroundColor
import com.leads.capitabull.android.theme.CapitaTheme
import com.leads.capitabull.android.theme.rememberWindowSizeClass

@Composable
fun  RegistrationScreen(navController: NavHostController) {
    val profilePhoto: Painter = painterResource(id = R.drawable.profile)
    val onProfileClick: () -> Unit = {
    }
    val context = LocalContext.current
    val currentRoute =
        remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    Column {
        MyAppBar(
            navController = navController,
            context = LocalContext.current,
            title = "Registration",
            onSearch = { searchText ->
            },
            showSearchBar = false,
            onProfileClick = onProfileClick,
            profilePhoto = profilePhoto,
            showSearchIcon = false,
            showNotificationIcon = false,
            currentRoute = currentRoute,
            showArrow = true,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) BackgroundColor else Color.White)
                .padding(bottom = 40.dp),
        ) {
            val window = rememberWindowSizeClass()
            CapitaTheme(window) {
                RegistrationView(navController)
            }
        }
    }
}