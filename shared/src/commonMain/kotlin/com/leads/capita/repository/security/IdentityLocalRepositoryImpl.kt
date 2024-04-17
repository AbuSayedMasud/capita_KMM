package com.leads.capita.repository.security

import com.leads.capita.api.security.Identity
import com.leads.capita.api.security.IdentityRepository


class IdentityLocalRepositoryImpl : IdentityRepository {

    private val identityList = listOf(
        Identity("capita", "Capita@1234"),
    )

    override fun userExists(username: String): Boolean {
        return identityList.any { it.username == username }
    }

    override fun getToken(username: String, password: String): String {
        return if (username == "capita" && password == "Capita@1234") {
            "hhfghfdg46456456"
        } else {
            ""
        }
    }

    override fun getBiometricRegistrationToken(username: String, password: String): String {
        return if (username == "capita" && password == "Capita@1234") {
            "okay"
        } else {
            ""
        }
    }
    override fun getPasswordRecoveryToken(
        username: String,
        dateOfBirth: String,
        mobileNumber: String,
        email: String,
    ): Boolean {
        return true
    }

    override fun getRegistrationToken(
        username: String,
        firstname: String,
        lastname: String,
        email: String,
        mobileNumber: String,
        accountCode: String,
        password: String,
        confirmPassword: String,
    ): String {
        return "hello"
    }
}
