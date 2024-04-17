package com.leads.capita.android.profile

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.formatnumber.formatNumberWithCommas

import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.theme.rememberWindowSizeClass
import com.leads.capita.android.R
import com.leads.capita.account.AccountBalance
import com.leads.capita.service.account.AccountServiceImpl

@Composable
fun AccountBalanceView(
    navController: NavHostController,
    expandedState: MutableState<Boolean>,
    toggleCardExpansion: () -> Unit,
    cardName: String,
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f
    val databaseDriverFactory: DatabaseDriverFactory = DatabaseDriverFactory(context)
        val accountService = AccountServiceImpl(databaseDriverFactory)
    var balances: List<com.leads.capita.account.AccountBalance>? by remember { mutableStateOf(null) }
        val accountBalanceList = accountService.getBalanceServices()
        balances = accountBalanceList
//    balances = MockLoaderDemo(context).balances
    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
        Card(
            modifier = Modifier
                .padding(bottom = paddingValue)
                .fillMaxWidth(),
            elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
            shape = MaterialTheme.shapes.large,
            backgroundColor = backgroundColor,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = "Balances",
                            style = MaterialTheme.typography.body1
                                .copy(
                                    fontWeight = FontWeight.Bold,
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
                                toggleCardExpansion()
                            },
                    ) {
                        if (expandedState.value && cardName == "balance") {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_up_24),
                                contentDescription = null,
                                tint = contentColor,
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                contentDescription = null,
                                tint = contentColor,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                for (balance in balances.orEmpty()) {
                    AnimatedVisibility(expandedState.value && cardName == "balance") {
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Column {
                                    Row(
                                        Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                    ) {
                                        Text(
                                            text = "Available Balance",
                                            style = MaterialTheme.typography.body2
                                                .copy(fontSize = 13.sp, color = contentColor),
                                        )

                                        Text(
                                            text = formatNumberWithCommas(balance.cashBalance, 2),
                                            style = MaterialTheme.typography.body2
                                                .copy(fontSize = 13.sp, color = contentColor),
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                        )
                                    }
                                    Row(
                                        Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                    ) {
                                        Text(
                                            text = "Current Balance",
                                            style = MaterialTheme.typography.body2
                                                .copy(fontSize = 13.sp, color = contentColor),
                                        )

                                        Text(
                                            text = formatNumberWithCommas(
                                                balance.currentBalance,
                                                2
                                            ),
                                            style = MaterialTheme.typography.body2
                                                .copy(fontSize = 13.sp, color = contentColor),
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                        )
                                    }

                                    Row(
                                        Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                    ) {
                                        Text(
                                            text = "Equity",
                                            style = MaterialTheme.typography.body2
                                                .copy(fontSize = 13.sp, color = contentColor),
                                        )

                                        Text(
                                            text = formatNumberWithCommas(balance.cashBalance, 2),
                                            style = MaterialTheme.typography.body2
                                                .copy(fontSize = 13.sp, color = contentColor),
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                        )
                                    }
                                    Row(
                                        Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                    ) {
                                        Text(
                                            text = "Purchase Power",
                                            style = MaterialTheme.typography.body2
                                                .copy(fontSize = 13.sp, color = contentColor),
                                        )

                                        Text(
                                            text = formatNumberWithCommas(balance.buyingPower, 2),
                                            style = MaterialTheme.typography.body2
                                                .copy(fontSize = 13.sp, color = contentColor),
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
