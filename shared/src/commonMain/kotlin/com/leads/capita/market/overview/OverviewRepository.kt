package com.leads.capita.market.overview


import com.leads.capita.market.Ticker

interface OverviewRepository {
    fun getStatus(): List<Status>
    fun getTradeVolume(): List<Ticker>
    fun getParticipation(): List<Participation>
    fun createStatus(status: List<Status>)
    fun createVolume(volume: List<Ticker>)
    fun createParticipation(participation: List<Participation>)
}
