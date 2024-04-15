package com.leads.capita.market.instrument

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.leads.capitabull.android.filter.InstrumentFilterScreen

import com.leads.capitabull.android.MockJsonLoader.MockLoader
import com.leads.capitabull.android.R
import com.leads.capitabull.android.api.market.Ticker
import com.leads.capitabull.android.market.instrument.InstrumentView
import com.leads.capitabull.android.theme.BackgroundColor
import com.leads.capitabull.android.theme.FloatingActionButtonColor
import com.leads.capitabull.android.theme.PrimaryColor
import com.leads.capitabull.android.theme.White

@Composable
fun InstrumentScreen(
    setShowAppBarAndMarketSectionBar: (Boolean) -> Unit,
    showInstrumentFilterView: Boolean,
    setShowInstrumentFilterView: (Boolean) -> Unit,
    isFloatingActionButtonVisible: Boolean,
    setIsFloatingActionButtonVisible: (Boolean) -> Unit,
    setIsSwipeEnabled: (Boolean) -> Unit,

) {
//    val instrumentServiceImpl = InstrumentServiceImpl()
    val context = LocalContext.current
    var instrumentList: List<Ticker>? by remember { mutableStateOf(null) }
//    val instrumentData = instrumentServiceImpl.getTicker("Instrument", context)
    val instrumentData=MockLoader(context).instrument
    instrumentList = instrumentData

    var selectedMarket by remember { mutableStateOf("") }

    val instruments = if (selectedMarket.isBlank()) {
        instrumentData.filter { it.market == "DSE" }
    } else {
        instrumentData.filter { it.market == selectedMarket }
    }

    val updateSelectedIndex: (String) -> Unit = { selected ->
        selectedMarket = selected
        setShowInstrumentFilterView(false) // Close the filters screen
        setShowAppBarAndMarketSectionBar(true)
    }

    if (showInstrumentFilterView) {
        InstrumentFilterScreen(setIsFloatingActionButtonVisible,setIsSwipeEnabled,updateSelectedIndex)
    } else {
        DisplayInstrument(instruments)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        if (isFloatingActionButtonVisible) {
            FloatingActionButton(
                onClick = {
                    setIsFloatingActionButtonVisible(false)
                    setShowAppBarAndMarketSectionBar(false)
                    setShowInstrumentFilterView(true)
                },
                content = {
                    val imagePainter = painterResource(R.drawable.filter)
                    Icon(
                        painter = imagePainter,
                        contentDescription = "Custom Icon",
                        modifier = Modifier.size(24.dp),
                        tint = PrimaryColor,
                    )
                },
                modifier = Modifier
                    .padding(16.dp)
                    .offset((-8).dp, (-60).dp)
                    .size(56.dp),
                backgroundColor = if (isSystemInDarkTheme()) FloatingActionButtonColor else White,
                contentColor = Color.Black,
            )
        }
    }
}

@Composable
fun DisplayInstrument(instruments: List<Ticker>) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundColor else Color.White),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 56.dp)
                .fillMaxHeight(),
        ) {
            items(instruments) { stock ->
                InstrumentView(stock)
            }
            item {
                Spacer(modifier = Modifier.height(76.dp))
            }
        }
    }
}
