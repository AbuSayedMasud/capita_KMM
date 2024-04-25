package com.leads.capita.service.customerProfile

import com.leads.capita.customerProfile.CustomerProfileRepository
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.repository.customerProfile.CustomerLocalRepositoryImpl
import com.leads.capita.repository.customerProfile.CustomerRepositoryImpl
import com.leads.capita.service.RuntimeProfile

object CustomerFactory {
    fun getCustomerProfileRepository(databaseDriverFactory: DatabaseDriverFactory): CustomerProfileRepository {
        return if (RuntimeProfile.getCurrentRuntime() == RuntimeProfile.LIVE_RUNTIME) {
            CustomerRepositoryImpl(databaseDriverFactory)
        } else {
            CustomerLocalRepositoryImpl(databaseDriverFactory)
        }
    }


}