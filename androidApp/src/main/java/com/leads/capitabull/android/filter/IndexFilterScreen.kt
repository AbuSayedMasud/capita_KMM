package com.leads.capitabull.android.filter

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
import com.leads.capitabull.android.filter.FilterView
import com.leads.capitabull.android.shell.MyAppBar
import com.leads.capitabull.android.theme.CapitaTheme
import com.leads.capitabull.android.theme.rememberWindowSizeClass

@Composable
fun IndexFilterScreen(
    setIsFloatingActionButtonVisible: (Boolean) -> Unit,
    setIsSwipeEnabled: (Boolean) -> Unit,
    onIndexSelected: (String) -> Unit,
) {
    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
        var searchText by remember { mutableStateOf("") }
        val navController = rememberNavController()

        Column(modifier = Modifier.fillMaxHeight()) {
            // Custom app bar
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

            // FilterView composable for displaying and selecting filter items
            FilterView(
                searchText = searchText,
                onIndexSelected = { selectedMarket ->
                    setIsSwipeEnabled(true)
                    setIsFloatingActionButtonVisible(true)
                    onIndexSelected(selectedMarket)
                },
            )

            // DisposableEffect to handle side effects when the composable is disposed
            DisposableEffect(Unit) {
                // Disable swipe and hide FAB when the composable is disposed
                setIsSwipeEnabled(false)
                setIsFloatingActionButtonVisible(false)
                onDispose {
                }
            }
        }
    }
}
