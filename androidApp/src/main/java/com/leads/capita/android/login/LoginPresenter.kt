package com.leads.capita.android.login

import android.content.Context
import com.leads.capita.account.AccountTransaction
import com.leads.capita.security.IdentityErrorResponse
import com.leads.capita.security.IdentitySuccessResponse
import com.leads.capita.service.security.SecurityFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

// Sealed class to represent different authentication results
sealed class AuthResult {
    object Success : AuthResult()
    data class Invalid(val errorMessage: String): AuthResult()
}

// Presenter class for handling login functionality
class LoginPresenter {
    /**
     * Performs login authentication.
     *
     * @param context The context.
     * @param username The provided username.
     * @param password The provided password.
     * @return An instance of AuthResult representing the authentication result.
     */
    fun login(
        context: Context,
        username: String,
        password: String,
    ): AuthResult {
        val identityService = SecurityFactory.getIdentityService("LEADS")
        val identityLoginResponse:String=identityService.authenticate(username, password)
        val jsonObject = Json.parseToJsonElement(identityLoginResponse ?: "").jsonObject
        var loginSuccessResponse:IdentitySuccessResponse?=null
        var loginErrorResponse: IdentityErrorResponse?=null
         val token = jsonObject["token"]?.jsonPrimitive?.contentOrNull
        return if(token?.isNotEmpty() == true){
            loginSuccessResponse=Json.decodeFromString<IdentitySuccessResponse>(identityLoginResponse)
            AuthResult.Success
        }else{
            loginErrorResponse=Json.decodeFromString<IdentityErrorResponse>(identityLoginResponse)

                AuthResult.Invalid(loginErrorResponse.detail)

        }
    }
}