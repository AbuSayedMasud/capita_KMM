package com.leads.capita.android.shell

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leads.capita.settings.SettingsScreen
import com.leads.capita.android.R
import com.leads.capita.android.home.HomeScreen
import com.leads.capita.android.market.MarketScreen
import com.leads.capita.android.portfolio.PortfolioScreen
import com.leads.capita.android.profile.ProfileScreen
import com.leads.capita.android.search.SearchScreen
import com.leads.capita.android.service.ServiceScreen
import com.leads.capita.android.service.deposit.DepositScreen
import com.leads.capita.android.service.deposit.DepositStatusScreen
import com.leads.capita.android.service.payment.PaymentScreen
import com.leads.capita.android.theme.themeactivity.ColorSelectionViewModel
import com.leads.capita.android.trade.TradeScreen
import com.leads.capita.android.transaction.TransactionScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavGraph(navController: NavHostController) {
    val profilePhoto: Painter = painterResource(id = R.drawable.profile_photo)
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

        composable(BottomBar.Trade.route) {
            TradeScreen(navController)
        }

        composable(BottomBar.Service.route) {
            ServiceScreen(navController)
        }
        composable(BottomBar.Profile.route) {
            ProfileScreen(
                colorSelectionViewModel = colorSelectionViewModel,
                navController = navController,
            )
        }
        composable("transaction") {
            TransactionScreen(colorSelectionViewModel, navController)
        }
        composable("search") {
            SearchScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
        composable("payment") {
            PaymentScreen(navController)
        }
        composable("deposit") {
            DepositScreen(navController)
        }
        composable("depositStatus") {
            DepositStatusScreen(navController)
        }
    }
}