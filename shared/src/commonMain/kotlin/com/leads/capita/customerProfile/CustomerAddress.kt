package com.leads.capita.customerProfile

import kotlinx.serialization.Serializable

@Serializable
data class CustomerAddress(
    val address1: String,
    val address2: String,
    val address3: String,
    val addressType: String,
    val city: String,
    val contactPerson: String,
    val country: String,
    val email: String,
    val mobileNumber: String,
    val phoneNumber: String,
    val state: String,
    val zipCode: String
)