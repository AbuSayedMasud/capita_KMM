package com.leads.capita.android.transaction.debitTransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.leads.capita.android.transaction.TransactionView

import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.account.AccountTransaction


@Composable
fun DebitTransactionScreen(transactionList: List<AccountTransaction>) {
    val debitTransactions = transactionList.filter { it.identity == "debit" }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundColor else Color.White),
    ) {
        TransactionView(debitTransactions)
    }
}
