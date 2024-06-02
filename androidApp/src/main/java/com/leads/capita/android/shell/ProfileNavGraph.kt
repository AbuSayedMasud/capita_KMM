package com.leads.capita.android.shell

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leads.capita.android.portfolio.PortfolioScreen
import com.leads.capita.android.profile.AccountDetailsView
import com.leads.capita.android.profile.ProfileScreen
import com.leads.capita.android.service.ServiceScreen
import com.leads.capita.android.service.deposit.DepositScreen
import com.leads.capita.android.service.deposit.DepositStatusScreen
import com.leads.capita.android.service.ipo.IpoScreen
import com.leads.capita.android.service.payment.PaymentScreen
import com.leads.capita.android.service.productSwitch.ProductSwitchScreen
import com.leads.capita.android.service.taxCertificate.TaxCertificateScreen
import com.leads.capita.android.theme.themeactivity.ColorSelectionViewModel
import com.leads.capita.android.trade.TradeScreen
import com.leads.capita.android.transaction.TransactionScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileNavGraph(navController: NavHostController) {
    val colorSelectionViewModel = viewModel<ColorSelectionViewModel>()
    NavHost(
        navController = navController,
        startDestination = "profile"
    ) {
        composable("profile") {
            ProfileScreen(colorSelectionViewModel,navController)
        }
        composable("transaction") {
            TransactionScreen(colorSelectionViewModel, navController)
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
        composable("accountDetails") {
            AccountDetailsView(navController)
        }
    }
}
