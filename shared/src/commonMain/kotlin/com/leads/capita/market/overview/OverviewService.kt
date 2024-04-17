package com.leads.capita.market.overview



interface OverviewService {
    fun listStatus(): List<Status>
    fun listParticipation(): List<Participation>
}