package com.leads.capitabull.android.home

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
import com.leads.capitabull.android.MockJsonLoader.MockLoader
import com.leads.capitabull.android.R
import com.leads.capitabull.android.api.account.AccountInstrument
import com.leads.capitabull.android.shell.BottomBar
import com.leads.capitabull.android.theme.getCardColors

@Composable
fun HomeInstrumentView(navController: NavHostController) {
    val context = LocalContext.current
//    val accountInstrument = AccountServiceImpl()
    var instrument: List<AccountInstrument>? by remember { mutableStateOf(null) }
//    val homeInstrument = accountInstrument.getInstrumentServices(context)
    instrument = MockLoader(context).instruments
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val (backgroundColor, contentColor) = getCardColors()
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
                            text = "Position",
                            style = MaterialTheme.typography.body1
                                .copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = contentColor,
                                ),
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
                                val bundle = bundleOf(BottomBar.Portfolio.ARG_VALUE to "Position")
                                navController.navigate("${BottomBar.Portfolio.route}/Position")
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
                    for (index in 0 until instrument?.let { minOf(2, it.size) }!!) {
                        val position = instrument?.get(index)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp, end = 5.dp),
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(3f),
                            ) {
                                position?.let {
                                    Text(
                                        text = it.shortName,
                                        textAlign = TextAlign.Start,
                                        style = MaterialTheme.typography.body1
                                            .copy(
                                                fontSize = 15.5.sp,
                                                color = contentColor,
                                                fontWeight = FontWeight.Bold,
                                            ),
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .weight(1f),
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    text = formatNumberWithCommas(position?.totalQuantity ?: 0.0, 2),
                                    textAlign = TextAlign.Start,
                                    style = MaterialTheme.typography.body1
                                        .copy(
                                            fontSize = 15.5.sp,
                                            color = contentColor,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp, end = 5.dp),
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(3f),
                            ) {
                                Text(
                                    text = "Market Price",
                                    textAlign = TextAlign.Start,
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 13.sp, color = contentColor),
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(1f),
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    text = formatNumberWithCommas(position?.closePrice ?: 0.0, 2),
                                    textAlign = TextAlign.End,
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 13.sp, color = contentColor),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp, end = 5.dp),
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(3f),
                            ) {
                                Text(
                                    text = "Average Price",
                                    textAlign = TextAlign.Start,
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 13.sp, color = contentColor),
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(1f),
                                horizontalAlignment = Alignment.End,
                            ) {
                                Text(
                                    text = formatNumberWithCommas(position?.averageCost ?: 0.0, 2),
                                    textAlign = TextAlign.End,
                                    style = MaterialTheme.typography.body2
                                        .copy(fontSize = 13.sp, color = contentColor),
                                )
                            }
                        }
                        if (index < instrument!!.size - 1) {
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
