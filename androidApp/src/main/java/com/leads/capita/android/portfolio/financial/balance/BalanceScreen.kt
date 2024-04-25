package com.leads.capita.android.portfolio.financial.balance

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
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.leads.capita.repository.DatabaseDriverFactory

import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.account.AccountBalance
import com.leads.capita.service.account.AccountServiceImpl
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Composable
fun BalanceScreen() {
    val context = LocalContext.current
//    var balance: List<AccountBalance> by remember { mutableStateOf(emptyList()) }
//    val databaseDriverFactory: DatabaseDriverFactory = DatabaseDriverFactory(context)
//    val balanceService = AccountServiceImpl(databaseDriverFactory)
//    val balanceData = balanceService.getBalanceServices()
////    val balanceData=MockLoaderDemo(context).balances
//    balance = listOf(balanceData[0])
    var balance: AccountBalance? by remember { mutableStateOf(null) }

    var  databaseDriverFactory= DatabaseDriverFactory(context)
    val accountService = AccountServiceImpl(databaseDriverFactory)
    // Fetch the account balance information from the service
    val homeBalance = accountService.getBalanceServices()
    val jsonObject = Json.parseToJsonElement(homeBalance ?: "").jsonObject
    var balancedata = jsonObject["status"]?.jsonPrimitive?.contentOrNull
    if (balancedata?.isNotEmpty() == true){

    }else{
        balance= Json.decodeFromString<AccountBalance>(homeBalance)
    }
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
            items(items = listOfNotNull(balance)) {
                balance?.let {
                    BalanceView(
                        accountBalance = it,
                        expandedIndex = expandedIndex,
                        onCardClicked = { clickedIndex ->
                            expandedIndex = if (expandedIndex == clickedIndex) "" else clickedIndex
                        },
                    )
                }
            }
        }
    }
}
