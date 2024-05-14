package com.leads.capita.android.service.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.leads.capita.android.customAlertDialog.CustomAlertDialog
import com.leads.capita.android.formatnumber.InputFieldValidator.validateFields
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.theme.White
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.formatnumber.isWithinMaxCharLimit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaymentView(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // Define sizes based on screen size
    val imageSize = (screenWidth * 0.09f).coerceAtMost(52.dp)
    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f // Increase text space on large screens
    val valueColumnWeight =
        if (screenWidth > 600.dp) 2f else 1f // Increase value space on large screens
    val textSize = if (screenWidth > 600.dp) 14.sp else 12.sp

    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val options = listOf("1990", "4760", "2150", "9870", "9730")

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("Select account code") }

    val paymentOptions = listOf("Cash", "Cheque", "BFTN", "NPSB", "RTGS")
    var paymentExpanded by remember { mutableStateOf(false) }
    var paymentSelectedOptionText by remember { mutableStateOf("Select payment type") }
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isAccountCodeError by remember { mutableStateOf(false) }
    var isPaymentTypeError by remember { mutableStateOf(false) }
    var isAmountError by remember { mutableStateOf(false) }
    var isDescriptionError by remember { mutableStateOf(false) }

    val placeholderTextColor = if (isSystemInDarkTheme()) Color(0x83F1F3F4) else Color.DarkGray
    var showDialog = remember { mutableStateOf(false) }
    var optionData: String = ""
    var optionPaymentData: String = ""
    if (selectedOptionText == "Select account code") {
        optionData = ""
    } else {
        optionData = selectedOptionText
    }
    if (paymentSelectedOptionText == "Select payment type") {
        optionPaymentData = ""
    } else {
        optionPaymentData = paymentSelectedOptionText
    }
    val (isValidationSuccess, errorMessage) = validateFields(
        "",
        "",
        "",
        "",
        "",
        optionData,
        optionPaymentData,
        amount,
        "",
        "",
        "",
        description,
        isForgetPasswordView = false,
        isRegistrationView = false,
        isBiometricRegistrationView = false,
        isBiometricFingerprintRegistrationView = false,
        isPaymentView = true
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 32.dp, end = 32.dp, bottom = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text(text = "Account Code") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Ascii,
                ),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = contentColor,
                    unfocusedLabelColor = contentColor,
                    focusedLabelColor = if (isAccountCodeError) Color.Red else contentColor,
                    unfocusedBorderColor = if (isAccountCodeError) Color.Red else contentColor,
                    focusedBorderColor = contentColor,
                    cursorColor = contentColor,
                    leadingIconColor = contentColor,
                    placeholderColor = placeholderTextColor,

                    ),
                modifier = Modifier.fillMaxWidth(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }, Modifier.background(backgroundColor)
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = paymentExpanded,
            onExpandedChange = {
                paymentExpanded = !paymentExpanded
            }
        ) {
            TextField(
                readOnly = true,
                value = paymentSelectedOptionText,
                onValueChange = { },
                label = { Text(text = "Payment Type") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = paymentExpanded
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Ascii,
                ),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = contentColor,
                    unfocusedLabelColor = contentColor,
                    focusedLabelColor = if (isPaymentTypeError) Color.Red else contentColor,
                    unfocusedBorderColor = if (isPaymentTypeError) Color.Red else contentColor,
                    focusedBorderColor = contentColor,
                    cursorColor = contentColor,
                    leadingIconColor = contentColor,
                    placeholderColor = placeholderTextColor,

                    ),
                modifier = Modifier.fillMaxWidth(),
            )
            ExposedDropdownMenu(
                expanded = paymentExpanded,
                onDismissRequest = {
                    paymentExpanded = false
                }, Modifier.background(backgroundColor)
            ) {
                paymentOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            paymentSelectedOptionText = selectionOption
                            paymentExpanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
        TextField(
            value = amount,
            onValueChange = {
                if (isWithinMaxCharLimit(it, 40)) {
                    amount = it
                }
            },
            placeholder = { Text("Enter amount", color = contentColor) },
            label = { Text("Amount", color = contentColor) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Ascii,
            ),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = contentColor,
                unfocusedLabelColor = contentColor,
                focusedLabelColor = if (isAmountError) Color.Red else contentColor,
                unfocusedBorderColor = if (isAmountError) Color.Red else contentColor,
                focusedBorderColor = contentColor,
                cursorColor = contentColor,
                leadingIconColor = contentColor,
                placeholderColor = placeholderTextColor,

                ),
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = description,
            onValueChange = {
                if (isWithinMaxCharLimit(it, 40)) {
                    description = it
                }
            },
            placeholder = { Text("Write Description", color = contentColor) },
            label = { Text("Description", color = contentColor) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Ascii,
            ),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = contentColor,
                unfocusedLabelColor = contentColor,
                focusedLabelColor = if (isDescriptionError) Color.Red else contentColor,
                unfocusedBorderColor = if (isDescriptionError) Color.Red else contentColor,
                focusedBorderColor = contentColor,
                cursorColor = contentColor,
                leadingIconColor = contentColor,
                placeholderColor = placeholderTextColor,

                ),
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = {
                if (isValidationSuccess) {

                } else {
                    showDialog.value = true
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
    if (showDialog.value) {
        CustomAlertDialog(message = errorMessage, isSuccess = false, dismissState = showDialog)
    }
}