package com.leads.capita.android.mockJsonLoader

import android.annotation.SuppressLint
import android.content.Context
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.account.AccountBalance
import com.leads.capita.account.AccountInstrument
import com.leads.capita.account.AccountReceivable
import com.leads.capita.account.AccountTransaction
import com.leads.capita.market.Ticker
import com.leads.capita.market.overview.Participation
import com.leads.capita.market.overview.Status
import com.leads.capita.news.News
import com.leads.capita.service.MockService
import kotlinx.serialization.json.Json

class MockLoader(private val context: Context) {

    fun init() {
        var isLoaded = false
        if (isLoaded) {
            return
        }

        val databaseDriverFactory: DatabaseDriverFactory = DatabaseDriverFactory(context)
        val service = MockService(databaseDriverFactory)

        // account Transaction
        val jsonTransactionContent = loadJson("account_transaction")
        // Deserialize JSON content into a list of AccountTransaction objects
        val transactions = Json.decodeFromString<List<AccountTransaction>>(jsonTransactionContent)
        service.loadAccountTransaction(transactions)

        // account Balance
        val jsonBalanceContent = loadJson("account_balance");
        val balances = Json.decodeFromString<List<AccountBalance>>(jsonBalanceContent)
        service.loadAccountBalance(balances)

        // account instrument
        val jsonAccountInstrumentContent = loadJson("account_instruments");
        val instruments = Json.decodeFromString<List<AccountInstrument>>(jsonAccountInstrumentContent)
        service.loadAccountInstrument(instruments)

        // account Receivable
        val jsonReceivableContent = loadJson("account_receivable");
        val receivables = Json.decodeFromString<List<AccountReceivable>>(jsonReceivableContent)
        service.loadAccountReceivable(receivables)

        // index
        val jsonIndexContent = loadJson("index");
        val indices = Json.decodeFromString<List<Ticker>>(jsonIndexContent)
        service.loadIndices(indices)

        // instrument
        val jsonInstrumentContent = loadJson("instrument");
        val instrument = Json.decodeFromString<List<Ticker>>(jsonInstrumentContent)
        service.loadInstrument(instrument)

        // news
        val jsonNewsContent = loadJson("news");
        val news = Json.decodeFromString<List<News>>(jsonNewsContent)
        service.loadNews(news)

        // participation
        val jsonParticipationContent=loadJson("participation_list")
        val participation = Json.decodeFromString<List<Participation>>(jsonParticipationContent)
        service.loadParticipation(participation)

        // status
        val jsonStatusContent=loadJson("status_list")
        val status = Json.decodeFromString<List<Status>>(jsonStatusContent)
        service.loadStatus(status)

        // volume
        val jsonVolumeContent=loadJson("volume_list");
        val volume = Json.decodeFromString<List<Ticker>>(jsonVolumeContent)
        service.loadvolume(volume)

        true.also { isLoaded = it }
    }

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
