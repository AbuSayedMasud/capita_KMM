package com.leads.capita.repository.instrument


import com.leads.capita.CapitaDb
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.instrument.InstrumentRepository
import com.leads.capita.market.Ticker


class InstrumentLocalRepositoryImpl(private var databaseDriverFactory: DatabaseDriverFactory) :
    InstrumentRepository {

    override fun getInstrument(): List<Ticker> {
        val db = CapitaDb(databaseDriverFactory.createDriver())
        return db.instrumentQueries.getInstrumentData()
            .executeAsList()
            .map { index ->
                Ticker(
                    type = index?.type!!,
                    market = index?.market!!,
                    symbol = index?.symbol!!,
                    name = index?.name!!,
                    open = index?.open_!!,
                    high = index?.high!!,
                    low = index?.low!!,
                    close = index?.close!!,
                    volume = index?.volume!!,
                    value = index?.value_!!,
                    trade = index?.trade!!,
                    change = index?.change!!,
                    changePercent = index?.changePercent!!,
                    changeDirection = index?.changeDirection!!,
                )
            }
    }

    override fun getIndex(): List<Ticker> {
        val db = CapitaDb(databaseDriverFactory.createDriver())
        return db.indexQueries.getIndexData()
            .executeAsList()
            .map { index ->
                Ticker(
                    type = index?.type!!,
                    market = index?.market!!,
                    symbol = index?.symbol!!,
                    name = index?.name!!,
                    open = index?.open_!!,
                    high = index?.high!!,
                    low = index?.low!!,
                    close = index?.close!!,
                    volume = index?.volume!!,
                    value = index?.value_!!,
                    trade = index?.trade!!,
                    change = index?.change!!,
                    changePercent = index?.changePercent!!,
                    changeDirection = index?.changeDirection!!,
                )
            }
    }

    override fun createIndices(indices: List<Ticker>) {
        val db = CapitaDb(databaseDriverFactory.createDriver())//        db.indexQueries.deleteIndexData()
        indices.forEach { index ->
            val existingIndex =
                db.indexQueries.getIndexByUniqueIndex(index.name)
            if (existingIndex.executeAsList().isEmpty()) {
                db.indexQueries.insertIndexData(
                    type = index.type,
                    market = index.market,
                    symbol = index.symbol,
                    name = index.name,
                    open_ = index.open,
                    high = index.high,
                    low = index.low,
                    close = index.close,
                    volume = index.volume,
                    value_ = index.value,
                    trade = index.trade,
                    change = index.change,
                    changePercent = index.changePercent,
                    changeDirection = index.changeDirection,
                )
            }
        }
    }

    override fun createInstrument(instrument: List<Ticker>) {
        val db = CapitaDb(databaseDriverFactory.createDriver())
//        db.instrumentQueries.deleteInstrumentData()
        instrument.forEach { instrument ->
            val existingInstrument =
                db.instrumentQueries.getInstrumentByUnique(instrument.name)
            if (existingInstrument.executeAsList().isEmpty()) {
                db.instrumentQueries.insertInstrumentData(
                    type = instrument.type,
                    market = instrument.market,
                    symbol = instrument.symbol,
                    name = instrument.name,
                    open_ = instrument.open,
                    high = instrument.high,
                    low = instrument.low,
                    close = instrument.close,
                    volume = instrument.volume,
                    value_ = instrument.value,
                    trade = instrument.trade,
                    change = instrument.change,
                    changePercent = instrument.changePercent,
                    changeDirection = instrument.changeDirection,
                )
            }
        }
    }
}
