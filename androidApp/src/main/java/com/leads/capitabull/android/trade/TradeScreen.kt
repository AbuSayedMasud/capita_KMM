package com.leads.capitabull.android.trade

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.leads.capitabull.android.R
import com.leads.capitabull.android.shell.MyAppBar
import com.leads.capitabull.android.theme.BackgroundColor

@Composable
fun TradeScreen(navController: NavHostController) {
    val profilePhoto: Painter = painterResource(R.drawable.profile)
    val onProfileClick: () -> Unit = {
        // Handle the profile photo click event here
    }
    val context = LocalContext.current
    val currentRoute = remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    Column {
        MyAppBar(
            navController = navController,
            context = LocalContext.current,
            title = "Trade",
            onSearch = { searchText ->
                // What happens when the search button is clicked
            },
            showSearchBar = false,
            onProfileClick = onProfileClick,
            profilePhoto = profilePhoto,
            showSearchIcon = true,
            showNotificationIcon = true,
            currentRoute = currentRoute,
            showArrow = false,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) BackgroundColor else Color.White),
        )
    }
}