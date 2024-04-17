package com.leads.capita.service.instrument


import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.market.Ticker
import com.leads.capita.market.TickerService


class InstrumentServiceImpl(private var databaseDriverFactory: DatabaseDriverFactory) :
    TickerService {

    override fun getTicker(type: String): List<Ticker> {
        if (type == "Index") {
            val repository = InstrumentFactory.getRepository(databaseDriverFactory)
            return repository.getIndex()
        } else {
            val repository = InstrumentFactory.getRepository(databaseDriverFactory)
            return repository.getInstrument()
        }
    }
}
