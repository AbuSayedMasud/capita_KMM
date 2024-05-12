package com.leads.capita.desposit

import kotlinx.serialization.Serializable

@Serializable
data class DepositStatusResponse (
    val accountCode: String,
    val amount:String,
    val description:String,
    val status:String,
    val transactionCode:String,
    val transactionDate: String,
    val transactionRef:String,
)