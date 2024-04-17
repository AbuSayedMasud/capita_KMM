package com.leads.capita.repository.overview

import com.leads.capita.CapitaDb
import com.leads.capita.DatabaseDriverFactory
import com.leads.capita.api.market.Ticker
import com.leads.capita.api.market.overview.OverviewRepository
import com.leads.capita.api.market.overview.Participation
import com.leads.capita.api.market.overview.Status


class OverviewLocalRepositoryImpl(private var databaseDriverFactory: DatabaseDriverFactory) : OverviewRepository {

    override fun getStatus(): List<Status> {
        val db = CapitaDb(databaseDriverFactory.createDriver())
        return db.statusDataQueries.getStatusData()
            .executeAsList()
            .map { statusData ->
                Status(
                    type = statusData?.type!!,
                    market = statusData?.market!!,
                    status = statusData?.status!!,
                    update = statusData?.updatedata!!,
                )
            }
    }

    override fun getTradeVolume(): List<Ticker> {
        val db = CapitaDb(databaseDriverFactory.createDriver())
        return db.volumnDataQueries.getTradeVolumnData()
            .executeAsList()
            .map { volumeData ->
                Ticker(
                    type = volumeData?.type!!,
                    market = volumeData?.market!!,
                    symbol = volumeData?.symbol!!,
                    name = volumeData?.name!!,
                    open = volumeData?.open_!!,
                    high = volumeData?.high!!,
                    low = volumeData?.low!!,
                    close = volumeData?.close!!,
                    volume = volumeData?.volume!!,
                    value = volumeData?.value_!!,
                    trade = volumeData?.trade!!,
                    change = volumeData?.change!!,
                    changePercent = volumeData?.changePercent!!,
                    changeDirection = volumeData?.changeDirection!!,
                )
            }
    }

    override fun getParticipation(): List<Participation> {
        val db = CapitaDb(databaseDriverFactory.createDriver())
        return db.participationDataQueries.getParticipationData()
            .executeAsList()
            .map { participationData ->
                Participation(
                    type = participationData?.type!!,
                    issuesAdvanced = participationData?.issuesAdvanced!!,
                    issuesDeclined = participationData?.issuesDeclined!!,
                    issuesUnchanged = participationData?.issuesUnchanged!!,
                    market = participationData?.market!!,
                )
            }
    }

    override fun createStatus(status: List<Status>) {
        val db = CapitaDb(databaseDriverFactory.createDriver())
//        db.statusDataQueries.deleteStatusData()
        status.forEach { status ->
            val existingStatus =
                db.statusDataQueries.getStatusByUnique(status.market)
            if (existingStatus.executeAsList().isEmpty()) {
                db.statusDataQueries.insertStatusData(
                    type = status.type,
                    market = status.market,
                    status = status.status,
                    updatedata = status.update,
                )
            }
        }
    }
    override fun createVolume(volume: List<Ticker>) {
        val db = CapitaDb(databaseDriverFactory.createDriver())
        volume.forEach { volume ->
            val existingVolumn =
                db.volumnDataQueries.getVolumnDataByUnique(volume.market)
            if (existingVolumn.executeAsList().isEmpty()) {
                db.volumnDataQueries.insertTradeVolumnData(
                    type = volume.type,
                    market = volume.market,
                    symbol = volume.symbol,
                    name = volume.name,
                    open_ = volume.open,
                    high = volume.high,
                    low = volume.low,
                    close = volume.close,
                    volume = volume.volume,
                    value_ = volume.value,
                    trade = volume.trade,
                    change = volume.change,
                    changePercent = volume.changePercent,
                    changeDirection = volume.changeDirection,
                )
            } 
        }
    }

    override fun createParticipation(participation: List<Participation>) {
        val db = CapitaDb(databaseDriverFactory.createDriver())
        participation.forEach { participationData ->
            val existingParticipation =
                db.participationDataQueries.getParticipationByUnique(participationData.market)
            if (existingParticipation.executeAsList().isEmpty()) {
                db.participationDataQueries.insertParticipationData(
                    type = participationData.type,
                    issuesAdvanced = participationData.issuesAdvanced,
                    issuesDeclined = participationData.issuesDeclined,
                    issuesUnchanged = participationData.issuesUnchanged,
                    market = participationData.market,
                )
            } 
        }
    }
}
