package com.leads.capita.api.news
import kotlinx.serialization.Serializable
@Serializable
data class News (
    val title:String,
    val description:String,
    val date:String
)
