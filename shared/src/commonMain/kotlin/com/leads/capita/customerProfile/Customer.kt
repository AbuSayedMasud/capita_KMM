package com.leads.capita.customerProfile

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val addresses: List<CustomerAddress>,
    val birthDate: String,
    val customerType: String,
    val fatherHusbandName: String,
    val gender: String,
    val lastName: String,
    val motherName: String,
    val nationalId: String,
    val nationality: String,
    val passportExpireDate: String,
    val passportIssueDate: String,
    val passportIssuePlace: String,
    val passportNumber: String,
    val surname: String,
    val taxId: String,
    val title: String
)