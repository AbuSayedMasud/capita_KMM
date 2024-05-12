package com.leads.capita.repository.payment

import com.leads.capita.payment.PaymentRepository
import com.leads.capita.repository.DatabaseDriverFactory

class PaymentRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory):PaymentRepository{
    override fun getPayment(): String {
        TODO("Not yet implemented")
    }

    override fun getPaymentStatus(): String {
        TODO("Not yet implemented")
    }
}