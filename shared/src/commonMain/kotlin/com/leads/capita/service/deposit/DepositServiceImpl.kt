package com.leads.capita.service.deposit

import com.leads.capita.desposit.DepositService
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.service.account.AccountFactory

class DepositServiceImpl(private val databaseDriverFactory: DatabaseDriverFactory):DepositService{
    override fun getDepositServices(): String {
        val repository = DepositFactory.getRepository(databaseDriverFactory);
        return repository.getDeposit()
    }

    override fun getDepositStatusServices(): String {
        val repository = DepositFactory.getRepository(databaseDriverFactory);
        return repository.getDepositStatus()
    }
}