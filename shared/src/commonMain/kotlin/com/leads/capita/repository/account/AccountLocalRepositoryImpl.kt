package com.leads.capita.repository.account

import com.leads.capita.CapitaDb
import com.leads.capita.DatabaseDriverFactory
import com.leads.capita.api.account.AccountBalance
import com.leads.capita.api.account.AccountInstrument
import com.leads.capita.api.account.AccountReceivable
import com.leads.capita.api.account.AccountRepository
import com.leads.capita.api.account.AccountTransaction


class AccountLocalRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory) : AccountRepository {
    val db = CapitaDb(databaseDriverFactory.createDriver())
    override fun getAccountBalance(): List<AccountBalance> {
        return db.accountBalanceQueries.getAccountBalance()
            .executeAsList()
            .map { accountBalanceData ->
                AccountBalance(
                    accountCode = accountBalanceData?.accountCode!!,
                    accruedCharge = accountBalanceData?.accruedCharge!!,
                    assetValue = accountBalanceData?.assetValue!!,
                    buyingPower = accountBalanceData?.buyingPower!!,
                    cashBalance = accountBalanceData?.cashBalance!!,
                    costValue = accountBalanceData?.costValue!!,
                    currentBalance = accountBalanceData?.currentBalance!!,
                    deptEquityRatio = accountBalanceData?.deptEquityRatio!!,
                    equity = accountBalanceData?.equity!!,
                    equityDebtRatio = accountBalanceData?.equityDebtRatio!!,
                    immatureBalance = accountBalanceData?.immatureBalance!!,
                    loanRatio = accountBalanceData?.loanRatio!!,
                    marginEquity = accountBalanceData?.marginEquity!!,
                    marketValue = accountBalanceData?.marketValue!!,
                    totalDeposit = accountBalanceData?.totalDeposit!!,
                    totalWithdrawal = accountBalanceData?.totalWithdrawal!!,
                    unclearCheque = accountBalanceData?.unclearCheque!!,
                )
            }
    }

    override fun getAccountInstrument(): List<AccountInstrument> {

        return db.accountInstrumentQueries.getAccountInstrumentData()
            .executeAsList()
            .map { accountInstrument ->
                AccountInstrument(
                    instrumentIndex = accountInstrument?.instrumentIndex!!,
                    longName = accountInstrument?.long_name!!,
                    shortName = accountInstrument?.short_name!!,
                    value = accountInstrument?.value_!!,
                    closedPrice = accountInstrument?.closed_price!!,
                    change = accountInstrument?.change!!,
                    changeIcon = accountInstrument?.change_icon!!,
                    totalQuantity = accountInstrument?.total_quantity!!,
                    salableQuantity = accountInstrument?.salable_quantity!!,
                    averageCost = accountInstrument?.average_cost!!,
                    totalCost = accountInstrument?.total_cost!!,
                    closePrice = accountInstrument?.average_cost!!,
                    unrealizedGain = accountInstrument?.unrealized_gain!!,
                    gainPercent = accountInstrument?.gain_percent!!,
                    costValue = accountInstrument?.cost_value!!,
                )
            }
    }

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

    override fun createAccountInstrument(instruments: List<AccountInstrument>) {


        instruments.forEach { instrument ->
            val existingInstrument = db.accountInstrumentQueries.getAccountInstrumentByUniqueId(instrument.shortName)
            if (existingInstrument.executeAsList().isEmpty()) {
                db.accountInstrumentQueries.insertAccountInstrumentData(
                    instrumentIndex = instrument.instrumentIndex,
                    long_name = instrument.longName,
                    short_name = instrument.shortName,
                    value_ = instrument.value,
                    closed_price = instrument.closedPrice,
                    change = instrument.change,
                    change_icon = instrument.changeIcon,
                    total_quantity = instrument.totalQuantity,
                    salable_quantity = instrument.salableQuantity,
                    average_cost = instrument.averageCost,
                    total_cost = instrument.totalCost,
                    close_price = instrument.closePrice,
                    unrealized_gain = instrument.unrealizedGain,
                    gain_percent = instrument.gainPercent,
                    cost_value = instrument.costValue
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
