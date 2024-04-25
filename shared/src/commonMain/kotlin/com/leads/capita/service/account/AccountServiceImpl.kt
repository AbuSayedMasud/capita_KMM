package com.leads.capita.service.account


import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.account.AccountBalance
import com.leads.capita.account.AccountInstrument
import com.leads.capita.account.AccountReceivable
import com.leads.capita.account.AccountService
import com.leads.capita.account.AccountTransaction

class AccountServiceImpl(private val databaseDriverFactory: DatabaseDriverFactory) :
    AccountService {

   override fun getBalanceServices(): String{
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