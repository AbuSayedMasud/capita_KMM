package com.leads.capita.desposit

import kotlinx.serialization.Serializable

@Serializable
data class DepositResponse(
    val referenceId: String,
)