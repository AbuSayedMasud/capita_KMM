package com.leads.capitabull.android.profile

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.leads.capitabull.android.R
import com.leads.capitabull.android.activity.MainActivity
import com.leads.capitabull.android.theme.CapitaTheme
import com.leads.capitabull.android.theme.PrimaryColor
import com.leads.capitabull.android.theme.White
import com.leads.capitabull.android.theme.getCardColors
import com.leads.capitabull.android.theme.rememberWindowSizeClass

@Composable
fun Logout(navController: NavHostController){val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f
    val (backgroundColor, contentColor) = getCardColors()
    var isLogoutClick by remember { mutableStateOf(false) }
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val window = rememberWindowSizeClass()
    val imageSize = (screenWidth * 0.09f).coerceAtMost(52.dp)
    CapitaTheme(window) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
                .clickable() {
                    isLogoutClick = true

                },
            elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
            shape = MaterialTheme.shapes.large,
            backgroundColor = backgroundColor,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Column {
                    Text(
                        text = "Logout",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            color = contentColor
                        ),
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Icon(
                        painter = painterResource(R.drawable.logout_24px),
                        contentDescription = null,
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Black

                    )
                }

            }
            if (isLogoutClick) {
                AlertDialog(
                    properties = DialogProperties(
                        dismissOnBackPress = false,
                        dismissOnClickOutside = false,
                    ),
                    onDismissRequest = {
                        // Handle dismiss request if needed
                    },
                    title = {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.high_importance_72),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp),
                                tint = Color.Yellow
                            )
//                            Text(
//                                "Confirmation",
//                                style = MaterialTheme.typography.body1
//                                    .copy(
//                                        fontWeight = FontWeight.Bold,
//                                        fontSize = 16.sp,
//                                        color = contentColor,
//                                        textAlign = TextAlign.Center,
//                                    ),
//                                modifier = Modifier.padding(bottom = 8.dp)
//                            )
                        }
                    },
                    text = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center,

                            ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {

                                Text(
                                    "Are you sure you want to logout",
                                    style = MaterialTheme.typography.body1
                                        .copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp,
                                            color = contentColor,
                                            textAlign = TextAlign.Center,
                                        ),
                                )
                            }

                        }
                    },
                    buttons = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp), // Added vertical padding
                            horizontalArrangement = Arrangement.SpaceEvenly, // Adjust the arrangement as needed
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                    val intent = Intent(context, MainActivity::class.java)
                                    context.startActivity(intent)
                                    isLogoutClick = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = PrimaryColor,
                                    contentColor = White,
                                ),
                                modifier = Modifier
                                    .weight(1f) // Set weights to distribute space
                                    .padding(horizontal = 8.dp) // Adjust horizontal padding
                            ) {
                                Text("Okay")
                            }

                            Button(
                                onClick = {
                                    isLogoutClick = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = PrimaryColor,
                                    contentColor = White,
                                ),
                                modifier = Modifier
                                    .weight(1f) // Set weights to distribute space
                                    .padding(horizontal = 8.dp) // Adjust horizontal padding
                            ) {
                                Text("Cancel")
                            }
                        }
                    },
                    backgroundColor = backgroundColor,
                )
            }

        }
    }
}

