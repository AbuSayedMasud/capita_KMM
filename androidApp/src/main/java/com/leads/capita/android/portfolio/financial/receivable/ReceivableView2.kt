package com.leads.capita.android.portfolio.financial.receivable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leads.capita.formatnumber.formatNumberWithCommas

import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.theme.rememberWindowSizeClass
import com.leads.capita.account.AccountReceivable


@Composable
fun ReceivableView2(accountReceivable: AccountReceivable) {
    val (backgroundColor, contentColor) = getCardColors()
    val window = rememberWindowSizeClass()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    CapitaTheme(window) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Card(
                modifier = Modifier.padding(top = paddingValue, bottom = 0.dp),
                elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
                shape = MaterialTheme.shapes.large,
                backgroundColor = backgroundColor,
            ) {
                Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = accountReceivable.name,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 16.dp),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.body2
                                .copy(fontSize = 15.5.sp),
                            color = contentColor,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(2f)
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Text(
                                text = accountReceivable.company1,
                                modifier = Modifier.padding(top = 0.dp),
                                fontSize = 13.sp,
                                textAlign = TextAlign.Start,
                                color = contentColor,
                            )
                            Text(
                                text = accountReceivable.company2,
                                modifier = Modifier.padding(top = 8.dp),
                                fontSize = 13.sp,
                                textAlign = TextAlign.Start,
                                color = contentColor,
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(2f)
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "${formatNumberWithCommas(accountReceivable.shareQuantity1, 2)} @ ${formatNumberWithCommas(accountReceivable.amount1, 2)} ",
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(top = 0.dp),
                                textAlign = TextAlign.Start,
                                color = contentColor,
                            )

                            Text(
                                text = "${formatNumberWithCommas(accountReceivable.shareQuantity2, 2)} @ ${formatNumberWithCommas(accountReceivable.amount2, 2)} ",
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(top = 8.dp),
                                textAlign = TextAlign.End,
                                color = contentColor,
                            )
                        }
                    }
                }
            }
        }
    }
}
