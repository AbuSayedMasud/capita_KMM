package com.leads.capita.android.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.leads.capita.account.AccountBalance
import com.leads.capita.android.R
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.customerProfile.CustomerProfileResponse
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.service.account.AccountServiceImpl
import com.leads.capita.service.customerProfile.CustomerProfileServiceImpl
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Composable
fun AccountProfileView(navController: NavHostController) {
    var name by remember { mutableStateOf("John Doe") }
    var designation by remember { mutableStateOf("Software Developer") }
    var imageUrl by remember { mutableStateOf("https://example.com/profile.jpg") }
    var accountOpeningDate by remember { mutableStateOf("April, 2023") }
    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val context= LocalContext.current
    var profileData: CustomerProfileResponse? by remember { mutableStateOf(null) }
    var  databaseDriverFactory= DatabaseDriverFactory(context)
    val profileService = CustomerProfileServiceImpl(databaseDriverFactory)
    // Fetch the account balance information from the service
    val profile = profileService.getCustomerProfile()
    val jsonObject = Json.parseToJsonElement(profile ?: "").jsonObject
    Log.d("user name", "data is${jsonObject} ")
    var profileObjectData = jsonObject["customerType"]?.jsonPrimitive?.contentOrNull
    if (profileObjectData?.isNotEmpty() == true){
        profileData= Json.decodeFromString<CustomerProfileResponse>(profile)

    }else{

    }
    Log.d("profile data",profile)
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    var selectedAccountData by remember { mutableStateOf("") }
    val options = listOf("F111", "F112", "F113")
    val accountDataMap = mapOf(
        "F111" to " 1202105357564563",
        "F112" to " 1402105354564565",
        "F112" to " 1602105358564567"
    )
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValue),
        elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
        backgroundColor = backgroundColor,
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                Column(Modifier.padding(end = 16.dp), verticalArrangement = Arrangement.Center) {
                    val borderSize = 1.dp
                    val borderColor = Color(0xFF978c21)

                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(borderSize, borderColor, CircleShape),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile_1),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp, start = 2.dp),
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    profileData?.let {
                        Text(
                            text = it.lastName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = contentColor,
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text(
                                text = "Account Code :",
                                fontSize = 13.sp,
                                color = contentColor,
                            )
                        }
                        Column {
                            Text(
                                text = (selectedOption.takeIf { it.isNotEmpty() } ?: profileData?.accounts?.get(0)?.accountCode)!!,
                                fontSize = 13.sp,
                                color = contentColor,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                        Column {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                contentDescription = "Dropdown",
                                tint = contentColor,
                                modifier = Modifier
                                    .clickable { expanded = true }
                                    .padding(start = 4.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            Modifier.background(backgroundColor)
                        ) {
                            profileData?.accounts?.forEach { option ->
                                DropdownMenuItem(onClick = {
                                    selectedOption = option.accountCode
                                    selectedAccountData =
                                       option.boId
                                    expanded = false
                                }) {
                                    Text(text = option.accountCode)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row() {
                        Column {
                            Text(
                                text = "BO ID : ",
                                fontSize = 13.sp,
                                color = contentColor,
                            )
                        }
                        Column {
                            (if (selectedAccountData.isNotEmpty()) selectedAccountData else profileData?.accounts?.get(0)?.boId)?.let {
                                Text(
                                    text = it,
                                    fontSize = 13.sp,
                                    color = contentColor,
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
//                    Row() {
//                        Column {
//                            Text(
//                                text = "Account Type :",
//                                fontSize = 13.sp,
//                                color = contentColor,
//                            )
//                        }
//                        Column {
//                            Text(
//                                text = " IDA",
//                                fontSize = 13.sp,
//                                color = contentColor,
//                            )
//                        }
//                    }
                }
            }
        }
    }
}