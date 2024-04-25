package com.leads.capita.repository.customerProfile

import com.leads.capita.customerProfile.CustomerProfileRepository
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.repository.RestUtil
import com.leads.capita.repository.RestUtil.getClient
import com.leads.capita.service.security.IdentityTokenManager
import com.leads.capita.service.security.IdentityUserProvider
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking

class CustomerLocalRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory) : CustomerProfileRepository {

    private val PROFILE_PATH: String = "/customers/profiles/${IdentityTokenManager.getAuthUserRef()}"

    override fun getCustomerProfile(): String {
        var response: String? = null

        runBlocking {
            try {
                response = getClient().get(urlString = "${RestUtil.BASE_URL}$PROFILE_PATH").body()
            } catch (e: Exception) {
            }
        }

        return response.toString()
    }

}