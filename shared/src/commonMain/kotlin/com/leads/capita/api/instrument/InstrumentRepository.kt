package com.leads.capita.api.instrument


import com.leads.capita.api.market.Ticker

interface InstrumentRepository {
    fun getInstrument(): List<com.leads.capita.api.market.Ticker>
    fun getIndex(): List<com.leads.capita.api.market.Ticker>
    fun createIndices(indices: List<com.leads.capita.api.market.Ticker>)
    fun createInstrument(instrument: List<com.leads.capita.api.market.Ticker>)
}
