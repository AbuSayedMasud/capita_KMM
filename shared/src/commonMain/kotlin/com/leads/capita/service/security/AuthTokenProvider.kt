package com.leads.capita.service.security

interface AuthTokenProvider {
    fun getAuthToken(): String?
}