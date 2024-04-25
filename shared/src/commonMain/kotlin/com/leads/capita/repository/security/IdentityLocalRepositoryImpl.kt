package com.leads.capita.repository.security


import com.leads.capita.repository.RestUtil.BASE_URL
import com.leads.capita.repository.RestUtil.getClient
import com.leads.capita.security.Identity
import com.leads.capita.security.IdentityRepository
import com.leads.capita.security.IdentityErrorResponse
import com.leads.capita.service.security.AuthTokenManager
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

var authToken: String? = null
class IdentityLocalRepositoryImpl : IdentityRepository {
    private val TOKEN_PATH: String = "/tokens"
    private val identityList = listOf(
        Identity("capita", "Capita@1234"),
    )

    override fun userExists(username: String): Boolean {
        return identityList.any { it.username == username }
    }

    @OptIn(InternalAPI::class)
    override fun getToken(username: String, password: String): String {
        val client = getClient()
        var responseContent: String? = null
        /* try {*/
        runBlocking {

            responseContent =
                client.post("$BASE_URL$TOKEN_PATH") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        Identity(
                            username = username,
                            password = password
                        )
                    )
                }.body()


            val jsonObject = Json.parseToJsonElement(responseContent ?: "").jsonObject
            val token = jsonObject["token"]?.jsonPrimitive?.contentOrNull
//                if (token.equals("")){
//                   error  = Json.parseToJsonElement(loginError?.detail ?: "").jsonObject.toString()
//
//                }
            //Logger.d("afffadgdgdfhfdjgsdshdf", responseContent.toString())
           authToken=token
            println("afffadgdgdfhfdjgsdshdf" + authToken.toString())

            AuthTokenManager.updateAuthToken("Bearer "+token)

            println("AuthToken: ${AuthTokenManager.getAuthToken()}")


        }

        return responseContent.toString()
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
