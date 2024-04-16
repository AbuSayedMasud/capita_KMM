package com.leads.capita.api.market.overview

import kotlinx.serialization.Serializable

@Serializable
class Status(
    val type: String,
    val market: String,
    val status: String,
    val update: String,
)
