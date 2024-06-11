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
import com.leads.capita.account.AccountInstrument
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.account.Instrument
import com.leads.capita.android.portfolio.ownershitp.position.PositionView
import com.leads.capita.service.account.AccountServiceImpl
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Composable
fun PositionScreen() {
    val context = LocalContext.current
//    val databaseDriverFactory: DatabaseDriverFactory = DatabaseDriverFactory(context)
//    val accountInstrument = AccountServiceImpl(databaseDriverFactory)
//    var instrument: List<Instrument>? by remember { mutableStateOf(null) }
//    val homeInstrument = accountInstrument.getInstrumentServices()
//    instrument = homeInstrument
//    val positionList = instrument
    val databaseDriverFactory = DatabaseDriverFactory(context)
    val accountInstrument = AccountServiceImpl(databaseDriverFactory)
    // Fetch the account balance information from the service
    val homeInstrument = accountInstrument.getInstrumentServices()
    val instrumentsList:List<Instrument> =try {
        val jsonElement = Json.parseToJsonElement(homeInstrument)

        when (jsonElement) {
            is JsonArray -> {
                // Process array
                jsonElement.mapNotNull { jsonElement ->
                    // Deserialize each element into Instrument
                    try {
                        Json.decodeFromJsonElement<Instrument>(jsonElement)
                    } catch (e: SerializationException) {
                        // Handle deserialization errors
                        e.printStackTrace()
                        null
                    }
                }
            }
            else -> {
                emptyList()
            }
        }
    } catch (e: Exception) {
        // Handle JSON parsing errors
        e.printStackTrace()
        emptyList()

    }
    val positionList: List<Instrument>? = instrumentsList

    var expandedIndex by remember { mutableStateOf(-1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 65.dp)
                .fillMaxHeight(),
        ) {
            itemsIndexed(positionList ?: emptyList()) { index, thisPosition ->
                PositionView(
                    accountInstrument = thisPosition,
                    expandedIndexPosition = expandedIndex,
                    index = index, // Pass the index value to PositionView
                ) { clickedIndex ->
                    expandedIndex = if (expandedIndex == clickedIndex) -1 else clickedIndex
                }
            }
        }
    }
}
