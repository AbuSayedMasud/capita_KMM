package com.leads.capita.service.payment

import com.leads.capita.payment.PaymentService
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.service.account.AccountFactory

class PaymentServiceImpl(private val databaseDriverFactory: DatabaseDriverFactory):PaymentService{
    override fun getPaymentServices(): String {
        val repository = PaymentFactory.getRepository(databaseDriverFactory);
        return repository.getPayment()
    }

    override fun getPaymentStatusServices(): String {
        val repository = PaymentFactory.getRepository(databaseDriverFactory);
        return repository.getPaymentStatus()
    }
}