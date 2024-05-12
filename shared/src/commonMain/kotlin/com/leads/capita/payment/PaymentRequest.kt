package com.leads.capita.payment

import kotlinx.serialization.Serializable

@Serializable
data class PaymentRequest(
    val accountCode:String,
    val transactionCode:String,
    val amount:String,
    val description:String
)