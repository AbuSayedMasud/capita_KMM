package com.leads.capita.android.service.deposit

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.leads.capita.android.R
import com.leads.capita.android.theme.Gray
import com.leads.capita.android.theme.LightGray
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.theme.White
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.formatnumber.isWithinMaxCharLimit
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DepositView(navController: NavHostController) {
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
    val bankNameOptions =
        listOf("AB Bank", "Bangladesh Bank", "City Bank", "Citizen Bank", "FSIBL Bank")
    var bankNameExpanded by remember { mutableStateOf(false) }
    var bankNameSelectedOptionText by remember { mutableStateOf("Select Bank Name") }
    val branchNameOptions = listOf("Mirpur", "Motijheel", "Savar", "Baipail", "Chandra")
    var branchNameExpanded by remember { mutableStateOf(false) }
    var branchNameSelectedOptionText by remember { mutableStateOf("Select Branch Name") }
    var amount by remember { mutableStateOf("") }
    var transactionRef by remember { mutableStateOf("") }
    var account by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val isAccountCodeError by remember { mutableStateOf(false) }
    val isPaymentTypeError by remember { mutableStateOf(false) }
    val isBankNameError by remember { mutableStateOf(false) }
    val isBranchNameError by remember { mutableStateOf(false) }
    val isAmountError by remember { mutableStateOf(false) }
    val isAccountError by remember { mutableStateOf(false) }
    val isDescriptionError by remember { mutableStateOf(false) }

    val placeholderTextColor = if (isSystemInDarkTheme()) Color(0x83F1F3F4) else Color.DarkGray
    val showDialog = remember { mutableStateOf(false) }
    var optionData: String = ""
    var optionPaymentData: String = ""
    var optionBankData: String = ""
    var optionBranchData: String = ""
    optionData = if (selectedOptionText == "Select account code") {
        ""
    } else {
        selectedOptionText
    }
    optionPaymentData = if (paymentSelectedOptionText == "Select payment type") {
        ""
    } else {
        paymentSelectedOptionText
    }
    optionBankData = if (bankNameSelectedOptionText == "Select Bank Name") {
        ""
    } else {
        paymentSelectedOptionText
    }
    optionBranchData = if (branchNameSelectedOptionText == "Select Branch Name") {
        ""
    } else {
        paymentSelectedOptionText
    }
    var isBankAndBranchName by remember { mutableStateOf(false) }
    var isBirthdayError by remember {
        mutableStateOf(false)
    }
    var birthday by remember { mutableStateOf("") }
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
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
                dateActiveBackgroundColor = contentColor,
            ),

            ) {
            pickedDate = it
        }
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
            expanded = !expanded
        }) {
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
                expanded = expanded, onDismissRequest = {
                    expanded = false
                }, Modifier.background(backgroundColor)
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    }) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
        TextField(
            value = account,
            onValueChange = {
                if (isWithinMaxCharLimit(it, 40)) {
                    account = it
                }
            },
            placeholder = { Text("Enter Account", color = contentColor) },
            label = { Text("Account", color = contentColor) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Ascii,
            ),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = contentColor,
                unfocusedLabelColor = contentColor,
                focusedLabelColor = if (isAccountError) Color.Red else contentColor,
                unfocusedBorderColor = if (isAccountError) Color.Red else contentColor,
                focusedBorderColor = contentColor,
                cursorColor = contentColor,
                leadingIconColor = contentColor,
                placeholderColor = placeholderTextColor,

                ),
            modifier = Modifier.fillMaxWidth(),
        )
        ExposedDropdownMenuBox(expanded = paymentExpanded, onExpandedChange = {
            paymentExpanded = !paymentExpanded
        }) {
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
                expanded = paymentExpanded, onDismissRequest = {
                    paymentExpanded = false
                }, Modifier.background(backgroundColor)
            ) {
                paymentOptions.forEach { selectionOption ->
                    DropdownMenuItem(onClick = {
                        paymentSelectedOptionText = selectionOption
                        paymentExpanded = false
                    }) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
        if (paymentSelectedOptionText == "NPSB" || paymentSelectedOptionText == "BFTN" || paymentSelectedOptionText == "RTGS") {
            isBankAndBranchName = true
            ExposedDropdownMenuBox(expanded = bankNameExpanded, onExpandedChange = {
                bankNameExpanded = !bankNameExpanded
            }) {
                TextField(
                    readOnly = true,
                    value = bankNameSelectedOptionText,
                    onValueChange = {},
                    label = { Text(text = "Bank Name") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = bankNameExpanded
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Ascii,
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = contentColor,
                        unfocusedLabelColor = contentColor,
                        focusedLabelColor = if (isBankNameError) Color.Red else contentColor,
                        unfocusedBorderColor = if (isBankNameError) Color.Red else contentColor,
                        focusedBorderColor = contentColor,
                        cursorColor = contentColor,
                        leadingIconColor = contentColor,
                        placeholderColor = placeholderTextColor,

                        ),
                    modifier = Modifier.fillMaxWidth(),
                )
                ExposedDropdownMenu(
                    expanded = bankNameExpanded, onDismissRequest = {
                        bankNameExpanded = false
                    }, Modifier.background(backgroundColor)
                ) {
                    bankNameOptions.forEach { selectionOption ->
                        DropdownMenuItem(onClick = {
                            bankNameSelectedOptionText = selectionOption
                            bankNameExpanded = false
                        }) {
                            Text(text = selectionOption)
                        }
                    }
                }
            }
            ExposedDropdownMenuBox(expanded = branchNameExpanded, onExpandedChange = {
                branchNameExpanded = !branchNameExpanded
            }) {
                TextField(
                    readOnly = true,
                    value = branchNameSelectedOptionText,
                    onValueChange = {},
                    label = { Text(text = "Bank Name") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = bankNameExpanded
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Ascii,
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = contentColor,
                        unfocusedLabelColor = contentColor,
                        focusedLabelColor = if (isBranchNameError) Color.Red else contentColor,
                        unfocusedBorderColor = if (isBranchNameError) Color.Red else contentColor,
                        focusedBorderColor = contentColor,
                        cursorColor = contentColor,
                        leadingIconColor = contentColor,
                        placeholderColor = placeholderTextColor,

                        ),
                    modifier = Modifier.fillMaxWidth(),
                )
                ExposedDropdownMenu(
                    expanded = branchNameExpanded, onDismissRequest = {
                        bankNameExpanded = false
                    }, Modifier.background(backgroundColor)
                ) {
                    branchNameOptions.forEach { selectionOption ->
                        DropdownMenuItem(onClick = {
                            branchNameSelectedOptionText = selectionOption
                            branchNameExpanded = false
                        }) {
                            Text(text = selectionOption)
                        }
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
                keyboardType = KeyboardType.Number,
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
                textColor = contentColor,
                unfocusedLabelColor = contentColor,
                unfocusedBorderColor = if (isBirthdayError) Color.Red else contentColor,
                focusedBorderColor = if (isBirthdayError) Color.Red else contentColor,
                focusedLabelColor = contentColor,
                cursorColor = contentColor,
                leadingIconColor = contentColor,
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
                    tint = PrimaryColor,
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Ascii,
            ),
            singleLine = true,
        )
        TextField(
            value = transactionRef,
            onValueChange = {
                if (isWithinMaxCharLimit(it, 40)) {
                    transactionRef = it
                }
            },
            placeholder = { Text("Enter Transaction Reference", color = contentColor) },
            label = { Text("Transaction Ref", color = contentColor) },
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
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .dashedBorder(1.dp, PrimaryColor, 4.dp),

            contentAlignment = Alignment.Center

        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (imageUri != null) {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(4.dp)
                            .height(250.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.FillBounds,
                        filterQuality= FilterQuality.High
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.uploadpic),
                        contentDescription = "upload",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(16.dp)
                    )
                }

                Text(
                    text = "Let’s upload receipt",
                    color = contentColor,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
                Text(
                    text = "Upload your receipt for your deposit \n" +
                            "request from your gallery or open camera",
                    color = if (isSystemInDarkTheme()) LightGray else Gray,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp, top = 4.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(
                        bottom = 16.dp,
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
                ) {
                    Button(
                        onClick = {
                            launcher.launch("image/*")
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 2.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = PrimaryColor,
                            contentColor = White,
                        ),
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.image_24px),
                            contentDescription = "gallery"
                        )
                        Text(
                            text = "Gallery",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(5.dp),
                            color = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 2.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = PrimaryColor,
                            contentColor = White,
                        ),
                        shape = RoundedCornerShape(20.dp),
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.photo_camera_24px),
                            contentDescription = "camera"
                        )
                        Text(
                            text = "Camera",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(5.dp),
                            color = Color.White,
                        )
                    }
                }

            }

        }
        Button(
            onClick = {

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
}

fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)