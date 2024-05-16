package com.leads.capita.android.service.deposit

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.leads.capita.android.R
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.android.theme.CardColor
import com.leads.capita.android.theme.Gray
import com.leads.capita.android.theme.LightGray
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.theme.White
import com.leads.capita.android.theme.getCardColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DepositStatusView(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val placeholderTextColor = if (isSystemInDarkTheme()) Color(0x83F1F3F4) else Color.DarkGray
    // Define sizes based on screen size
    val imageSize = (screenWidth * 0.09f).coerceAtMost(52.dp)
    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f // Increase text space on large screens
    val valueColumnWeight =
        if (screenWidth > 600.dp) 2f else 1f // Increase value space on large screens
    val textSize = if (screenWidth > 600.dp) 14.sp else 12.sp
    var searchText by remember { mutableStateOf("") }
    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val options = listOf("Add", "Edit", "Delete")
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Action") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                navController.navigate("deposit")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),

            colors = ButtonDefaults.buttonColors(
                backgroundColor = PrimaryColor,
                contentColor = White,
            ),
            shape = RoundedCornerShape(20.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "add"
            )
            Text(
                text = "New Request",
                fontSize = 16.sp,
                modifier = Modifier.padding(5.dp),
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Surface(
                modifier = Modifier.weight(2f),
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp),
                elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,

                ) {
                Column(
                    modifier = Modifier.padding(start = 10.dp, end = 4.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search), // Use your own search icon here
                            contentDescription = "Search Name",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        TextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            modifier = Modifier
                                .weight(1f)
                                .background(backgroundColor),
                            placeholder = { Text("Search Name") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = contentColor,
                                unfocusedLabelColor = contentColor,
                                focusedLabelColor = contentColor,
                                unfocusedBorderColor = backgroundColor,
                                focusedBorderColor = backgroundColor,
                                cursorColor = contentColor,
                                leadingIconColor = contentColor,
                                placeholderColor = placeholderTextColor,

                                ),
                            keyboardActions = KeyboardActions(onSearch = {
                                // Handle search action here
                            })
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Surface(
                modifier = Modifier.weight(1.2f),
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp),
                elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
            ) {
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
                    expanded = !expanded
                }) {
                    TextField(
                        readOnly = true,
                        value = selectedOption,
                        onValueChange = { },
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
                            unfocusedLabelColor = backgroundColor,
                            focusedLabelColor = backgroundColor,
                            unfocusedBorderColor = backgroundColor,
                            focusedBorderColor = backgroundColor,
                            cursorColor = backgroundColor,
                            leadingIconColor = contentColor,
                            placeholderColor = contentColor,

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
                                selectedOption = selectionOption
                                expanded = false
                            }) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Surface(
                modifier = Modifier.weight(2f),
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp
                    ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Account Code #12321",
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = contentColor,
                                ),
                            )

                        }
                        Column(
                            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End
                        ) {
                            Card(
                                backgroundColor = if (isSystemInDarkTheme()) BackgroundColor else Color(
                                    0xffecfbf5
                                ),
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = "Approved", style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color(0xff32c1b6),
                                        textAlign = TextAlign.Center
                                    ), modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                                )
                            }
                        }

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                        ) {
                            Row {
                                Text(
                                    text = "05 Mar 2024",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        color = if (isSystemInDarkTheme()) LightGray else Gray,
                                    ),
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Cash",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        color = if (isSystemInDarkTheme()) LightGray else Gray,
                                    ),
                                )
                            }


                        }
                        Column(
                            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = "+ 3,00,000.00", style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = contentColor,
                                    textAlign = TextAlign.Center
                                ), modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            )
                        }
                    }
                }

            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Surface(
                modifier = Modifier.weight(2f),
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp
                    ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Account Code #19921",
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = contentColor,
                                ),
                            )

                        }
                        Column(
                            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End
                        ) {
                            Card(
                                backgroundColor = if (isSystemInDarkTheme()) BackgroundColor else Color(
                                    0xffecfbf5
                                ),
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = "Pending", style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color(0xff32c1b6),
                                        textAlign = TextAlign.Center
                                    ), modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                                )
                            }
                        }

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                        ) {
                            Row {
                                Text(
                                    text = "12 May 2024",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        color = if (isSystemInDarkTheme()) LightGray else Gray,
                                    ),
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "NPSB",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        color = if (isSystemInDarkTheme()) LightGray else Gray,
                                    ),
                                )
                            }


                        }
                        Column(
                            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = "+ 5,00,000.00", style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = contentColor,
                                    textAlign = TextAlign.Center
                                ), modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            )
                        }
                    }
                }

            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Surface(
                modifier = Modifier.weight(2f),
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp
                    ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Account Code #10321",
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = contentColor,
                                ),
                            )

                        }
                        Column(
                            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End
                        ) {
                            Card(
                                backgroundColor = if (isSystemInDarkTheme()) BackgroundColor else Color(
                                    0xfffaeaeb
                                ),
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = "Declined", style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color(0xfffc6565),
                                        textAlign = TextAlign.Center
                                    ), modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                                )
                            }
                        }

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                        ) {
                            Row {
                                Text(
                                    text = "14 May 2024",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        color = if (isSystemInDarkTheme()) LightGray else Gray,
                                    ),
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "BFTN",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        color = if (isSystemInDarkTheme()) LightGray else Gray,
                                    ),
                                )
                            }


                        }
                        Column(
                            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = "+ 13,00,000.00",
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = contentColor,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            )
                        }
                    }
                }

            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Surface(
                modifier = Modifier.weight(2f),
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp
                    ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Account Code #19082",
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = contentColor,
                                ),
                            )

                        }
                        Column(
                            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End
                        ) {
                            Card(
                                backgroundColor = if (isSystemInDarkTheme()) BackgroundColor else Color(
                                    0xffecfbf5
                                ),
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = "Completed", style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color(0xff32c1b6),
                                        textAlign = TextAlign.Center
                                    ), modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                                )
                            }
                        }

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                        ) {
                            Row {
                                Text(
                                    text = "05 May 2024",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        color = if (isSystemInDarkTheme()) LightGray else Gray,
                                    ),
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "RTGS",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        color = if (isSystemInDarkTheme()) LightGray else Gray,
                                    ),
                                )
                            }


                        }
                        Column(
                            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = "+ 1,43,000.00", style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = contentColor,
                                    textAlign = TextAlign.Center
                                ), modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            )
                        }
                    }
                }

            }

        }
    }

}



