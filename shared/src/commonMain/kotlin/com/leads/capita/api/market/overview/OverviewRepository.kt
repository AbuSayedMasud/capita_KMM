package com.leads.capita.api.market.overview


import com.leads.capita.api.market.Ticker

interface OverviewRepository {
    fun getStatus(): List<com.leads.capita.api.market.overview.Status>
    fun getTradeVolume(): List<com.leads.capita.api.market.Ticker>
    fun getParticipation(): List<com.leads.capita.api.market.overview.Participation>
    fun createStatus(status: List<com.leads.capita.api.market.overview.Status>)
    fun createVolume(volume: List<com.leads.capita.api.market.Ticker>)
    fun createParticipation(participation: List<com.leads.capita.api.market.overview.Participation>)
}
