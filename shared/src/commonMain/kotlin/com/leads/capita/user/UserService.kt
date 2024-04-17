package com.leads.capita.user

interface UserService {
    suspend fun authenticate(username: String, password: String): Boolean
}