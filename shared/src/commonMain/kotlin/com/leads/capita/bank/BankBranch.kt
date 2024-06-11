package com.leads.capita.bank

import kotlinx.serialization.Serializable

@Serializable
data class BankBranch(
    val address: String?=null,
    val bankId: Int?=0,
    val branchId: Int?=0,
    val branchName: String?=null,
    val contactPerson: String?=null,
    val phone: String?=null,
    val routingNo: String?=null,
    val status: String?=null
)