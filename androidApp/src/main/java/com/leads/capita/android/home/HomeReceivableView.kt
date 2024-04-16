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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import com.leads.capita.formatnumber.formatNumberWithCommas

import com.leads.capita.android.MockJsonLoader.MockLoader
import com.leads.capita.android.api.account.AccountReceivable
import com.leads.capita.android.shell.BottomBar
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.R

@Composable
fun HomeReceivableView(navController: NavHostController) {
    val context = LocalContext.current
//    val accountInstrument = AccountServiceImpl()
    var receivableList: List<AccountReceivable>? by remember { mutableStateOf(null) }
//    val homeReceivableList = accountInstrument.getReceivableServices(context)
    receivableList = MockLoader(context).receivables
    val (backgroundColor, contentColor) = getCardColors()
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
                            text = "Receivable",
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
                                val bundle = bundleOf(BottomBar.Portfolio.ARG_VALUE to "Receivable")
                                navController.navigate("${BottomBar.Portfolio.route}/Receivable")
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
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                ) {
                    for (index in 0 until minOf(2, receivableList!!.size)) {
                        val receivable = receivableList!![index]

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = receivable.name,
                                style = MaterialTheme.typography.body2
                                    .copy(fontSize = 15.5.sp, color = contentColor, fontWeight = FontWeight.Bold),
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))

                        // Data Rows
                        Row {
                            Column(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(5.dp),
                                horizontalAlignment = Alignment.Start,
                            ) {
                                Text(
                                    text = receivable.company1,
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 13.sp)
                                        .copy(fontWeight = FontWeight.Normal, color = contentColor),
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    textAlign = TextAlign.Start,
                                )
                                Text(
                                    text = receivable.company2,
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 13.sp)
                                        .copy(fontWeight = FontWeight.Normal, color = contentColor),
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    textAlign = TextAlign.Start,
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .weight(1f),
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    text = "${formatNumberWithCommas(receivable.shareQuantity1, 2)} @ ${formatNumberWithCommas(receivable.amount1, 2)} ",
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 13.sp)
                                        .copy(fontWeight = FontWeight.Normal, color = contentColor),
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    textAlign = TextAlign.End,
                                )
                                Text(
                                    text = "${formatNumberWithCommas(receivable.shareQuantity2, 2)} @ ${formatNumberWithCommas(receivable.amount2, 2)} ",
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 13.sp)
                                        .copy(fontWeight = FontWeight.Normal, color = contentColor),
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    textAlign = TextAlign.End,
                                )
                            }
                        }
                        if (index < 1) {
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
