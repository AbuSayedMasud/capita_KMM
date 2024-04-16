package com.leads.capita.android.transaction.creditTransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.leads.capita.transaction.TransactionView

import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.api.account.AccountTransaction


@Composable
fun CreditTransactionScreen(transactionList: List<AccountTransaction>) {
    val creditTransactions = transactionList.filter { it.identity == "credit" }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundColor else Color.White),
    ) {
        TransactionView(creditTransactions)
    }
}
