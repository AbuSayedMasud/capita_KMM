package com.leads.capita.api.market



interface TickerService {
    fun getTicker(type: String): List<com.leads.capita.api.market.Ticker>
}