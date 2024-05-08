package com.leads.capita.customerProfile

import kotlinx.serialization.Serializable

@Serializable
data class AccountDetails(
    val accountCode: String,
    val bank: Bank,
    val boId: String,
    val customers: List<Customer>
)