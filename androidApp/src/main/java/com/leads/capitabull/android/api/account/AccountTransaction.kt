package com.leads.capitabull.android.api.account
import kotlinx.serialization.Serializable
@Serializable
class AccountTransaction(
    val transferType: String,
    val totalAmount: Double?,
    val description: String,
    val quantity: String,
    val date: String,
    val identity: String,
)
