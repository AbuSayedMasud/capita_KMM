package com.leads.capita.android.transaction

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
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.android.R

import com.leads.capita.android.shell.MyAppBar
import com.leads.capita.android.shell.SectionBar
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.theme.themeactivity.ColorSelectionViewModel
import com.leads.capita.android.transaction.allTransaction.AllTransactionScreen
import com.leads.capita.android.transaction.creditTransaction.CreditTransactionScreen
import com.leads.capita.android.transaction.debitTransaction.DebitTransactionScreen
import com.leads.capita.account.AccountTransaction
import com.leads.capita.service.account.AccountServiceImpl


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TransactionScreen(
    colorSelectionViewModel: ColorSelectionViewModel,
    navController: NavHostController,
) {
    val profilePhoto: Painter = painterResource(id = R.drawable.profile)
    val onProfileClick: () -> Unit = {
    }
    val context = LocalContext.current
    val databaseDriverFactory: DatabaseDriverFactory = DatabaseDriverFactory(context)
    val accountInstrument = AccountServiceImpl(databaseDriverFactory)
    var transactionList: List<AccountTransaction>? by remember { mutableStateOf(null) }
    val accountTransactionList = accountInstrument.getTransactionServices()
//    val accountTransactionList=MockLoaderDemo(context).transactions
    transactionList = accountTransactionList
    var transactionSelectedSection by remember { mutableIntStateOf(0) }
    val currentRoute =
        remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    val lighterAppBarColor =
        if (isSystemInDarkTheme()) colorSelectionViewModel.appBarColor else colorSelectionViewModel.appBarColor
    val sections = listOf(
        "All",
        "Debit",
        "Credit",
    )
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            transactionSelectedSection = page
        }
    }
    // LaunchedEffect to observe changes to selectedSection and animate page scroll
    LaunchedEffect(transactionSelectedSection) {
        pagerState.animateScrollToPage(transactionSelectedSection)
    }

    Column {
        Surface(
            modifier = Modifier.height(61.dp),
            color = PrimaryColor,
        ) {
            Box(
                modifier = Modifier.fillMaxSize()

            ) {
                MyAppBar(
                    navController = navController,
                    context = LocalContext.current,
                    title = "Ledger Statement",
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
            selectedSection = transactionSelectedSection,
            onSectionSelected = { section ->
                transactionSelectedSection = section
            },
            sectionBarColor = lighterAppBarColor,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))
        ) {
            HorizontalPager(
                count = sections.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
            ) { page ->
                Box(
                    Modifier.fillMaxSize(),
                ) {
                    when (sections[page]) {
                        "All" -> AllTransactionScreen(transactionList!!)
                        "Debit" -> DebitTransactionScreen(transactionList!!)
                        "Credit" -> CreditTransactionScreen(transactionList!!)
                    }
                }
            }
        }
    }
}
