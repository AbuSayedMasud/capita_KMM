package com.leads.capita.customerProfile

import kotlinx.serialization.Serializable

@Serializable
data class Accounts(
    var accountCode: String,
    var accountType: String,
    var boId: String,
    var operationType: String
)
