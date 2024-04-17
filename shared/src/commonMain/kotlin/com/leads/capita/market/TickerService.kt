package com.leads.capita.market



interface TickerService {
    fun getTicker(type: String): List<Ticker>
}