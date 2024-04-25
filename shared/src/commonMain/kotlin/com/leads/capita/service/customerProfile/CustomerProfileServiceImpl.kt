package com.leads.capita.service.customerProfile

import com.leads.capita.customerProfile.CustomerProfileService
import com.leads.capita.repository.DatabaseDriverFactory

class CustomerProfileServiceImpl (private val databaseDriverFactory: DatabaseDriverFactory) : CustomerProfileService {
    override fun getCustomerProfile(): String{
        val repository = CustomerProfileFactory.getCustomerProfileRepository(databaseDriverFactory);
        return repository.getCustomerProfile()
    }
}