package com.leads.capita.transaction

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leads.capita.formatnumber.formatNumberWithCommas
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.api.account.AccountTransaction


@Composable
fun TransactionView(ledger: List<AccountTransaction>) {
    val changeColor = Color(0xFF15A80B)
    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 56.dp)
                .fillMaxHeight(),
        ) {
            items(ledger) { ledger ->
                Card(
                    modifier = Modifier.padding(paddingValue, bottom = 0.dp),
                    elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
                    shape = MaterialTheme.shapes.large,
                    backgroundColor = backgroundColor,
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            val plusSign = "+"
                            val minusSign = "-"

                            val amount = ledger.totalAmount?.let { formatNumberWithCommas(it, 2) }
                            Text(
                                text = ledger.transferType,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(2f),
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.body2
                                    .copy(fontSize = 15.5.sp, color = contentColor),

                            )
                            if (ledger.identity == "credit") {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontWeight = FontWeight.Bold,
                                            ),
                                        ) {
                                            append(plusSign)
                                        }
                                        append(amount)
                                    },
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(2f),
                                    textAlign = TextAlign.End,
                                    fontSize = 15.5.sp,
                                    color = changeColor,

                                )
                            } else {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(

                                                fontWeight = FontWeight.Bold,
                                            ),
                                        ) {
                                            append(minusSign)
                                        }
                                        append(amount)
                                    },
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(2f),
                                    textAlign = TextAlign.End,
                                    fontSize = 15.5.sp,
                                    color = Color(0xFFff0100),

                                )
                            }
                        }
                        if (ledger.description.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = ledger.description,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp,
                                    modifier = Modifier.weight(2f),
                                    textAlign = TextAlign.Start,
                                    color = contentColor,
                                )

                                Text(
                                    text = ledger.quantity,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.End,
                                    color = contentColor,
                                )
                            }
                        } else {
                            Spacer(modifier = Modifier.height(0.dp))
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = ledger.date,
                                fontSize = 15.5.sp,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Start,
                                color = contentColor,
                            )
                        }
                    }
                }
            }
        }
    }
}
