package com.leads.capita.service.security

object IdentityTokenManager : IdentityUserProvider {
    private var authToken: String? = null
    private var userRef: String? = null

    override fun getAuthToken(): String? {
        return authToken
    }

    fun updateAuthToken(token: String?) {
        authToken = token
    }

    override fun getAuthUserRef(): String? {
        return userRef
    }

    fun userRef(useRef:String){
        userRef = useRef
    }


}