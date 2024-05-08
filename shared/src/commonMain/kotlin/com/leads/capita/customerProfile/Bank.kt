package com.leads.capita.customerProfile

import kotlinx.serialization.Serializable

@Serializable
data class Bank(
    val accountNumber: String
)