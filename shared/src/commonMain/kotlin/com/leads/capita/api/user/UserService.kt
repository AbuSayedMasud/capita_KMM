package com.leads.capita.api.user

interface UserService {
    suspend fun authenticate(username: String, password: String): Boolean
}