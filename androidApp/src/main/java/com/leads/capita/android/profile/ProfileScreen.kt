package com.leads.capita.android.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
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
import com.leads.capita.android.shell.MyAppBar
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.rememberWindowSizeClass
import com.leads.capita.android.theme.themeactivity.ColorSelectionViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    colorSelectionViewModel: ColorSelectionViewModel,
    navController: NavHostController,
) {

    val profilePhoto: Painter = painterResource(id = R.drawable.profile)
    val onProfileClick: () -> Unit = {
    }
    val currentRoute =
        remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }

    val expandedStateBalance = remember { mutableStateOf(false) }
    val expandedStateInstrument = remember { mutableStateOf(false) }

    fun toggleBalanceExpansion() {
        expandedStateBalance.value = !expandedStateBalance.value
        if (expandedStateBalance.value) {
            expandedStateInstrument.value = false
        }
    }

    fun toggleInstrumentExpansion() {
        expandedStateInstrument.value = !expandedStateInstrument.value
        if (expandedStateInstrument.value) {
            expandedStateBalance.value = false
        }
    }

    Column {
        MyAppBar(
            navController = navController,
            context = LocalContext.current,
            title = "Profile",
            onSearch = { searchText ->
            },
            showSearchBar = false,
            onProfileClick = onProfileClick,
            profilePhoto = profilePhoto,
            showSearchIcon = false,
            showNotificationIcon = false,
            showSettingsIcon=true,
            currentRoute = currentRoute,
            showArrow = false,
        )

        val window = rememberWindowSizeClass()
        CapitaTheme(window) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp, bottom = 0.dp),
                ) {
                    item {
                        AccountProfileView(navController)
                    }
                    item {
                        // Pass expandedStateBalance and toggleBalanceExpansion
                        AccountBalanceView(
                            navController,
                            expandedStateBalance,
                            ::toggleBalanceExpansion,
                            "balance",
                        )
                    }
                    item {
                        // Pass expandedStateInstrument and toggleInstrumentExpansion
                        AccountInstrumentView(
                            navController,
                            expandedStateInstrument,
                            ::toggleInstrumentExpansion,
                            "instrument",
                        )
                    }
                    item {
                        AccountProtfolioStatementView(navController, colorSelectionViewModel)
                    }
                    item {
                        AccountTransactionStatementView(navController)
                    }
                    item {
                        AccountDetailsScreen(navController)
                    }
                    item {
                        Logout(navController)
                    }
                    item {
                        Spacer(modifier = Modifier.height(76.dp))
                    }
                }
            }
        }
    }
}