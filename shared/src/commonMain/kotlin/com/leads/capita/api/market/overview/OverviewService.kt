package com.leads.capita.api.market.overview



interface OverviewService {
    fun listStatus(): List<com.leads.capita.api.market.overview.Status>
    fun listParticipation(): List<com.leads.capita.api.market.overview.Participation>
}