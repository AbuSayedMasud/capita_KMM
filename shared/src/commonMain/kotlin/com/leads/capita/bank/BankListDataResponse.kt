package com.leads.capita.bank

import kotlinx.serialization.Serializable

@Serializable
data class BankListDataResponse (
   val bankId:Int?=0,
   val bankName:String?=null,
   val shortName:String?=null,
)