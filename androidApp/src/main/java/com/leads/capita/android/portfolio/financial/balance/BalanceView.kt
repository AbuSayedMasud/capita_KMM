package com.leads.capita.android.portfolio.financial.balance

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leads.capita.formatnumber.formatNumberWithCommas

import com.leads.capita.android.theme.Black
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.White
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.theme.rememberWindowSizeClass
import com.leads.capita.account.AccountBalance


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BalanceView(
    accountBalance: AccountBalance,
    expandedIndex: String,
    onCardClicked: (String) -> Unit,
) {
    val isExpanded = expandedIndex == accountBalance.accountCode

    var expanded by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // Define sizes based on screen size
    val imageSize = (screenWidth * 0.09f).coerceAtMost(52.dp)
    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f // Increase text space on large screens
    val valueColumnWeight =
        if (screenWidth > 600.dp) 2f else 1f // Increase value space on large screens
    val textSize = if (screenWidth > 600.dp) 14.sp else 12.sp

    val (backgroundColor, contentColor) = getCardColors()

    val window = rememberWindowSizeClass()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    CapitaTheme(window) {
        Card(
            modifier = Modifier.padding(top = paddingValue, bottom = 0.dp),
            elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
            backgroundColor = backgroundColor,
            shape = MaterialTheme.shapes.large,
            onClick = { onCardClicked(accountBalance.accountCode) },
        ) {
            Column(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.weight(textColumnWeight)) {
                        Text(
                            text = "Current Balance",
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                            fontSize = 15.5.sp,
                            color = contentColor,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(valueColumnWeight)
                            .padding(top = screenWidth * 0.001f),
                        horizontalAlignment = Alignment.End,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Text(
                                text = formatNumberWithCommas(accountBalance.currentBalance,2),
                                style = MaterialTheme.typography.body1,
                                fontSize = 15.5.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End,
                                color = contentColor,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))

                AnimatedContent(
                    targetState = isExpanded,
                    label = "",
                ) { targetExpanded ->
                    AnimatedVisibility(
                        visible = targetExpanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically(),
                    ) {
                        if (isExpanded) {
                            Card(
                                modifier = Modifier.padding(top = 8.dp),
                                elevation = 0.dp,
                                backgroundColor = Color.Transparent,
                                shape = MaterialTheme.shapes.medium,
                                border = null,
                            ) {
                                Column(modifier = Modifier.padding(0.dp)) {
                                    BalanceItem("AccountCode", accountBalance.accountCode.toDouble())
                                    BalanceItem("AccruedCharge", accountBalance.accruedCharge)
                                    BalanceItem("AssetValue", accountBalance.assetValue)
                                    BalanceItem("BuyingPower ", accountBalance.buyingPower)
                                    BalanceItem("CashBalance", accountBalance.cashBalance)
                                    BalanceItem("CostValue", accountBalance.costValue)
                                    BalanceItem("DeptEquityRatio", accountBalance.deptEquityRatio)
                                    BalanceItem("Equity", accountBalance.equity)
                                    BalanceItem("EquityDebtRatio", accountBalance.equityDebtRatio)
                                    BalanceItem("ImmatureBalance", accountBalance.immatureBalance)
                                    BalanceItem("LoanRatio", accountBalance.loanRatio)
                                    BalanceItem("MarginEquity", accountBalance.marginEquity)
                                    BalanceItem("MarketValue", accountBalance.marketValue)
                                    BalanceItem("TotalDeposit", accountBalance.totalDeposit)
                                    BalanceItem("TotalWithdrawal", accountBalance.totalWithdrawal)
                                    BalanceItem("UnclearCheque", accountBalance.unclearCheque)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BalanceItem(label: String, value: Double) {
    val textColor = if (isSystemInDarkTheme()) White else Black
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f),
            color = textColor,
        )
        Text(
            text = formatNumberWithCommas(value, 2),
            style = MaterialTheme.typography.body2,
            fontSize = 13.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f),
            color = textColor,
        )
    }
}
