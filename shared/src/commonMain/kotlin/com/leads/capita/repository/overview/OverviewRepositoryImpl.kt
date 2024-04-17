package com.leads.capita.repository.overview


import com.leads.capita.market.Ticker
import com.leads.capita.market.overview.OverviewRepository
import com.leads.capita.market.overview.Participation
import com.leads.capita.market.overview.Status


class OverviewRepositoryImpl : OverviewRepository {

    override fun getStatus(): List<Status> {
        TODO("Not yet implemented")
    }

    override fun getTradeVolume(): List<Ticker> {
        TODO("Not yet implemented")
    }

    override fun getParticipation(): List<Participation> {
        TODO("Not yet implemented")
    }

    override fun createStatus(status: List<Status>) {
        TODO("Not yet implemented")
    }

    override fun createVolume(status: List<Ticker>) {
        TODO("Not yet implemented")
    }

    override fun createParticipation(status: List<Participation>) {
        TODO("Not yet implemented")
    }
}
