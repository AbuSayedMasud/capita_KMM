package com.leads.capita.security

import kotlinx.serialization.Serializable

@Serializable
data class IdentityErrorResponse(
    val detail: String,
    val status: Int,
    val title: String,
    val type: String
)