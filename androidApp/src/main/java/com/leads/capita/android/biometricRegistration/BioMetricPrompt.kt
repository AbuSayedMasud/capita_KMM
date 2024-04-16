package com.leads.capita.android.biometricRegistration

import android.content.Intent
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.leads.capita.android.MockJsonLoader.MockLoader
import com.leads.capita.android.activity.HomeActivity
import kotlinx.coroutines.runBlocking

class BioMetricPrompt {
    fun showBiometricPrompt(biometricPrompt: BiometricPrompt) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .setTitle("Verify that it's you")
            .setSubtitle("Use your fingerprint to continue")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    @Composable
    fun bioMetricAuthentication(): BiometricPrompt {
        val context = LocalContext.current
        val executor = remember { ContextCompat.getMainExecutor(context) }
        val biometricPrompt = BiometricPrompt(
            context as FragmentActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    val intent = Intent(context, HomeActivity::class.java)
//                    val service = MockLoader(context)
                    runBlocking {
//                        service.init()
//                        MockLoader(context).init()
                    }
                    context.startActivity(intent)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            }
        )
        return biometricPrompt
    }
}
