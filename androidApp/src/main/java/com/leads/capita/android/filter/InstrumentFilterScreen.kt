package com.leads.capita.android.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.leads.capita.android.filter.FilterView
import com.leads.capita.android.shell.MyAppBar
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.rememberWindowSizeClass

@Composable
fun InstrumentFilterScreen(
    setIsFloatingActionButtonVisible: (Boolean) -> Unit,
    setIsSwipeEnabled: (Boolean) -> Unit,
    onInstrumentSelected: (String) -> Unit,
) {
    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
        var searchText by remember { mutableStateOf("") }
        val navController = rememberNavController()

        Column(modifier = Modifier.fillMaxHeight()) {
            MyAppBar(
                navController = navController,
                context = LocalContext.current,
                title = "Search",
                onSearch = { newSearchText ->
                    searchText = newSearchText
                },
                showSearchBar = true,
                onProfileClick = null,
                profilePhoto = null,
                currentRoute = null,
            )

            FilterView(
                searchText = searchText,
                onIndexSelected = { selectedMarket ->
                    setIsSwipeEnabled(true)
                    setIsFloatingActionButtonVisible(true)
                    onInstrumentSelected(selectedMarket)
                },
            )

            DisposableEffect(Unit) {
                setIsSwipeEnabled(false)
                setIsFloatingActionButtonVisible(false)
                onDispose {
                }
            }
        }
    }
}
