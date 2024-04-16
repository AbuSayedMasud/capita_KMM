package com.leads.capita.android.market.instrument

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.leads.capita.android.MockJsonLoader.MockLoader

import com.leads.capita.android.api.market.Ticker
import com.leads.capita.android.filter.IndexFilterScreen
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.android.theme.FloatingActionButtonColor
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.theme.White
import com.leads.capita.android.R

@Composable
fun IndexScreen(
    context: Context,
    setShowAppBarAndMarketSectionBar: (Boolean) -> Unit,
    showIndexFilterView: Boolean,
    setShowIndexFilterView: (Boolean) -> Unit,
    isFloatingActionButtonVisible: Boolean,
    setIsFloatingActionButtonVisible: (Boolean) -> Unit,
    setIsSwipeEnabled: (Boolean) -> Unit,
) {
//    val instrumentServiceImpl = InstrumentServiceImpl()
    var indexList: List<Ticker>? by remember { mutableStateOf(null) }
//    val indexData = instrumentServiceImpl.getTicker("Index", context)
    val indexData = MockLoader(context).indices
    indexList = indexData
    var selectedIndex by remember { mutableStateOf("") }

    val indices = if (selectedIndex.isBlank()) {
        indexData.filter { it.market == "DSE" }
    } else {
        indexData.filter { it.market == selectedIndex }
    }

    // Function to update the selected index and close the filters screen
    val updateSelectedIndex: (String) -> Unit = { selected ->
        selectedIndex = selected
        setShowIndexFilterView(false) // Close the filters screen
        setShowAppBarAndMarketSectionBar(true)
    }

    if (showIndexFilterView) {
        IndexFilterScreen(setIsFloatingActionButtonVisible, setIsSwipeEnabled, updateSelectedIndex)
    } else {
        DisplayIndices(indices)
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
                    setShowIndexFilterView(true)
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
fun DisplayIndices(indices: List<Ticker>) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundColor else Color.White),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 56.dp)
                .fillMaxHeight(),
        ) {
            items(indices) { index ->
                InstrumentView(instrument = index)
            }
        }
    }
}
