package com.leads.capita.repository.account


import com.leads.capita.account.AccountBalance
import com.leads.capita.account.AccountInstrument
import com.leads.capita.account.AccountReceivable
import com.leads.capita.account.AccountRepository
import com.leads.capita.account.AccountTransaction
import com.leads.capita.account.Instrument
import com.leads.capita.repository.RestUtil.BASE_URL
import com.leads.capita.repository.RestUtil.getClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking

class AccountRepositoryImpl : AccountRepository {
    private val BALANCE_PATH: String = "/account-balances"
    private val TRANSACTION_PATH: String = "/account-balances"
    override fun getAccountBalance(): String {
        var response: String? = null

        runBlocking {
            try {
                response = getClient().get(urlString = "$BASE_URL$BALANCE_PATH/00001").body()
            } catch (e: Exception) {
            }
        }
        return response.toString()
    }

    override fun getAccountInstrument(): String {
        TODO("Not yet implemented")
    }

    override fun getAccountReceivable(): List<AccountReceivable> {
        TODO("Not yet implemented")
    }

    override fun getAccountTransaction(): List<AccountTransaction> {
        TODO("Not yet implemented")
    }

    override fun createAccountTransaction(transactions: List<AccountTransaction>) {
        TODO("Not yet implemented")
    }

    override fun createAccountInstrument(instruments: AccountInstrument) {
        TODO("Not yet implemented")
    }

    override fun createAccountBalance(balances: List<AccountBalance>) {
        TODO("Not yet implemented")
    }

    override fun createAccountReceivable(receivables: List<AccountReceivable>) {
        TODO("Not yet implemented")
    }
}
