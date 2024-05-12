package com.leads.capita.desposit

import kotlinx.serialization.Serializable

@Serializable
class DepositRequest(
    val accountCode: String,
    val routingNumber: String,
    val transactionDate: String,
    val transactionCode:String,
    val transactionRef:String,
    val amount:Int,
    val description:String,
    val status:String
)