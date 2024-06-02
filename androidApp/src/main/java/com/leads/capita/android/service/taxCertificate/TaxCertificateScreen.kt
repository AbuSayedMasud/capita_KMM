package com.leads.capita.android.service.taxCertificate

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
import com.leads.capita.android.R
import com.leads.capita.android.shell.MyAppBar
import com.leads.capita.android.theme.BackgroundColor

@Composable
fun TaxCertificateScreen(navController: NavHostController) {
    val profilePhoto: Painter = painterResource(id = R.drawable.profile)
    val onProfileClick: () -> Unit = {
        // Handle the profile photo click event here
    }
    val currentRoute =
        remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    Column {
        MyAppBar(
            navController = navController,
            context = LocalContext.current,
            title = "Tax Certificate",
            onSearch = { searchText ->
            },
            showSearchBar = false,
            onProfileClick = onProfileClick,
            profilePhoto = profilePhoto,
            showSearchIcon = true,
            showNotificationIcon = true,
            currentRoute = currentRoute,
            showArrow = true,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))

        ) {

        }
    }
}