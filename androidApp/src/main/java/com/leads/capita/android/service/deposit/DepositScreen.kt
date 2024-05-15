package com.leads.capita.android.service.deposit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.leads.capita.android.R
import com.leads.capita.android.service.ServiceView
import com.leads.capita.android.service.payment.PaymentView
import com.leads.capita.android.shell.MyAppBar
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.rememberWindowSizeClass

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DepositScreen(navController: NavHostController) {
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
            title = "Deposit",
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
                .background(if (isSystemInDarkTheme()) BackgroundColor else Color.White)
                .padding(bottom = 40.dp),
        ) {
            val window = rememberWindowSizeClass()
            CapitaTheme(window) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 0.dp, bottom = 20.dp),
                ) {
                    item {
                        DepositView(navController)
                    }

                }

            }
        }
    }
}