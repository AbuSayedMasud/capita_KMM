package com.leads.capita.shell

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leads.capita.ui.theme.themeactivity.ColorSelectionViewModel
import com.leads.capitabull.android.R
import com.leads.capitabull.android.account.AccountScreen
import com.leads.capitabull.android.home.HomeScreen
import com.leads.capitabull.android.market.MarketScreen
import com.leads.capitabull.android.portfolio.PortfolioScreen
import com.leads.capitabull.android.search.SearchScreen
import com.leads.capitabull.android.service.ServiceScreen
import com.leads.capitabull.android.transaction.TransactionScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    val profilePhoto: Painter = painterResource(id = R.drawable.profile_1)
    val onProfileClick: () -> Unit = {
        // Handle the profile photo click event here
    }
    val showNotificationIcon = remember { mutableStateOf(false) }
    val colorSelectionViewModel = viewModel<ColorSelectionViewModel>()
    val currentRoute =
        remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }

    NavHost(navController = navController, startDestination = BottomBar.Home.route) {
        composable(BottomBar.Home.route) {
            HomeScreen(
                navController = navController,
            )
        }
        composable(BottomBar.Market.route) {
            MarketScreen(
                navController = navController,
            )
        }
        composable(
            route = "${BottomBar.Portfolio.route}/{${BottomBar.Portfolio.ARG_VALUE}}",
        ) { backStackEntry ->
            val value = backStackEntry.arguments?.getString(BottomBar.Portfolio.ARG_VALUE)

            PortfolioScreen(
                colorSelectionViewModel,
                navController = navController,
                queryParameters = value,
            )
        }

        composable(BottomBar.Search.route) {
            SearchScreen()
        }

        composable(BottomBar.Service.route) {
            ServiceScreen()
        }
        composable(BottomBar.Account.route) {
            AccountScreen(
                colorSelectionViewModel = colorSelectionViewModel,
                navController = navController,
            )
        }
        composable("transaction") {
            TransactionScreen(colorSelectionViewModel, navController)
        }

    }
}
