package com.leads.capita.android.market

import android.annotation.SuppressLint // ktlint-disable import-ordering
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.leads.capita.android.market.instrument.IndexScreen
import com.leads.capita.android.market.instrument.InstrumentScreen
import com.leads.capita.android.market.news.NewsScreen
import com.leads.capita.android.market.overview.OverviewScreen
import com.leads.capita.android.shell.MyAppBar
import com.leads.capita.android.shell.SectionBar
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.R


@SuppressLint("RememberReturnType")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MarketScreen(
    navController: NavHostController,
) {
    val profilePhoto: Painter = painterResource(R.drawable.profile)
    val onProfileClick: () -> Unit = {
        // Handle the profile photo click event here
    }
    val context = LocalContext.current
    val currentRoute = remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    val lighterAppBarColor = if (isSystemInDarkTheme()) PrimaryColor else PrimaryColor
    val sections = listOf(
        "Overview",
        "Indices",
        "Stocks",
        "Watchlist",
        "News",
    )
    val enableSwipingStates = remember { mutableStateListOf(true, true, true, true, true) }
    val pagerState = rememberPagerState()
    var marketSelectedSection by remember { mutableIntStateOf(0) }

    val (showAppBarAndMarketSectionBar, setShowAppBarAndMarketSectionBar) = remember { mutableStateOf(true) }
    val (showInstrumentFilterView, setShowInstrumentFilterView) = remember { mutableStateOf(false) }
    val (showIndexFilterView, setShowIndexFilterView) = remember { mutableStateOf(false) }
    val (showOverviewFilterView, setShowOverviewFilterView) = remember { mutableStateOf(false) }
    val (isFloatingActionButtonVisible, setIsFloatingActionButtonVisible) = remember { mutableStateOf(true) }
    val (isSwipeEnabled, setIsSwipeEnabled) = remember { mutableStateOf(true) }

    // Observe the current page and update the selected section accordingly.
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            // Update the marketSelectedSection when the currentPage changes
            marketSelectedSection = page
        }
    }
    // Use another LaunchedEffect to animate scrolling to the selected market section
    LaunchedEffect(marketSelectedSection) {
        // Animate scrolling to the specified market section when marketSelectedSection changes
        pagerState.animateScrollToPage(marketSelectedSection)
    }

    Column {
        if (showAppBarAndMarketSectionBar) {
            Surface(
                modifier = Modifier.height(61.dp).zIndex(2f),
                color = PrimaryColor,
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    MyAppBar(
                        navController = navController,
                        context = LocalContext.current,
                        title = "Market",
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
                }
            }

            SectionBar(
                sections = sections,
                selectedSection = marketSelectedSection,
                onSectionSelected = { section ->
                    marketSelectedSection = section
                },
                sectionBarColor = lighterAppBarColor,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9)),
        ) {
            // HorizontalPager composable that displays different screens based on the selected page
            HorizontalPager(
                count = sections.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = isSwipeEnabled,
            ) { page ->
                Box(Modifier.fillMaxSize()) {
                    when (sections[page]) {
                        "Overview" -> OverviewScreen(
                            setShowAppBarAndMarketSectionBar,
                            showOverviewFilterView,
                            setShowOverviewFilterView,
                            isFloatingActionButtonVisible,
                            setIsFloatingActionButtonVisible,
                            setIsSwipeEnabled,
                        )

                        "Indices" -> IndexScreen(
                            context,
                            setShowAppBarAndMarketSectionBar,
                            showIndexFilterView,
                            setShowIndexFilterView,
                            isFloatingActionButtonVisible,
                            setIsFloatingActionButtonVisible,
                            setIsSwipeEnabled,
                        )

                        "Stocks" -> InstrumentScreen(
                            setShowAppBarAndMarketSectionBar,
                            showInstrumentFilterView,
                            setShowInstrumentFilterView,
                            isFloatingActionButtonVisible,
                            setIsFloatingActionButtonVisible,
                            setIsSwipeEnabled,
                        )

                        "News" -> NewsScreen()
                    }
                }
            }
        }
    }
}
