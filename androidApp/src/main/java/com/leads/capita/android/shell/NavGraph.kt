package com.leads.capita.android.shell

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leads.capita.android.biometricRegistration.BioMetricRegistrationScreen
import com.leads.capita.android.biometricRegistration.BiometricFingerPrintRegistrationView
import com.leads.capita.android.forgetPassword.ForgetPasswordScreen
import com.leads.capita.android.login.LoginScreen
import com.leads.capita.android.registration.RegistrationScreen
import com.leads.capita.android.sharePreference.PreferencesManager


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController, preferencesManager: PreferencesManager) {
    val currentRoute = remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController, preferencesManager)
        }
        composable("forgetPassword") {
            ForgetPasswordScreen(navController)
        }
        composable("biometricRegistration") {
            BioMetricRegistrationScreen(navController)
        }
        composable("registration") {
            RegistrationScreen(navController)
        }
        composable("fingerprint") {
            BiometricFingerPrintRegistrationView(
                navController = navController,
            )
        }
    }
}
