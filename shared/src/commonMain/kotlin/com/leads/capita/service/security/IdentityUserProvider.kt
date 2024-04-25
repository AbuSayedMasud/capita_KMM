package com.leads.capita.service.security

interface IdentityUserProvider {
    fun getAuthToken(): String?

    fun getAuthUserRef():String?
}