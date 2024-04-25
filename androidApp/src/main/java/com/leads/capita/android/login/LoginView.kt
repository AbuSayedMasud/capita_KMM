package com.leads.capita.android.login

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.leads.capita.formatnumber.isWithinMaxCharLimit

import com.leads.capita.android.activity.HomeActivity
import com.leads.capita.android.biometricRegistration.BioMetricPrompt
import com.leads.capita.android.sharePreference.PreferencesManager
import com.leads.capita.android.snackbar.CustomSnackbarVisuals
import com.leads.capita.android.theme.AppTheme
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.Orientation
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.theme.White
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.theme.rememberWindowSizeClass
import com.leads.capita.android.R
import com.leads.capita.android.mockJsonLoader.MockLoader
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginView(navController: NavHostController, preferencesManager: PreferencesManager) {
    var username by remember { mutableStateOf(preferencesManager.getKey("username", "")) }
    var password by remember { mutableStateOf(preferencesManager.getKey("password", "")) }
    var isForgetClicked by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(username.isNotEmpty() && password.isNotEmpty()) }
    var passwordVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val placeholderTextColor = if (isSystemInDarkTheme()) Color(0x83F1F3F4) else Color.DarkGray
    val (backgroundColor, contentColor) = getCardColors()
    val fingerButton = if (isSystemInDarkTheme()) Color(0xFF283138) else Color.White
    val biometricPrompt = BioMetricPrompt().bioMetricAuthentication()
    val preferencesManager = PreferencesManager(context)
    val getpreferenceData = preferencesManager.getKey("RegistrationKey", "default_value")
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val snackbarHostState = remember { SnackbarHostState() }

    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
        if (AppTheme.orientation == Orientation.Portrait) {
//            Scaffold(
//                scaffoldState = scaffoldState,
//                snackbarHost = {
//                    SnackbarHost(it) { data ->
//                        Snackbar(
//                            actionColor = Color.White,
//                            snackbarData = data,
//                        )
//                    }
//                },
//                backgroundColor = if (isSystemInDarkTheme()) BackgroundColor else Color.White,
//            ) {
            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState,
                        snackbar = { snackbarData: SnackbarData ->
                            val customVisuals = snackbarData.visuals as? CustomSnackbarVisuals
                            if (customVisuals != null) {
                                Snackbar(
                                    snackbarData = snackbarData,
                                    contentColor = customVisuals.contentColor,
                                    containerColor = customVisuals.containerColor,
                                )
                            } else {
                                Snackbar(snackbarData = snackbarData)
                            }
                        },
                    )
                },
                backgroundColor = if (isSystemInDarkTheme()) BackgroundColor else Color.White,
            ) {
                Column(
                    modifier = Modifier
//                    .padding(34.dp)
                        .padding(
                            top = AppTheme.dimens.mediumLarge,
                            start = AppTheme.dimens.large,
                            end = AppTheme.dimens.large,
                        )
                        .fillMaxWidth()
                        .background(if (isSystemInDarkTheme()) BackgroundColor else Color.White),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "Capita",
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(5.dp)
                                .offset(y = (20).dp),
                            color = PrimaryColor,
                        )
                    }

                    Spacer(modifier = Modifier.height(AppTheme.dimens.spacer1))

//                    Surface(
//                        shape = RoundedCornerShape(4.dp),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 16.dp),
//                        color = if (isSystemInDarkTheme()) BackgroundColor else Color.White,
//                    ) {
                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            if (isWithinMaxCharLimit(it, 40)) {
                                username = it
                            }
                        },
                        label = { Text("Username", color = contentColor, fontSize = 16.sp) },
                        placeholder = {
                            Text(
                                "Enter your username", color = contentColor, fontSize = 14.sp
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = contentColor,
                            unfocusedLabelColor = contentColor,
                            focusedLabelColor = contentColor,
                            unfocusedBorderColor = contentColor,
                            focusedBorderColor = contentColor,
                            cursorColor = contentColor,
                            leadingIconColor = contentColor,
                            placeholderColor = contentColor,
                        ),
                        leadingIcon = {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Username Icon",
                            )
                        },
                        trailingIcon = {
                            if (username.isNotEmpty()) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear Icon",
                                    modifier = Modifier.clickable { username = "" },
                                    tint = contentColor,
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Ascii,
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),

                        )
                    Spacer(modifier = Modifier.height(15.dp))

