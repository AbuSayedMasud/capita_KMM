package com.leads.capita.repository.account

import com.leads.capita.CapitaDb
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.account.AccountBalance
import com.leads.capita.account.AccountInstrument
import com.leads.capita.account.AccountReceivable
import com.leads.capita.account.AccountRepository
import com.leads.capita.account.AccountTransaction
import com.leads.capita.account.Instrument
import com.leads.capita.repository.RestUtil
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class AccountLocalRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory) :
    AccountRepository {
    val db = CapitaDb(databaseDriverFactory.createDriver())
    private val ACCOUNT_BALANCE_PATH: String = "/accounts/balances/1990"
    private val ACCOUNT_POSITION: String = "/accounts/positions/1990"
    private  val accountDetails:String="/accounts/details/1990"
    override fun getAccountBalance(): String {
        var response: String? = null

        runBlocking {
            try {
                response = RestUtil.getClient()
                    .get(urlString = "${RestUtil.BASE_URL}$ACCOUNT_BALANCE_PATH").body()
            } catch (e: Exception) {
            }
        }

        return response.toString()
    }

    override fun getAccountInstrument(): String {

        var response: String? = null

        runBlocking {
            try {
                response = RestUtil.getClient()
                    .get(urlString = "${RestUtil.BASE_URL}$ACCOUNT_POSITION").body()
            } catch (e: Exception) {
            }
        }

        return response.toString()
    }
//    override fun getAccountBalance(): String {
//
//        val accountBalanceList= db.accountBalanceQueries.getAccountBalance()
//            .executeAsList()
//            .map { accountBalanceData ->
//                AccountBalance(
//                    accountCode = accountBalanceData?.accountCode!!,
//                    accruedCharge = accountBalanceData?.accruedCharge!!,
//                    assetValue = accountBalanceData?.assetValue!!,
//                    buyingPower = accountBalanceData?.buyingPower!!,
//                    cashBalance = accountBalanceData?.cashBalance!!,
//                    costValue = accountBalanceData?.costValue!!,
//                    currentBalance = accountBalanceData?.currentBalance!!,
//                    deptEquityRatio = accountBalanceData?.deptEquityRatio!!,
//                    equity = accountBalanceData?.equity!!,
//                    equityDebtRatio = accountBalanceData?.equityDebtRatio!!,
//                    immatureBalance = accountBalanceData?.immatureBalance!!,
//                    loanRatio = accountBalanceData?.loanRatio!!,
//                    marginEquity = accountBalanceData?.marginEquity!!,
//                    marketValue = accountBalanceData?.marketValue!!,
//                    totalDeposit = accountBalanceData?.totalDeposit!!,
//                    totalWithdrawal = accountBalanceData?.totalWithdrawal!!,
//                    unclearCheque = accountBalanceData?.unclearCheque!!,
//                )
//            }
//
//        return Json.encodeToString(accountBalanceList)
//    }

//    override fun getAccountInstrument(): List<Instrument> {
//
//        return db.accountInstrumentQueries.getAccountInstrumentData()
//            .executeAsList()
//            .map { accountInstrument ->
//                Instrument(
//                    costPrice = accountInstrument.costPrice,
//                    costValue = accountInstrument.costValue,
//                    gr = accountInstrument.gr,
//                    marginable = if (accountInstrument.marginable?.toInt() ==0)false else true,
//                    marketPrice = accountInstrument.marketPrice,
//                    marketValue = accountInstrument.marketValue,
//                    matureQuantity = accountInstrument.matureQuantity,
//                    quantity = accountInstrument.quantity?.toInt(),
//                    symbole = accountInstrument.symbol,
//                    unrealizedGain = accountInstrument.unrealizedGain
//                )
//            }
//    }

    override fun getAccountReceivable(): List<AccountReceivable> {

        return db.accountReceivableQueries.getAccountReceivableData()
            .executeAsList()
            .map { accountReceivableData ->
                AccountReceivable(
                    name = accountReceivableData?.name!!,
                    company1 = accountReceivableData?.company1!!,
                    company2 = accountReceivableData?.company2!!,
                    shareQuantity1 = accountReceivableData?.shareQuantity1!!,
                    shareQuantity2 = accountReceivableData?.shareQuantity2!!,
                    amount1 = accountReceivableData?.amount1!!,
                    amount2 = accountReceivableData?.amount2!!,
                )
            }
    }

    override fun getAccountTransaction(): List<AccountTransaction> {

        return db.accountTransactionQueries.getAccountTransactionData()
            .executeAsList()
            .map { accountTransactionData ->
                AccountTransaction(
                    transferType = accountTransactionData?.transferType!!,
                    totalAmount = accountTransactionData?.totalAmount!!,
                    description = accountTransactionData?.description!!,
                    quantity = accountTransactionData?.quantity!!,
                    date = accountTransactionData?.date!!,
                    identity = accountTransactionData?.identity!!
                )
            }
    }

    override fun getAccountDetails(): String {
        var response: String? = null

        runBlocking {
            try {
                response = RestUtil.getClient()
                    .get(urlString = "${RestUtil.BASE_URL}$accountDetails").body()
            } catch (e: Exception) {
            }
        }

        return response.toString()
    }

    override fun createAccountTransaction(transactions: List<AccountTransaction>) {

        transactions.forEach { transaction ->
            val existingTransaction =
                db.accountTransactionQueries.getAccountTransactionByUniqueId(transaction.transferType)
            if (existingTransaction.executeAsList().isEmpty()) {
                db.accountTransactionQueries.insertAccountTransactionData(
                    transferType = transaction.transferType,
                    totalAmount = transaction.totalAmount,
                    description = transaction.description,
                    quantity = transaction.quantity,
                    date = transaction.date,
                    identity = transaction.identity
                )
            }
        }
    }

    override fun createAccountInstrument(instruments: AccountInstrument) {

        val instrument: List<Instrument> = instruments.instruments
        println("data is not found ${instrument.toString()}")
        db.accountInstrumentQueries.deleteAccountInstrumentData()
        instrument.forEach { instrument ->
            val existingInstrument =
                db.accountInstrumentQueries.getAccountInstrumentByUniqueId(instrument.symbole)
            if (existingInstrument.executeAsList().isEmpty()) {
                db.accountInstrumentQueries.insertAccountInstrumentData(
                    accountCode = "1990",
                    costPrice = instrument.costPrice,
                    costValue = instrument.costValue,
                    gr = instrument.gr,
                    marginable = if (instrument.marginable==false) 0 else 1,
                    marketPrice = instrument.marketPrice,
                    marketValue = instrument.marketValue,
                    matureQuantity = instrument.matureQuantity,
                    quantity = instrument.quantity?.toLong(),
                    symbol = instrument.symbole,
                    unrealizedGain = instrument.unrealizedGain
                )
            }
        }
    }

    override fun createAccountBalance(balances: List<AccountBalance>) {
        balances.forEach { balance ->
            val existingBalance =
                db.accountBalanceQueries.getAccountBalanceByUniqueId(balance.accountCode)
            if (existingBalance.executeAsList().isEmpty()) {
                db.accountBalanceQueries.insertAccountBalanceData(
                    accountCode = balance.accountCode,
                    accruedCharge = balance.accruedCharge,
                    assetValue = balance.assetValue,
                    buyingPower = balance.buyingPower,
                    cashBalance = balance.cashBalance,
                    costValue = balance.costValue,
                    currentBalance = balance.currentBalance,
                    deptEquityRatio = balance.deptEquityRatio,
                    equity = balance.equity,
                    equityDebtRatio = balance.equityDebtRatio,
                    immatureBalance = balance.immatureBalance,
                    loanRatio = balance.loanRatio,
                    marginEquity = balance.marginEquity,
                    marketValue = balance.marketValue,
                    totalDeposit = balance.totalDeposit,
                    totalWithdrawal = balance.totalWithdrawal,
                    unclearCheque = balance.unclearCheque,
                )
            }
        }
    }

    override fun createAccountReceivable(receivables: List<AccountReceivable>) {

//        db.accountReceivableQueries.deleteAccountReceivableData()
        receivables.forEach { receivable ->
            val existingReceivable =
                db.accountReceivableQueries.getAccountReceivableByUniqueName(receivable.name)
            if (existingReceivable.executeAsList().isEmpty()) {
                db.accountReceivableQueries.insertAccountReceivableData(
                    name = receivable.name,
                    company1 = receivable.company1,
                    company2 = receivable.company2,
                    shareQuantity1 = receivable.shareQuantity1,
                    shareQuantity2 = receivable.shareQuantity2,
                    amount1 = receivable.amount1,
                    amount2 = receivable.amount2,
                )
            }
        }
    }
}
