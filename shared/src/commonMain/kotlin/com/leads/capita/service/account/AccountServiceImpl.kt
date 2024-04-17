package com.leads.capita.service.account


import com.leads.capita.DatabaseDriverFactory
import com.leads.capita.api.account.AccountBalance
import com.leads.capita.api.account.AccountInstrument
import com.leads.capita.api.account.AccountReceivable
import com.leads.capita.api.account.AccountService
import com.leads.capita.api.account.AccountTransaction

class AccountServiceImpl(private val databaseDriverFactory: DatabaseDriverFactory) : AccountService {

   override fun getBalanceServices(): List<AccountBalance>{
       val repository = AccountFactory.getRepository(databaseDriverFactory);
        return repository.getAccountBalance()
    }

    override fun getInstrumentServices(): List<AccountInstrument> {
        val repository=AccountFactory.getRepository(databaseDriverFactory)
        return repository.getAccountInstrument()
    }

    override fun getReceivableServices(): List<AccountReceivable> {
        val repository=AccountFactory.getRepository(databaseDriverFactory)
        return repository.getAccountReceivable()
    }

    override fun getTransactionServices(): List<AccountTransaction> {
        val repository=AccountFactory.getRepository(databaseDriverFactory)
        return repository.getAccountTransaction()
    }


}