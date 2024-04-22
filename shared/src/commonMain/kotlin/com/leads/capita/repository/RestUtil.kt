package com.leads.capita.repository

import com.leads.capita.repository.security.authToken
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object RestUtil {
    val token = authToken

//    val token= TokenManager.getToken()?.token
    const val BASE_URL: String = "http://192.168.10.42:7001/capitapi"   //Internal Dev URL
    fun getClient(): HttpClient {
        return HttpClient() {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                    },
                )
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens("$token", "")
                    }
                }
            }
        }
    }
}
