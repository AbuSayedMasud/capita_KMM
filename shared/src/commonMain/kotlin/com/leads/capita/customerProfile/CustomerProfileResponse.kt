package com.leads.capita.customerProfile

import kotlinx.serialization.Serializable

@Serializable
data class CustomerProfileResponse(
    var accounts: ArrayList<Accounts>,
    var addresses: ArrayList<Addresses>,
    var birthDate: String,
    var customerId: String,
    var customerType: String,
    var fatherHusbandName: String,
    var gender: String,
    var lastName: String,
    var motherName: String,
    var nationalId: String,
    var nationality: String,
    var passportExpireDate: String,
    var passportIssueDate: String,
    var passportIssuePlace: String,
    var passportNumber: String,
    var surname: String,
    var taxId: String,
    var title: String
)
