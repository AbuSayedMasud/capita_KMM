package com.leads.capita.instrument


import com.leads.capita.market.Ticker

interface InstrumentRepository {
    fun getInstrument(): List<Ticker>
    fun getIndex(): List<Ticker>
    fun createIndices(indices: List<Ticker>)
    fun createInstrument(instrument: List<Ticker>)
}
