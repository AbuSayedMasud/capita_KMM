package com.leads.capita.account

import kotlinx.serialization.Serializable

@Serializable
data class Bank(
    val accountNumber: String
)