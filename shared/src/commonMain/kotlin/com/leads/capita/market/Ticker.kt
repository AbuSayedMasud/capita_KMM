package com.leads.capita.market
import kotlinx.serialization.Serializable
@Serializable
class Ticker(
    val type: String,
    var market: String,
    val symbol: String,
    val name: String,
    val open: String,
    val high: String,
    val low: String,
    val close: String,
    val volume: String,
    val value: String,
    val trade: String,
    val change: String,
    val changePercent: String,
    val changeDirection: String
)