package com.leads.capita.android.shell

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leads.capita.android.service.ServiceScreen
import com.leads.capita.android.service.deposit.DepositScreen
import com.leads.capita.android.service.deposit.DepositStatusScreen
import com.leads.capita.android.service.payment.PaymentScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ServiceNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "services"
    ) {
        composable("services") {
            ServiceScreen(navController = navController)
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
