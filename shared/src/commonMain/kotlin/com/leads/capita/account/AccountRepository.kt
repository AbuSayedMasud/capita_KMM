package com.leads.capita.account


interface AccountRepository {
    //    fun getAccountBalance(): List<AccountBalance>
    fun getAccountBalance(): String
    fun getAccountInstrument(): String
    fun getAccountReceivable(): List<AccountReceivable>
    fun getAccountTransaction(): List<AccountTransaction>
    fun getAccountDetails(): String
    fun createAccountTransaction(transactions: List<AccountTransaction>)
    fun createAccountInstrument(instruments: AccountInstrument)
    fun createAccountBalance(balances: List<AccountBalance>)
    fun createAccountReceivable(receivables: List<AccountReceivable>)

}
