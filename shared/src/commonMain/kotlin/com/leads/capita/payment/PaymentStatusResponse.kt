package com.leads.capita.payment

import kotlinx.serialization.Serializable

@Serializable
data class PaymentStatusResponse (
    val accountCode:String,
    val amount:String,
    val description:String,
    val status:String,
    val transactionCode:String
)