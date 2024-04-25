package com.leads.capita.service.security

object AuthTokenManager : AuthTokenProvider {
    private var authToken: String? = null

    override fun getAuthToken(): String? {
        return authToken
    }

    fun updateAuthToken(token: String?) {
        authToken = token
    }
}