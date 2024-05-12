package com.leads.capita.payment

import kotlinx.serialization.Serializable

@Serializable
data class PaymentRequest(
    val accountCode:String,
    val transactionCode:String,
    val currency:String,
    val amount:Double,
    val description:String
)