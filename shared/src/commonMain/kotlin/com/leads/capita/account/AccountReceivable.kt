package com.leads.capita.account
import kotlinx.serialization.Serializable
@Serializable
class AccountReceivable(
    val name: String,
    val company1: String,
    val company2: String,
    val shareQuantity1: Double,
    val shareQuantity2: Double,
    val amount1: Double,
    val amount2: Double,
)
