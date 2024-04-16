package com.leads.capita.android.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.leads.capita.android.portfolio.financial.balance.BalanceScreen
import com.leads.capita.android.portfolio.financial.receivable.ReceivableScreen
import com.leads.capita.android.portfolio.ownershitp.position.PositionScreen
import com.leads.capita.android.shell.MyAppBar
import com.leads.capita.android.shell.SectionBar
import com.leads.capita.android.theme.LightBlack
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.theme.themeactivity.ColorSelectionViewModel
import com.leads.capita.android.R


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PortfolioScreen(
    colorSelectionViewModel: ColorSelectionViewModel,
    navController: NavHostController,
    queryParameters: String?,
) {
    val profilePhoto: Painter = painterResource(id = R.drawable.profile)
    val onProfileClick: () -> Unit = {
        // Handle the profile photo click event here
    }
    var portfolioSelectedSection by remember { mutableIntStateOf(0) }
    val currentRoute =
        remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    val lighterAppBarColor =
        if (isSystemInDarkTheme()) colorSelectionViewModel.appBarColor else colorSelectionViewModel.appBarColor
    val sections = listOf(
        "Position",
        "Balance",
        "Receivable",
    )
    val pagerState = rememberPagerState()

    // Observe the current page and update the selected section accordingly.
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            // Update the marketSelectedSection when the currentPage changes
            portfolioSelectedSection = page
        }
    }
    // Use another LaunchedEffect to animate scrolling to the selected market section
    LaunchedEffect(portfolioSelectedSection) {
        // Animate scrolling to the specified market section when marketSelectedSection changes
        pagerState.animateScrollToPage(portfolioSelectedSection)
    }

    val pageIndexToShow = when {
        queryParameters == "Balance" -> sections.indexOf("Balance")
        queryParameters == "Receivable" -> sections.indexOf("Receivable")
        queryParameters == "Position" -> sections.indexOf("Position")
        else -> 0
    }

    LaunchedEffect(pageIndexToShow) {
        pagerState.animateScrollToPage(pageIndexToShow)
    }
    Column {
        Surface(
            modifier = Modifier.height(61.dp),
            color = PrimaryColor,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),

            ) {
                MyAppBar(
                    navController = navController,
                    context = LocalContext.current,
                    title = "Portfolio Statement",
                    onSearch = { searchText ->
                        // What happens when the search button is clicked
                    },
                    showSearchBar = false,
                    onProfileClick = onProfileClick,
                    profilePhoto = profilePhoto,
                    showSearchIcon = true,
                    showNotificationIcon = true,
                    currentRoute = currentRoute,
                    showArrow = true,
                )
            }
        }

        SectionBar(
            sections = sections,
            selectedSection = portfolioSelectedSection,
            onSectionSelected = { section ->
                portfolioSelectedSection = section
            },
            sectionBarColor = lighterAppBarColor,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) LightBlack else Color.White),
        ) {
            // HorizontalPager composable that displays different screens based on the selected page
            HorizontalPager(
                count = sections.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
            ) { page ->
                Box(Modifier.fillMaxSize()) {
                    when (sections[page]) {
                        "Position" -> PositionScreen()
                        "Balance" -> BalanceScreen()
                        "Receivable" -> ReceivableScreen()
                    }
                }
            }
        }
    }
}
