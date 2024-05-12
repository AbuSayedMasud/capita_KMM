package com.leads.capita.repository.deposit

import com.leads.capita.desposit.DepositRepository
import com.leads.capita.repository.DatabaseDriverFactory

class DepositLocalRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory):DepositRepository {
    override fun getDeposit(): String {
        TODO("Not yet implemented")
    }

    override fun getDepositStatus(): String {
        TODO("Not yet implemented")
    }
}