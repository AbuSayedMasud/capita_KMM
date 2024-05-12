package com.leads.capita.payment

import kotlinx.serialization.Serializable

@Serializable
class PaymentStatusRequest(val transactionRef: String)