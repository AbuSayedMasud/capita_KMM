package com.leads.capita.security

import kotlinx.serialization.Serializable

@Serializable
data class IdentityErrorResponse(
    val details: List<String>,
    val status: Int,
    val title: String,
    val type: String
)