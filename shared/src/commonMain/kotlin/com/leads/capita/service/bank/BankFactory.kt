package com.leads.capita.service.bank

import com.leads.capita.account.AccountRepository
import com.leads.capita.bank.BankRepository
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.repository.account.AccountLocalRepositoryImpl
import com.leads.capita.repository.account.AccountRepositoryImpl
import com.leads.capita.repository.bank.BankLocalRepositoryImpl
import com.leads.capita.repository.bank.BankRepositoryImpl
import com.leads.capita.service.RuntimeProfile

object BankFactory {
    fun getRepository(databaseDriverFactory: DatabaseDriverFactory): BankRepository {
        return if (RuntimeProfile.getCurrentRuntime() == RuntimeProfile.LIVE_RUNTIME) {
            BankRepositoryImpl(databaseDriverFactory)
        } else {
            BankLocalRepositoryImpl(databaseDriverFactory)
        }
    }
}