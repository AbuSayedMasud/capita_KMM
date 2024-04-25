package com.leads.capita.repository.customerProfile

import com.leads.capita.customerProfile.CustomerProfileRepository
import com.leads.capita.customerProfile.CustomerProfileResponse
import com.leads.capita.repository.DatabaseDriverFactory

class CustomerRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory) :
    CustomerProfileRepository {
    override fun getCustomerProfile(): String {

        return ""
    }

}