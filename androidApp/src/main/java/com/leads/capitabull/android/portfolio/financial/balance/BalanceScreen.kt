package com.leads.capitabull.android.portfolio.financial.balance

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.leads.capitabull.android.MockJsonLoader.MockLoader
import com.leads.capitabull.android.api.account.AccountBalance
import com.leads.capitabull.android.portfolio.financial.balance.BalanceView
import com.leads.capitabull.android.theme.BackgroundColor

@Composable
fun BalanceScreen() {
    val context = LocalContext.current
    var balance: List<AccountBalance> by remember { mutableStateOf(emptyList()) }
//    val balanceService = AccountServiceImpl()
//    val balanceData = balanceService.getBalanceServices(context)
    val balanceData=MockLoader(context).balances
    balance = listOf(balanceData[0])

    var expandedIndex by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 56.dp)
                .fillMaxHeight(),
        ) {
            items(balance) { thisBalance ->
                BalanceView(
                    accountBalance = thisBalance,
                    expandedIndex = expandedIndex,
                    onCardClicked = { clickedIndex ->
                        expandedIndex = if (expandedIndex == clickedIndex) "" else clickedIndex
                    },
                )
            }
        }
    }
}
