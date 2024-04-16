package com.leads.capita.api.account



interface AccountRepository {
    fun getAccountBalance(): List<AccountBalance>
    fun getAccountInstrument(): List<AccountInstrument>
    fun getAccountReceivable(): List<AccountReceivable>
    fun getAccountTransaction(): List<AccountTransaction>
    fun createAccountTransaction(transactions: List<AccountTransaction>)
    fun createAccountInstrument(instruments: List<AccountInstrument>)
    fun createAccountBalance(balances: List<AccountBalance>)
    fun createAccountReceivable(receivables: List<AccountReceivable>)
}
