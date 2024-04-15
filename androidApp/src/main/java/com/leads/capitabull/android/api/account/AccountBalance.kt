package com.leads.capitabull.android.api.account
import kotlinx.serialization.Serializable
@Serializable
class AccountBalance(
    val accountCode: String,
    val accruedCharge: Double,
    val assetValue: Double,
    val buyingPower: Double,
    val cashBalance: Double,
    val costValue: Double,
    val currentBalance: Double,
    val deptEquityRatio: Double,
    val equity: Double,
    val equityDebtRatio: Double,
    val immatureBalance: Double,
    val loanRatio: Double,
    val marginEquity: Double,
    val marketValue: Double,
    val totalDeposit: Double,
    val totalWithdrawal: Double,
    val unclearCheque: Double,
)
