package com.leads.capita.service.overview


import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.market.Ticker
import com.leads.capita.market.TickerService
import com.leads.capita.market.overview.OverviewService
import com.leads.capita.market.overview.Participation
import com.leads.capita.market.overview.Status
import com.leads.capita.service.overview.OverviewFactory

class OverviewServiceImpl(private var databaseDriverFactory: DatabaseDriverFactory) : TickerService,
   OverviewService {
    override fun getTicker(type: String): List<Ticker> {
        val repository = OverviewFactory.getRepository(databaseDriverFactory)
        return repository.getTradeVolume()
    }

    override fun listStatus(): List<Status> {
        val repository = OverviewFactory.getRepository(databaseDriverFactory)
        return repository.getStatus()
    }

    override fun listParticipation(): List<Participation> {
        val repository = OverviewFactory.getRepository(databaseDriverFactory)
        return repository.getParticipation()
    }
}
