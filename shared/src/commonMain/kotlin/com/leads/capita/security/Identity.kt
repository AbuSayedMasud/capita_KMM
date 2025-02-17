package com.leads.capita.security

import kotlinx.serialization.Serializable

@Serializable
data class Identity(
    val username: String,
    val password: String,
)
