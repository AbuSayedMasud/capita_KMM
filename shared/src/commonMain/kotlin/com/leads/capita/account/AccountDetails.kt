package com.leads.capita.account

import com.leads.capita.customerProfile.Customer
import kotlinx.serialization.Serializable

@Serializable
data class AccountDetails(
    val accountCode: String,
    val bank: Bank,
    val boId: String,
    val customers: List<Customer>
)