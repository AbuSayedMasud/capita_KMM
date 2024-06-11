package com.leads.capita.service.bank

import com.leads.capita.bank.BankSevice
import com.leads.capita.repository.DatabaseDriverFactory

class BankServiceImpl(private  val databaseDriverFactory: DatabaseDriverFactory):BankSevice {
    override fun getBankListService(): String {
        val repository = BankFactory.getRepository(databaseDriverFactory);
        return repository.getBankList()
    }

    override fun getBranchListService(bankId:Int): String {
        val repository = BankFactory.getRepository(databaseDriverFactory);
        return repository.getBranchList(bankId)
    }
}