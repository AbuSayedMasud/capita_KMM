package com.leads.capita.repository.instrument

import com.leads.capita.api.instrument.InstrumentRepository
import com.leads.capita.api.market.Ticker


class InstrumentRepositoryImpl() : InstrumentRepository {
    override fun getInstrument(): List<Ticker> {
        TODO("Not yet implemented")
    }

    override fun getIndex(): List<Ticker> {
        TODO("Not yet implemented")
    }

    override fun createIndices(indices: List<Ticker>) {
        TODO("Not yet implemented")
    }

    override fun createInstrument(instrument: List<Ticker>) {
        TODO("Not yet implemented")
    }
}
