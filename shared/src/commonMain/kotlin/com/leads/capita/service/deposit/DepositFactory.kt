package com.leads.capita.service.deposit

import com.leads.capita.desposit.DepositRepository


import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.repository.deposit.DepositLocalRepositoryImpl
import com.leads.capita.repository.deposit.DepositRepositoryImpl


import com.leads.capita.service.RuntimeProfile

object DepositFactory {
    fun getRepository(databaseDriverFactory: DatabaseDriverFactory): DepositRepository {
        return if (RuntimeProfile.getCurrentRuntime() == RuntimeProfile.LIVE_RUNTIME) {
            DepositLocalRepositoryImpl(databaseDriverFactory)
        } else {
            DepositRepositoryImpl(databaseDriverFactory)
        }
    }
}