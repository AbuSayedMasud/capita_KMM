package com.leads.capita.service.customerProfile

import com.leads.capita.customerProfile.CustomerProfileResponse
import com.leads.capita.customerProfile.CustomerProfileService
import com.leads.capita.repository.DatabaseDriverFactory

class CustomerServiceImpl (private val databaseDriverFactory: DatabaseDriverFactory) : CustomerProfileService {
    override fun getCustomerProfile(): List<CustomerProfileResponse> {
        return emptyList()
    }
}