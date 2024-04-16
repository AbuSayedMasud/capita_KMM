package com.leads.capita.android.portfolio.ownershitp.position

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.leads.capita.android.MockJsonLoader.MockLoader
import com.leads.capita.android.api.account.AccountInstrument
import com.leads.capita.android.theme.BackgroundColor


@Composable
fun PositionScreen() {
    val context = LocalContext.current
//    val accountInstrument = AccountServiceImpl()
    var instrument: List<AccountInstrument>? by remember { mutableStateOf(null) }
    val homeInstrument=MockLoader(context).instruments
//    val homeInstrument = accountInstrument.getInstrumentServices(context)
    instrument = homeInstrument
    val positionList = instrument

    var expandedIndex by remember { mutableStateOf(-1) }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 56.dp)
                .fillMaxHeight(),
        ) {
            itemsIndexed(positionList ?: emptyList()) { index, thisPosition ->
                PositionView(
                    accountInstrument = thisPosition,
                    expandedIndexPosition = expandedIndex,
                    onCardClicked = { clickedIndex ->
                        expandedIndex = if (expandedIndex == clickedIndex) -1 else clickedIndex
                    },
                )
            }
        }
    }
}
