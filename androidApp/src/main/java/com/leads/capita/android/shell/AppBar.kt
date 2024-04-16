package com.leads.capita.android.shell

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.R

@Composable
fun MyAppBar(
    navController: NavController,
    context: Context,
    title: String,
    onSearch: (String) -> Unit,
    showSearchBar: Boolean = false,
    onProfileClick: (() -> Unit)?,
    profilePhoto: Painter?,
    showNotificationIcon: Boolean = false,
    showSearchIcon: Boolean = false,
    showSettingsIcon: Boolean = false,
    currentRoute: MutableState<String?>?,
    showArrow: Boolean = false,
) {
    val myCustomColor = PrimaryColor

    var searchText by remember { mutableStateOf("") }
    val isSearching by remember { mutableStateOf(showSearchBar) }
    val isActionSearch by remember { mutableStateOf(showSearchBar) }

    TopAppBar(
        title = {
            // triggers if the search button is clicked
            if (isSearching || isActionSearch) {
                TextField(
                    value = searchText,
                    onValueChange = { newText ->
                        searchText = newText
                        onSearch(newText)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = (-8).dp),
                    placeholder = {
                        Text(
                            text = "Search",
                            color = Color.White,
                        )
                    },
                    leadingIcon = {
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = null,
                                tint = Color.White,
                            )
                        }
                    },
                    trailingIcon = {
                        // clears all texts if close icon is clicked when searchbar is not empty
                        if (searchText.isNotEmpty()) {
                            IconButton(onClick = { searchText = "" }) {
                                Icon(
                                    Icons.Filled.Close,
                                    contentDescription = null,
                                    tint = Color.White,
                                )
                            }
                        } else {
                            IconButton(onClick = {
                            }) {
                                Icon(
                                    Icons.Filled.Close,
                                    contentDescription = null,
                                    tint = Color.White,
                                )
                            }
                        }
                    },
                    textStyle = TextStyle(fontSize = 15.5.sp, fontWeight = FontWeight.Light),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Ascii,
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.White,
                        cursorColor = Color.White.copy(alpha = ContentAlpha.medium),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    if (showArrow) {
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier.offset(x = (-8).dp),
                        ) {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
                                },
                            ) {
                                Icon(
                                    painter = painterResource(id =R.drawable.baseline_arrow_back_24),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(27.dp),
                                )
                            }
                        }
                    } else if (onProfileClick != null) {
                        // profile icon
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier.offset(x = (-9).dp),
                        ) {
                            IconButton(onClick = onProfileClick) {
                                Box(
                                    modifier = Modifier
                                        .size(46.dp)
                                        .padding(8.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, Color.White, CircleShape)
                                        .background(Color.Gray),
                                ) {
                                    if (profilePhoto != null) {
                                        Image(
                                            painter = profilePhoto,
                                            contentDescription = "Profile Photo",
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop,
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // when in home page title (e.g. shanta) will be in center else title will be at the start of appbar (e.g. market)
                    if (currentRoute != null && currentRoute.value == BottomBar.Home.route) {
                        Text(
                            text = title,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 2.dp),
                        )
                    } else {
                        Text(
                            text = title,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 2.dp),
//                                .offset(x = 1.dp),
                        )
                    }
                }
            }
        },

        actions = {
            //search icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                if (showSearchIcon) {
                    IconButton(onClick = { navController.navigate("search") }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(27.dp),
                        )
                    }
                }
            }
            //notifications icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                if (showNotificationIcon) {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(27.dp),
                        )
                    }
                }
            }
            //settings icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                if (showSettingsIcon) {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(27.dp),
                        )
                    }
                }
            }
        },
        elevation = 8.dp,
        backgroundColor = myCustomColor,
    )
}
