package com.leads.capita.api.account



interface AccountService {
    fun getBalanceServices(): List<AccountBalance>
    fun getInstrumentServices():List<AccountInstrument>
    fun getReceivableServices():List<AccountReceivable>
    fun getTransactionServices():List<AccountTransaction>
}