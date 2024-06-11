package com.leads.capita.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AccountBalance(
    @SerialName("accountCode")  var accountCode: String = "",
    @SerialName("accruedCharge")  var accruedCharge: Double = 0.0,
    @SerialName("assetValue") var assetValue: Double = 0.0,
    @SerialName("buyingPower") var buyingPower: Double = 0.0,
    @SerialName("cashBalance")  var cashBalance: Double = 0.0,
    @SerialName("costValue")  var costValue: Double = 0.0,
    @SerialName("currentBalance")  var currentBalance: Double = 0.0,
    @SerialName("deposit") var deposit: Double = 0.0,
    @SerialName("deptEquityRatio") var deptEquityRatio: Double = 0.0,
    @SerialName("dividendIncome")  var dividendIncome: Double = 0.0,
    @SerialName("dividendReceivable") var dividendReceivable: Double=0.0,
    @SerialName("equity") var equity: Double=0.0,
    @SerialName("equityDebtRatio") var equityDebtRatio: Double=0.0,
    @SerialName("fundTransIn") var fundTransIn: Double=0.0,
    @SerialName("fundTransOut") var fundTransOut: Double=0.0,
    @SerialName("fundWithdrawalRequest") var fundWithdrawalRequest: Double=0.0,
    @SerialName("immatureBalance") var immatureBalance: Double=0.0,
    @SerialName("instrumentTransIn") var instrumentTransIn: Double=0.0,
    @SerialName("instrumentTransOut") var instrumentTransOut: Double=0.0,
    @SerialName("ipoApplication") var ipoApplication: Double=0.0,
    @SerialName("loanRatio") var loanRatio: Double=0.0,
    @SerialName("marginEquity") var marginEquity: Double=0.0,
    @SerialName("marketValue") var marketValue: Double=0.0,
    @SerialName("maxLoan") var maxLoan: Double=0.0,
    @SerialName("netDepositWithdraw") var netDepositWithdraw: Double=0.0,
    @SerialName("netGainLoss") var netGainLoss: Double=0.0,
    @SerialName("preIpoApplication") var preIpoApplication: Double=0.0,
    @SerialName("preferenceApplication") var preferenceApplication: Double=0.0,
    @SerialName("realiseGainLoss") var realiseGainLoss: Double=0.0,
    @SerialName("rightApplication") var rightApplication: Double=0.0,
    @SerialName("rightOrder") var rightOrder: Double=0.0,
    @SerialName("totalDeposit") var totalDeposit: Double=0.0,
    @SerialName("totalWithdrawal") var totalWithdrawal: Double=0.0,
    @SerialName("unclearCheque") var unclearCheque: Double=0.0,
    @SerialName("unrealiseGainLoss") var unrealiseGainLoss: Double=0.0,
    @SerialName("withdrawal") var withdrawal: Double=0.0
)


