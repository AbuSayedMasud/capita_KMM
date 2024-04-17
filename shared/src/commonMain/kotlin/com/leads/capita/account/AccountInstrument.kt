package com.leads.capita.account
import kotlinx.serialization.Serializable
@Serializable
class AccountInstrument(
    val instrumentIndex: Double,
    val longName: String,
    val shortName: String,
    val value: Double,
    val closedPrice: Double,
    val change: Double,
    val changeIcon: Double,
    val totalQuantity: Double,
    val salableQuantity: Double,
    val averageCost: Double,
    val totalCost: Double,
    val closePrice: Double,
    val unrealizedGain: Double,
    val gainPercent: Double,
    val costValue: Double,
)