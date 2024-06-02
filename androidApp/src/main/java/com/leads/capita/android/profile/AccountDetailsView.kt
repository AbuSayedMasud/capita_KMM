package com.leads.capita.android.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.leads.capita.account.AccountBalance
import com.leads.capita.account.AccountDetails
import com.leads.capita.android.R
import com.leads.capita.android.shell.MyAppBar
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.theme.rememberWindowSizeClass
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.service.account.AccountServiceImpl
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Composable
fun AccountDetailsView(navController: NavHostController) {

    val window = rememberWindowSizeClass()
    val profilePhoto: Painter = painterResource(id = R.drawable.profile)
    val onProfileClick: () -> Unit = {}
    val currentRoute =
        remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    var accountDetails: AccountDetails? by remember { mutableStateOf(null) }
    val context = LocalContext.current
    val databaseDriverFactory = DatabaseDriverFactory(context)
    val accountService = AccountServiceImpl(databaseDriverFactory)
    val accountDetailsService = accountService.getAccountDetailsServices()
    val jsonObject = Json.parseToJsonElement(accountDetailsService ?: "").jsonObject
    val accountDetailsData = jsonObject["status"]?.jsonPrimitive?.contentOrNull
    if (accountDetailsData?.isNotEmpty() == true) {

    } else {
        accountDetails = Json.decodeFromString<AccountDetails>(accountDetailsService)
    }
    Column {
        MyAppBar(
            navController = navController,
            context = LocalContext.current,
            title = "Account Details",
            onSearch = { searchText ->
            },
            showSearchBar = false,
            onProfileClick = onProfileClick,
            profilePhoto = profilePhoto,
            showSearchIcon = false,
            showNotificationIcon = false,
            showSettingsIcon = true,
            currentRoute = currentRoute,
            showArrow = true,
        )

        val window = rememberWindowSizeClass()
        CapitaTheme(window) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                ) {
                    item { accountDetails?.let { AccountDetailsItem(it) } }

                }
            }
        }
    }
}

@Composable
fun AccountDetailsItem(accountDetails: AccountDetails) {
    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    Card(
        modifier = Modifier
            .padding(bottom = paddingValue)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
        shape = MaterialTheme.shapes.large,
        backgroundColor = backgroundColor,
    ) {
        Column(
            modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Account Code",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 2.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = accountDetails.accountCode,
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
            }
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Name",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 2.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = accountDetails.customers[0].lastName,
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }

            }
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Address",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 2.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = accountDetails.customers[0].addresses[0].address1,
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }

            }
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 2.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = accountDetails.customers[0].addresses[0].email,
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }

            }
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Mobile No",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 2.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = accountDetails.customers[0].addresses[0].mobileNumber,
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }

            }
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Date of Birth ",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 2.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = accountDetails.customers[0].birthDate,
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }

            }
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Gender",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 2.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = accountDetails.customers[0].gender,
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }

            }
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Column(
                    modifier = Modifier.weight(1.5f), horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Tax ID",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 2.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = accountDetails.customers[0].taxId,
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Normal
                        ),
                    )
                }

            }

        }

    }
}



