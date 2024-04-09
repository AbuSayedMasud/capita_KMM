package com.leads.capitabull.android.shell

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
import com.leads.capitabull.android.biometricRegistration.BioMetricRegistrationScreen
import com.leads.capitabull.android.biometricRegistration.BiometricFingerPrintRegistrationView
import com.leads.capitabull.android.forgetPassword.ForgetPasswordScreen
import com.leads.capitabull.android.login.LoginScreen
import com.leads.capitabull.android.registration.RegistrationScreen
import com.leads.capitabull.android.sharePreference.PreferencesManager


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
