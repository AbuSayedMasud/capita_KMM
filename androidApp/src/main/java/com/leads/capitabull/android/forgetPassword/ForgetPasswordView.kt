package com.leads.capitabull.android.forgetPassword

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.leads.capita.formatnumber.isWithinMaxCharLimit
import com.leads.capitabull.android.R
import com.leads.capitabull.android.formatnumber.InputFieldValidator.validateFields
import com.leads.capitabull.android.theme.PrimaryColor
import com.leads.capitabull.android.theme.White
import com.leads.capitabull.android.theme.getCardColors
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetPasswordView(navController: NavHostController) {
    // Mutable state variables to track user input
    var username by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // Validate the fields and get the result and error message
    val (isValidationSuccess, errorMessage) = validateFields(
        username,
        "",
        "",
        email,
        mobileNumber,
        "",
        "",
        "",
        birthday,

        isForgetPasswordView = true,
        isRegistrationView = false,
        isBiometricRegistrationView = false,
        isBiometricFingerprintRegistrationView = false,
    )

    var isUsernameError by remember { mutableStateOf(false) }
    var isMobileNumberError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isBirthdayError by remember {
        mutableStateOf(false)
    }

    var isValidationErrorDialogVisible by remember { mutableStateOf(false) }

    val isDarkTheme = isSystemInDarkTheme()
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val placeholderTextColor = if (isDarkTheme) Color(0x83F1F3F4) else Color.DarkGray
    val context = LocalContext.current
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    val (backgroundColor, contentColor) = getCardColors()
    val formattedDate by remember {
        derivedStateOf {
            val month = pickedDate.monthValue.toString().padStart(2, '0')
            val day = pickedDate.dayOfMonth.toString().padStart(2, '0')
            val year = pickedDate.year
            "$month/$day/$year"
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    // Composable for the date picker dialog
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = MaterialTheme.typography.button,
            ) {
                birthday = formattedDate
            }
            negativeButton(text = "Cancel")
        },

    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date of birth",
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = PrimaryColor,
                headerTextColor = Color.White,
                dateActiveBackgroundColor = textColor,
            ),

        ) {
            pickedDate = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 32.dp, end = 32.dp, bottom = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = username,
            onValueChange = {
                if (isWithinMaxCharLimit(it, 40)) {
                    username = it
                    isUsernameError = it.isEmpty()
                }
            },
            placeholder = { Text("Username", color = contentColor) },
            label = { Text("Username", color = contentColor) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = textColor,
                unfocusedLabelColor = textColor,
                unfocusedBorderColor = if (isUsernameError) Color.Red else textColor,
                focusedBorderColor = if (isUsernameError) Color.Red else textColor,
                focusedLabelColor = textColor,
                cursorColor = textColor,
                leadingIconColor = textColor,
                placeholderColor = placeholderTextColor,

            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Ascii,
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        TextField(
            value = birthday,
            onValueChange = {
                if (isWithinMaxCharLimit(it, 10)) {
                    birthday = it
                    isBirthdayError = it.isEmpty()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Date of Birth", color = contentColor) },
            placeholder = { Text("MM/DD/YYYY", color = contentColor) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = textColor,
                unfocusedLabelColor = textColor,
                unfocusedBorderColor = if (isBirthdayError) Color.Red else textColor,
                focusedBorderColor = if (isBirthdayError) Color.Red else textColor,
                focusedLabelColor = textColor,
                cursorColor = textColor,
                leadingIconColor = textColor,
                placeholderColor = placeholderTextColor,
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                    contentDescription = "Calendar Icon",
                    modifier = Modifier
                        .clickable {
                            dateDialogState.show()
                        }
                        .padding(8.dp),
                    tint = textColor,
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Ascii,
            ),
            singleLine = true,
        )

        TextField(
            value = mobileNumber,
            onValueChange = {
                if (isWithinMaxCharLimit(it, 11)) {
                    mobileNumber = it
                    isMobileNumberError = it.isEmpty()
                }
            },
            placeholder = { Text("880XXXXXXXXX", color = contentColor) },
            label = { Text("Mobile Number", color = contentColor) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = textColor,
                unfocusedLabelColor = textColor,
                unfocusedBorderColor = if (isMobileNumberError) Color.Red else textColor,
                focusedBorderColor = if (isMobileNumberError) Color.Red else textColor,
                focusedLabelColor = textColor,
                cursorColor = textColor,
                leadingIconColor = textColor,
                placeholderColor = placeholderTextColor,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

//        if (isFocused) {
//            Text(text = "+880", modifier = Modifier.padding(start = 4.dp))
//        }

        TextField(
            value = email,
            onValueChange = {
                if (isWithinMaxCharLimit(it, 40)) {
                    email = it
                    isEmailError = it.isEmpty()
                }
            },
            placeholder = { Text("examle@gmail.com", color = contentColor) },
            label = { Text("Email", color = contentColor) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = textColor,
                unfocusedLabelColor = textColor,
                unfocusedBorderColor = if (isEmailError) Color.Red else textColor,
                focusedBorderColor = if (isEmailError) Color.Red else textColor,
                focusedLabelColor = textColor,
                cursorColor = textColor,
                leadingIconColor = textColor,
                placeholderColor = placeholderTextColor,

            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Ascii,
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
// Button for submitting the form
        Button(
            onClick =
            {
                // Perform password reset logic based on validation success
                if (isValidationSuccess) {
                    val passwordBean = ForgetPasswordPresenter()
                    val isPasswordChanged = passwordBean.forgetPasswordPresenter(
                        context = context,
                        username = username,
                        dateOfBirth = birthday,
                        mobileNumber = mobileNumber,
                        email = email,
                    )
                    if (isPasswordChanged) {
                        isValidationErrorDialogVisible = true
                    }
                } else {
                    isValidationErrorDialogVisible = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),

            colors = ButtonDefaults.buttonColors(
                backgroundColor = PrimaryColor,
                contentColor = White,
            ),
            shape = RoundedCornerShape(20.dp),
        ) {
            Text(
                text = "Submit",
                fontSize = 16.sp,
                modifier = Modifier.padding(5.dp),
                color = Color.White,
            )
        }
    }

    // Display an alert dialog for validation errors or success message
    if (isValidationErrorDialogVisible) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                // Display success or error icon based on the result
                Image(
                    painter = if (errorMessage == "Your password is successfully changed. Please check your E-mail.") {
                        painterResource(id = R.drawable.success)
                    } else {
                        painterResource(id = R.drawable.high_importance_72)
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                )
            },
            text = {
                // Display the success or error message
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        if (errorMessage == "Success") {
                            "Your password is successfully changed.Please check your E-mail."
                        } else {
                            errorMessage
                        },
                        color = contentColor,
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1
                            .copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = contentColor,
                                textAlign = TextAlign.Justify,
                            ),
                    )
                }
            },
            confirmButton = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    // Button to close the dialog
                    Button(
                        onClick = {
                            if (errorMessage == "Your password is successfully changed. Please check your E-mail.") {
                                isValidationErrorDialogVisible = false
                                navController.navigate("login")
                            } else {
                                isValidationErrorDialogVisible = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = PrimaryColor,
                            contentColor = White,
                        ),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 12.dp),
                    ) {
                        Text("Ok")
                    }
                }
            },
            backgroundColor = backgroundColor,
//            modifier = Modifier.size(270.dp, 200.dp),
        )
    }
}
