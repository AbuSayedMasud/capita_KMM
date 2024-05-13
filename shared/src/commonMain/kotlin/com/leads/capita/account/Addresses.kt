package com.leads.capita.account

import kotlinx.serialization.Serializable

@Serializable
data class Addresses(
    var address1: String,
    var address2: String,
    var address3: String,
    var addressType: String,
    var city: String,
    var contactPerson: String,
    var country: String,
    var email: String,
    var mobileNumber: String,
    var phoneNumber: String,
    var state: String,
    var zipCode: String
)
