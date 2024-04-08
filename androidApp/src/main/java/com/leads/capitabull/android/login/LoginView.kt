package com.leads.capitabull.android.login

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.leads.capita.ui.theme.AppTheme
import com.leads.capita.ui.theme.BackgroundColor
import com.leads.capita.ui.theme.Black
import com.leads.capita.ui.theme.CapitaTheme
import com.leads.capita.ui.theme.Orientation
import com.leads.capita.ui.theme.PrimaryColor
import com.leads.capita.ui.theme.White
import com.leads.capita.ui.theme.getCardColors
import com.leads.capita.ui.theme.rememberWindowSizeClass
import com.leads.capitabull.android.R
import com.leads.capitabull.android.biometricRegistration.BioMetricPrompt
import com.leads.capitabull.android.sharePreference.PreferencesManager


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginView(navController: NavHostController, preferencesManager: PreferencesManager) {
    var username by remember { mutableStateOf(preferencesManager.getKey("username", "")) }
    var password by remember { mutableStateOf(preferencesManager.getKey("password", "")) }
    var isForgetClicked by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(username.isNotEmpty() && password.isNotEmpty()) }
    var passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val placeholderTextColor = if (isSystemInDarkTheme()) Color(0x83F1F3F4) else Color.DarkGray
    val textColor = if (isSystemInDarkTheme()) White else Black
    val (backgroundColor, contentColor) = getCardColors()
    val fingerButton = if (isSystemInDarkTheme()) Color(0xFF283138) else Color.White
    val biometricPrompt = BioMetricPrompt().bioMetricAuthentication()
    val preferencesManager = PreferencesManager(context)
    val getpreferenceData = preferencesManager.getKey("RegistrationKey", "default_value")
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
        if (AppTheme.orientation == Orientation.Portrait) {
            Scaffold(
                scaffoldState = scaffoldState,
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

                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        color = if (isSystemInDarkTheme()) BackgroundColor else Color.White,
                    ) {
                        OutlinedTextField(
                            value = username,
                            onValueChange = {
                                if (isWithinMaxCharLimit(it, 40)) {
                                    username = it
                                }
                            },
                            label = { Text("Username") },
                            placeholder = { Text("Enter your username") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = textColor,
                                unfocusedLabelColor = textColor,
                                unfocusedBorderColor = textColor,
                                focusedBorderColor = textColor,
                                focusedLabelColor = textColor,
                                cursorColor = textColor,
                                leadingIconColor = textColor,
                                placeholderColor = placeholderTextColor,
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
                                        tint = textColor,
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Ascii,
                            ),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),

                            )
                    }
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        color = if (isSystemInDarkTheme()) BackgroundColor else Color.White,
                    ) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                if (isWithinMaxCharLimit(it, 20)) {
                                    password = it
                                }
                            },
                            label = { Text("Password") },
                            placeholder = { Text("Enter your password") },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = textColor,
                                unfocusedLabelColor = textColor,
                                unfocusedBorderColor = textColor,
                                focusedBorderColor = textColor,
                                focusedLabelColor = textColor,
                                cursorColor = textColor,
                                leadingIconColor = textColor,
                                placeholderColor = placeholderTextColor,
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
                                    tint = textColor,
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Ascii,
                            ),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
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
                                uncheckedColor = textColor,
                                checkedColor = textColor,
                            ),
                            modifier = Modifier.offset(x = (-10).dp),
                        )
                        Text(
                            text = "Remember me",
                            color = textColor,
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
                            fontSize = 15.sp,
                            textDecoration = TextDecoration.Underline,

                            )

                        ClickableText(
                            text = AnnotatedString("Forget Password?"),
                            onClick = {
                                navController.navigate("forgetPassword")
                            },
                            style = hyperlinkStyle,
                            modifier = Modifier.offset(y = (2).dp),
                        )
                    }

                    Button(
                        onClick = {
//                            val loginBean = LoginPresenter()
//                            val identityResponse = loginBean.login(context, username, password)
//                            if (identityResponse) {
//                                val intent = Intent(context, HomeActivity::class.java)
//                                val service = MockLoader(context)
//                                runBlocking {
//                                    service.init()
//                                }
//                                context.startActivity(intent)
//                            } else {
//                                coroutineScope.launch {
//                                    scaffoldState.snackbarHostState.showSnackbar("Login failed")
//                                }
//                                keyboardController?.hide()
//                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = AppTheme.dimens.large),
//                            .keyboardController(keyboardController),

                        colors = ButtonDefaults.buttonColors(
                            PrimaryColor,
                            contentColor = White,
                        ),
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(5.dp),
                            color = Color.White,
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
                            color = textColor,

                            )
                        ClickableText(
                            text = AnnotatedString("Sign Up"),
                            style = MaterialTheme.typography.body1
                                .copy(
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
                            color = textColor,

                            )
                        ClickableText(
                            text = AnnotatedString("TouchID"),
                            style = MaterialTheme.typography.body1
                                .copy(
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
