package com.leads.capita.payment

import kotlinx.serialization.Serializable

@Serializable
data class PaymentResponse(
    val transactionRef: String
)