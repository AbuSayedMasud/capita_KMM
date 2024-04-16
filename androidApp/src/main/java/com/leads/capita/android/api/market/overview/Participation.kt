package com.leads.capita.market.overview

import kotlinx.serialization.Serializable

@Serializable
class Participation(
    val type: String,
    val issuesAdvanced: String,
    val issuesDeclined: String,
    val issuesUnchanged: String,
    val market: String,
)
