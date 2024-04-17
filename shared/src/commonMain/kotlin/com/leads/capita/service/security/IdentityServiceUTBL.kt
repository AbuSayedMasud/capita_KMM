package com.leads.capita.service.security

import com.leads.capita.security.IdentityService
import com.leads.capita.repository.security.IdentityRepositoryImpl


class IdentityServiceUTBL : IdentityService {
    override fun userExists(username: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun authenticate(username: String, password: String): Boolean {
        val userRepository = IdentityRepositoryImpl()
        val apiResponse = userRepository.getToken(username, password)
        return apiResponse!!.isNotEmpty()
    }

    override fun biometricRegistered(username: String, password: String): String {
        TODO("Not yet implemented")
    }

    override fun passwordRecovery(
        username: String,
        dateOfBirth: String,
        mobileNumber: String,
        email: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun registered(
        username: String,
        firstname: String,
        lastname: String,
        email: String,
        mobileNumber: String,
        accountCode: String,
        password: String,
        confirmPassword: String,
    ): Boolean {
        TODO("Not yet implemented")
    }
}
