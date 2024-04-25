package com.leads.capita.account


interface AccountService {
    //    fun getBalanceServices(): List<AccountBalance>
    fun getBalanceServices(): String
    fun getInstrumentServices(): List<AccountInstrument>
    fun getReceivableServices(): List<AccountReceivable>
    fun getTransactionServices(): List<AccountTransaction>
}