package com.leads.capita.android.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.formatnumber.formatNumberWithCommas


import com.leads.capita.android.shell.BottomBar
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.theme.rememberWindowSizeClass
import com.leads.capita.android.R
import com.leads.capita.account.AccountBalance
import com.leads.capita.android.customAlertDialog.CustomAlertDialog
import com.leads.capita.security.IdentityErrorResponse
import com.leads.capita.service.account.AccountServiceImpl
import com.leads.capita.service.payment.PaymentServiceImpl
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


@Composable
fun HomeBalanceView(navController: NavHostController) {
    // Obtain the current context
    val context = LocalContext.current

    var balance: AccountBalance? by remember { mutableStateOf(null) }
    var dismissState = remember { mutableStateOf(true) }
    var databaseDriverFactory = DatabaseDriverFactory(context)

    //*** payment api check
//    val paymentService = PaymentServiceImpl(databaseDriverFactory)
//    val payment = paymentService.getPaymentServices()
//    val paymentStatus = paymentService.getPaymentStatusServices()
//    Log.d("payment data", payment.toString())
//    Log.d("paymentStatus data", paymentStatus.toString())
    //**payment api check
    //*** deposit api check
//    val depositService = DepositServiceImpl(databaseDriverFactory)
//    val deposit = depositService.getDepositServices()
//    val depositStatus = paymentService.getPaymentStatusServices()
//    Log.d("deposit data", deposit.toString())
//    Log.d("depositStatus data", depositStatus.toString())
//    val errors = Json.decodeFromString<IdentityErrorResponse>(deposit)
//    if (dismissState.value) {
//    CustomAlertDialog(
//        message = errors.details.toString(),
//        isSuccess = false,
//        dismissState = dismissState
//    )}
    //**deposit api check

    val accountService = AccountServiceImpl(databaseDriverFactory)
    // Fetch the account balance information from the service
    //* account details
    Log.d("account details",accountService.getAccountDetailsServices())
    //*account details
    val homeBalance = accountService.getBalanceServices()
    val jsonObject = Json.parseToJsonElement(homeBalance ?: "").jsonObject
    var balancedata = jsonObject["status"]?.jsonPrimitive?.contentOrNull
    if (balancedata?.isNotEmpty() == true) {
        val error = Json.decodeFromString<IdentityErrorResponse>(homeBalance)
        CustomAlertDialog(
            message = error.details.toString(),
            isSuccess = false,
            dismissState = dismissState
        )
    } else {
        balance = Json.decodeFromString<AccountBalance>(homeBalance)
    }

    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
        Spacer(modifier = Modifier.height(paddingValue))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.dp),
            shadowElevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
            color = backgroundColor,
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
            ) {
                // Title Card
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shadowElevation = 4.dp,
                    color = backgroundColor,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Column(
                            modifier = Modifier.weight(3f),
                        ) {
                            Text(
                                text = "Balance",
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = contentColor,
                                ),
                            )
                        }
                        // Column for navigation arrow
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .weight(1f)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                ) {
                                    val bundle =
                                        bundleOf(BottomBar.Portfolio.ARG_VALUE to "Balance")
                                    navController.navigate("${BottomBar.Portfolio.route}/Balance")
                                },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                contentDescription = null,
                                tint = contentColor,
                            )
                        }
                    }
                }
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    color = backgroundColor,
                ) {
                    Column(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),

                        ) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(), // Complete the modifier
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column {
                                Text(
                                    text = "Available Balance",
                                    style = MaterialTheme.typography.body2.copy(
                                        fontSize = 15.5.sp,
                                        color = contentColor,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    text = formatNumberWithCommas(
                                        (balance?.cashBalance ?: 0.0) as Double, 2
                                    ),
                                    style = MaterialTheme.typography.body2.copy(
                                        fontSize = 15.5.sp,
                                        color = contentColor,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }
                        }
                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(), // Complete the modifier
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column {
                                Text(
                                    text = "Current Balance",
                                    style = MaterialTheme.typography.body2.copy(
                                        fontSize = 15.5.sp,
                                        color = contentColor,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    text = formatNumberWithCommas(
                                        (balance?.currentBalance ?: 0.0) as Double, 2
                                    ),
                                    style = MaterialTheme.typography.body2.copy(
                                        fontSize = 15.5.sp,
                                        color = contentColor,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }
                        }
                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(), // Complete the modifier
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column {
                                Text(
                                    text = "Equtity",
                                    style = MaterialTheme.typography.body2.copy(
                                        fontSize = 15.5.sp,
                                        color = contentColor,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    text = formatNumberWithCommas(
                                        (balance?.equity ?: 0.0) as Double, 2
                                    ),
                                    style = MaterialTheme.typography.body2.copy(
                                        fontSize = 15.5.sp,
                                        color = contentColor,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }
                        }
                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(), // Complete the modifier
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column {
                                Text(
                                    text = "Purchase Power",
                                    style = MaterialTheme.typography.body2.copy(
                                        fontSize = 15.5.sp,
                                        color = contentColor,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    text = formatNumberWithCommas(
                                        (balance?.buyingPower ?: 0.0) as Double, 2
                                    ),
                                    style = MaterialTheme.typography.body2.copy(
                                        fontSize = 15.5.sp,
                                        color = contentColor,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
