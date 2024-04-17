package com.leads.capita.service


import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.account.AccountBalance
import com.leads.capita.account.AccountInstrument
import com.leads.capita.account.AccountReceivable
import com.leads.capita.account.AccountTransaction
import com.leads.capita.market.Ticker
import com.leads.capita.market.overview.Participation
import com.leads.capita.market.overview.Status
import com.leads.capita.news.News
import com.leads.capita.service.account.AccountFactory
import com.leads.capita.service.instrument.InstrumentFactory
import com.leads.capita.service.overview.OverviewFactory
import com.leads.capita.service.news.NewsFactory

class MockService(private val databaseDriverFactory: DatabaseDriverFactory) {
    fun loadAccountTransaction(transactions: List<AccountTransaction>) {
        val repo = AccountFactory.getRepository(databaseDriverFactory)
        repo.createAccountTransaction(transactions)
    }
    fun loadAccountBalance(balances: List<AccountBalance>) {
        val repo = AccountFactory.getRepository(databaseDriverFactory)
        repo.createAccountBalance(balances)
    }
    fun loadAccountInstrument(instruments: List<AccountInstrument>) {
        val repo = AccountFactory.getRepository(databaseDriverFactory)
        repo.createAccountInstrument(instruments)
    }
    fun loadAccountReceivable(receivables: List<AccountReceivable>) {
        val repo = AccountFactory.getRepository(databaseDriverFactory)
        repo.createAccountReceivable(receivables)
    }
    fun loadIndices(indices: List<Ticker>) {
        val repo = InstrumentFactory.getRepository(databaseDriverFactory)
        repo.createIndices(indices)
    }
    fun loadInstrument(instrument: List<Ticker>) {
        val repo = InstrumentFactory.getRepository(databaseDriverFactory)
        repo.createInstrument(instrument)
    }
    fun loadNews(news: List<News>) {
        val repo = NewsFactory.getRepository(databaseDriverFactory)
        repo.createNews(news)
    }
    fun loadParticipation(participation: List<Participation>) {
        val repo = OverviewFactory.getRepository(databaseDriverFactory)
        repo.createParticipation(participation)
    }
    fun loadStatus(status: List<Status>) {
        val repo = OverviewFactory.getRepository(databaseDriverFactory)
        repo.createStatus(status)
    }
    fun loadvolume(volume: List<Ticker>) {
        val repo = OverviewFactory.getRepository(databaseDriverFactory)
        repo.createVolume(volume)
    }
}
