package com.leads.capita.security

import kotlinx.serialization.Serializable

@Serializable
data class IdentitySuccessResponse (
    var token: String,
    var useRef: String
)