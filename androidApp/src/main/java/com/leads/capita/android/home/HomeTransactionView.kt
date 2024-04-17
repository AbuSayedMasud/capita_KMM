package com.leads.capita.android.home

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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.formatnumber.formatNumberWithCommas

import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.R
import com.leads.capita.account.AccountTransaction
import com.leads.capita.service.account.AccountServiceImpl


@Composable
fun HomeTransactionView(navController: NavHostController) {
    val context = LocalContext.current
    var databaseDriverFactory = DatabaseDriverFactory(context)
    val accountInstrument = AccountServiceImpl(databaseDriverFactory)
    var transactionList: List<AccountTransaction>? by remember { mutableStateOf(null) }
    val homeTransactionList = accountInstrument.getTransactionServices()
    transactionList = homeTransactionList

    val (backgroundColor, contentColor) = getCardColors()
    val changeColor = Color(0xFF15A80B)
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
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
                modifier = Modifier
                    .fillMaxWidth(),
                shadowElevation = 4.dp,
                color = backgroundColor,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .weight(3f),
                    ) {
                        Text(
                            text = "Transaction",
                            style = MaterialTheme.typography.body1
                                .copy(fontWeight = FontWeight.Bold, fontSize = 18.sp, color = contentColor),
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .weight(1f)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {
                                navController.navigate("transaction")
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

            // Content Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                color = backgroundColor,
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                ) {
                    for (index in 0 until minOf(3, transactionList!!.size)) {
                        val transaction = transactionList!![index]
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp, end = 5.dp),
                        ) {
                            val plusSign = "+"
                            val minusSign = "-"
                            val amount = transaction.totalAmount?.let { formatNumberWithCommas(it, 2) }

                            // Left side
                            Text(
                                text = transaction.transferType,
                                modifier = Modifier.weight(2f),
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.body2
                                    .copy(fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Bold),
                            )

                            // Right side
                            if (transaction.identity == "credit") {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = changeColor,

                                            ),
                                        ) {
                                            append(plusSign)
                                        }
                                        append(amount)
                                    },
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Bold),
                                    modifier = Modifier.weight(2f),
                                    textAlign = TextAlign.End,
                                    color = changeColor,
                                )
                            } else {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.Red,
                                            ),
                                        ) {
                                            append(minusSign)
                                        }
                                        append(amount)
                                    },
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Bold),
                                    modifier = Modifier.weight(2f),
                                    textAlign = TextAlign.End,
                                    color = Color(0xFFff0100),
                                )
                            }
                        }
                        if (transaction.description.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp, end = 5.dp),
                            ) {
                                Text(
                                    text = transaction.description,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.weight(2f),
                                    textAlign = TextAlign.Start,
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 13.sp, color = contentColor),
                                )

                                Text(
                                    text = transaction.quantity,
                                    fontWeight = FontWeight.Normal,
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 15.5.sp, color = contentColor),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.End,
                                )
                            }
                        } else {
                            Spacer(modifier = Modifier.height(0.dp))
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp, end = 5.dp),
                        ) {
                            Text(
                                text = transaction.date,
                                style = MaterialTheme.typography.body2
                                    .copy(fontSize = 13.sp)
                                    .copy(color = contentColor),
                                modifier = Modifier.weight(1f),
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Start,
                            )
                        }

                        if (index < 2) {
                            Divider(
                                modifier = Modifier.padding(vertical = 8.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
