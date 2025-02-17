package com.leads.capita.android.formatnumber

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.time.temporal.TemporalAmount

object InputFieldValidator {
    /**
     * Function to check if a given date string has a valid MM/DD/YYYY format.
     *
     * @param date Date string to be validated.
     * @return True if the date is valid, false otherwise.
     */
    @Composable
    fun isValidDateFormat(date: String): Boolean {
        val dateRegex = Regex("""\d{1,2}/\d{1,2}/\d{4}""")
        return dateRegex.matches(date)
    }

    /**
     * Function to validate various input fields based on the provided conditions.

     * @param isForgetPasswordView Flag indicating if it's a forget password view.
     * @param isRegistrationView Flag indicating if it's a registration view.
     * @param isBiometricRegistrationView Flag indicating if it's a biometric registration view.
     * @param isBiometricFingerprintRegistrationView Flag indicating if it's a biometric fingerprint registration view.
     *
     * @return Triple containing a boolean indicating if the validation is successful,
     *         a string message indicating the result, and a pair of booleans indicating
     *         if there are errors in the username and password fields.
     */
    @Composable
    fun validateFields(
        username: String,
        firstName: String,
        lastName: String,
        email: String,
        mobileNumber: String,
        accountCode: String?,
        paymentType: String,
        bankName: String,
        branchName: String,
        amount: String,
        password: String,
        confirmPassword: String,
        birthday: String?,
        description: String,
        isForgetPasswordView: Boolean,
        isBankAndBranchVisible: Boolean,
        isRegistrationView: Boolean,
        isBiometricRegistrationView: Boolean,
        isBiometricFingerprintRegistrationView: Boolean,
        isPaymentView: Boolean
    ): Triple<Boolean, String, Pair<Boolean, Boolean>> {
        var isUsernameError = false
        var isFirstNameError = false
        var isLastNameError = false
        var isAccountCodeError = false
        var isMobileNumberError = false
        var isEmailError = false
        var isPasswordError = false
        var isConfirmPasswordError = false
        var isBirthdayError = false
        var isPaymentTypeError = false
        var isBankNameError = false
        var isBranchNameError = false
        var isAmountError = false
        var isDescriptionError = false

        if (username.isEmpty()) {
            isUsernameError = true
        }

        if (firstName.isEmpty()) {
            isFirstNameError = true
        }

        if (lastName.isEmpty()) {
            isLastNameError = true
        }

        if (accountCode.isNullOrEmpty()) {
            isAccountCodeError = true
        }
        if (paymentType.isEmpty()) {
            isPaymentTypeError = true
        }
        if (bankName.isEmpty()) {
            isBankNameError = true
        }
        if (branchName.isEmpty()) {
            isBranchNameError = true
        }
        if (amount.isEmpty()) {
            isAmountError = true
        }
        if (description.isEmpty()) {
            isDescriptionError = true
        }
        val phoneRegex = Regex("^(013|014|015|016|017|018|019)\\d{8}$")
        if (!phoneRegex.matches(mobileNumber)) {
            isMobileNumberError = true
        }

        val emailRegex = Regex("^\\S+@\\S+\\.\\S+\$")
        if (!emailRegex.matches(email)) {
            isEmailError = true
        }

        val passwordRegex =
            Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-_]).{8,}\$")
        if (!passwordRegex.matches(password)) {
            isPasswordError = true
        }

        if (password != confirmPassword) {
            isConfirmPasswordError = true
        }

        if (isForgetPasswordView) {
            if (birthday.isNullOrEmpty()) {
                isBirthdayError = true
            } else {
                // Validate date format and individual components
                val dateRegex = Regex("""\d{1,2}/\d{1,2}/\d{4}""")
                if (!dateRegex.matches(birthday)) {
                    isBirthdayError = true
                } else {
                    // Split the birthday into its components
                    val parts = birthday.split("/")
                    val month = parts[0].toIntOrNull()
                    val day = parts[1].toIntOrNull()
                    val year = parts[2].toIntOrNull()

                    // Validate the individual components of the date
                    isBirthdayError = month !in 1..12 || day !in 1..31
                }
            }
        }
        Log.d("value check", (!isUsernameError && !isFirstNameError && !isLastNameError && !isAccountCodeError && !isPaymentTypeError && !isBankNameError && !isBranchNameError
                && !isMobileNumberError && !isEmailError && !isPasswordError && !isConfirmPasswordError &&
                !isBirthdayError && !isAmountError && !isDescriptionError).toString()
        )
        // Check for the "Success" condition
        if (isBankAndBranchVisible){
            if (isAccountCodeError==false && isPaymentTypeError==false && isBankNameError==false && isBranchNameError==false && isAmountError==false && isDescriptionError==false){
                return Triple(true, "Success", Pair(isUsernameError, isPasswordError))
            }
        } else if(isPaymentView){
            if (isAccountCodeError==false && isPaymentTypeError==false && isAmountError==false && isDescriptionError==false){
                return Triple(true, "Success", Pair(isUsernameError, isPasswordError))
            }
        }
        if (!isUsernameError && !isFirstNameError && !isLastNameError && !isAccountCodeError && !isPaymentTypeError && !isBankNameError && !isBranchNameError
            && !isMobileNumberError && !isEmailError && !isPasswordError && !isConfirmPasswordError &&
            !isBirthdayError && !isAmountError && !isDescriptionError

        ) {

            return Triple(true, "Success", Pair(isUsernameError, isPasswordError))
        }

        // Handle error messages
        val errorMessage = when {
            isForgetPasswordView -> when {
                isUsernameError -> "Enter valid username"
                isBirthdayError -> {
                    if (birthday?.let { isValidDateFormat(it) } == true) {
                        "Enter date in MM/DD/YYYY format"
                    } else {
                        "Enter a valid date of birth"
                    }
                }

                isMobileNumberError -> "Enter valid phone number"
                isEmailError -> "Enter valid email"

                else -> "Your password is successfully changed. Please check your E-mail."
            }

            isRegistrationView -> when {
                isUsernameError -> "Enter valid username"
                isFirstNameError -> "Enter valid first name"
                isLastNameError -> "Enter valid last name"
                isEmailError -> "Enter valid email"
                isMobileNumberError -> "Enter valid phone number"
                isAccountCodeError -> "Enter valid account code"
                isPasswordError -> "Please create a valid password that is eight characters long, and includes one uppercase letter, one lowercase letter, one number, and one special character"
                confirmPassword.isEmpty() -> "Confirm Password field is empty, enter a valid password"
                isConfirmPasswordError -> "Password did not match"

                else -> "Account has been successfully registered. Please check your E-mail."
            }

            isBiometricRegistrationView -> when {
                isUsernameError -> "Enter valid username"
                isPasswordError -> "Please enter a valid password that is eight characters long, and includes one uppercase letter, one lowercase letter, one number, and one special character"

                else -> "Unknown error"
            }

            isBiometricRegistrationView -> when {
                isUsernameError -> "Enter valid username"
                isPasswordError -> "Please enter a valid password that is eight characters long, and includes one uppercase letter, one lowercase letter, one number, and one special character"

                else -> "Unknown error"
            }

            isBankAndBranchVisible && isPaymentView -> when {
                isAccountCodeError -> "Enter valid account code"
                isPaymentTypeError -> "Enter valid payment type"
                isBankNameError -> "Enter Your Bank Name"
                isBranchNameError -> "Enter Your Branch Name"
                isAmountError -> "Enter your amount"
                isDescriptionError -> "Enter your description"
                else -> "Unknown error"
            }

            !isBankAndBranchVisible && isPaymentView -> when {
                isAccountCodeError -> "Enter valid account code"
                isPaymentTypeError -> "Enter valid payment type"
                isAmountError -> "Enter your amount"
                isDescriptionError -> "Enter your description"
                else -> "Unknown error"
            }

            else -> "Unknown error"
        }

        return Triple(false, errorMessage, Pair(isUsernameError, isPasswordError))
    }
}

// Button(
// onClick = {
//    if (errorFlags.first || errorFlags.second) {
//        isValidationErrorDialogVisible = true
//    } else {
//        isValidationSuccess = true
//        runBlocking {
//            val promptInfo = BiometricPrompt.PromptInfo
//                .Builder()
//                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
//                .setTitle("Verify that it's you")
//                .setSubtitle("Use your fingerprint to continue")
//                .setNegativeButtonText("Cancel")
//                .build()
//
//            biometricPromptStepOne.authenticate(promptInfo)
//        }
//    }
// },
