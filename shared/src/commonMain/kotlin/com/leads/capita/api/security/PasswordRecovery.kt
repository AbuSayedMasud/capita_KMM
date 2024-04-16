package com.leads.capita.api.security

data class PasswordRecovery(
    val userName: String,
    val dateOfBirth: String,
    val mobileNumber: String,
    val email: String,
)
