package com.leads.capita.android.market.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.android.filter.OverviewFilterScreen
import com.leads.capita.android.market.instrument.InstrumentView
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.android.theme.FloatingActionButtonColor
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.theme.White
import com.leads.capita.android.R
import com.leads.capita.service.instrument.InstrumentServiceImpl
import com.leads.capita.service.overview.OverviewServiceImpl

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OverviewScreen(
    setShowAppBarAndMarketSectionBar: (Boolean) -> Unit,
    showOverviewFilterView: Boolean,
    setShowOverviewFilterView: (Boolean) -> Unit,
    isFloatingActionButtonVisible: Boolean,
    setIsFloatingActionButtonVisible: (Boolean) -> Unit,
    setIsSwipeEnabled: (Boolean) -> Unit,
) {
    var context = LocalContext.current
    var databaseDriverFactory = DatabaseDriverFactory(context)

    val overviewService = OverviewServiceImpl(databaseDriverFactory)
    val instrumentService = InstrumentServiceImpl(databaseDriverFactory)
    var selectedMarket by remember { mutableStateOf("") }


    var enableSwiping = remember { mutableStateOf(true) }
    val indexData = instrumentService.getTicker("Index")
    val statusData = overviewService.listStatus()
    val volumeData = overviewService.getTicker("")
    val participationData = overviewService.listParticipation()
//    val indexData=MockLoaderDemo(context).indices
//    var statusData=MockLoaderDemo(context).status
//    var volumeData=MockLoaderDemo(context).volume
//    var participationData=MockLoaderDemo(context).participation
    // Fetch the instruments based on the selected market
    val overviewValues =
        indexData.filter { if (selectedMarket.isBlank()) it.market == "DSE" else it.market == selectedMarket }
    val statusValues =
        statusData.filter { if (selectedMarket.isBlank()) it.market == "DSE" else it.market == selectedMarket }
    val volumeValues =
        volumeData.filter { if (selectedMarket.isBlank()) it.market == "DSE" else it.market == selectedMarket }
    val participationValues =
        participationData.filter { if (selectedMarket.isBlank()) it.market == "DSE" else it.market == selectedMarket }

    // Function to update the selected index and close the filters screen
    val updateSelectedIndex: (String) -> Unit = { selected ->
        selectedMarket = selected
        setShowOverviewFilterView(false) // Close the filters screen
        setShowAppBarAndMarketSectionBar(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9)),
    ) {
        if (showOverviewFilterView) {
            OverviewFilterScreen(setIsFloatingActionButtonVisible, setIsSwipeEnabled, updateSelectedIndex)
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 56.dp)
                    .fillMaxSize(),
            ) {
                item {
                    StatusView(statusValues)
                }
                items(overviewValues) { ticker ->
                    InstrumentView(ticker)
                }
                item {
                    TotalView(volumeValues)
                }
                item {
                    ParticipationView(participationValues)
                }
                item {
                    Spacer(modifier = Modifier.height(76.dp))
                }
            }
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
                        setShowOverviewFilterView(true)
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
}
