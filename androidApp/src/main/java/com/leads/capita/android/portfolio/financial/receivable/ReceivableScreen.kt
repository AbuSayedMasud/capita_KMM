package com.leads.capita.android.portfolio.financial.receivable

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.leads.capita.repository.DatabaseDriverFactory

import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.account.AccountReceivable
import com.leads.capita.service.account.AccountServiceImpl


@Composable
fun ReceivableScreen() {
    val context = LocalContext.current
    val databaseDriverFactory: DatabaseDriverFactory = DatabaseDriverFactory(context)

    val accountInstrument = AccountServiceImpl(databaseDriverFactory)
    var receivableList: List<AccountReceivable>? by remember { mutableStateOf(null) }
//    val homeReceivableList=MockLoaderDemo(context).receivables
    val homeReceivableList = accountInstrument.getReceivableServices()
    receivableList = homeReceivableList

    DisplayReceivables(receivableList!!)
}

@Composable
fun DisplayReceivables(accountReceivable: List<AccountReceivable>) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))
    ) {
        LazyColumn {
            items(accountReceivable) { thisReceivable ->
                ReceivableView2(thisReceivable)
            }
            item {
                Spacer(modifier = Modifier.height(76.dp))
            }
        }
    }
}
