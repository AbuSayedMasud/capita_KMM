package com.leads.capita.account

import kotlinx.serialization.Serializable

@Serializable
data class Instrument(
    val costPrice: Double?,
    val costValue: Double?,
    val gr: String?,
    val marginable: Boolean?,
    val marketPrice: Double?,
    val marketValue: Double?,
    val matureQuantity: Long?,
    val quantity: Int?,
    val symbole: String?,
    val unrealizedGain: Double?
)