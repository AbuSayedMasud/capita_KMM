package com.leads.capita.android.MockJsonLoader

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leads.capita.api.account.AccountBalance
import com.leads.capita.api.account.AccountInstrument
import com.leads.capita.api.account.AccountReceivable
import com.leads.capita.api.account.AccountTransaction
import com.leads.capita.api.market.Ticker
import com.leads.capita.api.market.overview.Participation
import com.leads.capita.api.market.overview.Status
import com.leads.capita.api.news.News
import kotlinx.serialization.json.Json

class MockLoader(private val context: Context) {

//    fun init() {
//        var isLoaded = false
//        if (isLoaded) {
//            return
//        }

//        val service = MockService(context)

        // account Transaction
        val jsonContent = loadJson("accounttransaction")
        // Deserialize JSON content into a list of AccountTransaction objects
        val transactions = Json.decodeFromString<List<AccountTransaction>>(jsonContent)
//        service.loadAccountTransaction(transactions)
        // account Balance

        var balances = Json.decodeFromString<List<AccountBalance>>(loadJson("accountbalance"))
//        service.loadAccountBalance(balances)


        // account instrument
        val instruments = Json.decodeFromString<List<AccountInstrument>>(loadJson("accountinstruments"))
//        service.loadAccountInstrument(instruments)


        // account Receivable
        val receivables = Json.decodeFromString<List<AccountReceivable>>(loadJson("accountreceivable"))
//        service.loadAccountReceivable(receivables)


        // index
        val indices = Json.decodeFromString<List<Ticker>>(loadJson("index"))
//        service.loadIndices(indices)


        // instrument
        val instrument = Json.decodeFromString<List<Ticker>>(loadJson("instrument"))
//        service.loadInstrument(instrument)


        // news
        val news = Json.decodeFromString<List<News>>(loadJson("news"))
//        service.loadNews(news)


        // participation
        val participation = Json.decodeFromString<List<Participation>>(loadJson("participationlist"))
//        service.loadParticipation(participation)


        // status
        val status = Json.decodeFromString<List<Status>>(loadJson("statuslist"))
//        service.loadStatus(status)


        // volume
        val volume = Json.decodeFromString<List<Ticker>>(loadJson("volumelist"))
//        service.loadvolume(volume)


//        true.also { isLoaded = it }
//    }

    /**
     * Loads JSON data from a raw resource file.
     *
     * @param context The context providing access to resources.
     * @param fileName The name of the JSON file (without extension) located in the 'raw' resource folder.
     * @return The JSON data as a string.
     */
    @SuppressLint("DiscouragedApi")
    fun loadJson(fileName: String): String {
        // Get the package name of the app
        val packageName = context.packageName
        // Get the resource ID of the raw resource file
        val resourceId = context.resources.getIdentifier(fileName, "raw", packageName)
        // Open an input stream to read the raw resource file
        val inputStream = context.resources.openRawResource(resourceId)
        // Use a buffered reader to efficiently read the text from the input stream
        return inputStream.bufferedReader().use { it.readText() }
    }

}


