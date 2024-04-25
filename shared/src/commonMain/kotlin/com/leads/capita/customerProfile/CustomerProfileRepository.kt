package com.leads.capita.customerProfile

interface CustomerProfileRepository {
    fun getCustomerProfile(): List<CustomerProfileResponse>
}