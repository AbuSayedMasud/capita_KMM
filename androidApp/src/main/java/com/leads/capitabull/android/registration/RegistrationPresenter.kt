package com.leads.capitabull.android.registration


class RegistrationPresenter {
    fun registration(username: String, firstname: String, lastname: String, email: String, mobileNumber: String, accountCode: String, password: String, confirmPassword: String): Boolean {
//        val identityService = SecurityFactory.getIdentityService("LEADS")

//        return identityService.registered(username, firstname, lastname, email, mobileNumber, accountCode, password, confirmPassword)
        return true
    }
}
