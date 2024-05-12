package com.leads.capita.service.payment

import com.leads.capita.payment.PaymentRepository
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.repository.account.AccountLocalRepositoryImpl
import com.leads.capita.repository.account.AccountRepositoryImpl
import com.leads.capita.repository.payment.PaymentLocalRepositoryImpl
import com.leads.capita.repository.payment.PaymentRepositoryImpl
import com.leads.capita.service.RuntimeProfile

object PaymentFactory {
    fun getRepository(databaseDriverFactory: DatabaseDriverFactory): PaymentRepository {
        return if (RuntimeProfile.getCurrentRuntime() == RuntimeProfile.LIVE_RUNTIME) {
            PaymentLocalRepositoryImpl(databaseDriverFactory)
        } else {
            PaymentRepositoryImpl(databaseDriverFactory)
        }
    }
}