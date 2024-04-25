package com.leads.capita.security

interface IdentityService {
    fun userExists(username: String): Boolean
    fun authenticate(username: String, password: String): String
    fun biometricRegistered(username: String, password: String): String
    fun passwordRecovery(username: String, dateOfBirth: String, mobileNumber: String, email: String): Boolean
    fun registered(username: String, firstname: String, lastname: String, email: String, mobileNumber: String, accountCode: String, password: String, confirmPassword: String): Boolean
}