//                    Surface(
//                        shape = RoundedCornerShape(4.dp),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 16.dp),
//                        color = if (isSystemInDarkTheme()) BackgroundColor else Color.White,
//                    ) {
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            if (isWithinMaxCharLimit(it, 20)) {
                                password = it
                            }
                        },
                        label = { Text("Password", color = contentColor, fontSize = 16.sp) },
                        placeholder = {
                            Text(
                                "Enter your password", color = contentColor, fontSize = 16.sp
                            )
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = contentColor,
                            unfocusedLabelColor = contentColor,
                            unfocusedBorderColor = contentColor,
                            focusedBorderColor = contentColor,
                            focusedLabelColor = contentColor,
                            cursorColor = contentColor,
                            leadingIconColor = contentColor,
                            placeholderColor = contentColor,
                        ),
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = "Password Icon",
                            )
                        },
                        trailingIcon = {
                            val visibilityIcon =
                                if (passwordVisible) R.drawable.baseline_visibility_off_24 else R.drawable.baseline_visibility_24
                            val visibilityIconContentDescription =
                                if (passwordVisible) "Hide password" else "Show password"
                            Icon(
                                painter = painterResource(id = visibilityIcon),
                                contentDescription = visibilityIconContentDescription,
                                modifier = Modifier.clickable {
                                    passwordVisible = !passwordVisible
                                },
                                tint = contentColor,
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Ascii,
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { isChecked ->
                                checked = isChecked
                                if (!isChecked) {
                                    preferencesManager.removeKey("username")
                                    preferencesManager.removeKey("password")
                                    username = ""
                                    password = ""
                                }
                            },
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = contentColor,
                                checkedColor = contentColor,
                            ),
                            modifier = Modifier.offset(x = (-10).dp),
                        )
                        Text(
                            text = "Remember me",
                            color = contentColor,
                            modifier = Modifier.offset(x = (-16).dp),
                        )

                        DisposableEffect(Unit) {
                            onDispose {
                                if (checked) {
                                    preferencesManager.saveKey("username", username)
                                    preferencesManager.saveKey("password", password)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        val hyperlinkStyle = TextStyle(
                            color = PrimaryColor,
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.Underline,
                        )

                        ClickableText(
                            text = AnnotatedString("Forget Password?"),
                            onClick = {
                                navController.navigate("forgetPassword")
                            },
                            style = hyperlinkStyle,

                            )
                    }

                    Button(
                        onClick = {
                            val loginBean = LoginPresenter()
                            // Perform login authentication
                            val authResult = loginBean.login(context, username, password)
                            // Handle the authentication result
                            when (authResult) {
                                AuthResult.Success -> {
                                    val intent = Intent(context, HomeActivity::class.java)
                                    val service = MockLoader(context)
                            // Initialize the MockLoader service
                                    runBlocking {
                                        service.init()
                                    }
                            // Start the HomeActivity
                                    context.startActivity(intent)
                                }
                            // If the username is invalid, show a snackbar with an error message
                                is AuthResult.Invalid -> {
                                    val errorMessage = authResult.errorMessage
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            CustomSnackbarVisuals(
                                                message = errorMessage.toString(),
                                                contentColor = Color.White,
                                            ),
                                        )
                                    }
                            // Hide the keyboard
                                    keyboardController?.hide()
                                }
                            // Handle other authentication results if needed
                                else -> {}
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = AppTheme.dimens.large),
//                            .keyboardController(keyboardController),

                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = PrimaryColor,
                            contentColor = White,
                        ),
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(3.dp),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, end = 16.dp, start = 16.dp, bottom = 4.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "I don't have an account? ",
                            style = MaterialTheme.typography.body1,
                            color = contentColor,

                            )
                        ClickableText(
                            text = AnnotatedString("Sign Up"),
                            style = MaterialTheme.typography.body1.copy(
                                textDecoration = TextDecoration.Underline,
                                color = PrimaryColor,
                            ),

                            onClick = {
                                navController.navigate("registration")
                            },
                        )
                    }

                    Spacer(modifier = Modifier.height(AppTheme.dimens.spacer2))
                    val painter = if (isSystemInDarkTheme()) {
                        painterResource(id = R.drawable.fingerprint_dark)
                    } else {
                        painterResource(id = R.drawable.fingerprint_light)
                    }
                    Image(
                        painter = painter,
                        contentDescription = "Biometric Icon",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(80.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,

                                ) {
                                Log.d("preference Data0ne", getpreferenceData)
                                if (getpreferenceData == "okay") {
                                    BioMetricPrompt().showBiometricPrompt(biometricPrompt)
                                } else {
                                    Toast
                                        .makeText(
                                            context,
                                            "Please Register the Finger Print ",
                                            Toast.LENGTH_SHORT,
                                        )
                                        .show()
                                }
                            }
                            .clip(shape = RoundedCornerShape(8.dp))
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 12.dp, bottom = 8.dp),

                        )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, end = 16.dp, start = 16.dp, bottom = 5.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Don't have TouchID yet? ",
                            style = MaterialTheme.typography.body1,
                            color = contentColor,

                            )
                        ClickableText(
                            text = AnnotatedString("TouchID"),
                            style = MaterialTheme.typography.body1.copy(
                                textDecoration = TextDecoration.Underline,
                                color = PrimaryColor,
                            ),

                            onClick = {
                                navController.navigate("biometricRegistration")
                            },
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = (-10).dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.shanta_logo),
                            contentDescription = "Login Image",
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(8.dp))
                                .size(78.dp),
//                            .offset(y = (16).dp)
//                            .padding(bottom = 5.dp, start = 5.dp, top = 5.dp, end = 0.dp),
                        )
                        Text(
                            text = "SHANTA",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor,
//                        modifier = Modifier
//                            .padding(bottom = 5.dp, start = 0.dp, top = 5.dp, end = 5.dp)
//                            .offset(y = (16).dp),
                        )
                    }
                }
            }
        }
    }
}