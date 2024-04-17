package com.leads.capita.service.security

import com.leads.capita.api.security.IdentityRepository
import com.leads.capita.api.security.IdentityService
import com.leads.capita.repository.security.IdentityLocalRepositoryImpl
import com.leads.capita.repository.security.IdentityRepositoryImpl

import com.leads.capita.service.RuntimeProfile
import com.leads.capita.service.RuntimeProfile.LIVE_RUNTIME

object SecurityFactory {

    fun getIdentityService(name: String): IdentityService {
        return if (name == "UTBL") {
            IdentityServiceUTBL()
        } else {
            IdentityServiceImpl()
        }
    }

    fun getIdentityService(): IdentityService {
        return getIdentityService("LEADS")
    }

    fun getIdentityRepository(): IdentityRepository {
        if (RuntimeProfile.getCurrentRuntime() == LIVE_RUNTIME) {
            return IdentityRepositoryImpl()
        } else {
            return IdentityLocalRepositoryImpl()
        }
    }
}
