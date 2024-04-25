package com.leads.capita.android.login

import android.content.Context
import com.leads.capita.service.security.SecurityFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

// Sealed class to represent different authentication results
sealed class AuthResult {
    object Success : AuthResult()
    object InvalidUsername : AuthResult()
    object InvalidPassword : AuthResult()
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
         val token = jsonObject["token"]?.jsonPrimitive?.contentOrNull
        return if(token?.isNotEmpty() == true){
            AuthResult.Success
        }else{
            if (!identityService.userExists(username)) {
                AuthResult.InvalidUsername
            } else {
                AuthResult.InvalidPassword
            }
        }
    }
}